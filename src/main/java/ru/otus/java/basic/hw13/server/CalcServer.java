package ru.otus.java.basic.hw13.server;

import ru.otus.java.basic.hw13.exceptions.DataException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CalcServer {
    private static final List<String> OPS = Arrays.asList("*", "/", "+", "-");
    private static final Map<String, Integer> PRIORITY = Map.of(
            "*", 2,
            "/", 2,
            "+", 1,
            "-", 1
    );

    public static void main(String[] args) {
        System.out.println("\nСервер калькулятора начал работу! :)");
        while (true) {
            try (ServerSocket server = new ServerSocket(8080)) {
                while (true) {
                    Socket client = server.accept();

                    DataInputStream dis = new DataInputStream(client.getInputStream());
                    DataOutputStream dos = new DataOutputStream(client.getOutputStream());

                    String userInput = dis.readUTF();
                    if (("exit").equalsIgnoreCase(userInput)) {
                        System.out.println("Клиент разрывает связь!");
                        dos.writeUTF("Bye");
                        client.close();
                        continue;
                    }
                    System.out.println("\nСервер калькулятора принял: " + userInput);
                    try {
                        double result = evaluate(userInput);
                        System.out.println("Вычисления выполнены!");
                        dos.writeUTF("Результат вычислений: " + result);
                    } catch (DataException e) {
                        System.out.println("Ошибка вычислений!");
                        dos.writeUTF(e.getMessage());
                    }

                    dos.flush();
                }
            } catch (IOException e) {
                System.out.println("\nСоединение с клиентом было разорвано... ;(");
            }
        }
    }

    private static double evaluate(String expression) throws DataException {
        System.out.println("\nСервер калькулятора начал считать выражение...");
        List<String> tokens = new ArrayList<>(Arrays.asList(expression.trim().split("\\s+")));
        resolveParentheses(tokens);
        return calculateFlat(tokens);
    }

    private static void resolveParentheses(List<String> tokens) throws DataException {
        while (tokens.contains("(")) {
            int open = -1;
            int close = -1;
            for (int i = 0; i < tokens.size(); i++) {
                if (tokens.get(i).equals("(")) {
                    open = i;
                } else if (tokens.get(i).equals(")")) {
                    close = i;
                    break;
                }
            }
            if (open == -1 || close == -1 || close < open) {
                throw new DataException("Ошибка в скобках \"(\" \")\"! Проверьте выражение!");
            }
            List<String> subExpr = new ArrayList<>(tokens.subList(open + 1, close));
            double subResult = calculateFlat(subExpr);
            replaceSubExpression(tokens, open, close, subResult);
        }
    }

    private static void replaceSubExpression(List<String> tokens, int open, int close, double value) {
        for (int i = close; i >= open; i--) {
            tokens.remove(i);
        }
        tokens.add(open, String.valueOf(value));
    }

    private static double calculateFlat(List<String> tokens) throws DataException {
        try {
            for (int p = 2; p >= 1; p--) {
                processOperations(tokens, p);
            }
            return Double.parseDouble(tokens.get(0));
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new DataException("Не удалось преобразовать введенные данные! Проверьте выражение!");
        }
    }

    private static void processOperations(List<String> tokens, int priorityLevel) throws DataException {
        int i = 0;
        while (i < tokens.size()) {
            String token = tokens.get(i);
            if (isInvalidOperatorPosition(i, token))
                throw new DataException("Неизвестная операция (Допустимые операторы: +,-,*,/)! Проверьте выражение!");
            if (isMatchingPriority(token, priorityLevel)) {
                double res = performOperation(tokens, i, token);
                updateTokens(tokens, i, res);
            } else {
                i++;
            }
        }
    }

    private static boolean isInvalidOperatorPosition(int i, String token) {
        return i % 2 != 0 && !OPS.contains(token);
    }

    private static boolean isMatchingPriority(String token, int p) {
        return OPS.contains(token) && PRIORITY.get(token) == p;
    }

    private static double performOperation(List<String> tokens, int i, String op) throws DataException {
        double a = Double.parseDouble(tokens.get(i - 1));
        double b = Double.parseDouble(tokens.get(i + 1));
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) throw new DataException("Деление на 0! Проверьте выражение!");
                return a / b;
            default:
                throw new DataException("Неизвестная операция! Проверьте выражение!");
        }
    }

    private static void updateTokens(List<String> tokens, int i, double result) {
        tokens.set(i - 1, String.valueOf(result));
        tokens.remove(i);
        tokens.remove(i);
    }
}

package ru.otus.java.basic.hw13.client;

import ru.otus.java.basic.hw13.classes.ClientOptions;
import ru.otus.java.basic.hw13.exceptions.DataException;

import java.io.IOException;
import java.net.Socket;
import java.util.*;

public class Client {

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в приложение калькулятор! :)");
        while (true) {
            try (Socket socket = new Socket("localhost", 8080)) {
                ClientOptions client = new ClientOptions(socket.getInputStream(), socket.getOutputStream());
                try {
                    String userInput = inputValue();
                    if (("exit").equalsIgnoreCase(userInput)) {
                        client.send(userInput);
                        break;
                    }
                    client.send(userInput);
                } catch (DataException e) {
                    System.out.println(e.getMessage());
                }

            } catch (IOException e) {
                System.out.println("\nНе удается подключиться к серверу... ;(");
                return;
            }
        }
    }

    private static String inputValue() throws DataException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВведите математическое выражение через пробел (Допускаются скобки и операторы: +,-,*,/).\nДля выхода из калькултора, введите \"exit\":");
        String line = scanner.nextLine();
        if (line.isEmpty()) {
            throw new DataException("Ошибка! Введены неверные данные!");
        }
        return line;
    }
}

package ru.otus.java.basic.hw8;

import ru.otus.java.basic.hw8.exceptions.AppArrayDataException;
import ru.otus.java.basic.hw8.exceptions.AppArraySizeException;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        String[][] array = inputStringTwoDimensionalArray();
        try {
            int result = sumStringTwoDimensionalArray(array);
            System.out.println("Сумма элементов двумерного массива равна: " + result);
        } catch (AppArraySizeException | AppArrayDataException e) {
            System.out.println("Ошибка!!!");
            System.out.println(e.getMessage());
        }
        System.out.println("Конец программы!");
    }

    /**
     * @return Строковый двумерный массив, введеный пользователем
     * @apiNote Метод считывает строковый двумерный массив, введеный пользователем
     */
    private static String[][] inputStringTwoDimensionalArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строковый двумерный массив 4 * 4 через пробел (каждый массив с новой строки).");
        System.out.println("Для окончания ввода, введите пустую строку:");
        List<String[]> list = new ArrayList<>();
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                break;
            }
            list.add(line.split("\\s+"));
        }
        return list.toArray(new String[0][]);
    }

    /**
     * @param array двумерный строковый массив.
     * @return сумму элементов типа int.
     * @apiNote Метод преобразовывает строки двумерного строкового массива в int и суммриует все элементы.
     * Метод имеет исключения на размер массива и возможность преобразовать String в int.
     */
    private static int sumStringTwoDimensionalArray(String[][] array) throws AppArraySizeException, AppArrayDataException {
        if (array.length != 4) {
            throw new AppArraySizeException("Неверный размер двумерного массива (" + (array.length > 4 ? "больше" : "меньше") + " 4-х строк)!");
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new AppArraySizeException("Неверный размер двумерного массива (" + (array[i].length > 4 ? "больше" : "меньше") + " 4-х в строке " + (i + 1) + ")!");
            }
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new AppArrayDataException("Невозможно преобразовать строку \"" + array[i][j] + "\" в число. Место исключения: строка " + (i + 1) + ", стобец " + (j + 1) + "!");
                }
            }
        }
        return sum;
    }
}

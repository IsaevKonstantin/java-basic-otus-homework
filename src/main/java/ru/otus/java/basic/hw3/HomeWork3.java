package ru.otus.java.basic.hw3;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HomeWork3 {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в меню!");
        System.out.println("Для продолжения работы выберете пункт от 1 до 5, где:\n1 - Сумма эл. двумерного массива, значение которых > 0;\n2 - Напечатать квадрат;\n3 - Занулить диагональные значения двумерного массива;\n4 - Максимальный эл.массива;\n5 - Сумма элементов второй строки двумерного массива;");
        while (true) {
            try {
                int menuItem = inputNum();
                switch (menuItem) {
                    case 1:
                        System.out.println("Пункт 1.");
                        int[][] arr1 = inputIntTwoDimensionalArray();
                        sumOfPositiveElements(arr1);
                        break;
                    case 2:
                        System.out.println("Пункт 2.");
                        while (true) {
                            try {
                                int size = inputNum();
                                if (size < 1) {
                                    throw new InputMismatchException();
                                }
                                printSquareBySize(size);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Внимание! Размер квадрата должен быть >= 1. Попробуйте еще раз:");
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Пункт 3.");
                        System.out.println("Выберете диагональ, которую вы хотите занулить:\n1 - Главная диагональ;\n2 - Побочная диагональ;\n3 - Обе диагонали;");
                        while (true) {
                            try {
                                int dNum = inputNum();
                                if (dNum < 1 || dNum > 3) {
                                    throw new InputMismatchException();
                                }
                                int[][] arr3 = inputIntTwoDimensionalArray();
                                diagonalsOfTwoDimensionalArray(arr3, dNum);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Внимание! Возможные варианты: 1-3. Попробуйте еще раз.");
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Пункт 4.");
                        int[][] arr4 = inputIntTwoDimensionalArray();
                        int maxValue = findMax(arr4);
                        System.out.println("Макимальное значение внутри двумерного массива: " + maxValue);
                        break;
                    case 5:
                        System.out.println("Пункт 5.");
                        int[][] arr5 = inputIntTwoDimensionalArray();
                        System.out.println("Введите значение строки (от 1), сумму эл. которой вы хотите получить:");
                        while (true) {
                            try {
                                int index = inputNum();
                                if (index < 1) {
                                    throw new InputMismatchException();
                                }
                                int result = sumElementArrayByStringIndex(arr5, index);
                                System.out.println("Результат: " + result);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Внимание! Индекс строки не может быть < 1. Попробуйте еще раз.");
                            }
                        }


                        break;
                    default:
                        throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Внимание! Возможные варианты: 1-5. Попробуйте еще раз.");
            }
        }
        System.out.println("Программа выполнена! Спасибо за использование приложения.");

    }

    /**
     * @apiNote Метод возвращает целое число, введеное пользователем
     * и проверяет валидность значения через InputMismatchException
     * @return Целое число, введеное пользователем
     */
    public static int inputNum() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число:");
        int num;
        while (true) {
            try {
                num = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Вы ввели неверное значение. Попробуйте еще раз:");
            }
        }
        return num;
    }

    /**
     * @apiNote Метод возвращает целочисленный двумерный массив, введеный пользователем
     * и проверяет валидность значений через NumberFormatException
     * @return целочисленный двумерный массив, введеный пользователем
     */
    public static int[][] inputIntTwoDimensionalArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите массивы целых чисел (каждый массив с новой строки, числа через пробел).");
        System.out.println("Для окончания ввода, введите пустую строку:");
        List<int[]> list = new ArrayList<>();
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                break;
            }
            String[] strArr = line.split("\\s+");
            int[] arr = new int[strArr.length];
            try {
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = Integer.parseInt(strArr[i]);
                }
                list.add(arr);
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели неверное значение внутри массива. Попробуйте еще раз:");
            }
        }
        return list.toArray(new int[0][]);
    }

    /**
     * @apiNote Метод печатает в консоль целочисленный двумерный массив
     * @param arr целочисленный двумерный массив
     */
    public static void printTwoDimensionalArray(int[][] arr) {
        for (int[] items : arr) {
            for (int item : items) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    /**
     * @apiNote Реализовать метод sumOfPositiveElements(..),
     * принимающий в качестве аргумента целочисленный двумерный массив,
     * метод должен посчитать и вернуть сумму всех элементов массива, которые больше 0
     * @param arr Целочисленный двумерный массив
     */
    public static void sumOfPositiveElements(int[][] arr) {
        int sumResult = 0;
        for (int[] items : arr) {
            for (int item : items) {
                if (item > 0) {
                    sumResult += item;
                }
            }
        }
        System.out.println("Сумма всех элементов двумерного массива, значение которых > 0, равна: " + sumResult);
    }

    /**
     * @apiNote Реализовать метод, который принимает в качестве аргумента int size
     * и печатает в консоль квадрат из символов * со сторонами соответствующей длины
     * @param size Размер кввадрата
     */
    public static void printSquareBySize(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("*  ");
            }
            System.out.println();
        }
    }

    /**
     * @apiNote Реализовать метод, принимающий в качестве аргумента двумерный целочисленный массив,
     * и зануляющий его диагональные элементы (можете выбрать любую из диагоналей, или занулить обе);
     * @param arr Целочисленный двумерный массив
     * @param num Вариант зануления диагоналей (1-3)
     */
    public static void diagonalsOfTwoDimensionalArray(int[][] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                switch (num) {
                    case 1:
                        if (i == j) {
                            arr[i][j] = 0;
                        }
                        break;
                    case 2:
                        if (i == arr[i].length - 1 - j) {
                            arr[i][j] = 0;
                        }
                        break;
                    case 3:
                        if (i == j || i == arr[i].length - 1 - j) {
                            arr[i][j] = 0;
                        }
                        break;
                    default:
                        System.out.println("Ошибка выбора диагонали! Перезапустите приложение.");
                        return;
                }
            }
        }
        printTwoDimensionalArray(arr);
    }

    /**
     * @apiNote Реализовать метод findMax(int[][] array) который должен найти и вернуть максимальный элемент массива;
     * @param array Целочисленный двумерный массив
     * @return Целое число
     */
    public static int findMax(int[][] array) {
        int resultMax = array[0][0];
        for (int[] items : array) {
            for (int item : items) {
                if (item > resultMax) {
                    resultMax = item;
                }
            }
        }
        return resultMax;
    }

    /**
     * @apiNote Реализуйте метод, который считает сумму элементов второй строки двумерного массива,
     * если второй строки не существует, то в качестве результата необходимо вернуть -1
     * @param arr Целочисленный двумерный массив
     * @param index Целочисленный индекс строки
     * @return Сумма целых чисел или -1
     */
    public static int sumElementArrayByStringIndex(int[][] arr, int index) {
        if (index > arr.length) {
            return  -1;
        }
        int sumResult = 0;
        for (int i = 0; i < arr[index - 1].length; i++) {
            sumResult += arr[index - 1][i];
        }
        return sumResult;
    }
}

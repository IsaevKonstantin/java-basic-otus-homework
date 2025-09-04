package ru.otus.java.basic.hw2;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HomeWork2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в меню!");
        System.out.println("Для продолжения работы выберете пункт от 1 до 5, где:\n1 - Напечатать строку;\n2 - Сумма эл.массива, значение которых > 5;\n3 - Заменить значения массива;\n4 - Увеличить значения эл.массива;\n5 - Сумма большей половины массива;");
        while (true) {
            try {
                int menuItem = inputNum();
                switch (menuItem) {
                    case 1:
                        System.out.println("Пункт 1.");
                        while (true) {
                            try {
                                int count = inputNum();
                                if (count < 1) {
                                    throw new InputMismatchException();
                                }
                                System.out.println("Введите строку:");
                                String str = scanner.nextLine();
                                printSeveralLines(count, str);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Внимание! Кол-во распечатки должна быть >= 1. Попробуйте еще раз:");
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Пункт 2.");
                        int[] arrSum = inputIntArray();
                        sumArray(arrSum);
                        break;
                    case 3:
                        System.out.println("Пункт 3.");
                        int num = inputNum();
                        int[] arrDiff = inputIntArray();
                        replaceArrayValues(num, arrDiff);
                        break;
                    case 4:
                        System.out.println("Пункт 4.");
                        int delta = inputNum();
                        int[] arrInit = inputIntArray();
                        increaseArrayElements(delta, arrInit);
                        break;
                    case 5:
                        System.out.println("Пункт 5.");
                        int[] arrNum = inputIntArray();
                        sumArrayHalfElements(arrNum);
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

    public static int[] inputIntArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите массив целых чисел через пробел:");
        int[] arr;
        while (true) {
            String[] strArr = scanner.nextLine().trim().split("\\s+");
            arr = new int[strArr.length];
            try {
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = Integer.parseInt(strArr[i]);
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели неверное значение внутри массива. Попробуйте еще раз:");
            }
        }
        return arr;
    }

    public static void printSeveralLines(int count, String str) {
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }

    public static void sumArray(int[] arr) {
        int sumResult = 0;
        for (int item : arr) {
            if (item > 5) {
                sumResult += item;
            }
        }
        System.out.println("Сумма всех элементов массива, значение которых > 5, равна: " + sumResult);
    }

    public static void replaceArrayValues(int newValue, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = newValue;
        }
        System.out.println("Преобразованный массив: " + Arrays.toString(arr));
    }

    public static void increaseArrayElements(int delta, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] += delta;
        }
        System.out.println("Преобразованный массив: " + Arrays.toString(arr));
    }

    public static void sumArrayHalfElements(int[] arr) {
        int sumLeftHalf = 0;
        int sumRightHalf = 0;
        boolean isEven = arr.length % 2 == 0;
        int middle = arr.length / 2;
        for (int i = 0; i < arr.length; i++) {
            if (i < middle) {
                sumLeftHalf += arr[i];
            }
            if (i > middle && !isEven || i >= middle && isEven) {
                sumRightHalf += arr[i];
            }
        }
        if (sumLeftHalf == sumRightHalf) {
            System.out.println("Левая сторона массива равна правой стороне.");
        } else if (sumLeftHalf > sumRightHalf) {
            System.out.println("Левая сторона массива больше.");
        } else {
            System.out.println("Правая сторона массива больше.");
        }
    }
}

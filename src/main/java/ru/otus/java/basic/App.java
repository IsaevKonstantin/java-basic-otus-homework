package ru.otus.java.basic;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число:");
        int methodNumber = scanner.nextInt();
        if (methodNumber == 1) {
            greetings();
        }
        if (methodNumber == 2) {
            checkSign(10, 20, -31);
        }
        if (methodNumber == 3) {
            selectColor(21);
        }
        if (methodNumber == 4) {
            compareNumbers(19, 20);
        }
        if (methodNumber == 5) {
            addOrSubtractAndPrint(0, 10, true);
        }
    }

    public static void greetings() {
        System.out.println("Hello");
        System.out.println("World");
        System.out.println("from");
        System.out.println("Java");
    }

    public static void checkSign(int a, int b, int c) {
        int sum = a + b + c;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void selectColor(int data) {
        if (data <= 10) {
            System.out.println("Красный");
        } else if (data <= 20) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers(int a, int b) {
        boolean isMore = a >= b;
        if (isMore) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public static void addOrSubtractAndPrint(int initValue, int delta, boolean increment) {
        if (increment) {
            initValue += delta;
        } else  {
            initValue -= delta;
        }
        System.out.println(initValue);
    }
}
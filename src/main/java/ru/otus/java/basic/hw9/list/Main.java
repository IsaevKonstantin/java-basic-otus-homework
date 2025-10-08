package ru.otus.java.basic.hw9.list;

import ru.otus.java.basic.hw9.list.exceptions.AppDataException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Пункт 1:");
            int min1 = inputNum("Введите минимальнные целое число:");
            int max1 = inputNum("Введите максимальнное целое число:");
            List<Integer> list1 = getListByMinMax(min1, max1);
            System.out.println("Список от min до max: " + list1 + ".");
            System.out.println();

            System.out.println("Пункт 2:");
            List<Integer> list2 = inputIntList();
            int result2 = sumListElements(list2);
            System.out.println("Сумма всех элеменнтов списка, значение которых > 5: " + result2 + ".");
            System.out.println();

            System.out.println("Пункт 3:");
            int num3 = inputNum("Введите целое число:");
            List<Integer> list3 = inputIntList();
            List<Integer> resList3 = replaceAllItemList(num3, list3);
            System.out.println("Список перезаполненный введеным значением: " + resList3 + ".");
            System.out.println();

            System.out.println("Пункт 4:");
            int num4 = inputNum("Введите целое число:");
            List<Integer> list4 = inputIntList();
            List<Integer> resList4 = replaceAllAddItemList(num4, list4);
            System.out.println("Список со значениями, увеличенными на " + num4 + ": " + resList4 + ".");
            System.out.println();
        } catch (AppDataException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Программа завершена!");
    }

    private static int inputNum(String str) throws AppDataException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(str);
        int num;
        try {
            num = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new AppDataException("Вы ввели неверное значение.");
        }
        return num;
    }

    private static List<Integer> inputIntList() throws AppDataException {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        System.out.println("Введите целочисленный массив через пробел:");
        String[] strArr = scanner.nextLine().trim().split("\\s+");

        for (String item : strArr) {
            try {
                list.add(Integer.parseInt(item));
            } catch (NumberFormatException e) {
                throw new AppDataException("Значение \"" + item + "\" не может быть преобразованно в число.");
            }
        }
        return list;
    }

    private static List<Integer> getListByMinMax(int min, int max) throws AppDataException {
        if (min > max) {
            throw new AppDataException("Значение min не может быть больше значения max.");
        }
        List<Integer> list = new ArrayList<>(max - min + 1);
        for (int i = min; i < max + 1; i++) {
            list.add(i);
        }
        return list;
    }

    private static int sumListElements(List<Integer> list) {
        int sum = 0;
        for (int item : list) {
            if (item > 5) {
                sum += item;
            }
        }
        return sum;
    }

    private static List<Integer> replaceAllItemList(int item, List<Integer> list) {
        List<Integer> newList = new ArrayList<>(list.size());
        newList.addAll(list);
        newList.replaceAll(n -> item);
        return newList;
    }

    private static List<Integer> replaceAllAddItemList(int item, List<Integer> list) {
        List<Integer> newList = new ArrayList<>(list.size());
        newList.addAll(list);
        newList.replaceAll(n -> n + item);
        return newList;
    }
}

package ru.otus.java.basic.hw3.boxes;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в меню приложения КОРОБКА!");
        System.out.println("Для начала создадим коробку");
        int length = inputNum("Введите длину:", 1);
        int width = inputNum("Введите ширину:", 1);
        int height = inputNum("Введите высоту:",1);
        String color = inputStr("Введите цвет коробки (введите пустую строку, если не хотите задавать цвет):");
        Box box;
        if (color.isEmpty()) {
            box = new Box(length, width, height);
        } else {
            box = new Box(length, width, height, color);
        }
        boolean isWork = true;
        while (isWork) {
            System.out.println();
            System.out.println("Для продолжения работы выберете действие от 1 до 7, где:\n1 - Перекрасить коробку;\n2 - Открыть коробку;\n3 - Закрыть коробку;\n4 - Положить предмет в коробку;\n5 - Освободить коробку;\n6 - Информация о коробке;\n7 - Закрыть приложенние;");
            int menuItem = inputNum("Введите целое число:", 1, 7);
            switch (menuItem) {
                case 1:
                    String newColor = "";
                    while (newColor.isEmpty()) {
                        newColor = inputStr("Введите новый цвет коробки:");
                    }
                    String lastColor = box.getColor();
                    if (lastColor.equalsIgnoreCase(newColor)) {
                        System.out.println("Нельзя перекрасить коробку в тот же цвет.");
                    } else {
                        box.setColor(newColor);
                        System.out.println("Коробка перекрашена в " + color + ".");
                    }
                    break;
                case 2:
                    boolean isOpen = box.isOpen();
                    if (isOpen) {
                        System.out.println("Не удалось открыть коробку. Коробка уже была открыта.");
                    } else {
                        box.setIsOpen(true);
                        System.out.println("Вы открыли коробку.");
                    }
                    break;
                case 3:
                    boolean isClose = !box.isOpen();
                    if (isClose) {
                        System.out.println("Не удалось закрыть коробку. Коробка уже была закрыта.");
                    } else {
                        box.setIsOpen(false);
                        System.out.println("Вы закрыли коробку.");
                    }
                    break;
                case 4:
                    if (!box.isOpen()) {
                        System.out.println("Не удалось положить предмет в коробку. Коробка закрыта.");
                        System.out.println("Сначала откройте коробку.");
                        break;
                    }
                    String lastItem4 = box.getItem();
                    if (!lastItem4.isEmpty()) {
                        System.out.println("Не удалось положить предмет в коробку. В коробке уже лежит " + lastItem4 + ".");
                        System.out.println("Сначала освободите коробку.");
                        break;
                    }
                    String newItem = "";
                    while (newItem.isEmpty()) {
                        newItem = inputStr("Введите наименование предмета:");
                    }
                    box.setItem(newItem);
                    System.out.println("Вы положили " + newItem + " в коробку.");
                    break;
                case 5:
                    if (!box.isOpen()) {
                        System.out.println("Не удалось освободить коробку. Коробка закрыта.");
                        System.out.println("Сначала откройте коробку.");
                        break;
                    }
                    String lastItem5 = box.getItem();
                    if (lastItem5.isEmpty()) {
                        System.out.println("Не удалось освободить коробку. В коробке уже ничего нет.");
                        System.out.println("Сначала наполлните коробку.");
                        break;
                    }
                    box.setItem("");
                    System.out.println("Вы освободили коробку.");
                    break;
                case 6:
                    box.printBoxInfo();
                    break;
                default:
                    isWork = false;
            }
        }
        System.out.println("Спасибо за использвание приложения КОРОБКА");
        System.out.println("До свидания!");
    }

    public static int inputNum(String str, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(str);
        int num;
        while (true) {
            try {
                num = scanner.nextInt();
                if (num < min || num > max) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Вы ввели неверное значение (Допустимые значения от " + min + " до " + max + "). Попробуйте еще раз:");
            }
        }
        return num;
    }

    public static int inputNum(String str, int min) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(str);
        int num;
        while (true) {
            try {
                num = scanner.nextInt();
                if (num < min) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Вы ввели неверное значение (Допустимые значения от " + min + "). Попробуйте еще раз:");
            }
        }
        return num;
    }

    public static String inputStr(String str) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(str);
        return scanner.nextLine();
    }
}

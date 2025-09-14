package ru.otus.java.basic.hw5.app;

import ru.otus.java.basic.hw5.classes.Animal;
import ru.otus.java.basic.hw5.classes.Cat;
import ru.otus.java.basic.hw5.classes.Dog;
import ru.otus.java.basic.hw5.classes.Horse;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в МИР ЖИВОТНЫХ");
        System.out.println("Для начала выберете животное, где:\n1 - Кот;\n2 - Собака;\n3 - Лошадь;");
        String animalType;
        Animal animal;
        switch (inputNum("Введите значение:", 1, 3)) {
            case 1:
                String catName = inputStr("Введите имя кота:");
                int catRunSpeed = inputNum("Введите скорость бега кота:", 0);
                int catEndurance = inputNum("Введите выносливость кота:", 0);
                animal = new Cat(catName, catRunSpeed, catEndurance);
                animalType = "Кот";
                break;
            case 2:
                String dogName = inputStr("Введите имя собаки:");
                int dogRunSpeed = inputNum("Введите скорость бега собаки:", 0);
                int dogSwimSpeed = inputNum("Введите скорость плавания собаки:", 0);
                int dogEndurance = inputNum("Введите выносливость собаки:", 0);
                animal = new Dog(dogName, dogRunSpeed, dogSwimSpeed, dogEndurance);
                animalType = "Собака";
                break;
            case 3:
                String horseName = inputStr("Введите имя лошади:");
                int horseRunSpeed = inputNum("Введите скорость бега лошади:", 0);
                int horseSwimSpeed = inputNum("Введите скорость плавания лошади:", 0);
                int horseEndurance = inputNum("Введите выносливость лошади:", 0);
                animal = new Horse(horseName, horseRunSpeed, horseSwimSpeed, horseEndurance);
                animalType = "Лошадь";
                break;
            default:
                System.out.println("Ошибка! Завершение программы.");
                return;
        }
        boolean isWork = true;
        while (isWork) {
            System.out.println();
            System.out.println("Для продолжения работы выберете действие от 1 до 6, где:\n1 - Пробежать дистапнцию;\n2 - Проплыть дистанцию;\n3 - Поспать и восстановить выносливость;\n4 - Выносливость животного;\n5 - Информация о животном;\n6 - Закрыть приложенние;");
            int menuItem = inputNum("Введите значение:", 1, 6);
            switch (menuItem) {
                case 1:
                    int runDistance = inputNum("Введите дистанцию бега:", 1);
                    int runTime = animal.run(runDistance);
                    if (runTime == -2) {
                        System.out.println(animalType + " " + animal.getName() + " не умеет бегать.");
                    } else if (runTime == -1) {
                        System.out.println("У " + animalType.toLowerCase() + " " + animal.getName() + " появилось состояние усталости.");
                    } else {
                        System.out.println(animalType + " " + animal.getName() + " пробежал эту дистанцию за: " + runTime + "c.");
                    }
                    break;
                case 2:
                    int swimDistance = inputNum("Введите дистанцию плавания:", 1);
                    int swimTime = animal.swim(swimDistance);
                    if (swimTime == -2) {
                        System.out.println(animalType + " " + animal.getName() + " не умеет плавать.");
                    } else if (swimTime == -1) {
                        System.out.println("У " + animalType.toLowerCase() + " " + animal.getName() + " появилось состояние усталости.");
                    } else {
                        System.out.println(animalType + " " + animal.getName() + " проплыл эту дистанцию за: " + swimTime + "c.");
                    }
                    break;
                case 3:
                    int relaxTime = inputNum("Введите часы отдыха:", 1);
                    int relaxEndurance = animal.relax(relaxTime);
                    System.out.println(animalType + " " + animal.getName() + " отдохнул " + relaxTime + "ч. Выносливость составляет: " + relaxEndurance + ".");
                    break;
                case 4:
                    System.out.println(animalType + " " + animal.getName() + " имеет выносливость: " + animal.getEndurance() + ".");
                    break;
                case 5:
                    System.out.println("Информация о " + animalType.toLowerCase() + ":");
                    animal.info();
                    break;
                default:
                    isWork = false;
            }
        }
        System.out.println("Спасибо за использвание приложения МИР ЖИВОТНЫХ");
        System.out.println("До свидания!");
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

    public static String inputStr(String str) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(str);
        return scanner.nextLine();
    }
}

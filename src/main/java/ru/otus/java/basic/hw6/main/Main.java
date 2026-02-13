package ru.otus.java.basic.hw6.main;

import ru.otus.java.basic.hw6.classes.Cat;
import ru.otus.java.basic.hw6.classes.Plate;

public class Main {
    public static void main(String[] args) {
        Plate plate = new Plate(9);
        System.out.println();
        System.out.println("У нас есть тарелка для еды котам:");
        System.out.println(plate);
        Cat[] cats = {
                new Cat("Barsik", 10),
                new Cat("Orsi", 8),
                new Cat("Pushok", 5),
                new Cat("Kotush", 7)
        };
        System.out.println();
        System.out.println("У нас есть коты:");
        for (Cat cat : cats) {
            System.out.println(cat);
        }
        while (true) {
            boolean isCatsSatiety = true;
            int lackOfFood = 0;
            System.out.println();
            System.out.println("Кормим котов:");
            for (Cat cat : cats) {
                boolean isFeeding = cat.feedTheCat(plate);
                if (!isFeeding && cat.isCanEat(plate)) {
                    lackOfFood += cat.getAppetite();
                    isCatsSatiety = false;
                }
            }
            if (isCatsSatiety) {
                System.out.println();
                System.out.println("Всех, кого могли - накормили! " + plate);
                break;
            }
            System.out.println("Не все коты накормлены. Для кормления нехватает: " + (lackOfFood - plate.getAmountOfFood()) + ".");
            plate.addFood(lackOfFood - plate.getAmountOfFood());
        }
    }
}

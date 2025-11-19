package ru.otus.java.basic.hw19.main;

import ru.otus.java.basic.hw19.classes.Apple;
import ru.otus.java.basic.hw19.classes.Box;
import ru.otus.java.basic.hw19.classes.Fruit;
import ru.otus.java.basic.hw19.classes.Orange;

public class Main {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        Box<Fruit> fruitBox = new Box<>();

        for (int i = 0; i < 10; i++) {
            appleBox.addFruit(new Apple());
            fruitBox.addFruit(new Apple());
        }
        for (int i = 0; i < 5; i++) {
            orangeBox.addFruit(new Orange());
            fruitBox.addFruit(new Orange());
        }

        System.out.println("В коробке для яблок: " + appleBox.getBoxWeight() + "кг. (кол-во: " + appleBox.getFruitQuantity() + ")");
        System.out.println("Масса коробки яблок " + (appleBox.compareBox(orangeBox) ? "равна" : "не равна") + " массе коробки апельсинов");
        System.out.println("В коробке для апельсинов: " + orangeBox.getBoxWeight() + "кг. (кол-во: " + orangeBox.getFruitQuantity() + ")");
        System.out.println("Масса коробки апельсинов " + (orangeBox.compareBox(fruitBox) ? "равна" : "не равна") + " массе коробки фруктов");
        System.out.println("В коробке для фруктов: " + fruitBox.getBoxWeight() + "кг. (кол-во: " + fruitBox.getFruitQuantity() + ")");
        System.out.println("Масса коробки фруктов " + (fruitBox.compareBox(appleBox) ? "равна" : "не равна") + " массе коробки яблок");
        System.out.println();

        appleBox.sprinkleFruitInto(fruitBox);
        System.out.println("В коробке для яблок: " + appleBox.getBoxWeight() + "кг. (кол-во: " + appleBox.getFruitQuantity() + ")");
        System.out.println("В коробке для фруктов: " + fruitBox.getBoxWeight() + "кг. (кол-во: " + fruitBox.getFruitQuantity() + ")");
        System.out.println("Масса коробки яблок " + (appleBox.compareBox(orangeBox) ? "равна" : "не равна") + " массе коробки апельсинов");
    }
}

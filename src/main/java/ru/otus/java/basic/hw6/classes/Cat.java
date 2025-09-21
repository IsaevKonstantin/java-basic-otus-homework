package ru.otus.java.basic.hw6.classes;

public class Cat {
    String name;
    int appetite;
    boolean isSatiety;

    public int getAppetite() {
        return appetite;
    }

    public boolean isSatiety() {
        return isSatiety;
    }

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.isSatiety = false;
    }

    public boolean feedTheCat(Plate plate) {
        boolean isFeeding = plate.reduceFood(appetite);
        if (!isFeeding) {
            return false;
        }
        isSatiety = true;
        return true;
    }

    @Override
    public String toString() {
        return "Кот " + name + " - " + (isSatiety ? "сытый." : "голодный (аппетит: " + appetite + ").");
    }
}

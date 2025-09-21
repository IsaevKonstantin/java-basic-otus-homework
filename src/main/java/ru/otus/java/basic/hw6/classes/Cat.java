package ru.otus.java.basic.hw6.classes;

public class Cat {
    private final String name;
    private final int appetite;
    private boolean isSatiety;

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
        if (!isSatiety && plate.reduceFood(appetite)) {
            isSatiety = true;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Кот " + name + " - " + (isSatiety ? "сытый." : "голодный (аппетит: " + appetite + ").");
    }
}

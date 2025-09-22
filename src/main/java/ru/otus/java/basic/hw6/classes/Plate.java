package ru.otus.java.basic.hw6.classes;

public class Plate {
    private final int size;
    private int amountOfFood;

    public int getSize() {
        return size;
    }

    public int getAmountOfFood() {
        return amountOfFood;
    }

    public Plate(int size) {
        this.size = size;
        this.amountOfFood = size;
    }

    public void addFood(int food) {
        if (food >= 0) {
            amountOfFood += Math.min(food, size - amountOfFood);
            System.out.println("Еда добавлена в тарелку. " + this.toString());
        }
    }

    public boolean reduceFood(int food) {
        if (amountOfFood - food < 0 || food <= 0) {
            return false;
        }
        amountOfFood -= food;
        return true;
    }

    @Override
    public String toString() {
        return "В тарелке размером " + size + ", еды: " + amountOfFood + ".";
    }
}

package ru.otus.java.basic.hw6.classes;

public class Cat {
    private final String name;
    private final int appetite;
    private boolean isSatiety;

    public int getAppetite() {
        return appetite;
    }

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.isSatiety = false;
    }

    public boolean feedTheCat(Plate plate) {
        boolean result;
        String str = "";
        if (isSatiety) {
            str = " Не ест.";
            result = false;
        } else {
            if (plate.reduceFood(appetite)) {
                isSatiety = true;
                str = " Теперь еды в тарелке осталось: " + plate.getAmountOfFood() + ".";
            } else if (appetite > plate.getSize()) {
                str = " Но ему не хватит одной тарелки.";
            }
            result = isSatiety;
        }
        System.out.println(this.toString() + str);
        return result;
    }

    public boolean isCanEat(Plate plate) {
        return !isSatiety && appetite <= plate.getSize();
    }

    @Override
    public String toString() {
        return "Кот " + name + " - " + (isSatiety ? "сытый." : "голодный (аппетит: " + appetite + ").");
    }
}

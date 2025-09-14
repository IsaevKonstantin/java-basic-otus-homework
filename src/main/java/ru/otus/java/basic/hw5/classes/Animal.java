package ru.otus.java.basic.hw5.classes;

public abstract class Animal {
    String name;
    int swimSpeed;
    int runSpeed;
    int endurance;

    public String getName() {
        return name;
    }

    public int getEndurance() {
        return endurance;
    }

    public abstract int swim(int distance);

    public void info() {
        System.out.println("Животное зовут: " + name + ".");
        System.out.println(runSpeed != 0 ? "Скорость бега: " + runSpeed + "м/с." : "Не умеет бегать.");
        System.out.println(swimSpeed != 0 ? "Скорость плавания: " + swimSpeed + "м/с." : "Не умеет плавать.");
        System.out.println("Выносливость: " + endurance + ".");
    }

    public int run(int distance) {
        if (runSpeed == 0) {
            return -2;
        }
        if (distance > endurance) {
            return -1;
        }
        endurance -= distance;
        return (int) Math.ceil((double) distance / runSpeed);
    }

    public int relax(int time) {
        endurance += time * 2;
        return endurance;
    }
}

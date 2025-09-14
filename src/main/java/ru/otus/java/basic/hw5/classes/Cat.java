package ru.otus.java.basic.hw5.classes;

public class Cat extends Animal {
    public Cat(String name, int runSpeed, int endurance) {
        this.name = name;
        this.runSpeed = runSpeed;
        this.endurance = endurance;
    }

    @Override
    public int swim(int distance) {
        return -2;
    }
}

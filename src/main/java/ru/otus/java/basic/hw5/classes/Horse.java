package ru.otus.java.basic.hw5.classes;

public class Horse extends  Animal {
    public Horse(String name, int runSpeed, int swimSpeed, int endurance) {
        this.name = name;
        this.runSpeed = runSpeed;
        this.swimSpeed = swimSpeed;
        this.endurance = endurance;
    }

    @Override
    public int swim(int distance) {
        if (swimSpeed == 0) {
            return -2;
        }
        if (distance * 4 > endurance) {
            return -1;
        }
        endurance -= distance * 4;
        return distance / swimSpeed;
    }
}

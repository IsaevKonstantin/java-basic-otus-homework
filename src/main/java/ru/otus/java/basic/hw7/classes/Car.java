package ru.otus.java.basic.hw7.classes;

import ru.otus.java.basic.hw7.interfaces.Terrain;
import ru.otus.java.basic.hw7.interfaces.Transport;

public class Car implements Transport {
    private int benzine;
    private Human driver;
    static final String TYPE = "Машина";

    @Override
    public String getType() {
        return TYPE;
    }

    public Car(int benzine) {
        this.benzine = benzine;
        this.driver = null;
    }

    @Override
    public void humanUsed(Human human) {
        System.out.println(human.getName() + " сел в машину.");
        driver = human;
    }

    @Override
    public void humanNotUsed() {
        System.out.println(driver.getName() + " больше не использует машину.");
        driver = null;
    }

    @Override
    public boolean movement(Terrain terrain) {
        if (terrain.isNotTransportSuitable(TYPE)) {
            System.out.println("Машина не может ехать по: " + terrain.getRusName() + ".");
            return false;
        }
        if (driver == null) {
            System.out.println("Машина не может ехать без водителя.");
            return false;
        }
        if (terrain.getDistance() * terrain.getComplexity() > benzine) {
            benzine = 0;
            System.out.println("Машина не справилась с: " + terrain.getRusName() + ". Закончился бензин.");
            return false;
        }
        benzine -= terrain.getDistance() * terrain.getComplexity();
        System.out.println("Машина справилась с: " + terrain.getRusName() + ". Запас бензина: " + benzine + ".");
        return true;
    }

    @Override
    public String toString() {
        return "Машина " + (driver != null ? "используется " + driver.getName() + "." : "не имеет водителя.") +
                " Запас бензина: " + benzine + ".";
    }
}

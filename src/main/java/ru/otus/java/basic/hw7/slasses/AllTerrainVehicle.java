package ru.otus.java.basic.hw7.slasses;

import ru.otus.java.basic.hw7.interfaces.Terrain;
import ru.otus.java.basic.hw7.interfaces.Transport;

public class AllTerrainVehicle implements Transport {
    private int benzine;
    private Human driver;
    static final String type = "Вездеход";

    @Override
    public String getType() {
        return type;
    }

    public AllTerrainVehicle(int benzine) {
        this.benzine = benzine;
        this.driver = null;
    }

    @Override
    public void humanUsed(Human human) {
        System.out.println(human.getName() + " сел в вездеход.");
        driver = human;
    }

    @Override
    public void humanNotUsed() {
        System.out.println(driver.getName() + " больше не использует вездеход.");
        driver = null;
    }

    @Override
    public boolean movement(Terrain terrain) {
        if (terrain.isNotTransportSuitable(type)) {
            System.out.println("Вездеход не может ехать по: " + terrain.getRusName() + ".");
            return false;
        }
        if (driver == null) {
            System.out.println("Вездеход не может ехать без водителя.");
            return false;
        }
        if (terrain.getDistance() * 2 * terrain.getComplexity() > benzine) {
            benzine = 0;
            System.out.println("Вездеход не справился с: " + terrain.getRusName() + ". Закончился бензин.");
            return false;
        }
        benzine -= terrain.getDistance() * 2 * terrain.getComplexity();
        System.out.println("Вездеход справился с: " + terrain.getRusName() + ". Запас бензина: " + benzine + ".");
        return true;
    }

    @Override
    public String toString() {
        return "Вездеход " + (driver != null ? "используется " + driver.getName() + "." : "не имеет водителя.") +
                " Запас бензина: " + benzine + ".";
    }
}

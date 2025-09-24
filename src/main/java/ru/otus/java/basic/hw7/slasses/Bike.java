package ru.otus.java.basic.hw7.slasses;

import ru.otus.java.basic.hw7.interfaces.Terrain;
import ru.otus.java.basic.hw7.interfaces.Transport;

public class Bike implements Transport {
    private Human cyclist;
    private final int motivationEnergy;
    static final String type = "Велосипед";

    @Override
    public String getType() {
        return type;
    }

    public Bike(int motivationEnergy) {
        this.motivationEnergy = motivationEnergy;
        this.cyclist = null;
    }

    @Override
    public void humanUsed(Human human) {
        System.out.println(human.getName() + " сел на велосипед.");
        cyclist = human;
    }

    @Override
    public void humanNotUsed() {
        System.out.println(cyclist.getName() + " больше не использует велосипед.");
        cyclist = null;
    }

    @Override
    public boolean movement(Terrain terrain) {
        if (terrain.isNotTransportSuitable(type)) {
            System.out.println("Велосипед не может ехать по: " + terrain.getRusName() + ".");
            return false;
        }
        if (cyclist == null) {
            System.out.println("Велосипед не может ехать без велосипедиста.");
            return false;
        }
        if (terrain.getDistance() * terrain.getComplexity() / 2 > cyclist.getEndurance() + motivationEnergy) {
            cyclist.setEndurance(0);
            System.out.println("Велосипед не справился с: " + terrain.getRusName() + ". Закончились силы у велосипедиста.");
            return false;
        }
        cyclist.setEndurance(motivationEnergy + cyclist.getEndurance() - terrain.getDistance() * terrain.getComplexity() / 2);
        System.out.println("Велосипед справился с: " + terrain.getRusName() + ". Запас сил у велосипедиста: " + cyclist.getEndurance() + ".");
        return true;
    }

    @Override
    public String toString() {
        return "Велосипед " + (cyclist != null ? "используется " + cyclist.getName() + "." : "не имеет велосипедиста.");
    }
}

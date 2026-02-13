package ru.otus.java.basic.hw7.classes;

import ru.otus.java.basic.hw7.interfaces.Terrain;
import ru.otus.java.basic.hw7.interfaces.Transport;

public class Horse implements Transport {
    private int endurance;
    private Human rider;
    static final String TYPE = "Лошадь";

    @Override
    public String getType() {
        return TYPE;
    }

    public Horse(int endurance) {
        this.endurance = endurance;
        this.rider = null;
    }

    @Override
    public void humanUsed(Human human) {
        System.out.println(human.getName() + " сел на лошадь.");
        rider = human;
    }

    @Override
    public void humanNotUsed() {
        System.out.println(rider.getName() + " больше не использует лошадь.");
        rider = null;
    }

    @Override
    public boolean movement(Terrain terrain) {
        if (terrain.isNotTransportSuitable(TYPE)) {
            System.out.println("Лошадь не может скакать по: " + terrain.getRusName() + ".");
            return false;
        }
        if (rider == null) {
            System.out.println("Лошадь не может ехать без наездника.");
            return false;
        }
        if (terrain.getDistance() * terrain.getComplexity() / 3 > endurance) {
            System.out.println("Лошадь не справилась с: " + terrain.getRusName() + ". Закончились силы.");
            return false;
        }
        endurance -= terrain.getDistance() * terrain.getComplexity() / 3;
        System.out.println("Лошадь справилась с: " + terrain.getRusName() + ". Запас сил: " + endurance + ".");
        return true;
    }

    @Override
    public String toString() {
        return "Лошадь " + (rider != null ? "используется " + rider.getName() + "." : "не имеет наездника.") +
                " Запас сил: " + endurance + ".";
    }
}

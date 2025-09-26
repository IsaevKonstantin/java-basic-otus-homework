package ru.otus.java.basic.hw7.classes;

import ru.otus.java.basic.hw7.interfaces.Terrain;
import ru.otus.java.basic.hw7.interfaces.Transport;

public class Human {
    private final String name;
    private Transport currentTransport;
    private int endurance;

    public String getName() {
        return name;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public Human(String name, int endurance) {
        this.endurance = endurance;
        this.name = name;
        this.currentTransport = null;
    }

    public boolean move(Terrain terrain) {
        if (currentTransport != null) {
            return currentTransport.movement(terrain);
        }
        if (terrain.getDistance() > endurance) {
            endurance = 0;
            System.out.println(name + " пешком не справился с: " + terrain.getRusName() + ". Закончились силы.");
            return false;
        }
        endurance -= terrain.getDistance();
        System.out.println(name + " пешком справился с: " + terrain.getRusName() + ". Запас сил: " + endurance + ".");
        return true;
    }

    public void setCurrentTransport(Transport transport) {
        if (currentTransport != null) {
            System.out.println(name + " не удалось использовать " +
                    transport.getType().toLowerCase() + ", так как он уже использует " +
                    currentTransport.getType().toLowerCase() + ".");
            return;
        }
        transport.humanUsed(this);
        currentTransport = transport;
    }

    public void deleteCurrentTransport() {
        if (currentTransport == null) {
            System.out.println("Не удалось удалить транспорт у " + name + ", так как его нет.");
            return;
        }
        currentTransport.humanNotUsed();
        currentTransport = null;
    }

    @Override
    public String toString() {
        return "Человек по имени " + name +
                (currentTransport != null ? " использует: " + currentTransport.getType() + "." : " не использует транспорт.") +
                " Сил у " + name + ": " + endurance + ".";
    }
}

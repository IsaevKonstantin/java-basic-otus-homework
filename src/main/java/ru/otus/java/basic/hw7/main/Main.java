package ru.otus.java.basic.hw7.main;

import ru.otus.java.basic.hw7.interfaces.Terrain;
import ru.otus.java.basic.hw7.interfaces.Transport;
import ru.otus.java.basic.hw7.slasses.*;

public class Main {
    public static void main(String[] args) {
        Human human = new Human("Maik", 30);
        Transport[] transports = {
                new Bike(40),
                new Car(80),
                new Horse(150),
                new AllTerrainVehicle(290),
        };
        Terrain[] terrains = {
                new Flatland(40),
                new DenseForest(60),
                new Swamp(50),
        };
        printInfo(human, transports, terrains);

        for (Terrain terrain : terrains) {
            System.out.println();
            System.out.println(human.getName() + " приступает к преодалению местности: " + terrain.getRusName() + ".");
            if (!isDoneSprint(terrain, human, transports)) {
                System.out.println();
                System.out.println(human.getName() + " не справился со спринтом(");
                return;
            }
        }
        System.out.println();
        System.out.println(human.getName() + " справился со спринтом)");
    }

    public static void printInfo(Human human, Transport[] transports, Terrain[] terrains) {
        System.out.println();
        System.out.println("Дано:");
        System.out.println(human);
        for (int i = 0; i < transports.length; i++) {
            System.out.println("Т" + (i + 1) + ": " + transports[i]);
        }
        for (int i = 0; i < terrains.length; i++) {
            System.out.println("М" + (i + 1) + ": " + terrains[i]);
        }
    }

    public static boolean isDoneSprint(Terrain terrain, Human human, Transport[] transports) {
        if (!terrain.doIt(human)) {
            for (Transport transport : transports) {
                switchTransport(human, transport);
                if (terrain.doIt(human)) {
                    human.deleteCurrentTransport();
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static void switchTransport(Human human, Transport transport) {
        human.deleteCurrentTransport();
        human.setCurrentTransport(transport);
    }
}

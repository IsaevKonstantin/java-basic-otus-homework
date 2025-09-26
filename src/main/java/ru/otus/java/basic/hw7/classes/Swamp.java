package ru.otus.java.basic.hw7.classes;

import ru.otus.java.basic.hw7.enums.TerrainType;
import ru.otus.java.basic.hw7.interfaces.Terrain;

public class Swamp implements Terrain {
    private final int distance;
    private final TerrainType terrainType;

    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public int getComplexity() {
        return terrainType.getComplexity();
    }

    @Override
    public String getRusName() {
        return terrainType.getRusName();
    }

    public Swamp(int distance) {
        this.distance = distance;
        this.terrainType = TerrainType.SWAMP;
    }

    @Override
    public boolean isNotTransportSuitable(String type) {
        return terrainType.isNotTransportSuitable(type);
    }

    @Override
    public String toString() {
        return "Болото, дистанция: " + distance + ".";
    }
}

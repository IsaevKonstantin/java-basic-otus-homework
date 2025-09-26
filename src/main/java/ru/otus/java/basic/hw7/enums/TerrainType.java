package ru.otus.java.basic.hw7.enums;

import java.util.Arrays;

public enum TerrainType {
    SWAMP(3, new String[]{"Вездеход"}, "Болото"), DENSE_FOREST(2, new String[]{"Вездеход", "Лошадь", "Велосипед"}, "Густой лес"), FLATLAND(1, new String[]{"Вездеход", "Лошадь", "Велосипед", "Машина"}, "Равнина");

    private final int complexity;
    private final String[] suitableTransport;
    private final String rusName;

    TerrainType(int complexity, String[] suitableTransport, String rusName) {
        this.complexity = complexity;
        this.suitableTransport = suitableTransport;
        this.rusName = rusName;
    }

    public int getComplexity() {
        return complexity;
    }

    public String getRusName() {
        return rusName;
    }

    public boolean isNotTransportSuitable(String type) {
        return !Arrays.asList(suitableTransport).contains(type);
    }
}

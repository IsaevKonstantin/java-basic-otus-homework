package ru.otus.java.basic.hw7.interfaces;

public interface Terrain {
    int getDistance();

    int getComplexity();

    String getRusName();

    boolean isNotTransportSuitable(String type);
}

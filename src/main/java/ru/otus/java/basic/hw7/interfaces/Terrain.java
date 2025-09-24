package ru.otus.java.basic.hw7.interfaces;

import ru.otus.java.basic.hw7.slasses.Human;

public interface Terrain {
    int getDistance();
    int getComplexity();
    String getRusName();
    boolean isNotTransportSuitable(String type);
    boolean doIt(Human human);
}

package ru.otus.java.basic.hw7.interfaces;

import ru.otus.java.basic.hw7.slasses.Human;

public interface Transport {
    String getType();
    void humanUsed(Human human);
    void humanNotUsed();
    boolean movement(Terrain terrain);
}

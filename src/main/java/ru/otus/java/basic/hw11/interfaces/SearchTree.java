package ru.otus.java.basic.hw11.interfaces;

import java.util.List;

public interface SearchTree<T> {
    T find(T element);

    List<T> getSortedList();
}

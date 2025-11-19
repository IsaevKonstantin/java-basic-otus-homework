package ru.otus.java.basic.hw19.classes;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private final List<T> fruits = new ArrayList<>();

    public void addFruit(T element) {
        fruits.add(element);
    }

    public double getBoxWeight() {
        double res = 0;
        for (T el : fruits) {
            res += el.getWeight();
        }
        return res;
    }

    public Integer getFruitQuantity() {
        return fruits.size();
    }

    public Boolean compareBox(Box<?> o) {
        return Math.abs(this.getBoxWeight() - o.getBoxWeight()) < 0.0001;
    }

    public void sprinkleFruitInto (Box<? super T> o) {
        if (this == o) return;
        o.fruits.addAll(this.fruits);
        this.fruits.clear();
    }

}

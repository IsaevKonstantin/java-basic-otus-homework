package ru.otus.java.basic.hw22.main;

import ru.otus.java.basic.hw22.clasess.Arr;

public class Main {
    public static void main(String[] args) {
        Arr arr = new Arr();
        int[] res = arr.elementsAfterLastOne(new int[]{1, 2, 1, 3, 2});
        System.out.print("[");
        for (int i = 0; i < res.length; i++) {
            System.out.print(i == res.length - 1 ? res[i] : (res[i] + ", "));
        }
        System.out.print("]");
    }
}

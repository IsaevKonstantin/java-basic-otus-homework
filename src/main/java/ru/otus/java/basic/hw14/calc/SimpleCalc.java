package ru.otus.java.basic.hw14.calc;

public class SimpleCalc {

    public static void main(String[] args) {
        createAndComputeArray();
    }

    public static void createAndComputeArray() {
        int size = 10_000_000;
        double[] array = new double[size];

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            System.out.println(i + ": " + array[i]); //Время выполнения цикла: 17888 мс
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения цикла: " + (endTime - startTime) + " мс");
    }
}

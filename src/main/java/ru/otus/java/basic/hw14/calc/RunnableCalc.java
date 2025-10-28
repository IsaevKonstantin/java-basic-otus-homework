package ru.otus.java.basic.hw14.calc;

public class RunnableCalc {
    public static void main(String[] args) {
        int size = 100_000_000;
        double[] array = new double[size];

        int numThreads = 4;
        Thread[] threads = new Thread[numThreads];
        int chunkSize = size / numThreads;

        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = start + chunkSize;
            threads[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    array[j] = 1.14 * Math.cos(j) * Math.sin(j * 0.2) * Math.cos(j / 1.2);
                }
            });
        }

        long startTime = System.currentTimeMillis();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения цикла (" + numThreads + " потока): " + (endTime - startTime) + " мс");

    }
}

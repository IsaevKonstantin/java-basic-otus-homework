package ru.otus.java.basic.hw14.calc;

public class RunnableCalc implements Runnable {
    private final int start;
    private final int end;
    private final double[] array;

    public RunnableCalc(int start, int end, double[] array) {
        this.start = start;
        this.end = end;
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            System.out.println(i + ": " + array[i]); //Время выполнения цикла (2 потока): 35385 мс
        }
    }

    public static void main(String[] args) {
        int size = 10_000_000;
        double[] array = new double[size];

        int numThreads = 2;
        Thread[] threads = new Thread[numThreads];
        int chunkSize = size / numThreads;

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = start + chunkSize;
            threads[i] = new Thread(new RunnableCalc(start, end, array), "Поток " + (i + 1));
            threads[i].start();
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

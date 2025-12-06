package ru.otus.java.basic.hw21.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Object lock = new Object();
    private static final char[] chars = {'A', 'B', 'C'};
    private static char current = chars[0];
    private static final Integer count = 3;

    public static void main(String[] args) {

        ExecutorService serv = Executors.newFixedThreadPool(chars.length);

        System.out.println("Выполнение программы!");

        for (int i = 0; i < chars.length; i++) {
            char target = chars[i];
            char next = (i + 1) < chars.length ? chars[i + 1] : chars[0];
            serv.execute(() -> printLetter(target, next));
        }

        serv.shutdown();
        try {
            serv.awaitTermination(10, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\nКонец программы!");
    }

    private static void printLetter(char target, char next) {
        for (int i = 0; i < count; i++) {
            synchronized (lock) {
                while (current != target) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print(target);
                current = next;
                lock.notifyAll();
            }
        }
    }
}

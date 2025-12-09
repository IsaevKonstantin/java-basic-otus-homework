package ru.otus.java.basic.hw21.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Object LOCK = new Object();
    private static final char[] CHARS = {'A', 'B', 'C'};
    private static char current = CHARS[0];
    private static final int REPEAT_COUNT = 3;

    public static void main(String[] args) {

        ExecutorService serv = Executors.newFixedThreadPool(CHARS.length);

        System.out.println("Выполнение программы!");

        for (int i = 0; i < CHARS.length; i++) {
            char target = CHARS[i];
            char next = (i + 1) < CHARS.length ? CHARS[i + 1] : CHARS[0];
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
        for (int i = 0; i < REPEAT_COUNT; i++) {
            synchronized (LOCK) {
                while (current != target) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print(target);
                current = next;
                LOCK.notifyAll();
            }
        }
    }
}

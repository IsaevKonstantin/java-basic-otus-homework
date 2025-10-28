package ru.otus.java.basic.chat.server;

public class ServerApp {
    public static final int PORT = 8189;

    public static void main(String[] args) {
        new Server(PORT).start();
    }
}
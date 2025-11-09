package ru.otus.java.basic.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private final int port;

    private final List<ClientHandler> clients;
    private final AuthenticatedProvider authenticatedProvider;

    public Server(int port) {
        this.port = port;
        clients = new CopyOnWriteArrayList<>();
        authenticatedProvider = new InMemoryAuthenticatedProvider(this);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запустился на порту: " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket, this);
            }
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        System.out.println("Клиент с ником: " + clientHandler.getUsername() + " - отключился");
        clients.remove(clientHandler);
    }

    public void broadcastMessage(String message, String sender) {
        for (ClientHandler c : clients) {
            if (!sender.equals(c.getUsername())) {
                c.sendMsg(message);
            }
        }
    }

    public void sendPrivateMessage(String userName, String message) {
        for (ClientHandler c : clients) {
            if (c.getUsername().equals(userName)) c.sendMsg(message);
        }
    }

    public boolean kickUser(String userName) {
        for (ClientHandler c : clients) {
            if (c.getUsername().equals(userName) && c.getRole() == Role.USER) {
                c.sendMsg(ConsoleColors.RED_BOLD + "Вы были отключены администратором!" + ConsoleColors.RESET);
                c.disconnect();
                return true;
            }
        }
        return false;
    }

    public boolean isContains(String userName) {
        for (ClientHandler c : clients) {
            if (c.getUsername().equals(userName)) return true;
        }
        return false;
    }

    public AuthenticatedProvider getAuthenticatedProvider() {
        return authenticatedProvider;
    }
}

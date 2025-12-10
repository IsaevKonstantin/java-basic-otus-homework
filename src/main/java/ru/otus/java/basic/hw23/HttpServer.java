package ru.otus.java.basic.hw23;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private final int PORT;
    private final Dispatcher DISPATCHER;

    public HttpServer(int PORT) {
        this.PORT = PORT;
        this.DISPATCHER = new Dispatcher();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту: " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> handleClient(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket socket) {
        try (socket) {
            System.out.println("\nКлиент подключился!");
            byte[] buffer = new byte[8192];
            int n = socket.getInputStream().read(buffer);
            if (n >= 1) {
                String rawRequest = new String(buffer, 0, n);
                HttpRequest request = new HttpRequest(rawRequest);
                DISPATCHER.execute(request, socket.getOutputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

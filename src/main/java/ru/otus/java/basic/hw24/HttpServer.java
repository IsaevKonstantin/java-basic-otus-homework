package ru.otus.java.basic.hw24;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.java.basic.hw24.application.ItemsStorage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private final int PORT;
    private final Dispatcher DISPATCHER;
    private final ExecutorService threadPool;
    private static final Logger logger = LogManager.getLogger(HttpServer.class.getName());

    public HttpServer(int PORT) {
        this.PORT = PORT;
        this.DISPATCHER = new Dispatcher();
        ItemsStorage.init();
        this.threadPool = Executors.newFixedThreadPool(10);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            logger.info(PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                threadPool.execute(() -> handleClient(socket));
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private void handleClient(Socket socket) {
        try (socket) {
            logger.info("Клиент подключился");
            byte[] buffer = new byte[8192];
            int n = socket.getInputStream().read(buffer);
            if (n >= 1) {
                String rawRequest = new String(buffer, 0, n);
                HttpRequest request = new HttpRequest(rawRequest);
                request.info();
                DISPATCHER.execute(request, socket.getOutputStream());
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}

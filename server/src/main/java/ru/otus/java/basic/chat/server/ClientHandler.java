package ru.otus.java.basic.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private final Socket socket;
    private final Server server;
    private final DataInputStream in;
    private final DataOutputStream out;

    private String username;

    public ClientHandler(Socket socket, Server server) throws IOException, RuntimeException {
        this.socket = socket;
        this.server = server;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        username = "user" + socket.getPort();

        new Thread(() -> {
            System.out.println("Клиент подключился " + socket.getPort());
            sendMsg("Вы подключились с ником: " + username);
            try {
                while (true) {
                    String message = in.readUTF();
                    if (message.startsWith("/")) {
                        if (("/exit").equals(message)) {
                            sendMsg("/exitok");
                            server.broadcastMessage(username + " - отключился!");
                            break;
                        }
                        String[] token = message.split(" ", 3);
                        if (("/w").equals(token[0])) {
                            try {
                                if (token[1].equals(username)) {
                                    sendMsg("Невозможно отправить сообщение себе");
                                } else if (!server.isContains(token[1])) {
                                    sendMsg("Получатель не найден");
                                } else if (token[2].trim().isEmpty()) {
                                    sendMsg("Невозможно отправить пустое сообщение пользователю " + token[1]);
                                } else {
                                    sendMsg("Приватное сообщение отправлено");
                                    server.sendPrivateMessage(token[1], "Приватное сообщение от " + username + ": " + token[2].trim());
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                sendMsg("Для отправки приватного сообщения необходимо указать пользователя и текст!");
                            }
                        }
                    } else {
                        server.broadcastMessage(username + ": " + message);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                disconnect();
            }
        }).start();
    }

    public void sendMsg(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void disconnect() {
        server.unsubscribe(this);
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

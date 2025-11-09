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
    private Role role;
    private boolean authenticated;

    public ClientHandler(Socket socket, Server server) throws IOException, RuntimeException {
        this.socket = socket;
        this.server = server;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            System.out.println("Клиент подключился по порту: " + socket.getPort());
            try {
                while (true) {
                    sendMsg("Перед работой с чатом необходимо выполнить аутентификацию " +
                            ConsoleColors.GREEN_UNDERLINED + "'/auth login password'" + ConsoleColors.RESET +
                            " или регистрацию " +
                            ConsoleColors.GREEN_UNDERLINED + "'/reg login password username'" + ConsoleColors.RESET);
                    String message = in.readUTF();
                    if (message.startsWith("/")) {
                        if (message.startsWith("/exit")) {
                            System.out.println("Клиент отключился не пройдя аутентификацию по порту: " + socket.getPort());
                            sendMsg("/exitok");
                            break;
                        }
                        if (message.startsWith("/auth ")) {
                            String[] token = message.split(" ");
                            if (token.length != 3) {
                                sendMsg("Неверный формат команды /auth");
                                continue;
                            }
                            if (server.getAuthenticatedProvider()
                                    .authenticate(this, token[1], token[2])) {
                                authenticated = true;
                                System.out.println("Клиент с ником: " + username + " - прошел аутентификацию");
                                server.broadcastMessage(ConsoleColors.CYAN_BOLD + username + ConsoleColors.RESET + ConsoleColors.GREEN + " - вошел в чат!" + ConsoleColors.RESET, username);
                                break;
                            }
                            continue;
                        }
                        if (message.startsWith("/reg ")) {
                            String[] token = message.split(" ");
                            if (token.length != 4) {
                                sendMsg("Неверный формат команды /reg");
                                continue;
                            }
                            if (server.getAuthenticatedProvider()
                                    .register(this, token[1], token[2], token[3])) {
                                authenticated = true;
                                System.out.println("Клиент с ником: " + username + " - прошел регистрацию");
                                server.broadcastMessage(ConsoleColors.CYAN_BOLD + username + ConsoleColors.RESET + ConsoleColors.GREEN + " - зарегестрировался в чате!" + ConsoleColors.RESET, username);
                                break;
                            }
                        }
                    }
                }

                while (authenticated) {
                    String message = in.readUTF();
                    if (message.startsWith("/")) {
                        if (("/exit").equals(message)) {
                            sendMsg("/exitok");
                            server.broadcastMessage(ConsoleColors.CYAN_BOLD + username + ConsoleColors.RESET + ConsoleColors.RED + " - вышел из чата!" + ConsoleColors.RESET, username);
                            break;
                        }
                        String[] token = message.split(" ", 3);
                        if (("/w").equals(token[0])) {
                            try {
                                if (token[1].equals(username)) {
                                    sendMsg("Невозможно отправить сообщение себе!");
                                } else if (!server.isContains(token[1])) {
                                    sendMsg("Получатель не найден!");
                                } else if (token[2].trim().isEmpty()) {
                                    sendMsg("Невозможно отправить пустое сообщение пользователю " + token[1] + "!");
                                } else {
                                    sendMsg("Приватное сообщение отправлено");
                                    server.sendPrivateMessage(token[1], ConsoleColors.GREEN_BOLD + "Приватное сообщение от " + ConsoleColors.RESET + ConsoleColors.CYAN_BOLD + username + ": " + ConsoleColors.RESET + token[2].trim());
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                sendMsg("Для отправки приватного сообщения необходимо указать пользователя и текст!");
                            }
                        }
                        if (("/kick").equals(token[0])) {
                            try {
                                if (role == Role.USER) {
                                    sendMsg("Отключать пользователей имеет право только администратор!");
                                } else if (token[1].equals(username)) {
                                    sendMsg("Невозможно отключить себя!");
                                } else if (!server.isContains(token[1])) {
                                    sendMsg("Пользователь не найден!");
                                } else {
                                    if (server.kickUser(token[1])) {
                                        sendMsg("/kickok");
                                        server.broadcastMessage("Клиент с ником: " + ConsoleColors.CYAN_BOLD + token[1] + ConsoleColors.RESET + ConsoleColors.RED_BOLD + " - был отключен!" + ConsoleColors.RESET, username);
                                    } else {
                                        sendMsg("/kickerr");
                                    }
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                sendMsg("Для отключения пользователя от чата необходимо указать ник!");
                            }
                        }
                    } else {
                        server.broadcastMessage(ConsoleColors.CYAN_BOLD + username + ": " + ConsoleColors.RESET + message, username);
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

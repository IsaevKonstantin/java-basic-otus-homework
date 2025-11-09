package ru.otus.java.basic.chat.server;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryAuthenticatedProvider implements AuthenticatedProvider {
    private class User {
        private final String login;
        private final String password;
        private final String username;
        private final Role role;

        public User(String login, String password, String username) {
            this.login = login;
            this.password = password;
            this.username = username;
            this.role = Role.USER;
        }

        public User(String login, String password, String username, Role role) {
            this.login = login;
            this.password = password;
            this.username = username;
            this.role = role;
        }
    }

    private final List<User> users;
    private final Server server;

    public InMemoryAuthenticatedProvider(Server server) {
        this.server = server;
        this.users = new CopyOnWriteArrayList<>();
        users.add(new User("qwe", "qwe", "qwe1", Role.ADMIN));
        users.add(new User("asd", "asd", "asd1"));
        users.add(new User("zxc", "zxc", "zxc1", Role.ADMIN));
        users.add(new User("aaa", "aaa", "aaa1"));
    }

    private String getUsernameByLoginAndPassword(String login, String password) {
        for (User u : users) {
            if (u.login.equalsIgnoreCase(login) && u.password.equals(password)) {
                return u.username;
            }
        }
        return null;
    }

    private boolean isLoginAlreadyExists(String login) {
        for (User u : users) {
            if (u.login.equalsIgnoreCase(login)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUsernameAlreadyExists(String username) {
        for (User u : users) {
            if (u.username.equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    private Role getRoleByLoginAndPassword(String login, String password) {
        for (User u : users) {
            if (u.login.equalsIgnoreCase(login) && u.password.equals(password)) {
                return u.role;
            }
        }
        return null;
    }

    @Override
    public void initialize() {
        System.out.println("used InMemoryAuthenticatedProvider");
    }

    @Override
    public boolean authenticate(ClientHandler clientHandler, String login, String password) {
        String authUsername = getUsernameByLoginAndPassword(login, password);
        Role role = getRoleByLoginAndPassword(login, password);
        if (authUsername == null) {
            clientHandler.sendMsg("Некорректный логин/пароль");
            return false;
        }
        if (server.isContains(authUsername)) {
            clientHandler.sendMsg("Указанная учетная запись уже используется");
            return false;
        }
        clientHandler.setRole(role == null ? Role.USER : role);
        clientHandler.setUsername(authUsername);
        server.subscribe(clientHandler);
        clientHandler.sendMsg("/authok " + authUsername);
        return true;
    }

    @Override
    public boolean register(ClientHandler clientHandler, String login, String password, String username) {
        if (login.length() < 3) {
            clientHandler.sendMsg("Логин должен содержать 3+ символов");
            return false;
        }
        if (!login.toLowerCase().matches("[a-z]+")) {
            clientHandler.sendMsg("Логин должен состоять только из латинских букв");
            return false;
        }
        if (password.length() < 3) {
            clientHandler.sendMsg("Пароль должен содержать 3+ символов");
            return false;
        }
        if (isLoginAlreadyExists(login)) {
            clientHandler.sendMsg("Такой логин уже занят");
            return false;
        }
        if (isUsernameAlreadyExists(username)) {
            clientHandler.sendMsg("Такое имя пользователя уже занято");
            return false;
        }
        users.add(new User(login, password, username));
        clientHandler.setUsername(username);
        clientHandler.setRole(Role.USER);
        server.subscribe(clientHandler);
        clientHandler.sendMsg("/regok " + username);
        return true;
    }
}

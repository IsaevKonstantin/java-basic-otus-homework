package ru.otus.java.basic.chat.server;

import java.sql.SQLException;

public class DatabaseAuthenticatedProvider implements AuthenticatedProvider {
    private final Server server;
    private final UserDAO userDAO;

    public DatabaseAuthenticatedProvider(Server server) {
        this.server = server;
        this.userDAO = new UserDAO();
    }

    @Override
    public void initialize() {
        System.out.println("used DatabaseAuthenticatedProvider");
    }

    @Override
    public boolean authenticate(ClientHandler clientHandler, String login, String password) {
        try {
            UserRecord user = userDAO.getUserByLoginAndPassword(login, password);
            if (user == null) {
                clientHandler.sendMsg("Некорректный логин/пароль");
                return false;
            }
            if (server.isContains(user.geUserName())) {
                clientHandler.sendMsg("Указанная учетная запись уже используется");
                return false;
            }
            clientHandler.setUsername(user.geUserName());
            clientHandler.setRole(user.getRole());
            server.subscribe(clientHandler);
            clientHandler.sendMsg("/authok " + user.geUserName());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            clientHandler.sendMsg("Ошибка базы данных при аутентификации");
            return false;
        }
    }

    @Override
    public boolean register(ClientHandler clientHandler, String login, String password, String username) {
        try {
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
            if (userDAO.isLoginExists(login)) {
                clientHandler.sendMsg("Такой логин уже занят");
                return false;
            }
            if (userDAO.isUsernameExists(username)) {
                clientHandler.sendMsg("Такое имя пользователя уже занято");
                return false;
            }
            userDAO.addUser(login, password, username);
            clientHandler.setUsername(username);
            clientHandler.setRole(Role.USER);
            server.subscribe(clientHandler);
            clientHandler.sendMsg("/regok " + username);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            clientHandler.sendMsg("Ошибка базы данных при регистрации");
            return false;
        }
    }
}

package ru.otus.java.basic.chat.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public boolean isLoginExists(String login) throws SQLException {
        String sql = "SELECT id FROM users WHERE login = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public boolean isUsernameExists(String username) throws SQLException {
        String sql = "SELECT id FROM users WHERE username = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public void addUser(String login, String password, String username) throws SQLException {
        String sql = "INSERT INTO users (login, password, username, role) VALUES (?, ?, ?, 'USER')";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, password);
            stmt.setString(3, username);

            stmt.executeUpdate();
        }
    }

    public UserRecord getUserByLoginAndPassword(String login, String password) throws SQLException {
        String sql = "SELECT username, role FROM users WHERE login = ? AND password = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                Role role = Role.valueOf(rs.getString("role"));
                return new UserRecord(username, role);
            }
        }
        return null;
    }
}

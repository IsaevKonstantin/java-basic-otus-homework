package ru.otus.java.basic.chat.server;

public class UserRecord {
    private final String userName;
    private final Role role;

    public UserRecord(String userName, Role role) {
        this.userName = userName;
        this.role = role;
    }

    public String geUserName() {
        return userName;
    }

    public Role getRole() {
        return role;
    }
}

package ru.otus.java.basic.hw3.users;

public class User {
    private final String name;
    private final String surname;
    private final String patronymic;
    private final int yearOfBirth;
    private final String email;

    public int getYearOfBirth() {
        return  yearOfBirth;
    }

    public User(String name, String surname, String patronymic, int yearOfBirth, String email) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
    }

    public void printInfo() {
        System.out.println("ФИО: " + surname + " " + name + " " + patronymic);
        System.out.println("Год рождения: " + yearOfBirth);
        System.out.println("e-mail: " + email);
        System.out.println();
    }
}

package ru.otus.java.basic.hw11.classes;

import java.util.Objects;

public class Employee {
    private final String name;
    private final String lastName;
    private final int age;
    private final int id;

    public int getId() {
        return id;
    }

    public Employee(String name, String lastName, int age, int id) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.id = id;
    }

    public String talk() {
        return String.format("Привет! Меня зовут %s %s, мой возраст: %d.", lastName, name, age);
    }

    @Override
    public String toString() {
        return "* " + lastName + " " + name + "(возраст: " + age + ", ID: " + id + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age && id == employee.id && Objects.equals(name, employee.name) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, age, id);
    }
}

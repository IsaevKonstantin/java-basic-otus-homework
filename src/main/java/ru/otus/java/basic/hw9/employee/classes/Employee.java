package ru.otus.java.basic.hw9.employee.classes;

public class Employee {
    private final String name;
    private final int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean isOlder(int age) {
        return this.age >= age;
    }

    @Override
    public String toString() {
        return "Имя: " + name + ", возраст: " + age + ".";
    }
}

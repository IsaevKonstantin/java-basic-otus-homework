package ru.otus.java.basic.hw3.users;

public class Main {
    public static void main(String[] args) {
        User[] users = {
                new User("Антон", "Гончаров", "Генадиевич", 2000, "anton@mail.ru"),
                new User("Сергей", "Дмитриев", "Иванов", 1984, "sergey@mail.ru"),
                new User("Виктория", "Добролюбова", "Генадиевна", 2000, "victoria@mail.ru"),
                new User("Константин", "Исаев", "Андреевич", 1997, "isaev@mail.ru"),
                new User("Дарина", "Исаева", "Сергеевна", 1998, "darina@mail.ru"),
                new User("Мария", "Сорокина", "Иванова", 1999, "maria@mail.ru"),
                new User("Елизавета", "Гончарова", "Антоновна", 1980, "liza@mail.ru"),
                new User("Владислав", "Баженов", "Антонович", 1990, "vlad@mail.ru"),
                new User("Кирилл", "Ким", "Владимирович", 2002, "kim@mail.ru"),
                new User("Людмила", "Баженова", "Генадиевич", 1975, "ludmila@mail.ru"),
        };

        printOldUsers(users);
    }

    private static void printOldUsers(User[] users) {
        int currentYear = java.time.Year.now().getValue();
        for (User user : users) {
            if (currentYear - user.getYearOfBirth() >= 40) {
                user.printInfo();
            }
        }
    }
}

package ru.otus.java.basic.hw10;

import ru.otus.java.basic.hw10.classes.Contact;
import ru.otus.java.basic.hw10.classes.PhoneBook;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();
        List<Contact> contacts = List.of(
                new Contact("Андрей", "Исаев", "Сергеевич", List.of("1111")),
                new Contact("Сергей", "Имайкин", "Андреевич", List.of("3333", "4444")),
                new Contact("Валерий", "Бондарчук", "Федотов", List.of("5555", "6666")),
                new Contact("Ольга", "Имайкина", "Валерьевна", List.of("7777", "8888", "2200")),
                new Contact("Мария", "Исаева", "Сергеевна", List.of("9999", "1000", "1100")),
                new Contact("Константин", "Исаев", "Андреевич", List.of("2000", "3000")),
                new Contact("Дарина", "Исаева", "Сергеевна", List.of("4000", "5000", "6000", "7000"))
        );

        System.out.println();
        addSomeNewContact(contacts, book);

        System.out.println();
        addNewContact(new Contact("Дмитрий", "Дмитриев", "Артемов", List.of("1234")), book);

        System.out.println();
        addNewContact(new Contact("Дмитрий", "Дмитриев", "Артемов", List.of("1234")), book);

        System.out.println();
        searchByLastName("Исаев", book);
        System.out.println();
        searchByLastName("Лобанов", book);

        System.out.println();
        searchByPhoneNumber("7777", book);
        System.out.println();
        searchByPhoneNumber("0000", book);
    }

    private static void searchByPhoneNumber(String phoneNumber, PhoneBook book) {
        System.out.println("Контакт с номером телефона \"" + phoneNumber + "\":");
        boolean isContains = book.containsPhoneNumber(phoneNumber);
        System.out.println(isContains ? " + " + book.findByPhoneNumber(phoneNumber) + "." : " - Контакт не найден!");
    }

    private static void searchByLastName(String lastName, PhoneBook book) {
        System.out.println("Список контактов по фамилии \"" + lastName + "\":");
        List<Contact> findContacts = book.findByLastName(lastName);
        if (findContacts.isEmpty()) {
            System.out.println(" - Контакт не найден!");
            return;
        }
        for (Contact contact : findContacts) {
            System.out.println(" + " + contact + ".");
        }
    }

    private static void addNewContact(Contact contact, PhoneBook book) {
        System.out.println("Добавляем новый контакт:");
        boolean isAdd = book.add(contact);
        System.out.println((isAdd ? " + Добавлен: " : " - Ну удалось добавить: ") + contact + ".");
    }

    private static void addSomeNewContact(List<Contact> contacts, PhoneBook book) {
        System.out.println("Добавляем новые контакты:");
        for (Contact contact : contacts) {
            boolean isAdd = book.add(contact);
            System.out.println((isAdd ? " + Добавлен: " : " - Ну удалось добавить: ") + contact + ".");
        }
    }
}

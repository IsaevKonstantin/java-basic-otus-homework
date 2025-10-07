package ru.otus.java.basic.hw10;

import ru.otus.java.basic.hw10.classes.Contact;
import ru.otus.java.basic.hw10.classes.PhoneBook;

import java.util.List;
import java.util.Set;

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
        System.out.println("Добавляем новые коннтакты:");
        for (Contact contact : contacts) {
            boolean isAdd = book.add(contact);
            System.out.println((isAdd ? " + Добавлен: " : " - Ну удалось добавить: ") + contact + ".");
        }

        System.out.println();
        System.out.println("Попробуем добавить имеющийся контакт:");
        Contact doubleContact = new Contact("Валерий", "Бондарчук", "Федотов", List.of("5555", "6666"));
        boolean isAddDouble = book.add(doubleContact);
        System.out.println((isAddDouble ? " + Добавлен: " : " - Ну удалось добавить: ") + doubleContact + ".");

        String lastName = "Исаев";
        System.out.println();
        System.out.println("Список контактов по фамилии \"" + lastName + "\":");
        Set<Contact> findContacts = book.findByLastName(lastName);
        for (Contact contact : findContacts) {
            System.out.println(" * " + contact + ".");
        }

        String phoneNumber = "7777";
        System.out.println();
        System.out.println("Контакт с номером телефона \"" + phoneNumber + "\":");
        boolean isContains = book.containsPhoneNumber(phoneNumber);
        System.out.println(isContains ? " * " + book.findByPhoneNumber(phoneNumber) + "." : " * Контакт не найден!");

        String emptyPhoneNumber = "77";
        System.out.println();
        System.out.println("Попробуем найти не имеющийся контакт \"" + emptyPhoneNumber + "\":");
        boolean isEmptyContains = book.containsPhoneNumber(emptyPhoneNumber);
        System.out.println(isEmptyContains ? " * " + book.findByPhoneNumber(phoneNumber) + "." : " * Контакт не найден!");
    }
}

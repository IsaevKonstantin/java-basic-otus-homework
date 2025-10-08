package ru.otus.java.basic.hw10.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contact {
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final List<String> phoneNumbers;

    public String getLastName() {
        return lastName;
    }

    public List<String> getPhoneNumbers() {
        List<String> copyPhoneNumbers = new ArrayList<>(phoneNumbers.size());
        copyPhoneNumbers.addAll(phoneNumbers);
        return copyPhoneNumbers;
    }

    public Contact(String firstName, String lastName, String patronymic, List<String> phoneNumbers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + patronymic + ", " + (phoneNumbers.size() > 1 ? "номера телефонов: " : "номер телефона: ") + phoneNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(firstName, contact.firstName) && Objects.equals(lastName, contact.lastName) && Objects.equals(patronymic, contact.patronymic) && Objects.equals(phoneNumbers, contact.phoneNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, patronymic, phoneNumbers);
    }
}

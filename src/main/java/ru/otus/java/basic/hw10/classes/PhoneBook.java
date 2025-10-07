package ru.otus.java.basic.hw10.classes;

import java.util.*;

public class PhoneBook {
    private final Map<String, Contact> phoneBook = new HashMap<>();
    private final Map<String, String> keyMap = new HashMap<>();

    public boolean add(Contact contact) {
        if (phoneBook.containsValue(contact)) return false;
        for (String phoneNumber : contact.getPhoneNumbers()) {
            phoneBook.putIfAbsent(contact.getLastName() + " " + phoneNumber, contact);
            keyMap.putIfAbsent(phoneNumber, contact.getLastName());
        }
        return true;
    }

    public Set<Contact> findByLastName(String lastName) {
        Set<Contact> contacts = new HashSet<>();
        if (lastName != null && keyMap.containsValue(lastName)) {
            for (String phoneNumber : keyMap.keySet()) {
                if (phoneBook.containsKey(lastName + " " + phoneNumber)) {
                    contacts.add(phoneBook.get(lastName + " " + phoneNumber));
                }
            }
        }
        return contacts;
    }

    public Contact findByPhoneNumber(String phoneNumber) {
        return phoneBook.get(keyMap.get(phoneNumber) + " " + phoneNumber);
    }

    public boolean containsPhoneNumber(String phoneNumber) {
        return keyMap.containsKey(phoneNumber);
    }
}

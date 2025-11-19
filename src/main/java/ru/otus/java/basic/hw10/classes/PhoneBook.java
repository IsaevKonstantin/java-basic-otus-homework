package ru.otus.java.basic.hw10.classes;

import java.util.*;

public class PhoneBook {
    private final Map<String, Contact> phoneBookByNumber = new HashMap<>();
    private final Map<String, List<Contact>> lastNameToContacts = new HashMap<>();

    public boolean add(Contact contact) {
        List<Contact> contacts = lastNameToContacts.computeIfAbsent(contact.getLastName(), k -> new ArrayList<>());
        if (contacts.contains(contact)) return false;
        contacts.add(contact);
        for (String phoneNumber : contact.getPhoneNumbers()) {
            phoneBookByNumber.put(phoneNumber, contact);
        }
        return true;
    }

    public List<Contact> findByLastName(String lastName) {
        return lastNameToContacts.getOrDefault(lastName, new ArrayList<>());
    }

    public Contact findByPhoneNumber(String phoneNumber) {
        return phoneBookByNumber.get(phoneNumber);
    }

    public boolean containsPhoneNumber(String phoneNumber) {
        return phoneBookByNumber.containsKey(phoneNumber);
    }
}

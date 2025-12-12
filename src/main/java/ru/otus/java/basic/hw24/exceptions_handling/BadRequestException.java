package ru.otus.java.basic.hw24.exceptions_handling;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}

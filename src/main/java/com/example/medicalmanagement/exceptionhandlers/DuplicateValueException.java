package com.example.medicalmanagement.exceptionhandlers;

public class DuplicateValueException extends RuntimeException {
    public DuplicateValueException(String message) {
        super(message);
    }
}

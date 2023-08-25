package com.example.medicalmanagement.exceptionhandlers;

public class InvalidUserDataException extends RuntimeException {
    public InvalidUserDataException(String message) {
        super(message);
    }
}
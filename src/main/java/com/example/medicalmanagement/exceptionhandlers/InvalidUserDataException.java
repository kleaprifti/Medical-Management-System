package com.example.medicalmanagement.exceptionhandlers;

public class InvalidUserDataException extends Throwable {
    public InvalidUserDataException(String message) {
        super(message);
    }
}
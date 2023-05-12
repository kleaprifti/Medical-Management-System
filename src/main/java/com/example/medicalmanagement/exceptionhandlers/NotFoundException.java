package com.example.medicalmanagement.exceptionhandlers;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}

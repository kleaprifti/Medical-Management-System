package com.example.medicalmanagement.exceptionhandlers;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(String message) {
        super(message);
    }
}

package com.example.medicalmanagement.exceptionhandlers;

public class DurationException extends RuntimeException{
    public DurationException(String message) {
        super(message);
    }
}

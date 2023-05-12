package com.example.medicalmanagement.exceptionhandlers;

public class SamePersonException extends RuntimeException{
    public SamePersonException(String message) {
        super(message);
    }
}

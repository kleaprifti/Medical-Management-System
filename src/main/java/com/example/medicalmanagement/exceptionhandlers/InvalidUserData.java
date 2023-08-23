package com.example.medicalmanagement.exceptionhandlers;

public class InvalidUserData extends Throwable {
    public InvalidUserData(String message) {
        super(message);
    }
}
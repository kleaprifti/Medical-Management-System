package com.example.medicalmanagement.exceptionhandlers;

public class RoleException extends RuntimeException{
    public RoleException(String message) {
        super(message);
    }
}

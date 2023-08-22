package com.example.medicalmanagement.exceptionhandlers;

public class UserAdditionException extends RuntimeException{
    public UserAdditionException(String message) {
        super(message);
    }

}

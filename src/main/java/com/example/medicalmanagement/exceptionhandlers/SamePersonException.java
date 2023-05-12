package com.example.medicalmanagement.exceptionhandlers;

import lombok.Data;

@Data
public class SamePersonException extends RuntimeException{
    public SamePersonException(String message) {
        super(message);
    }
}

package com.example.medicalmanagement.exceptionhandlers;

import lombok.Data;

@Data
public class DurationException extends RuntimeException{
    public DurationException(String message) {
        super(message);
    }
}

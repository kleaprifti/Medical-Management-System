package com.example.medicalmanagement.exceptionhandlers;

import lombok.Data;

@Data
public class TimeException extends RuntimeException {
    public TimeException(String message) {
        super(message);
    }
}

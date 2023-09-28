package com.example.medicalmanagement.exceptionhandlers;

public class DoctorNotAvailableException extends Exception {
    public DoctorNotAvailableException(String message) {
        super(message);
    }

}

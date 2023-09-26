package com.example.medicalmanagement.exceptionhandlers;

public class DoctorOnHolidayException extends Exception {
    public DoctorOnHolidayException(String message) {
        super(message);
    }

}
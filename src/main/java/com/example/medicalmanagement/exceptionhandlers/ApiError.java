package com.example.medicalmanagement.exceptionhandlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiError {
    private HttpStatus httpStatus;
    private String message;
}

package com.example.medicalmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.medicalmanagement.stepDefinitions")
public class MedicalManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalManagementSystemApplication.class, args);

	}

}
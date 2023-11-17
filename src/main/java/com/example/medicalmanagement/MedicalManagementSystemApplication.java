package com.example.medicalmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = "com.example.medicalmanagement")
public class MedicalManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalManagementSystemApplication.class, args);

	}

}
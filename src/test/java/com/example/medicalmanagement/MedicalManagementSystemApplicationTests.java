package com.example.medicalmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.example.medicalmanagement.repository")
@SpringBootTest
class MedicalManagementSystemApplicationTests {

	@Test
	void contextLoads() {
	}
}

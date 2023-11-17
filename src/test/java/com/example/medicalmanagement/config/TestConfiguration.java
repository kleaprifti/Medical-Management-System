package com.example.medicalmanagement.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

@org.springframework.boot.test.context.TestConfiguration
@TestPropertySource
public class TestConfiguration {
    @Bean
    public ModelMapper testModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public TestRestTemplate restTemplate(RestTemplateBuilder builder) {
        return new TestRestTemplate(builder);
    }
}

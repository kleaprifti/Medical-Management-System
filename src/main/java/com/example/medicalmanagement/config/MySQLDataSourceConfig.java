package com.example.medicalmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
@Profile("mysql")
@Configuration
public class MySQLDataSourceConfig {

    @Bean
    public DataSource dataSource() {
        // Configure and return MySQL data source
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/medical_management_system");
        dataSource.setUsername("root");
        dataSource.setPassword("Athina21!");
        return dataSource;
    }
}

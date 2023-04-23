package com.example.medicalmanagement.repository;

import com.example.medicalmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}

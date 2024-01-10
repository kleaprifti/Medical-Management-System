package com.example.medicalmanagement.repository;

import com.example.medicalmanagement.model.PersistentLogin;
import com.example.medicalmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersistentLoginRepository extends JpaRepository<PersistentLogin, Long> {

    Optional<PersistentLogin> findBySeries(String series);

    void deleteBySeries(String series);

    Optional<PersistentLogin> findByUser(User user);
}

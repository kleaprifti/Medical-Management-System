package com.example.medicalmanagement.repository;

import com.example.medicalmanagement.model.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {
    ContactInfo findByEmail(String email);
    List<ContactInfo> findAll();

}

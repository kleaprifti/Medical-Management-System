package com.example.medicalmanagement.repository;

import com.example.medicalmanagement.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<Speciality,Long> {
    Speciality findByName(String specialityName);
}

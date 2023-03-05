package com.example.Medical.Management.System.repository;

import com.example.Medical.Management.System.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}

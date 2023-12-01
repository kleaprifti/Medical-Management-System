package com.example.medicalmanagement.repository;

import com.example.medicalmanagement.model.UserDetails;
import com.example.medicalmanagement.model.UserRole;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
        List<UserDetails> findByRolesUserRole(UserRole role, Sort sort);
        Optional<UserDetails> findByIdAndRolesUserRole(Long id, UserRole role);

        UserDetails findByFullNameAndBirthDateAndIdMedicalCard(String fullName, LocalDate birthDate, String idMedicalCard);


}
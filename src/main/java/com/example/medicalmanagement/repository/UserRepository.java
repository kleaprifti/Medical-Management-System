package com.example.medicalmanagement.repository;

import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.model.UserRole;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        List<User> findByRolesUserRole(UserRole role, Sort sort);
        Optional<User> findByIdAndRolesUserRole(Long id, UserRole role);

        User findByFullNameAndBirthDateAndIdMedicalCard(String fullName, LocalDate birthDate, String idMedicalCard);


}
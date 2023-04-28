package com.example.medicalmanagement.repository;

import com.example.medicalmanagement.model.Role;
import com.example.medicalmanagement.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByUserRole(UserRole userRole);
}

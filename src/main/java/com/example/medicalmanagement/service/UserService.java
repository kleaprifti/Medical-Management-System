package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.SpecialityDto;
import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.Role;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.model.UserRole;
import com.example.medicalmanagement.repository.RoleRepository;
import com.example.medicalmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    public List<UserDto> getAllDoctors() {
        Role doctorRole = roleRepository.findByUserRole(UserRole.DOCTOR); // find the Role entity with UserRole.DOCTOR
        List<User> doctorUsers = userRepository.findByRole(doctorRole); // find all User entities with the doctorRole
        return mapToDto(doctorUsers);
    }

    private List<UserDto> mapToDto(List<User> users) {
        return users.stream()
                .map(user -> new UserDto(user.getId(), user.getFullName(), user.getRole(),
                        user.getSpecialities().stream()
                                .map(speciality -> new SpecialityDto(speciality.getName()))
                                .toList()))
                .toList();
    }
}
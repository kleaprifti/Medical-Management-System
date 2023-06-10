package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.model.UserRole;
import com.example.medicalmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.medicalmanagement.model.Speciality;
import com.example.medicalmanagement.model.Role;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllDoctors() {
    return userRepository.findByRolesUserRole(UserRole.DOCTOR)
            .stream()
            .filter(user -> user.getRoles()
                    .stream()
                    .anyMatch(role -> role.getUserRole() == UserRole.DOCTOR))
            .map(this::mapToDto)
            .toList();
    }

    private UserDto mapToDto(User user) {
        return new UserDto(user.getId(), user.getFullName(),
                user.getRoles()
                        .stream()
                        .map(Role::getUserRole)
                        .toList(),
                user.getSpecialities()
                        .stream()
                        .map(Speciality::getName)
                        .toList());
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

}
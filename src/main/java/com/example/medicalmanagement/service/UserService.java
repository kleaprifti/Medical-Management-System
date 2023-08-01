package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.*;
import com.example.medicalmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllDoctors() {
       Sort sort = Sort.by(Sort.Direction.ASC, "fullName");

        return userRepository.findByRolesUserRole(UserRole.DOCTOR,sort)
            .stream()
            .filter(user -> user.getRoles()
                    .stream()
                    .anyMatch(role -> role.getUserRole() == UserRole.DOCTOR))
            .map(this::mapToDto)
            .toList();
    }

    private UserDto mapToDto(User user) {
        List<NotificationType> notificationTypes = new ArrayList<>();
        if (user.getNotificationTypes() != null) {
            notificationTypes = user.getNotificationTypes().stream()
                    .map(UserNotificationType::getNotificationType)
                    .collect(Collectors.toList());
        }
        return new UserDto(user.getId(), user.getEmail() ,user.getFullName(),
                user.getRoles()
                        .stream()
                        .map(Role::getUserRole)
                        .toList(),
                user.getSpecialities()
                        .stream()
                        .map(Speciality::getName)
                        .toList(),notificationTypes);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
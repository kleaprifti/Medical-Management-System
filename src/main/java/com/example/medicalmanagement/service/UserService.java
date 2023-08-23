package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.*;
import com.example.medicalmanagement.repository.RoleRepository;
import com.example.medicalmanagement.repository.SpecialityRepository;
import com.example.medicalmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SpecialityRepository specialityRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<UserDto> getAllUsers(UserRole userRole) {
        Sort sort = Sort.by(Sort.Direction.ASC, "fullName");

        return userRepository.findByRolesUserRole(userRole, sort)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public UserDto mapToDto(User user) {
        List<NotificationType> notificationTypes = new ArrayList<>();
        if (!user.getNotificationTypes().isEmpty()) {
            notificationTypes = user.getNotificationTypes().stream()
                    .map(UserNotificationType::getNotificationType)
                    .toList();
        }
        return new UserDto(user.getId(), user.getEmail(), user.getFullName(),
                user.getBirthDate(), user.getPhoneNumber(), user.getIdMedicalCard(),
                user.getRoles()
                        .stream()
                        .map(Role::getUserRole)
                        .toList(),
                user.getSpecialities()
                        .stream()
                        .map(Speciality::getName)
                        .toList(), notificationTypes);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public boolean addUser(UserDto userDto) {
        try {
            if (!validateUserDto(userDto)) {
                return false;
            }

            User newUser = new User();
            newUser.setFullName(userDto.getFullName());
            newUser.setBirthDate(userDto.getBirthDate());
            newUser.setPhoneNumber(userDto.getPhoneNumber());
            newUser.setIdMedicalCard(userDto.getIdMedicalCard());

            List<Role> userRoles = userDto.getRoles().stream()
                    .map(roleRepository::findByUserRole)
                    .toList();

            newUser.setRoles(userRoles);

            boolean isDoctor = userRoles.stream().anyMatch(role -> role.getUserRole() == UserRole.DOCTOR);
            if (isDoctor) {
                List<Speciality> userSpecialities = userDto.getSpecialities().stream()
                        .map(specialityRepository::findByName)
                        .toList();

                newUser.setSpecialities(userSpecialities);
            }

            userRepository.save(newUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validateUserDto(UserDto userDto) {
        if (userDto == null) {
            return false;
        }

        if (userDto.getFullName() == null || userDto.getFullName().isEmpty() ||
                userDto.getBirthDate() == null || userDto.getBirthDate().isAfter(LocalDate.now()) ||
                userDto.getPhoneNumber() == null || userDto.getIdMedicalCard() == null || userDto.getIdMedicalCard().isEmpty() || userDto.getIdMedicalCard().length() != 16 ||
                userDto.getRoles() == null || userDto.getRoles().isEmpty()) {
            return false;
        }

        for (UserRole userRole : userDto.getRoles()) {
            if (userRole != UserRole.DOCTOR && userRole != UserRole.PATIENT) {
                return false;
            }

            if (userRole == UserRole.DOCTOR && (userDto.getSpecialities() == null || userDto.getSpecialities().isEmpty())) {
                return false;

            }
        }
        return true;
    }

}
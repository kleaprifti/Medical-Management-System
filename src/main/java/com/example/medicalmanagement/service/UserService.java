package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.*;
import com.example.medicalmanagement.repository.RoleRepository;
import com.example.medicalmanagement.repository.SpecialityRepository;
import com.example.medicalmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        if (userRole != null) {
            return userRepository.findByRolesUserRole(userRole, sort)
                    .stream()
                    .map(this::mapToDto)
                    .toList();
        } else return userRepository.findAll(sort).stream().map(this::mapToDto).toList();
    }

    public UserDto mapToDto(User user) {
        List<NotificationType> notificationTypes = new ArrayList<>();
        if (!user.getNotificationTypes().isEmpty()) {
            notificationTypes = user.getNotificationTypes().stream()
                    .map(UserNotificationType::getNotificationType)
                    .toList();
        }

        boolean emailSent = true;
        return new UserDto(user.getId(), user.getFullName(),
                user.getBirthDate(), user.getIdMedicalCard(), user.getContactInfo(),
                user.getRoles()
                        .stream()
                        .map(Role::getUserRole)
                        .toList(),
                user.getSpecialities()
                        .stream()
                        .map(Speciality::getName)
                        .toList(), emailSent, notificationTypes);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public boolean addUser(UserDto userDto) {
        User newUser = new User();
        newUser.setContactInfo(userDto.getContactInfo());
        newUser.setFullName(userDto.getFullName());
        newUser.setBirthDate(userDto.getBirthDate());
        newUser.setIdMedicalCard(userDto.getIdMedicalCard());

        List<Role> userRoles = userDto.getRoles().stream()
                .map(roleRepository::findByUserRole)
                .toList();

        newUser.setRoles(userRoles);

        boolean isDoctor = userRoles.stream().anyMatch(role -> role.getUserRole() == UserRole.DOCTOR);
        if (isDoctor) {
            List<Speciality> validSpecialities = new ArrayList<>();
            for (String specialityName : userDto.getSpecialities()) {
                Speciality speciality = specialityRepository.findByName(specialityName);
                if (speciality == null) {
                    speciality = new Speciality();
                    speciality.setName(specialityName);
                    speciality = specialityRepository.save(speciality);
                }
                validSpecialities.add(speciality);
            }

            newUser.setSpecialities(validSpecialities);
        } else if (userRoles.stream().anyMatch(role -> role.getUserRole() == UserRole.PATIENT)) {
            newUser.setSpecialities(new ArrayList<>());
        }

        userRepository.save(newUser);
        return true;
    }
}
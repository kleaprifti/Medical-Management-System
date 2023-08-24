package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.exceptionhandlers.DuplicateValueException;
import com.example.medicalmanagement.exceptionhandlers.InvalidUserDataException;
import com.example.medicalmanagement.exceptionhandlers.RoleException;
import com.example.medicalmanagement.exceptionhandlers.SpecialityException;
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
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SpecialityRepository specialityRepository;

    @Autowired
    private RoleRepository roleRepository;
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

    public List<UserDto>getAllPatients(){
        Sort sort = Sort.by(Sort.Direction.ASC, "fullName");
        return userRepository.findByRolesUserRole(UserRole.PATIENT,sort)
                .stream()
                .filter(user -> user.getRoles()
                        .stream()
                        .anyMatch(role -> role.getUserRole() == UserRole.PATIENT))
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
        boolean emailSent = true;
        return new UserDto(user.getId(), user.getEmail() ,user.getFullName(),
                user.getBirthDate(),user.getPhoneNumber(), user.getIdMedicalCard() ,
                user.getRoles()
                        .stream()
                        .map(Role::getUserRole)
                        .toList(),
                user.getSpecialities()
                        .stream()
                        .map(Speciality::getName)
                        .toList() , emailSent, notificationTypes);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public boolean addUser(UserDto userDto) {
            validateUserDto(userDto);
            User newUser = new User();
            newUser.setEmail(userDto.getEmail());
            newUser.setFullName(userDto.getFullName());
            newUser.setBirthDate(userDto.getBirthDate());
            newUser.setPhoneNumber(userDto.getPhoneNumber());
            newUser.setIdMedicalCard(userDto.getIdMedicalCard());

            List<Role> userRoles = userDto.getRoles().stream()
                    .map(roleRepository::findByUserRole)
                    .collect(Collectors.toList());

            newUser.setRoles(userRoles);

            boolean isDoctor = userRoles.stream().anyMatch(role -> role.getUserRole() == UserRole.DOCTOR);
             if (isDoctor) {
                 List<Speciality> userSpecialities = userDto.getSpecialities().stream()
                         .map(specialityRepository::findByName)
                         .collect(Collectors.toList());

                 newUser.setSpecialities(userSpecialities);
             }else if (userRoles.stream().anyMatch(role -> role.getUserRole() == UserRole.PATIENT)) {
                 newUser.setSpecialities(new ArrayList<>());
             }
        userRepository.save(newUser);
        return true;

    }

   private void validateUserDto(UserDto userDto) throws InvalidUserDataException, RoleException, SpecialityException, DuplicateValueException {
    validateDuplicateValues(userDto);
    validateRequiredFields(userDto);
    validateBirthDate(userDto);
    validateMedicalCardId(userDto);
    validateUserRolesAndSpecialities(userDto);
    }

    private void validateDuplicateValues(UserDto userDto) throws DuplicateValueException {
        if (userRepository.existsByIdMedicalCard(userDto.getIdMedicalCard())) {
            throw new DuplicateValueException("Duplicate record found for medical card ID.");
        }
        if (userRepository.existsByEmailOrPhoneNumber(userDto.getEmail(), userDto.getPhoneNumber())) {
            throw new DuplicateValueException("Duplicate record found for email or phone number.");
        }
    }

    private void validateRequiredFields(UserDto userDto) throws InvalidUserDataException {
        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()
                || userDto.getFullName() == null || userDto.getFullName().isEmpty()
                || userDto.getPhoneNumber() == null || userDto.getPhoneNumber().isEmpty()
                || userDto.getIdMedicalCard() == null || userDto.getIdMedicalCard().isEmpty()
                || userDto.getRoles() == null || userDto.getRoles().isEmpty()) {
            throw new InvalidUserDataException("Please provide values for all required fields: email, full name, phone number, medical card ID, and roles.");
        }
    }

    private void validateBirthDate(UserDto userDto) throws InvalidUserDataException {
        if (userDto.getBirthDate().isAfter(LocalDate.now())) {
            throw new InvalidUserDataException("Birthdate should be a date in the past.");
        }
    }

    private void validateMedicalCardId(UserDto userDto) throws InvalidUserDataException {
        if (userDto.getIdMedicalCard().length() != 16) {
            throw new InvalidUserDataException("Medical card ID must consist of exactly 16 characters.");
        }
    }

    private void validateUserRolesAndSpecialities(UserDto userDto) throws RoleException, SpecialityException {
        for (UserRole userRole : userDto.getRoles()) {
            if (userRole != UserRole.DOCTOR && userRole != UserRole.PATIENT) {
                throw new RoleException("Invalid user role specified.");
            }
            if (userRole == UserRole.DOCTOR && (userDto.getSpecialities() == null || userDto.getSpecialities().isEmpty())) {
                throw new SpecialityException("Doctors must be associated with at least one speciality.");
            }
            if (userRole == UserRole.PATIENT && (userDto.getSpecialities() != null && !userDto.getSpecialities().isEmpty())) {
                throw new SpecialityException("Patients cannot be associated with specialities.");
            }
        }
    }

}
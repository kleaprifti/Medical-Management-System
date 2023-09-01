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
        }else return userRepository.findAll(sort).stream().map(this::mapToDto).toList();
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
                user.getBirthDate(),user.getIdMedicalCard(),user.getContactInfo(),
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
        validateUserDto(userDto);
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
        if (userRepository.existsByContactInfo_PhoneNumber(userDto.getContactInfo().getPhoneNumber())) {
            throw new DuplicateValueException("Duplicate record found for phone number.");
        }
        if (userRepository.existsByContactInfo_Email(userDto.getContactInfo().getEmail())) {
            throw new DuplicateValueException("Duplicate record found for email.");
        }
    }

    private void validateRequiredFields(UserDto userDto) throws InvalidUserDataException {
        if (userDto.getContactInfo().getEmail() == null || userDto.getContactInfo().getEmail().isEmpty()
                || userDto.getFullName() == null || userDto.getFullName().isEmpty()
                || userDto.getContactInfo().getPhoneNumber() == null || userDto.getContactInfo().getPhoneNumber().isEmpty()
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
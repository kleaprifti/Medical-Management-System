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

    public void addUser(UserDto userDto)  throws InvalidUserDataException, RoleException, SpecialityException, DuplicateValueException{
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
            }

            userRepository.save(newUser);

    }

    private void validateUserDto(UserDto userDto)  throws InvalidUserDataException, RoleException, SpecialityException, DuplicateValueException {
        if (userRepository.existsByIdMedicalCard(userDto.getIdMedicalCard())) {
            throw new DuplicateValueException("Duplicate idMedicalCard");
        }
        if (userRepository.existsByEmailOrPhoneNumber((userDto.getEmail()), (userDto.getPhoneNumber()))) {
            throw new DuplicateValueException("Duplicate email or phone number");
        }
        if (userDto.getEmail() == null || userDto.getEmail().isEmpty() ||
                userDto.getFullName() == null || userDto.getFullName().isEmpty() ||
                userDto.getPhoneNumber() == null || userDto.getPhoneNumber().isEmpty() ||
                userDto.getIdMedicalCard() == null || userDto.getIdMedicalCard().isEmpty() ||
                userDto.getRoles() == null || userDto.getRoles().isEmpty())
            throw new InvalidUserDataException("The parameter is empty");
        if (userDto.getBirthDate().isAfter(LocalDate.now()))
            throw new InvalidUserDataException("You have put the wrong birthdate because is a past date");
        if (userDto.getIdMedicalCard().length() != 16)
            throw new InvalidUserDataException("Medical Card Id must be exactly with 16 characters");
        for (UserRole userRole : userDto.getRoles()) {
            if (userRole != UserRole.DOCTOR && userRole != UserRole.PATIENT) {
                throw new RoleException("Invalid user role");
            }
            if (userRole == UserRole.DOCTOR && (userDto.getSpecialities() == null || userDto.getSpecialities().isEmpty())) {
                throw new SpecialityException("Doctors must have specialities");

            }
        }
    }
}
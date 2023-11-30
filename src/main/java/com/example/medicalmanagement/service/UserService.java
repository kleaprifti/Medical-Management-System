package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.*;
import com.example.medicalmanagement.repository.RoleRepository;
import com.example.medicalmanagement.repository.SpecialityRepository;
import com.example.medicalmanagement.repository.UserDetailsRepository;
import com.example.medicalmanagement.validator.UserValidator;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private UserDetailsRepository userDetailsRepository;
    private SpecialityRepository specialityRepository;
    private UserValidator userValidator;
    private RoleRepository roleRepository;

    public UserService(UserDetailsRepository userDetailsRepository, SpecialityRepository specialityRepository, UserValidator userValidator, RoleRepository roleRepository) {
        this.userDetailsRepository = userDetailsRepository;
        this.specialityRepository = specialityRepository;
        this.userValidator = userValidator;
        this.roleRepository = roleRepository;
    }

    public List<UserDto> getAllUsers(UserRole userRole) {
        Sort sort = Sort.by(Sort.Direction.ASC, "fullName");
        if (userRole != null) {
            return userDetailsRepository.findByRolesUserRole(userRole, sort)
                    .stream()
                    .map(this::mapToDto)
                    .toList();
        } else return userDetailsRepository.findAll(sort).stream().map(this::mapToDto).toList();
    }

    public UserDto mapToDto(UserDetails user) {
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
        userDetailsRepository.deleteAll();
    }

    public boolean addUser(UserDto userDto) {
        UserDetails newUser = new UserDetails();
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

        userDetailsRepository.save(newUser);
        return true;
    }

    public boolean isDoctorAvailable(Long doctorId, LocalDateTime startTime, LocalDateTime endTime) {
        Optional<UserDetails> optionalDoctor = userDetailsRepository.findByIdAndRolesUserRole(doctorId, UserRole.DOCTOR);

        if (optionalDoctor.isPresent()) {
            UserDetails doctor = optionalDoctor.get();
            LocalDate date = startTime.toLocalDate();

            boolean isOnHoliday = userValidator.isDoctorOnHoliday(doctor, date);

            if (isOnHoliday) {
                return false;
            } else {
                return userValidator.isDoctorAvailableInTimeRange(doctor, startTime, endTime);
            }
        } else {
            return false;
        }

    }


}
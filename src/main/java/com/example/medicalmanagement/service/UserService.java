package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.*;
import com.example.medicalmanagement.repository.RoleRepository;
import com.example.medicalmanagement.repository.SpecialityRepository;
import com.example.medicalmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    public Optional<String> checkDoctorAvailability(Long doctorId, LocalDateTime startTime, LocalDateTime endTime) {
      Optional<User> optionalDoctor = userRepository.findByIdAndRolesUserRole(doctorId, UserRole.DOCTOR);

    if (optionalDoctor.isPresent()) {
        User doctor = optionalDoctor.get();
        DayOfWeek workday = startTime.getDayOfWeek();
        boolean isAvailable = isDoctorAvailableInTimeRange(doctor, startTime, endTime);

        return Optional.of(isAvailable
                ? "Doctor is available in the specified time range on " + workday + "."
                : "Doctor is not available in the specified time range on " + workday + ".");
    } else {
        return Optional.of("Doctor not found");
    }
}

    private boolean isDoctorAvailableInTimeRange(User doctor, LocalDateTime startTime, LocalDateTime endTime) {
        List<DoctorAvailability> availabilitySchedule = doctor.getDoctorAvailabilities();

        return availabilitySchedule.stream()
                .filter(availability -> availability.getWorkingDays().contains(startTime.getDayOfWeek()))
                .anyMatch(availability -> isTimeRangeOverlap(availability.getStartTime(), availability.getEndTime(), startTime, endTime));

    }

    private boolean isTimeRangeOverlap(LocalTime start1, LocalTime end1, LocalDateTime start2, LocalDateTime end2) {
        return !start1.isAfter(end2.toLocalTime()) && !start2.toLocalTime().isAfter(end1);
    }
}
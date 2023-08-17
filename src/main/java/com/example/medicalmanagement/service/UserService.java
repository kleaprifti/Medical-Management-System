package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.Role;
import com.example.medicalmanagement.model.Speciality;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.model.UserRole;
import com.example.medicalmanagement.repository.SpecialityRepository;
import com.example.medicalmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SpecialityRepository specialityRepository;

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
        return new UserDto(user.getId(), user.getEmail() ,user.getFullName(),
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

    public boolean addUser(UserDto userDto) {
        try {
            if (!validateInputParameters(userDto)) {
                return false;
            }

            User user = mapToUser(userDto);

            userRepository.save(user);

            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean validateInputParameters(UserDto userDto) {

        if (userDto.getFullName() == null || userDto.getFullName().isEmpty() ||
                userDto.getBirthDate() == null || userDto.getBirthDate().isEmpty() ||
                userDto.getPhoneNumber() == null || userDto.getPhoneNumber().isEmpty() ||
                (userDto.getRoles() != null && userDto.getRoles().contains(UserRole.DOCTOR) && userDto.getSpecialities().isEmpty()) ||
                userDto.getIdMedicalCard() == null || userDto.getIdMedicalCard().isEmpty()) {
            return false;
        }

        return true;
    }

    private User mapToUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setBirthDate(userDto.getBirthDate());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setIdMedicalCard(userDto.getIdMedicalCard());

        List<Role> roles = new ArrayList<>();
        if (userDto.getRoles() != null) {
            roles = userDto.getRoles().stream()
                    .map(role -> new Role(role))
                    .collect(Collectors.toList());
        }
        user.setRoles(roles);

        List<Speciality> specialities = new ArrayList<>();
        if (userDto.getSpecialities() != null) {
            specialities = userDto.getSpecialities().stream()
                    .map(specialityName -> {
                        Speciality speciality = specialityRepository.findByName(specialityName);
                        if (speciality == null) {
                            speciality = new Speciality(specialityName);
                            specialityRepository.save(speciality);
                        }
                        return speciality;
                    })
                    .collect(Collectors.toList());
        }
        user.setSpecialities(specialities);

        return user;
    }

}
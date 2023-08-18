package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.Role;
import com.example.medicalmanagement.model.Speciality;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.model.UserRole;
import com.example.medicalmanagement.repository.RoleRepository;
import com.example.medicalmanagement.repository.SpecialityRepository;
import com.example.medicalmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
                userDto.getPhoneNumber() == null || userDto.getIdMedicalCard() == null || userDto.getIdMedicalCard().isEmpty() ||
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
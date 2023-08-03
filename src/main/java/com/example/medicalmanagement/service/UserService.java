package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.model.UserRole;
import com.example.medicalmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.medicalmanagement.model.Speciality;
import com.example.medicalmanagement.model.Role;
import java.util.List;


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

    String fullName = userDto.getFullName();
    String birthDate = userDto.getBirthDate();
    String phoneNumber = userDto.getPhoneNumber();
    String idMedicalCard = userDto.getIdMedicalCard();
    List<String> specialities = userDto.getSpecialities();

    if (fullName == null || fullName.isEmpty()
            || birthDate == null || birthDate.isEmpty()
            || phoneNumber == null || phoneNumber.isEmpty()
            || idMedicalCard == null || idMedicalCard.length() != 16) {
        return false;
    }

    try {
        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.setBirthDate(birthDate);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setIdMedicalCard(idMedicalCard);

        if (specialities != null && !specialities.isEmpty()) {
            newUser.getSpecialities();

            newUser.getRoles().clear();
            newUser.setRoles((List<Role>) new Role(UserRole.DOCTOR));
        } else {
            newUser.getRoles().clear();
            newUser.setRoles((List<Role>) new Role(UserRole.PATIENT));
        }
        userRepository.save(newUser);

        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    }
}
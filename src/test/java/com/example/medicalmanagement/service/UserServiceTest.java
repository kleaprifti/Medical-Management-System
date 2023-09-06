package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.*;
import com.example.medicalmanagement.repository.RoleRepository;
import com.example.medicalmanagement.repository.SpecialityRepository;
import com.example.medicalmanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Mock
    private UserRepository userRepository;
    @Mock
    private SpecialityRepository specialityRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService;

    private List<User> mockUsers;

    @BeforeEach
    public void setup() {
    }


    @Test
     void getAllUsersForDoctors() {
        List<User> doctorUsers = new ArrayList<>();
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        doctorUsers.add(user1);
        doctorUsers.add(user2);

        when(userRepository.findByRolesUserRole(UserRole.DOCTOR, Sort.by(Sort.Direction.ASC, "fullName")))
                .thenReturn(doctorUsers);

        List<UserDto> doctorDtos = userService.getAllUsers(UserRole.DOCTOR);

        assertEquals(doctorUsers.size(), doctorDtos.size());

    }

    @Test
     void getAllUsersForPatients() {
        List<User> patientUsers = new ArrayList<>();
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        patientUsers.add(user1);
        patientUsers.add(user2);

        when(userRepository.findByRolesUserRole(UserRole.PATIENT, Sort.by(Sort.Direction.ASC, "fullName")))
                .thenReturn(patientUsers);

        List<UserDto> patientDtos = userService.getAllUsers(UserRole.PATIENT);

        assertEquals(patientUsers.size(), patientDtos.size());

    }




    @Test
    void deleteAllUsers() {
        userService.deleteAllUsers();

        verify(userRepository, times(1)).deleteAll();
    }


    @Test
    void addUser() {
        MockitoAnnotations.initMocks(this);

        UserDto userDto = createUserDto();


        Mockito.when(roleRepository.findByUserRole(any(UserRole.class))).thenReturn(createRole());
        Mockito.when(specialityRepository.findByName(anyString())).thenReturn(createSpeciality());

        userDto.setRoles(Collections.singletonList(UserRole.DOCTOR));
        userDto.setSpecialities(Collections.singletonList("Cardiology"));

        userService.addUser(userDto);

        Mockito.verify(userRepository, times(1)).save(any(User.class));
    }


    private UserDto createUserDto() {
        UserDto userDto = new UserDto();
        User user = new User();
        ContactInfo contactInfo = new ContactInfo(2L,"aldoshehu@gmail.com","97327","aldius",user);
        userDto.setFullName("Aldo Shehu");
        userDto.setBirthDate(LocalDate.of(1998, 11, 17));
        userDto.setContactInfo(contactInfo);
        userDto.setIdMedicalCard("1234567890123456");
        userDto.setRoles(Collections.singletonList(UserRole.DOCTOR));
        return userDto;
    }

    private Role createRole() {
        Role role = new Role();
        role.setUserRole(UserRole.DOCTOR);
        return role;
    }

    private Speciality createSpeciality() {
        return new Speciality();
    }
}
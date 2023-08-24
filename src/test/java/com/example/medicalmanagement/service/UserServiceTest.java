package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.exceptionhandlers.DuplicateValueException;
import com.example.medicalmanagement.exceptionhandlers.InvalidUserDataException;
import com.example.medicalmanagement.exceptionhandlers.RoleException;
import com.example.medicalmanagement.exceptionhandlers.SpecialityException;
import com.example.medicalmanagement.model.*;
import com.example.medicalmanagement.repository.RoleRepository;
import com.example.medicalmanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Arrays;
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
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService;

    private List<User> mockUsers;

    @BeforeEach
    public void setup() {
        Speciality speciality1 = new Speciality(1L,  "Speciality 1", null);
        Speciality speciality2 = new Speciality(2L,  "Speciality 2",null);
        Role role = new Role();
        role.setUserRole(UserRole.DOCTOR);
        Role role1 = new Role();
        role1.setUserRole(UserRole.PATIENT);
        UserNotificationType userNotificationType = new UserNotificationType();
        userNotificationType.setNotificationType(NotificationType.WHATSAPP);

        User user1 = new User();
        user1.setId(1L);
        user1.setEmail("doctor1@gmail.com");
        user1.setFullName("Doctor 1");
        user1.setRoles(Collections.singletonList(role));
        user1.setSpecialities(Collections.singletonList(speciality1));
        user1.setNotificationTypes(Collections.singletonList(userNotificationType));

        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("doctor2@gmail.com");
        user2.setFullName("Doctor 2");
        user2.setRoles(Collections.singletonList(role));
        user2.setSpecialities(Collections.singletonList(speciality2));
        user2.setNotificationTypes(Collections.singletonList(userNotificationType));

        User user3 = new User();
        user3.setId(3L);
        user3.setEmail("patient1@gmail.com");
        user3.setFullName("Patient 1");
        user3.setRoles(Collections.singletonList(role1));
        user3.setSpecialities(Collections.singletonList(speciality2));
        user3.setNotificationTypes(Collections.singletonList(userNotificationType));

        User user4 = new User();
        user4.setId(4L);
        user4.setEmail("patient2@gmail.com");
        user4.setFullName("Patient 2");
        user4.setRoles(Collections.singletonList(role1));
        user4.setSpecialities(Collections.singletonList(speciality2));
        user4.setNotificationTypes(Collections.singletonList(userNotificationType));

        mockUsers = Arrays.asList(user1, user2, user3, user4);
    }


    @Test
    void getAllDoctors() {
        when(userRepository.findByRolesUserRole(any(), any())).thenReturn(mockUsers);

        List<UserDto> doctorDtos = userService.getAllDoctors();
        assertEquals(2, doctorDtos.size());

        UserDto doctorDto1 = doctorDtos.get(0);
        assertEquals(1L, doctorDto1.getId());
        assertEquals("doctor1@gmail.com", doctorDto1.getEmail());
        assertEquals("Doctor 1", doctorDto1.getFullName());
        assertEquals(Collections.singletonList(UserRole.DOCTOR), doctorDto1.getRoles());
        assertEquals(Collections.singletonList("Speciality 1"), doctorDto1.getSpecialities());

        UserDto doctorDto2 = doctorDtos.get(1);
        assertEquals(2L, doctorDto2.getId());
        assertEquals("doctor2@gmail.com", doctorDto2.getEmail());
        assertEquals("Doctor 2", doctorDto2.getFullName());
        assertEquals(Collections.singletonList(UserRole.DOCTOR), doctorDto2.getRoles());
        assertEquals(Collections.singletonList("Speciality 2"), doctorDto2.getSpecialities());

        logger.info("getAllDoctors() method test completed successfully");

    }


    @Test
    void getAllPatients() {
        when(userRepository.findByRolesUserRole(any(), any())).thenReturn(mockUsers);

        List<UserDto> patientDtos = userService.getAllPatients();

        assertEquals(2, patientDtos.size());

        UserDto patientDto1 = patientDtos.get(0);
        assertEquals(3L, patientDto1.getId());
        assertEquals("patient1@gmail.com", patientDto1.getEmail());
        assertEquals("Patient 1", patientDto1.getFullName());
        assertEquals(Collections.singletonList(UserRole.PATIENT), patientDto1.getRoles());

        UserDto patientDto2 = patientDtos.get(1);
        assertEquals(4L, patientDto2.getId());
        assertEquals("patient2@gmail.com", patientDto2.getEmail());
        assertEquals("Patient 2", patientDto2.getFullName());
        assertEquals(Collections.singletonList(UserRole.PATIENT), patientDto2.getRoles());

        logger.info("getAllPatients() method test completed successfully");

    }

    @Test
    void deleteAllUsers() {
        userService.deleteAllUsers();

        verify(userRepository, times(1)).deleteAll();
    }
    @Test
     void addUser() throws InvalidUserDataException, RoleException, SpecialityException, DuplicateValueException {
        UserDto userDto = createUserDto();

        Mockito.when(userRepository.existsByIdMedicalCard(anyString())).thenReturn(false);
        Mockito.when(userRepository.existsByEmailOrPhoneNumber(anyString(), anyString())).thenReturn(false);
        Mockito.when(roleRepository.findByUserRole(any(UserRole.class))).thenReturn(createRole());

        userService.addUser(userDto);

        Mockito.verify(userRepository, Mockito.times(1)).save(any(User.class));


    }
    private UserDto createUserDto() {
        UserDto userDto = new UserDto();
        userDto.setEmail("aldoshehu@example.com");
        userDto.setFullName("Aldo Shehu");
        userDto.setBirthDate(LocalDate.of(1998, 11, 17));
        userDto.setPhoneNumber("1234567890");
        userDto.setIdMedicalCard("1234567890123456");
        userDto.setRoles(Collections.singletonList(UserRole.PATIENT));
        return userDto;
    }

    private Role createRole() {
        Role role = new Role();
        role.setUserRole(UserRole.PATIENT);
        return role;
    }
}
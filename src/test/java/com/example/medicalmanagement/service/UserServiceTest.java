package com.example.medicalmanagement.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.Role;
import com.example.medicalmanagement.model.Speciality;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.model.UserRole;
import com.example.medicalmanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
 class UserServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private List<User> mockUsers;

    @BeforeEach
    public void setup() {
        Speciality speciality1 = new Speciality(1L,"doctor1@gmail.com","Speciality 1",null);
        Speciality speciality2 = new Speciality(2L,"doctor2@gmail.com","Speciality 2",null);
        Role role = new Role();
        role.setUserRole(UserRole.DOCTOR);

        User user1 = new User();
        user1.setId(1L);
        user1.setEmail("doctor1@gmail.com");
        user1.setFullName("Doctor 1");
        user1.setRoles(Collections.singletonList(role));
        user1.setSpecialities(Collections.singletonList(speciality1));

        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("doctor2@gmail.com");
        user2.setFullName("Doctor 2");
        user2.setRoles(Collections.singletonList(role));
        user2.setSpecialities(Collections.singletonList(speciality2));

        mockUsers = Arrays.asList(user1, user2);
    }

    @Test
     void getAllDoctors() {
        when(userRepository.findByRolesUserRole(any(), any())).thenReturn(mockUsers);

        List<UserDto> doctorDtos = userService.getAllDoctors();

        assertEquals(2, doctorDtos.size());

        UserDto doctorDto1 = doctorDtos.get(0);
        assertEquals(1L, doctorDto1.getId());
        assertEquals("doctor1@gmail.com",doctorDto1.getEmail());
        assertEquals("Doctor 1", doctorDto1.getFullName());
        assertEquals(Collections.singletonList(UserRole.DOCTOR), doctorDto1.getRoles());
        assertEquals(Collections.singletonList("Speciality 1"), doctorDto1.getSpecialities());

        UserDto doctorDto2 = doctorDtos.get(1);
        assertEquals(2L, doctorDto2.getId());
        assertEquals("doctor2@gmail.com",doctorDto2.getEmail());
        assertEquals("Doctor 2", doctorDto2.getFullName());
        assertEquals(Collections.singletonList(UserRole.DOCTOR), doctorDto2.getRoles());
        assertEquals(Collections.singletonList("Speciality 2"), doctorDto2.getSpecialities());

        logger.info("getAllDoctors() method test completed successfully");

    }
    @Test
     void deleteAllUsers() {
        userService.deleteAllUsers();

        verify(userRepository, times(1)).deleteAll();
    }
}
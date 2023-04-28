//package com.example.medicalmanagement.service;
//
//import com.example.medicalmanagement.dto.SpecialityDto;
//import com.example.medicalmanagement.dto.UserDto;
//import com.example.medicalmanagement.model.Role;
//import com.example.medicalmanagement.model.Speciality;
//import com.example.medicalmanagement.model.User;
//import com.example.medicalmanagement.model.UserRole;
//import com.example.medicalmanagement.repository.RoleRepository;
//import com.example.medicalmanagement.repository.UserRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UserServiceTest {
//
//    @Mock
//    private RoleRepository roleRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    @Test
//    public void testGetAllDoctors() {
//        Role doctorRole = new Role();
//        doctorRole.setUserRole(UserRole.DOCTOR);
//
//        List<User> mockDoctorUsers = new ArrayList<>();
//        User mockDoctorUser1 = new User();
//        mockDoctorUser1.setId(1L);
//        mockDoctorUser1.setFullName("John Smith");
//        mockDoctorUser1.setRole(doctorRole);
//        mockDoctorUser1.setSpecialities(Arrays.asList(new SpecialityDto("Pediatrics")));
//        mockDoctorUsers.add(mockDoctorUser1);
//
//        User mockDoctorUser2 = new User();
//        mockDoctorUser2.setId(2L);
//        mockDoctorUser2.setFullName("Jane Doe");
//        mockDoctorUser2.setRole(doctorRole);
//        mockDoctorUser2.setSpecialities(Arrays.asList(new SpecialityDto("Dermatology"), new Speciality("Oncology")));
//        mockDoctorUsers.add(mockDoctorUser2);
//
//        when(roleRepository.findByUserRole(UserRole.DOCTOR)).thenReturn(doctorRole);
//        when(userRepository.findByRole(doctorRole)).thenReturn(mockDoctorUsers);
//
//        List<UserDto> expectedDoctorDtos = Arrays.asList(
//                new UserDto(1L, "John Smith", doctorRole,
//                        Arrays.asList(new SpecialityDto("Pediatrics"))),
//                new UserDto(2L, "Jane Doe", doctorRole,
//                        Arrays.asList(new SpecialityDto("Dermatology"), new SpecialityDto("Oncology")))
//        );
//
//        List<UserDto> actualDoctorDtos = userService.getAllDoctors();
//
//        assertEquals(expectedDoctorDtos, actualDoctorDtos);
//    }
//}
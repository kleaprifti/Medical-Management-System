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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
     void checkDoctorAvailability() {
        MockitoAnnotations.initMocks(this);
        User doctor = new User();
        Long doctorId = 1L;
        doctor.setId(doctorId);
        LocalDateTime startTime = LocalDateTime.of(2023, 11, 27, 10, 0); // Example start time
        LocalDateTime endTime = LocalDateTime.of(2023, 11, 27, 12, 0);   // Example end time
        List<DoctorAvailability> availabilitySchedule = new ArrayList<>();
        DoctorAvailability availability = new DoctorAvailability();
        availability.setWorkingDays(Collections.singleton(DayOfWeek.MONDAY)); // Example day
        availability.setStartTime(LocalDateTime.of(2023, 11, 27, 8, 0)); // Example availability start time
        availability.setEndTime(LocalDateTime.of(2023, 11, 27, 16, 0)); // Example availability end time
        availabilitySchedule.add(availability);
        doctor.setDoctorAvailabilities(availabilitySchedule);

        Mockito.when(userRepository.findByIdAndRolesUserRole(eq(doctorId), eq(UserRole.DOCTOR))).thenReturn(Optional.of(doctor));

        Optional<String> result = userService.checkDoctorAvailability(doctorId, startTime, endTime);

        assertEquals("Doctor is available in the specified time range on MONDAY.", result.orElse(null));
    }

}
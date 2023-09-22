package com.example.medicalmanagement.validator;

import com.example.medicalmanagement.model.DoctorAvailability;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

 class UserValidatorTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserValidator userValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void isDoctorAvailableInTimeRangeDoctorAvailable() {
        User doctor = new User();
        List<DoctorAvailability> availabilitySchedule = new ArrayList<>();
        DoctorAvailability doctorAvailability = Mockito.mock(DoctorAvailability.class);
        DoctorAvailability doctorAvailability1 = Mockito.mock(DoctorAvailability.class);
        availabilitySchedule.add(doctorAvailability1);
        availabilitySchedule.add(doctorAvailability);
        doctor.setDoctorAvailabilities(availabilitySchedule);

        LocalDateTime startTime = LocalDateTime.of(2023, 9, 25, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 9, 25, 11, 0);

        boolean result = userValidator.isDoctorAvailableInTimeRange(doctor, startTime, endTime);

        assertFalse(result);
    }

    @Test
    void isDoctorAvailableInTimeRangeDoctorNotAvailable() {
        User doctor = new User();
        List<DoctorAvailability> availabilitySchedule = new ArrayList<>();
        DoctorAvailability doctorAvailability = Mockito.mock(DoctorAvailability.class);
        DoctorAvailability doctorAvailability1 = Mockito.mock(DoctorAvailability.class);
        availabilitySchedule.add(doctorAvailability1);
        availabilitySchedule.add(doctorAvailability);
        doctor.setDoctorAvailabilities(availabilitySchedule);

        LocalDateTime startTime = LocalDateTime.of(2023, 9, 25, 14, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 9, 25, 15, 0);


        boolean result = userValidator.isDoctorAvailableInTimeRange(doctor, startTime, endTime);

        assertFalse(result);
    }
}

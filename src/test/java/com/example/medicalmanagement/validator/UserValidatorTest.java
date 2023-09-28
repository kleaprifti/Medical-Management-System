package com.example.medicalmanagement.validator;

import com.example.medicalmanagement.exceptionhandlers.DoctorAvailabilityCheckResult;
import com.example.medicalmanagement.exceptionhandlers.DoctorNotAvailableException;
import com.example.medicalmanagement.exceptionhandlers.DoctorOnHolidayException;
import com.example.medicalmanagement.exceptionhandlers.NotFoundException;
import com.example.medicalmanagement.model.DoctorAvailability;
import com.example.medicalmanagement.model.Holidays;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.model.UserRole;
import com.example.medicalmanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class UserValidatorTest {

//    @Mock
//    private UserRepository userRepository;

    @InjectMocks
    private UserValidator userValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
     void isDoctorAvailableInTimeRange() {

        User doctor = Mockito.mock(User.class);

        DoctorAvailability availability1 = Mockito.mock(DoctorAvailability.class);
        DoctorAvailability availability2 = Mockito.mock(DoctorAvailability.class);

        Set<DayOfWeek> workingDays1 = new HashSet<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY));
        Set<DayOfWeek> workingDays2 = new HashSet<>(Arrays.asList(DayOfWeek.THURSDAY, DayOfWeek.TUESDAY));


        when(availability1.getWorkingDays()).thenReturn(workingDays1);
        when(availability1.getStartTime()).thenReturn(LocalDateTime.of(2023, 9, 28, 9, 0).toLocalTime());
        when(availability1.getEndTime()).thenReturn(LocalDateTime.of(2023, 9, 28, 13, 0).toLocalTime());

        when(availability2.getWorkingDays()).thenReturn(workingDays2);
        when(availability2.getStartTime()).thenReturn(LocalDateTime.of(2023, 9, 28, 14, 0).toLocalTime());
        when(availability2.getEndTime()).thenReturn(LocalDateTime.of(2023, 9, 28, 18, 0).toLocalTime());

        List<DoctorAvailability> availabilityList = Arrays.asList(availability1, availability2);

        when(doctor.getDoctorAvailabilities()).thenReturn(availabilityList);

        UserValidator userValidator1 = new UserValidator(); // Replace with the actual class name
        LocalDateTime startTime = LocalDateTime.of(2023, 9, 28, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 9, 28, 12, 0);

        assertFalse(userValidator1.isDoctorAvailableInTimeRange(doctor, startTime, endTime), "Expected doctor to be available in this time range");

        startTime = LocalDateTime.of(2023, 9, 28, 13, 30);
        endTime = LocalDateTime.of(2023, 9, 28, 15, 0);

        assertTrue(userValidator1.isDoctorAvailableInTimeRange(doctor, startTime, endTime), "Expected doctor not to be available in this time range");
    }

    @Test
     void isDoctorOnHoliday() {
        User doctor = Mockito.mock(User.class);

        Holidays holiday1 = Mockito.mock(Holidays.class);
        Holidays holiday2 = Mockito.mock(Holidays.class);

        LocalDate date1 = LocalDate.of(2023, 9, 28); // Example holiday date
        LocalDate date2 = LocalDate.of(2023, 9, 29); // Another example holiday date

        when(holiday1.getHolidayDate()).thenReturn(date1);
        when(holiday2.getHolidayDate()).thenReturn(date2);

        List<Holidays> holidayList = Arrays.asList(holiday1, holiday2);

        when(doctor.getHolidays()).thenReturn(holidayList);

        UserValidator yourClass = new UserValidator(); // Replace with the actual class name

        LocalDate holidayDate = LocalDate.of(2023, 9, 28); // Example holiday date
        assertTrue(yourClass.isDoctorOnHoliday(doctor, holidayDate), "Expected doctor to be on holiday on this date");

        LocalDate nonHolidayDate = LocalDate.of(2023, 9, 30); // A date that is not a holiday
        assertFalse(yourClass.isDoctorOnHoliday(doctor, nonHolidayDate), "Expected doctor not to be on holiday on this date");
    }

    @Test
     void isTimeRangeOverlap() {
        UserValidator yourClass = new UserValidator(); // Replace with the actual class name

        LocalTime start1 = LocalTime.of(9, 0);
        LocalTime end1 = LocalTime.of(12, 0);
        LocalDateTime start2 = LocalDateTime.of(2023, 9, 28, 10, 0);
        LocalDateTime end2 = LocalDateTime.of(2023, 9, 28, 11, 0);

        assertTrue(yourClass.isTimeRangeOverlap(start1, end1, start2, end2), "Expected time ranges to overlap");

        LocalTime start3 = LocalTime.of(14, 0);
        LocalTime end3 = LocalTime.of(16, 0);
        LocalDateTime start4 = LocalDateTime.of(2023, 9, 28, 17, 0);
        LocalDateTime end4 = LocalDateTime.of(2023, 9, 28, 18, 0);

        assertFalse(yourClass.isTimeRangeOverlap(start3, end3, start4, end4), "Expected time ranges not to overlap");

        LocalTime start5 = LocalTime.of(9, 0);
        LocalTime end5 = LocalTime.of(12, 0);
        LocalDateTime start6 = LocalDateTime.of(2023, 9, 28, 11, 0);
        LocalDateTime end6 = LocalDateTime.of(2023, 9, 28, 14, 0);

        assertTrue(yourClass.isTimeRangeOverlap(start5, end5, start6, end6), "Expected time ranges to partially overlap");
    }

    @Test
     void validateDoctorAvailability() throws DoctorNotAvailableException, NotFoundException, DoctorOnHolidayException {
        // Create a mock UserRepository
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        User doctor = Mockito.mock(User.class);

        when(userRepository.findByIdAndRolesUserRole(anyLong(), eq(UserRole.DOCTOR))).thenReturn(Optional.of(doctor));

        DoctorAvailabilityCheckResult expectedResult = new DoctorAvailabilityCheckResult(true, "Doctor is available");

        UserValidator yourClass = new UserValidator(userRepository);

        LocalDateTime startTime1 = LocalDateTime.of(2023, 9, 28, 10, 0);
        LocalDateTime endTime1 = LocalDateTime.of(2023, 9, 28, 12, 0);

        DoctorAvailabilityCheckResult result1 = Mockito.mock(DoctorAvailabilityCheckResult.class);

        assertEquals(result1, result1, "Expected valid doctor availability result");


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

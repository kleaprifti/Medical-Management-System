package com.example.medicalmanagement.validator;

import com.example.medicalmanagement.model.DoctorAvailability;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@NoArgsConstructor
public class UserValidator {
    @Autowired
    private UserRepository userRepository;
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean isDoctorAvailableInTimeRange(User doctor, LocalDateTime startTime, LocalDateTime endTime) {
        List<DoctorAvailability> availabilitySchedule = doctor.getDoctorAvailabilities();

        return availabilitySchedule.stream()
                .filter(availability -> availability.getWorkingDays().contains(startTime.getDayOfWeek()))
                .anyMatch(availability -> isTimeRangeOverlap(availability.getStartTime(), availability.getEndTime(), startTime, endTime));

    }

    public boolean isDoctorOnHoliday(User doctor, LocalDate date) {
        return doctor.getHolidays()
                .stream()
                .anyMatch(holiday -> holiday.getHolidayDate().equals(date));
    }

    private boolean isTimeRangeOverlap(LocalTime start1, LocalTime end1, LocalDateTime start2, LocalDateTime end2) {
        return !start1.isAfter(end2.toLocalTime()) && !start2.toLocalTime().isAfter(end1);
    }
}

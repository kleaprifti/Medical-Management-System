package com.example.medicalmanagement.validator;

import com.example.medicalmanagement.exceptionhandlers.DoctorAvailabilityCheckResult;
import com.example.medicalmanagement.exceptionhandlers.DoctorNotAvailableException;
import com.example.medicalmanagement.exceptionhandlers.DoctorOnHolidayException;
import com.example.medicalmanagement.exceptionhandlers.NotFoundException;
import com.example.medicalmanagement.model.DoctorAvailability;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.model.UserRole;
import com.example.medicalmanagement.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class UserValidator {
    @Autowired
    private UserRepository userRepository;
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DoctorAvailabilityCheckResult validateDoctorAvailability(Long doctorId, LocalDateTime startTime, LocalDateTime endTime)
            throws DoctorNotAvailableException, NotFoundException, DoctorOnHolidayException {
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new IllegalArgumentException("Start time must be before the end time.");
        }

        Optional<User> optionalDoctor = userRepository.findByIdAndRolesUserRole(doctorId, UserRole.DOCTOR);

        if (optionalDoctor.isPresent()) {
            User doctor = optionalDoctor.get();
            LocalDate date = startTime.toLocalDate();

            boolean isOnHoliday = isDoctorOnHoliday(doctor, date);

            if (isOnHoliday) {
                throw new DoctorOnHolidayException("Doctor is on holiday on " + date + ".");
            } else {
                boolean isAvailable = isDoctorAvailableInTimeRange(doctor, startTime, endTime);
                if (!isAvailable) {
                    throw new DoctorNotAvailableException("Doctor is not available in the specified time range on " + date.getDayOfWeek() + ".");
                }
                return new DoctorAvailabilityCheckResult(true, "Doctor is available.");
            }
        } else {
            throw new NotFoundException("Doctor not found");
        }
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

    public boolean isTimeRangeOverlap(LocalTime start1, LocalTime end1, LocalDateTime start2, LocalDateTime end2) {
        return !start1.isAfter(end2.toLocalTime()) && !start2.toLocalTime().isAfter(end1);
    }
}

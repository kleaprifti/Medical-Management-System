package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.exceptionhandlers.DoctorAvailabilityCheckResult;
import com.example.medicalmanagement.exceptionhandlers.DoctorNotAvailableException;
import com.example.medicalmanagement.exceptionhandlers.DoctorOnHolidayException;
import com.example.medicalmanagement.exceptionhandlers.NotFoundException;
import com.example.medicalmanagement.model.UserRole;
import com.example.medicalmanagement.service.UserService;
import com.example.medicalmanagement.validator.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody @Valid UserDto userDto) {

        try {
            userService.addUser(userDto);
            return ResponseEntity.ok("User added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<UserDto> getAllUsersByRole(@RequestParam(value = "userRole", required = false) UserRole userRole) {
        return userService.getAllUsers(userRole);
    }


@GetMapping("/{doctorId}/check-availability")
public ResponseEntity<DoctorAvailabilityCheckResult> checkDoctorAvailability(
        @PathVariable Long doctorId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalDateTime startTime,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalDateTime endTime) {
    try {
        boolean isAvailable = userService.isDoctorAvailable(doctorId, startTime, endTime);
        DoctorAvailabilityCheckResult result = userValidator.validateDoctorAvailability(doctorId, startTime, endTime);

        result.setAvailable(isAvailable);

        return ResponseEntity.ok(result);
    } catch (NotFoundException e) {
        return ResponseEntity.notFound().build();
    } catch (DoctorNotAvailableException e) {
        return ResponseEntity.ok(new DoctorAvailabilityCheckResult(false, e.getMessage()));
    } catch (DoctorOnHolidayException e) {
        return ResponseEntity.ok(new DoctorAvailabilityCheckResult(false, e.getMessage()));
    }

    }
}
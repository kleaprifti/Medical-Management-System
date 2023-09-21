package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.UserRole;
import com.example.medicalmanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;


@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

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
    public ResponseEntity<String> checkDoctorAvailability(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,
            @RequestParam DayOfWeek workday) {

        String message = userService.checkDoctorAvailability(doctorId, startTime, endTime, workday);
        return ResponseEntity.ok(message);
    }

}
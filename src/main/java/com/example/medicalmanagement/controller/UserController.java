package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/doctors")
    public List<UserDto> getAllDoctors() {
        return userService.getAllDoctors();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {
        boolean result = userService.addUser(userDto);

        if (result) {
            return ResponseEntity.ok("User added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding user");
        }
    }

}
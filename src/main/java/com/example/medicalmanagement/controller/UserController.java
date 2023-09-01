package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.exceptionhandlers.DuplicateValueException;
import com.example.medicalmanagement.exceptionhandlers.InvalidUserDataException;
import com.example.medicalmanagement.exceptionhandlers.RoleException;
import com.example.medicalmanagement.exceptionhandlers.SpecialityException;
import com.example.medicalmanagement.model.UserRole;
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


    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {
    try {
        userService.addUser(userDto);
        return ResponseEntity.ok("User added successfully");
    } catch (InvalidUserDataException | RoleException | SpecialityException | DuplicateValueException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request.");
//    }
    }
    }


    @GetMapping
    public List<UserDto> getAllUsersByRole(@RequestParam(value = "userRole", required = false) UserRole userRole) {
        return userService.getAllUsers(userRole);
    }
}
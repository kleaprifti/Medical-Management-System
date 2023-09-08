package com.example.medicalmanagement.customvalidators;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.UserRole;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class SpecialitiesRequiredForDoctorValidatorTest {

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;
    @Mock
    private SpecialitiesRequiredForDoctorValidator validator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        validator = new SpecialitiesRequiredForDoctorValidator();
    }

    @Test
     void isValidWithNullUserDto() {
        assertTrue(validator.isValid(null, constraintValidatorContext));
        validator.initialize(null);

    }

    @Test
     void isValidWithNonDoctorUser() {
        UserDto userDto = Mockito.mock(UserDto.class);
        userDto.setRoles(Collections.singletonList(UserRole.DOCTOR));

        assertTrue(validator.isValid(userDto, constraintValidatorContext));
    }
    @Test
     void isValidWithDoctorAndSpecialities() {
        UserDto userDto = new UserDto();
        userDto.setRoles(Collections.singletonList(UserRole.DOCTOR));
        userDto.setSpecialities(Collections.singletonList("Cardiology"));

        assertTrue(validator.isValid(userDto, constraintValidatorContext));
    }
}
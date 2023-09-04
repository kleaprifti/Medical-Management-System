package com.example.medicalmanagement.customvalidators;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.model.UserRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class SpecialitiesRequiredForDoctorValidator implements ConstraintValidator<SpecialitiesRequiredForDoctor, User> {

    @Override
    public void initialize(SpecialitiesRequiredForDoctor constraintAnnotation) {
        // Initialization, if needed
    }

    @Override
    public boolean isValid(User userDto, ConstraintValidatorContext context) {
        if (userDto == null || !userDto.getRoles().contains(UserRole.DOCTOR)) {

            return true;
        }

        if (userDto.getSpecialities() == null || userDto.getSpecialities().isEmpty()) {
            // Validation fails, set the error message
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("At least one speciality is required for a doctor.")
                    .addPropertyNode("specialities") // Associate error with the "specialities" field
                    .addConstraintViolation();
            return false;
        }

        return true; // Validation passes
    }
}


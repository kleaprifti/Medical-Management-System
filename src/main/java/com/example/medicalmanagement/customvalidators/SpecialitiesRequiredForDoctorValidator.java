package com.example.medicalmanagement.customvalidators;


import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.UserRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



public class SpecialitiesRequiredForDoctorValidator implements ConstraintValidator<SpecialitiesRequiredForDoctor, UserDto> {

    @Override
    public void initialize(SpecialitiesRequiredForDoctor constraintAnnotation) {
        // Initialization, if needed
    }

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {
        if (userDto == null) {
            return true;
        }

        if (userDto.getRoles() == null || !userDto.getRoles().contains(UserRole.DOCTOR)) {
            return true;
        }

        if (userDto.getSpecialities() == null || userDto.getSpecialities().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("At least one speciality is required for a doctor.")
                    .addPropertyNode("specialities")
                    .addConstraintViolation();
            return false;
        }

        return true; // Validation passes
    }
}

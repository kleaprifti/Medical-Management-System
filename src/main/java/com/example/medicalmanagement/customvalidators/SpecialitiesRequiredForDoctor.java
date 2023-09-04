package com.example.medicalmanagement.customvalidators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;
@Target({ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SpecialitiesRequiredForDoctorValidator.class)
public @interface SpecialitiesRequiredForDoctor {
    String message() default "At least one speciality is required for a doctor.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


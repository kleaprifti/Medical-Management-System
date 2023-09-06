package com.example.medicalmanagement.customvalidators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;
@Target({ElementType.TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SpecialitiesRequiredForDoctorValidator.class)
public @interface SpecialitiesRequiredForDoctor {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


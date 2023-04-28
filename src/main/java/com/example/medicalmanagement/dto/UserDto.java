package com.example.medicalmanagement.dto;

import com.example.medicalmanagement.model.Role;
import com.example.medicalmanagement.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

        private Long id;
        private String fullName;
        private UserRole userRole;
        private List<SpecialityDto> specialities;
        public UserDto(Long id, String fullName, Role role, List<SpecialityDto> specialities) {
            this.id = id;
            this.fullName = fullName;
            this.userRole = role.getUserRole();
            this.specialities = specialities;
        }
}


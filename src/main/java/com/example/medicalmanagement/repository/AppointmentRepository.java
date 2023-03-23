package com.example.medicalmanagement.repository;

import com.example.medicalmanagement.dto.DoctorDto;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.model.Doctor;
import com.example.medicalmanagement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findByPatient(Patient patient);
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorIdAndAppointmentDateTimeBetween(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime);



    }

package com.example.medicalmanagement.repository;

import com.example.medicalmanagement.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    List<Appointment> findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter(Long  doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Appointment> findAppointmentsByDoctorId(Long doctorId);

}
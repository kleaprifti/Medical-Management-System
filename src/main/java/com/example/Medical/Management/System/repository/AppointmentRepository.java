package com.example.Medical.Management.System.repository;

import com.example.Medical.Management.System.model.Appointment;
import com.example.Medical.Management.System.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findByPatient(Patient patient);
    List<Appointment> findByAppointmentDateBetween(Date start, Date end);
}

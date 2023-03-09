package com.example.Medical.Management.System.repository;

import com.example.Medical.Management.System.model.Appointment;
import com.example.Medical.Management.System.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findByPatient(Patient patient);
    List<Appointment> findByAppointmentDateBetween(Date start, Date end);
}

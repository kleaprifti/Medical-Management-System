package com.example.medicalmanagement.repository;

import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findByPatient(Patient patient);
    List<Appointment> findByAppointmentDateBetween(Date start, Date end);

    List<Appointment> findByPatientId(Long patientId);

//

}

package com.example.medicalmanagement.batch;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.service.AppointmentService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class AppointmentItemWriter implements ItemWriter<AppointmentDto> {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentItemWriter(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public void write(List<? extends AppointmentDto> items) throws Exception {
        for (AppointmentDto item : items) {
            appointmentService.addAppointment(item);
        }
    }
}
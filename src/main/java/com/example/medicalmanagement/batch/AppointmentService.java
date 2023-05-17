package com.example.medicalmanagement.batch;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.repository.AppointmentRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentItemReader appointmentItemReader;
    private final AppointmentItemProcessor appointmentItemProcessor;
    private final AppointmentItemWriter appointmentItemWriter;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobLauncher jobLauncher;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              AppointmentItemReader appointmentItemReader,
                              AppointmentItemProcessor appointmentItemProcessor,
                              AppointmentItemWriter appointmentItemWriter,
                              JobBuilderFactory jobBuilderFactory,
                              StepBuilderFactory stepBuilderFactory,
                              JobLauncher jobLauncher) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentItemReader = appointmentItemReader;
        this.appointmentItemProcessor = appointmentItemProcessor;
        this.appointmentItemWriter = appointmentItemWriter;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobLauncher = jobLauncher;
    }

    public void processAppointments() {
        try {
            Step step = stepBuilderFactory.get("processAppointmentsStep")
                    .<AppointmentDto, Appointment>chunk(10)
                    .reader(appointmentItemReader)
                    .processor(appointmentItemProcessor)
                    .writer(appointmentItemWriter)
                    .build();

            // Define the job
            Job job = jobBuilderFactory.get("processAppointmentsJob")
                    .incrementer(new RunIdIncrementer())
                    .flow(step)
                    .end()
                    .build();

            JobExecution jobExecution = jobLauncher.run(job, new JobParameters());

            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                System.out.println("Job completed successfully");
            } else {
                System.out.println("Job failed with status: " + jobExecution.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

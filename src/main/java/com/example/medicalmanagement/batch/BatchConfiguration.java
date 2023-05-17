package com.example.medicalmanagement.batch;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.model.Appointment;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private final AppointmentService appointmentService;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobLauncher jobLauncher;
    private final AppointmentItemProcessor appointmentItemProcessor;
    private final AppointmentItemWriter appointmentItemWriter;
    private final AppointmentItemReader appointmentItemReader;



    @Autowired
    public BatchConfiguration(AppointmentService appointmentService,
                              JobBuilderFactory jobBuilderFactory,
                              StepBuilderFactory stepBuilderFactory,
                              JobLauncher jobLauncher, AppointmentItemProcessor appointmentItemProcessor, AppointmentItemWriter appointmentItemWriter, AppointmentItemReader appointmentItemReader) {
        this.appointmentService = appointmentService;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobLauncher = jobLauncher;
        this.appointmentItemProcessor = appointmentItemProcessor;
        this.appointmentItemWriter = appointmentItemWriter;
        this.appointmentItemReader = appointmentItemReader;
    }

    @Bean
    public Step processAppointmentsStep(AppointmentItemReader appointmentItemReader,
                                        AppointmentItemProcessor appointmentItemProcessor,
                                        AppointmentItemWriter appointmentItemWriter) {
        return stepBuilderFactory.get("processAppointmentsStep")
                .<AppointmentDto, Appointment>chunk(10)
                .reader(appointmentItemReader)
                .processor(appointmentItemProcessor)
                .writer(appointmentItemWriter)
                .build();
    }

    @Bean
    public Job processAppointmentsJob(Step processAppointmentsStep) {
        return jobBuilderFactory.get("processAppointmentsJob")
                .incrementer(new RunIdIncrementer())
                .flow(processAppointmentsStep)
                .end()
                .build();
    }

    public void runBatchJob() {
        try {
            JobExecution jobExecution = jobLauncher.run(processAppointmentsJob(null), new JobParameters());

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

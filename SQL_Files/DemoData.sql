use medical_management_system;

INSERT INTO patients (name,last_name, phone_number)
VALUES
    ('John', 'Doe', '123-456-7801'),
    ('Jane','Doe', '234-567-8901'),
    ('Klea',' Prifti', '345-678-9012'),
    ('Romeisa',' Aliu', '456-789-0123'),
    ('Klejda',' Rrapaj', '567-890-1234'),
    ('Sildi',' Ricku', '678-901-2345'),
    ('Orgesa',' Agolli', '789-012-3456'),
    ('Risilda',' Osmani', '890-123-4567'),
    ('Aida',' Sufaj', '901-234-5678'),
    ('Steve',' Rogers', '012-345-6789'),
    ('Tony',' Stark', '123-456-7890'),
    ('Natasha',' Romanoff', '234-567-756'),
    ('Wanda',' Maximoff', '345-678-493'),
    ('Stephen',' Strange', '456-789-947'),
    ('Thor',' Odinson ', '567-890-2953'),
    ('Peter',' Parker', '678-901-8734'),
    ('Andrew',' Garfield', '789-012-1111'),
    ('Peggy',' Carter', '890-123-0000'),
    ('Reed',' Richards', '901-234-6321'),
    ('Tchalla', 'Tchalla','012-345-8392'),
    ('Carol','Denvers', '123-456-0945'),
    ('Nick',' Fury', '234-567-7955'),
    ('Bruce',' Banner', '345-678-9090'),
    ('Loki',' Laufeyson', '456-789-4212'),
    ('Peter',' Quill', '567-890-3333'),
    ('Thanos','Thanos', '678-901-4444'),
    ('Kang','Kang', '789-012-1616');

INSERT INTO doctors (full_name, profile)
VALUES
    ('Dr.Rrapaj', 'General'),
    ('Dr.Stephen Strange', 'Pediatrician'),
    ('Dr.Hank Pym', 'Cardiologist');






INSERT INTO `medical_management_system`.`appointments` ( `appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-03-20 10:00:00', '2023-03-20 09:00:00', (SELECT id from doctors where full_name='Dr.Rrapaj'), (SELECT id from patients where phone_number = '123-456-7801'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ( '2023-03-21 12:00:00', '2023-03-21 11:00:00', (SELECT id from doctors where full_name='Dr.Rrapaj'),(SELECT id from patients where phone_number = '234-567-8901'));
INSERT INTO `medical_management_system`.`appointments` ( `appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-03-22 14:00:00', '2023-03-22 13:00:00', (SELECT id from doctors where full_name='Dr.Rrapaj'), (SELECT id from patients where phone_number = '345-678-9012' ));
INSERT INTO `medical_management_system`.`appointments` ( `appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ( '2023-03-23 16:00:00', '2023-03-23 15:00:00', (SELECT id from doctors where full_name='Dr.Stephen Strange'), (SELECT id from patients where phone_number = '456-789-0123' ));
INSERT INTO `medical_management_system`.`appointments` ( `appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ( '2023-03-24 18:00:00', '2023-03-24 17:00:00', (SELECT id from doctors where full_name='Dr.Stephen Strange'), (SELECT id from patients where phone_number = '567-890-1234'));
INSERT INTO `medical_management_system`.`appointments` ( `appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-03-25 11:30:00', '2023-03-25 10:30:00', (SELECT id from doctors where full_name='Dr.Stephen Strange'), (SELECT id from patients where phone_number = '678-901-2345' ));
INSERT INTO `medical_management_system`.`appointments` ( `appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ( '2023-03-26 12:45:00', '2023-03-26 11:45:00', (SELECT id from doctors where full_name='Dr.Hank Pym'), (SELECT id from patients where phone_number = '789-012-3456' ));
INSERT INTO `medical_management_system`.`appointments` ( `appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ( '2023-03-27 13:10:00', '2023-03-27 12:10:00', (SELECT id from doctors where full_name='Dr.Hank Pym'), (SELECT id from patients where phone_number = '890-123-4567' ));
INSERT INTO `medical_management_system`.`appointments` ( `appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ( '2023-03-28 16:00:00', '2023-03-28 15:00:00', (SELECT id from doctors where full_name='Dr.Hank Pym'), (SELECT id from patients where phone_number = '901-234-5678' ));
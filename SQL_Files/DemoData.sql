use medical_management_system;

INSERT INTO patients (name,last_name, phone_number)
VALUES
    ('John', 'Doe', '123-456-7890'),
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
    ('Natasha',' Romanoff', '234-567-8901'),
    ('Wanda',' Maximoff', '345-678-9012'),
    ('Stephen',' Strange', '456-789-0123'),
    ('Thor',' Odinson ', '567-890-1234'),
    ('Peter',' Parker', '678-901-2345'),
    ('Andrew',' Garfield', '789-012-3456'),
    ('Peggy',' Carter', '890-123-4567'),
    ('Reed',' Richards', '901-234-5678'),
    ('Tchalla', 'Tchalla','012-345-6789'),
    ('Carol','Denvers', '123-456-7890'),
    ('Nick',' Fury', '234-567-8901'),
    ('Bruce',' Banner', '345-678-9012'),
    ('Loki',' Laufeyson', '456-789-0123'),
    ('Peter',' Quill', '567-890-1234'),
    ('Thanos','Thanos', '678-901-2345'),
    ('Kang','Kang', '789-012-3456');

INSERT INTO doctors (full_name, profile)
VALUES
    ('Dr.Rrapaj', 'General'),
    ('Dr.Stephen Strange', 'Pediatrician'),
    ('Dr.Hank Pym', 'Cardiologist');






INSERT INTO `medical_management_system`.`appointments` ( `appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-03-20 10:00:00', '2023-03-20 09:00:00', (SELECT id from doctors where full_name='Dr.Rrapaj'), '1');
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ( '2023-03-21 12:00:00', '2023-03-21 11:00:00', (SELECT id from doctors where full_name='Dr.Rrapaj'),'2');
INSERT INTO `medical_management_system`.`appointments` ( `appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-03-22 14:00:00', '2023-03-22 13:00:00', (SELECT id from doctors where full_name='Dr.Rrapaj'), '3');
INSERT INTO `medical_management_system`.`appointments` ( `appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ( '2023-03-23 16:00:00', '2023-03-23 15:00:00', (SELECT id from doctors where full_name='Dr.Stephen Strange'), '4');
INSERT INTO `medical_management_system`.`appointments` ( `appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ( '2023-03-24 18:00:00', '2023-03-24 17:00:00', (SELECT id from doctors where full_name='Dr.Stephen Strange'), '5');
INSERT INTO `medical_management_system`.`appointments` ( `appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-03-25 11:30:00', '2023-03-25 10:30:00', (SELECT id from doctors where full_name='Dr.Stephen Strange'), '6');
INSERT INTO `medical_management_system`.`appointments` ( `appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ( '2023-03-26 12:45:00', '2023-03-26 11:45:00', (SELECT id from doctors where full_name='Dr.Hank Pym'), '7');
INSERT INTO `medical_management_system`.`appointments` ( `appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ( '2023-03-27 13:10:00', '2023-03-27 12:10:00', (SELECT id from doctors where full_name='Dr.Hank Pym'), '8');
INSERT INTO `medical_management_system`.`appointments` ( `appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ( '2023-03-28 16:00:00', '2023-03-28 15:00:00', (SELECT id from doctors where full_name='Dr.Hank Pym'), '9');
use medical_management_system;
#Insert into Role

INSERT INTO `medical_management_system`.`role` (`user_role`) VALUES ('DOCTOR');
INSERT INTO `medical_management_system`.`role` (`user_role`) VALUES ('PATIENT');


#Inser into Users
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Steve Rogers', '4728337482', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Tony Stark', '8348923612', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Natasha Romanoff', '92362184', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Peggy Carter ', '547423093', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Romeisa Aliu', '003834252', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Klejda Rrapaj', '57239145', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Sildi Ricku', '93482663', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Carlos Sainz', '3627183', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Pablus Pabliuus', '57355253', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Francesco Totti', '298342184', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Matt Smith', '473829742', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Wanda Maximoff', '76538291', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Max Verstappen', '5738219353', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Charles Leclerc', '83728264', (SELECT id FROM role WHERE user_role = 'DOCTOR'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Daniel  Ricciardo', '8038472', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Sebastian Vettel', '58384734', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Fernando Alonso', '85232244', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Lewis Hamilton', '1234567', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Lando Norris', '85634283', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Carol Denvers', '462827421', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Gamora Quill', '000394821', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Sherlock Holmes', '111482231', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Gregory House', '222333444', (SELECT id FROM role WHERE user_role = 'DOCTOR'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Stephen Strange', '8884443222', (SELECT id FROM role WHERE user_role = 'DOCTOR'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('John Watson', '999323333', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Irene Adler', '0002222111', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('John Smith', '373163982', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('James Moriarty', '32223833', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Charles  Leclerc', '33821102', (SELECT id FROM role WHERE user_role = 'PATIENT'));
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`, `role_id`) VALUES ('Thanos Lang', '212221114', (SELECT id FROM role WHERE user_role = 'PATIENT'));



#Insert into speciality
INSERT INTO `medical_management_system`.`speciality` (`name`) VALUES ('General');
INSERT INTO `medical_management_system`.`speciality` (`name`) VALUES ('Pediatrican');
INSERT INTO `medical_management_system`.`speciality` (`name`) VALUES ('Cardiology');

#Insert into user_speciality

INSERT INTO `medical_management_system`.`user_speciality` (`user_id`, `speciality_id`) VALUES ((SELECT id FROM users WHERE full_name='Charles Leclerc'),(SELECT id FROM speciality WHERE name = 'General'));
INSERT INTO `medical_management_system`.`user_speciality` (`user_id`, `speciality_id`) VALUES ((SELECT id FROM users WHERE full_name='Gregory House'),(SELECT id FROM speciality WHERE  name = 'Pediatrican'));
INSERT INTO `medical_management_system`.`user_speciality` (`user_id`, `speciality_id`) VALUES ((SELECT id FROM users WHERE full_name='Stephen Strange'),(SELECT id FROM speciality WHERE name = 'Cardiology'));





#Insert into Appointments
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-03-20 10:00:00', '2023-03-20 09:00:00', (SELECT id FROM users WHERE full_name='Charles Leclerc') ,(SELECT id FROM users WHERE phone_number = '4728337482'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-03-21 12:00:00', '2023-03-21 11:00:00', (SELECT id FROM users WHERE full_name='Charles Leclerc') ,(SELECT id FROM users WHERE phone_number = '8348923612'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-03-22 14:00:00', '2023-03-22 13:00:00', (SELECT id FROM users WHERE full_name='Charles Leclerc') ,(SELECT id FROM users WHERE phone_number = '92362184'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-03-23 16:00:00', '2023-03-23 15:00:00', (SELECT id FROM users WHERE full_name='Gregory House') ,(SELECT id FROM users WHERE phone_number = '547423093'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-03-24 18:00:00', '2023-03-24 17:00:00', (SELECT id FROM users WHERE full_name='Gregory House') ,(SELECT id FROM users WHERE phone_number = '003834252'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-03-25 11:30:00', '2023-03-25 10:30:00', (SELECT id FROM users WHERE full_name='Gregory House') ,(SELECT id FROM users WHERE phone_number = '57239145'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-03-26 12:45:00', '2023-03-26 11:45:00', (SELECT id FROM users WHERE full_name='Stephen Strange') ,(SELECT id FROM users WHERE phone_number = '93482663'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-03-27 13:10:00', '2023-03-27 12:10:00', (SELECT id FROM users WHERE full_name='Stephen Strange') ,(SELECT id FROM users WHERE phone_number = '3627183'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-03-28 16:00:00', '2023-03-28 15:00:00', (SELECT id FROM users WHERE full_name='Stephen Strange') ,(SELECT id FROM users WHERE phone_number = '57355253'));


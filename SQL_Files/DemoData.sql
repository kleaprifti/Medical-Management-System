use medical_management_system;
#Insert into Role

INSERT INTO `medical_management_system`.`role` (`roles`) VALUES ('DOCTOR');
INSERT INTO `medical_management_system`.`role` (`roles`) VALUES ('PATIENT');


#Inser into Users

INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('sildiricku3@gmail.com', 'Steve Rogers', '4728337482');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('sildiricku3@gmail.com', 'Tony Stark', '8348923612');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('sildiricku3@gmail.com', 'Natasha Romanoff', '92362184');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('sildiricku3@gmail.com', 'Peggy Carter', '547423093');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('sildiricku3@gmail.com', 'Romeisa Aliu', '003834252');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('sildiricku3@gmail.com', 'Klejda Rrapaj', '57239145');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('sildiricku3@gmail.com', 'Sildi Ricku', '93482663');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('kleaprifti21@gmail.com', 'Carlos Sainz', '3627183');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('kleaprifti21@gmail.com', 'Pablus Pabliuus', '57355253');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('kleaprifti21@gmail.com', 'Francesco Totti', '298342184');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('kleaprifti21@gmail.com', 'Matt Smith', '473829742');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('kleaprifti21@gmail.com', 'Wanda Maximoff', '76538291');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('kleaprifti21@gmail.com', 'Max Verstappen', '5738219353');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('kleaprifti21@gmail.com', 'Charles Leclerc', '83728264');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('kleaprifti21@gmail.com', 'Daniel Ricciardo', '8038472');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('romeisaaliu1@gmail.com', 'Sebastian Vettel', '58384734');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('romeisaaliu1@gmail.com', 'Fernando Alonso', '85232244');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('romeisaaliu1@gmail.com', 'Lewis Hamilton', '1234567');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('romeisaaliu1@gmail.com', 'Lando Norris', '85634283');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('romeisaaliu1@gmail.com', 'Carol Denvers', '462827421');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('romeisaaliu1@gmail.com', 'Gamora Quill', '000394821');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('romeisaaliu1@gmail.com', 'Sherlock Holmes', '111482231');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('romeisaaliu1@gmail.com', 'Gregory House', '222333444');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('romeisaaliu1@gmail.com', 'Stephen Strange', '8884443222');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('romeisaaliu1@gmail.com', 'John Watson', '999323333');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('romeisaaliu1@gmail.com', 'Irene Adler', '0002222111');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('romeisaaliu1@gmail.com', 'John Smith', '373163982');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('romeisaaliu1@gmail.com', 'James Moriarty', '32223833');
INSERT INTO `medical_management_system`.`users` ( `email`, `full_name`, `phone_number`) VALUES ('romeisaaliu1@gmail.com', 'Thanos Lang', '212221114');


INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '4728337482'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '8348923612'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '92362184'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '547423093'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '003834252'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '57239145'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '93482663'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '3627183'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '57355253'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '298342184'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '473829742'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '76538291'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '5738219353'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '83728264'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '83728264'),(SELECT id FROM role WHERE roles = 'DOCTOR'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '8038472'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '58384734'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '85232244'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '1234567'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '85634283'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '462827421'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '000394821'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '111482231'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '222333444'),(SELECT id FROM role WHERE roles = 'DOCTOR'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '8884443222'),(SELECT id FROM role WHERE roles = 'DOCTOR'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '999323333'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '0002222111'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '373163982'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '32223833'),(SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT id FROM users WHERE phone_number = '212221114'),(SELECT id FROM role WHERE roles = 'PATIENT'));

#Insert into speciality
INSERT INTO `medical_management_system`.`speciality` (`name`) VALUES ('General');
INSERT INTO `medical_management_system`.`speciality` (`name`) VALUES ('Pediatrican');
INSERT INTO `medical_management_system`.`speciality` (`name`) VALUES ('Cardiology');

#Insert into user_speciality

INSERT INTO `medical_management_system`.`user_speciality` (`user_id`, `speciality_id`) VALUES ((SELECT id FROM users WHERE full_name='Charles Leclerc'),(SELECT id FROM speciality WHERE name = 'General'));
INSERT INTO `medical_management_system`.`user_speciality` (`user_id`, `speciality_id`) VALUES ((SELECT id FROM users WHERE full_name='Gregory House'),(SELECT id FROM speciality WHERE  name = 'Pediatrican'));
INSERT INTO `medical_management_system`.`user_speciality` (`user_id`, `speciality_id`) VALUES ((SELECT id FROM users WHERE full_name='Stephen Strange'),(SELECT id FROM speciality WHERE name = 'Cardiology'));





# Insert into Appointments
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-07-28 10:00:00', '2023-07-28 09:00:00', (SELECT id FROM users WHERE full_name='Charles Leclerc') ,(SELECT id FROM users WHERE phone_number = '4728337482'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-07-30 12:00:00', '2023-07-30 11:00:00', (SELECT id FROM users WHERE full_name='Charles Leclerc') ,(SELECT id FROM users WHERE phone_number = '8348923612'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-08-13 14:00:00', '2023-08-13 13:00:00', (SELECT id FROM users WHERE full_name='Charles Leclerc') ,(SELECT id FROM users WHERE phone_number = '92362184'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-07-29 16:00:00', '2023-07-29 15:00:00', (SELECT id FROM users WHERE full_name='Gregory House') ,(SELECT id FROM users WHERE phone_number = '547423093'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-07-27 18:00:00', '2023-07-27 17:00:00', (SELECT id FROM users WHERE full_name='Gregory House') ,(SELECT id FROM users WHERE phone_number = '003834252'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-08-08 11:30:00', '2023-08-08 10:30:00', (SELECT id FROM users WHERE full_name='Gregory House') ,(SELECT id FROM users WHERE phone_number = '57239145'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-07-28 12:45:00', '2023-07-28 11:45:00', (SELECT id FROM users WHERE full_name='Stephen Strange') ,(SELECT id FROM users WHERE phone_number = '93482663'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-08-18 13:10:00', '2023-08-18 12:10:00', (SELECT id FROM users WHERE full_name='Stephen Strange') ,(SELECT id FROM users WHERE phone_number = '3627183'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`) VALUES ('2023-08-18 16:00:00', '2023-08-18 15:00:00', (SELECT id FROM users WHERE full_name='Stephen Strange') ,(SELECT id FROM users WHERE phone_number = '57355253'));


INSERT INTO `medical_management_system`.`contact_info` ( `email`, `phone_number`, `slack_username`) VALUES ('romeisaaliu@gmail.com', '4728337482', 'romeisal');
INSERT INTO `medical_management_system`.`contact_info` ( `email`, `phone_number`, `slack_username`) VALUES ( 'sildiricku@gmail.com', '8348923612', 'sildiR');

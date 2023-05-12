use medical_management_system;
#Insert into Role

INSERT INTO `medical_management_system`.`role` (`roles`) VALUES ('DOCTOR');
INSERT INTO `medical_management_system`.`role` (`roles`) VALUES ('PATIENT');


#Inser into Users
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Steve Rogers', '4728337482');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Tony Stark', '8348923612');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Natasha Romanoff', '92362184');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Peggy Carter ', '547423093');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Romeisa Aliu', '003834252');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Klejda Rrapaj', '57239145');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Sildi Ricku', '93482663');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Carlos Sainz', '3627183');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Pablus Pabliuus', '57355253');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Francesco Totti', '298342184');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Matt Smith', '473829742');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Wanda Maximoff', '76538291');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Max Verstappen', '5738219353');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Charles Leclerc', '83728264');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Daniel  Ricciardo', '8038472');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Sebastian Vettel', '58384734');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Fernando Alonso', '85232244');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Lewis Hamilton', '1234567');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Lando Norris', '85634283');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Carol Denvers', '462827421');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Gamora Quill', '000394821');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Sherlock Holmes', '111482231');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Gregory House', '222333444');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Stephen Strange', '8884443222');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('John Watson', '999323333');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Irene Adler', '0002222111');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('John Smith', '373163982');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('James Moriarty', '32223833');
INSERT INTO `medical_management_system`.`users` ( `full_name`, `phone_number`) VALUES ('Thanos Lang', '212221114');

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


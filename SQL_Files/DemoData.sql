use
medical_management_system;
#Insert into authority
INSERT INTO `medical_management_system`.`authority` (`authority`) VALUES ('ADMIN');
INSERT INTO `medical_management_system`.`authority` (`authority`) VALUES ('USER');

#Insert into Role

INSERT INTO `medical_management_system`.`role` (`roles`) VALUES ('DOCTOR');
INSERT INTO `medical_management_system`.`role` (`roles`) VALUES ('PATIENT');

#INSERT into contact_info
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-7890', 's1');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-7891', 's2');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-7892', 's3');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-7893', 's4');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ('123-456-7894', 's5');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-7895', 's6');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-7896', 's7');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-7897', 's8');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-7898', 's9');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-7899', 's10');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78910', 's11');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78911', 's12');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78912', 's13');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78913', 's14');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78914', 's15');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78915', 's16');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78916', 's17');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78917', 's18');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78918', 's19');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78919', 's20');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78920', 's21');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78921', 's22');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78922', 's23');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78923', 's24');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78924', 's25');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78925', 's26');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78926', 's27');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78927', 's28');
INSERT INTO `medical_management_system`.`contact_info` (phone_number, slack_Username)
VALUES ( '123-456-78928', 's29');


#Insert into UserDetails
INSERT INTO `medical_management_system`.`user_details` ( `birth_date`,`full_name`,`id_medical_card`,`contact_info_id`,`authority_id`) VALUES ( '1990-05-15','Steve Rogers',  '0837462958176432',(SELECT id FROM contact_info WHERE slack_username = 's1'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1985-09-28', 'Tony Stark', '4536912702841967',(SELECT id FROM contact_info WHERE slack_username = 's2'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1988-07-03', 'Natasha Romanoff', '1468295710397256',(SELECT id FROM contact_info WHERE slack_username = 's3'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1982-11-20', 'Peggy Carter', '3208941659278413',(SELECT id FROM contact_info WHERE slack_username = 's4'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1995-04-10', 'Romeisa Aliu', '5736489215649132',(SELECT id FROM contact_info WHERE slack_username = 's5'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1998-12-07', 'Klejda Rrapaj', '7904825613249051',(SELECT id FROM contact_info WHERE slack_username = 's6'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1992-02-25', 'Sildi Ricku', '8695472134059328',(SELECT id FROM contact_info WHERE slack_username = 's7'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1993-08-30', 'Carlos Sainz', '2654389710296548',(SELECT id FROM contact_info WHERE slack_username = 's8'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1997-06-14', 'Pablus Pabliuus', '7641852093765412',(SELECT id FROM contact_info WHERE slack_username = 's9'),(SELECT id FROM authority WHERE authority = 'ADMIN'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1989-03-07', 'Francesco Totti', '9385721465029164',(SELECT id FROM contact_info WHERE slack_username = 's10'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1987-01-12', 'Matt Smith', '4591783426138956',(SELECT id FROM contact_info WHERE slack_username = 's11'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1991-10-02', 'Wanda Maximoff', '6329485107945308',(SELECT id FROM contact_info WHERE slack_username = 's12'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1994-09-20', 'Max Verstappen', '8150246794832169',(SELECT id FROM contact_info WHERE slack_username = 's13'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1998-06-23', 'Charles Leclerc', '1374298658102397',(SELECT id FROM contact_info WHERE slack_username = 's14'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1990-07-29', 'Daniel Ricciardo', '2480935467102834',(SELECT id FROM contact_info WHERE slack_username = 's15'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1993-04-17', 'Sebastian Vettel', '7946210385627104',(SELECT id FROM contact_info WHERE slack_username = 's16'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1986-02-06', 'Fernando Alonso', '9052638174612957',(SELECT id FROM contact_info WHERE slack_username = 's17'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1984-08-14', 'Lewis Hamilton', '4728135960847623',(SELECT id FROM contact_info WHERE slack_username = 's18'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1999-11-13', 'Lando Norris', '6514907328465201',(SELECT id FROM contact_info WHERE slack_username = 's19'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1996-12-25', 'Carol Denvers', '8243156789024175',(SELECT id FROM contact_info WHERE slack_username = 's20'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1983-05-01', 'Gamora Quill', '1657432987815023',(SELECT id FROM contact_info WHERE slack_username = 's21'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1978-09-09', 'Sherlock Holmes', '3298174650281796',(SELECT id FROM contact_info WHERE slack_username = 's22'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1975-11-11', 'Gregory House', '5021948317562049',(SELECT id FROM contact_info WHERE slack_username = 's23'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1980-01-03', 'Stephen Strange', '7865123490671345',(SELECT id FROM contact_info WHERE slack_username = 's24'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1997-07-18', 'John Watson', '9362107542815637',(SELECT id FROM contact_info WHERE slack_username = 's25'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1991-03-27', 'Irene Adler', '4103985274618392',(SELECT id FROM contact_info WHERE slack_username = 's26'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1988-10-08', 'John Smith', '6820493715269301',(SELECT id FROM contact_info WHERE slack_username = 's27'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1977-12-22', 'James Moriarty', '9574032168213496',(SELECT id FROM contact_info WHERE slack_username = 's28'),(SELECT id FROM authority WHERE authority = 'USER'));
INSERT INTO `medical_management_system`.`user_details` (`birth_date`, `full_name`, `id_medical_card`,`contact_info_id`,`authority_id`)
VALUES ('1971-06-28', 'Thanos Lang', '1068294157832049',(SELECT id FROM contact_info WHERE slack_username = 's29'),(SELECT id FROM authority WHERE authority = 'USER'));

#Insert into User
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
('sildiricku13@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '0837462958176432' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('sildiricku33@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '4536912702841967' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('sildiricku23@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '1468295710397256' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('sildiricku37@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '3208941659278413' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('sildiricku39@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '5736489215649132' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('sildiricku31@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '7904825613249051' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('sildiricku30@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '8695472134059328' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('sildiricku32@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '2654389710296548' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('sildiricku3@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '7641852093765412' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('kleaprifti21@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '9385721465029164' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('kleaprifti221@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '4591783426138956' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('kleaprifti231@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '6329485107945308' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('kleaprifti241@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '8150246794832169' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('kleaprifti251@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '1374298658102397' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('kleaprifti261@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '2480935467102834' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('kleaprifti271@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '7946210385627104' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('kleaprifti281@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '9052638174612957' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('kleaprifti291@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '4728135960847623' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('kleaprifti321@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '6514907328465201' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('romeisaaliu1@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '8243156789024175' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('romeisaaliu12@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '1657432987815023' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('romeisaaliu13@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '3298174650281796' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('romeisaaliu14@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '5021948317562049' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('romeisaaliu15@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '7865123490671345' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('romeisaaliu16@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '9362107542815637' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('romeisaaliu17@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '4103985274618392' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('romeisaaliu18@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '6820493715269301' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('romeisaaliu19@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '9574032168213496' ));
INSERT INTO `medical_management_system`.`user` (email, password, user_details_id)
VALUES
    ('romeisaaliu123@gmail.com', '$2a$12$pHMpmnZko3UGLPzdIZwnRO05oodj/ygp6v1cfB/Ic0nKt1hymEHP2', (SELECT id FROM user_details WHERE id_medical_card = '1068294157832049' ));


#Assign role to user

INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '0837462958176432'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '4536912702841967'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '1468295710397256'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '3208941659278413'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '5736489215649132'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '7904825613249051'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '8695472134059328'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '2654389710296548'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '7641852093765412'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '9385721465029164'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '4591783426138956'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '6329485107945308'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '8150246794832169'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '1374298658102397'), (SELECT id FROM role WHERE roles = 'DOCTOR'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '2480935467102834'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '7946210385627104'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '9052638174612957'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '4728135960847623'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '6514907328465201'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '8243156789024175'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '1657432987815023'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '3298174650281796'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '5021948317562049'), (SELECT id FROM role WHERE roles = 'DOCTOR'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '7865123490671345'), (SELECT id FROM role WHERE roles = 'DOCTOR'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '9362107542815637'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '4103985274618392'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '6820493715269301'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '9574032168213496'), (SELECT id FROM role WHERE roles = 'PATIENT'));
INSERT INTO `medical_management_system`.`user_role` (`user_id`, `role_id`)
VALUES ((SELECT id FROM user_details WHERE id_medical_card = '1068294157832049'), (SELECT id FROM role WHERE roles = 'PATIENT'));

#Insert into speciality
INSERT INTO `medical_management_system`.`speciality` (`name`) VALUES ('General');
INSERT INTO `medical_management_system`.`speciality` (`name`)
VALUES ('Pediatrican');
INSERT INTO `medical_management_system`.`speciality` (`name`)
VALUES ('Cardiology');

#Insert into user_speciality

INSERT INTO `medical_management_system`.`user_speciality` (`user_id`, `speciality_id`) VALUES ((SELECT id FROM user_details WHERE full_name='Charles Leclerc'),(SELECT id FROM speciality WHERE name = 'General'));
INSERT INTO `medical_management_system`.`user_speciality` (`user_id`, `speciality_id`)
VALUES ((SELECT id FROM user_details WHERE full_name = 'Gregory House'),
        (SELECT id FROM speciality WHERE name = 'Pediatrican'));
INSERT INTO `medical_management_system`.`user_speciality` (`user_id`, `speciality_id`)
VALUES ((SELECT id FROM user_details WHERE full_name = 'Stephen Strange'),
        (SELECT id FROM speciality WHERE name = 'Cardiology'));


#Insert into Notification Type
INSERT INTO `medical_management_system`.`user_notification_type` (`notification_type`) VALUES ('WHATSAPP');
INSERT INTO `medical_management_system`.`user_notification_type` (`notification_type`)
VALUES ('EMAIL');
INSERT INTO `medical_management_system`.`user_notification_type` (`notification_type`)
VALUES ('SLACK');

#Map User notification preference to user
INSERT INTO `medical_management_system`.`user_notification_mapping` (`user_id`, `notification_id`) VALUES ((SELECT id FROM user_details WHERE full_name='Charles Leclerc'),(SELECT notification_id FROM user_notification_type WHERE notification_type = 'WHATSAPP'));
INSERT INTO `medical_management_system`.`user_notification_mapping` (`user_id`, `notification_id`)
VALUES ((SELECT id FROM user_details WHERE full_name = 'Gregory House'),
        (SELECT notification_id FROM user_notification_type WHERE notification_type = 'EMAIL'));
INSERT INTO `medical_management_system`.`user_notification_mapping` (`user_id`, `notification_id`)
VALUES ((SELECT id FROM user_details WHERE full_name = 'Gregory House'),
        (SELECT notification_id FROM user_notification_type WHERE notification_type = 'EMAIL'));
INSERT INTO `medical_management_system`.`user_notification_mapping` (`user_id`, `notification_id`)
VALUES ((SELECT id FROM user_details WHERE full_name = 'Stephen Strange'),
        (SELECT notification_id FROM user_notification_type WHERE notification_type = 'SLACK'));
INSERT INTO `medical_management_system`.`user_notification_mapping` (`user_id`, `notification_id`)
VALUES ((SELECT id FROM user_details WHERE full_name = 'Stephen Strange'),
        (SELECT notification_id FROM user_notification_type WHERE notification_type = 'EMAIL'));


#Insert into Appointments
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`, `doctor_id`, `patient_id`)
    VALUES ('2023-07-28 10:00:00', '2023-07-28 09:00:00', (SELECT id FROM user_details WHERE full_name='Charles Leclerc') ,(SELECT id FROM user_details WHERE id_medical_card = '0837462958176432'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`,
                                                        `doctor_id`, `patient_id`)
VALUES ('2023-07-30 12:00:00', '2023-07-30 11:00:00', (SELECT id FROM user_details WHERE full_name = 'Charles Leclerc'),
        (SELECT id FROM user_details WHERE id_medical_card = '4536912702841967'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`,
                                                        `doctor_id`, `patient_id`)
VALUES ('2023-08-13 14:00:00', '2023-08-13 13:00:00', (SELECT id FROM user_details WHERE full_name = 'Charles Leclerc'),
        (SELECT id FROM user_details WHERE id_medical_card = '1468295710397256'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`,
                                                        `doctor_id`, `patient_id`)
VALUES ('2023-07-29 16:00:00', '2023-07-29 15:00:00', (SELECT id FROM user_details WHERE full_name = 'Gregory House'),
        (SELECT id FROM user_details WHERE id_medical_card = '3208941659278413'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`,
                                                        `doctor_id`, `patient_id`)
VALUES ('2023-07-27 18:00:00', '2023-07-27 17:00:00', (SELECT id FROM user_details WHERE full_name = 'Gregory House'),
        (SELECT id FROM user_details WHERE id_medical_card = '5736489215649132'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`,
                                                        `doctor_id`, `patient_id`)
VALUES ('2023-08-08 11:30:00', '2023-08-08 10:30:00', (SELECT id FROM user_details WHERE full_name = 'Gregory House'),
        (SELECT id FROM user_details WHERE id_medical_card = '7904825613249051'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`,
                                                        `doctor_id`, `patient_id`)
VALUES ('2023-07-28 12:45:00', '2023-07-28 11:45:00', (SELECT id FROM user_details WHERE full_name = 'Stephen Strange'),
        (SELECT id FROM user_details WHERE id_medical_card = '8695472134059328'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`,
                                                        `doctor_id`, `patient_id`)
VALUES ('2023-08-18 13:10:00', '2023-08-18 12:10:00', (SELECT id FROM user_details WHERE full_name = 'Stephen Strange'),
        (SELECT id FROM user_details WHERE id_medical_card = '2654389710296548'));
INSERT INTO `medical_management_system`.`appointments` (`appointment_date_end_time`, `appointment_date_start_time`,
                                                        `doctor_id`, `patient_id`)
VALUES ('2023-08-18 16:00:00', '2023-08-18 15:00:00', (SELECT id FROM user_details WHERE full_name = 'Stephen Strange'),
        (SELECT id FROM user_details WHERE id_medical_card = '7641852093765412'));

#Insert into doctor availability

INSERT INTO `medical_management_system`.`doctor_availability` ( `end_time`, `start_time`) VALUES ('14:00:00', '08:00:00');
INSERT INTO `medical_management_system`.`doctor_availability` ( `end_time`, `start_time`) VALUES ( '18:00:00', '08:00:00');
INSERT INTO `medical_management_system`.`doctor_availability` ( `end_time`, `start_time`) VALUES ('21:00:00', '15:00:00');
INSERT INTO `medical_management_system`.`doctor_availability` ( `end_time`, `start_time`) VALUES ( '15:00:00', '09:00:00');
INSERT INTO `medical_management_system`.`doctor_availability` (`end_time`, `start_time`) VALUES ('22:00:00', '16:00:00');
INSERT INTO `medical_management_system`.`doctor_availability` ( `end_time`, `start_time`) VALUES ( '05:00:00', '21:00:00');
INSERT INTO `medical_management_system`.`doctor_availability` (`end_time`, `start_time`) VALUES ( '01:00:00', '17:00:00');

#Insert into doctor holidays
INSERT INTO `medical_management_system`.`doctor_holidays` ( `holiday_date`, `holiday_name`) VALUES ( '2023-12-25', 'Christmas');
INSERT INTO `medical_management_system`.`doctor_holidays` ( `holiday_date`, `holiday_name`) VALUES ( '2023-01-01', 'New Years Day');
INSERT INTO `medical_management_system`.`doctor_holidays` ( `holiday_date`, `holiday_name`) VALUES ( '2024-03-31', 'Easter');
INSERT INTO `medical_management_system`.`doctor_holidays` ( `holiday_date`, `holiday_name`) VALUES ( '2024-05-01', 'Labor Day');

#Insert into doctor working days
INSERT INTO medical_management_system.doctor_working_days (doctor_availability_id, working_day) VALUES ((SELECT id FROM doctor_availability WHERE end_time = '14:00:00'), 'MONDAY');
INSERT INTO medical_management_system.doctor_working_days (doctor_availability_id, working_day) VALUES ((SELECT id FROM doctor_availability WHERE end_time = '22:00:00'), 'MONDAY');
INSERT INTO medical_management_system.doctor_working_days (doctor_availability_id, working_day) VALUES ((SELECT id FROM doctor_availability WHERE end_time = '18:00:00'), 'WEDNESDAY');
INSERT INTO medical_management_system.doctor_working_days (doctor_availability_id, working_day) VALUES ((SELECT id FROM doctor_availability WHERE end_time = '01:00:00'), 'WEDNESDAY');
INSERT INTO medical_management_system.doctor_working_days (doctor_availability_id, working_day) VALUES ((SELECT id FROM doctor_availability WHERE end_time = '21:00:00'), 'TUESDAY');
INSERT INTO medical_management_system.doctor_working_days (doctor_availability_id, working_day) VALUES ((SELECT id FROM doctor_availability WHERE end_time = '15:00:00'), 'THURSDAY');
INSERT INTO medical_management_system.doctor_working_days (doctor_availability_id, working_day) VALUES ((SELECT id FROM doctor_availability WHERE end_time = '05:00:00'), 'FRIDAY');
INSERT INTO medical_management_system.doctor_working_days (doctor_availability_id, working_day) VALUES ((SELECT id FROM doctor_availability WHERE end_time = '18:00:00'), 'SATURDAY');
INSERT INTO medical_management_system.doctor_working_days (doctor_availability_id, working_day) VALUES ((SELECT id FROM doctor_availability WHERE end_time = '01:00:00'), 'SUNDAY');

#Insert into user availability
INSERT INTO medical_management_system.user_availability (user_id, doctor_availability_id) VALUES ((SELECT id FROM user_details WHERE full_name='Charles Leclerc'), (SELECT id FROM doctor_availability WHERE end_time = '14:00:00'));
INSERT INTO medical_management_system.user_availability (user_id, doctor_availability_id) VALUES ((SELECT id FROM user_details WHERE full_name='Gregory House'), (SELECT id FROM doctor_availability WHERE end_time = '18:00:00'));
INSERT INTO medical_management_system.user_availability (user_id, doctor_availability_id) VALUES ((SELECT id FROM user_details WHERE full_name='Charles Leclerc'), (SELECT id FROM doctor_availability WHERE end_time = '22:00:00'));
INSERT INTO medical_management_system.user_availability (user_id, doctor_availability_id) VALUES ((SELECT id FROM user_details WHERE full_name='Stephen Strange'), (SELECT id FROM doctor_availability WHERE end_time = '21:00:00'));
INSERT INTO medical_management_system.user_availability (user_id, doctor_availability_id) VALUES ((SELECT id FROM user_details WHERE full_name='Charles Leclerc'), (SELECT id FROM doctor_availability WHERE end_time = '15:00:00'));
INSERT INTO medical_management_system.user_availability (user_id, doctor_availability_id) VALUES ((SELECT id FROM user_details WHERE full_name='Stephen Strange'), (SELECT id FROM doctor_availability WHERE end_time = '01:00:00'));
INSERT INTO medical_management_system.user_availability (user_id, doctor_availability_id) VALUES ((SELECT id FROM user_details WHERE full_name='Gregory House'), (SELECT id FROM doctor_availability WHERE end_time = '05:00:00'));


#Insert into user holidays

INSERT INTO medical_management_system.user_holidays (user_id, holiday_id) VALUES ((SELECT id FROM user_details WHERE full_name='Charles Leclerc'), (SELECT id FROM doctor_holidays WHERE holiday_name='Christmas'));
INSERT INTO medical_management_system.user_holidays (user_id, holiday_id) VALUES ((SELECT id FROM user_details WHERE full_name='Gregory House'), (SELECT id FROM doctor_holidays WHERE holiday_name='Christmas'));
INSERT INTO medical_management_system.user_holidays (user_id, holiday_id) VALUES ((SELECT id FROM user_details WHERE full_name='Stephen Strange'), (SELECT id FROM doctor_holidays WHERE holiday_name='Christmas'));
INSERT INTO medical_management_system.user_holidays (user_id, holiday_id) VALUES ((SELECT id FROM user_details WHERE full_name='Charles Leclerc'), (SELECT id FROM doctor_holidays WHERE holiday_name='New Years Day'));
INSERT INTO medical_management_system.user_holidays (user_id, holiday_id) VALUES ((SELECT id FROM user_details WHERE full_name='Gregory House'), (SELECT id FROM doctor_holidays WHERE holiday_name='New Years Day'));
INSERT INTO medical_management_system.user_holidays (user_id, holiday_id) VALUES ((SELECT id FROM user_details WHERE full_name='Stephen Strange'), (SELECT id FROM doctor_holidays WHERE holiday_name='New Years Day'));
INSERT INTO medical_management_system.user_holidays (user_id, holiday_id) VALUES ((SELECT id FROM user_details WHERE full_name='Charles Leclerc'), (SELECT id FROM doctor_holidays WHERE holiday_name='Easter'));
INSERT INTO medical_management_system.user_holidays (user_id, holiday_id) VALUES ((SELECT id FROM user_details WHERE full_name='Gregory House'), (SELECT id FROM doctor_holidays WHERE holiday_name='Easter'));
INSERT INTO medical_management_system.user_holidays (user_id, holiday_id) VALUES ((SELECT id FROM user_details WHERE full_name='Charles Leclerc'), (SELECT id FROM doctor_holidays WHERE holiday_name='Labor Day'));
INSERT INTO medical_management_system.user_holidays (user_id, holiday_id) VALUES ((SELECT id FROM user_details WHERE full_name='Gregory House'), (SELECT id FROM doctor_holidays WHERE holiday_name='Labor Day'));
INSERT INTO medical_management_system.user_holidays (user_id, holiday_id) VALUES ((SELECT id FROM user_details WHERE full_name='Stephen Strange'), (SELECT id FROM doctor_holidays WHERE holiday_name='Labor Day'));
use medical_management_system;
#delete

SET SQL_SAFE_UPDATES = 0;
DELETE FROM `appointments`;
DELETE FROM `user_speciality`;
DELETE FROM `user_role`;
DELETE FROM `speciality`;
DELETE FROM `role`;
DELETE FROM `user_notification_mapping`;
DELETE FROM `user_notification_type`;
DELETE from `user_availability`;
DELETE from `user_holidays`;
DELETE FROM `user`;
DELETE FROM `user_details`;
DELETE FROM `contact_info`;
DELETE from `doctor_working_days`;
DELETE from `doctor_availability`;
DELETE from `doctor_holidays`;
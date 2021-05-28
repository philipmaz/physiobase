CREATE DATABASE `physiobase`
CHARACTER SET utf8
COLLATE utf8_general_ci;

USE `physiobase`;

SHOW tables;

USE physiobase
;

SHOW tables;

SELECT * FROM visits;
SELECT * FROM trainings;
SELECT * FROM patients;
SELECT * FROM patients_visits;
SELECT * FROM patients_trainings;

INSERT INTO patients_visits VALUES (2,1);

DELETE FROM trainings ;
DELETE FROM patients_trainings ;

ALTER TABLE patients_visits ADD CONSTRAINT `FKpx8d62dad3t9aw0hc81ob6ot4` FOREIGN KEY ('visits_id') REFERENCES visits ('visits_id');


SELECT * FROM visits v JOIN patients_visits pv ON v.id=pv.visits_id;
DELETE v FROM visits v JOIN patients_visits pv ON v.id=pv.visits_id WHERE v.id=19;

DELETE FROM `visits` WHERE `id` =19 LIMIT 1;

SET FOREIGN_KEY_CHECKS=0;
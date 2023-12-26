DROP DATABASE IF EXISTS JTraining;
CREATE DATABASE JTraining;
USE JTraining;
CREATE TABLE tExercise (
	id int not null auto_increment PRIMARY KEY,
    name varchar(255)
);
CREATE TABLE tTrack (
	id int not null auto_increment PRIMARY KEY,
	exercise_id int not null,
    name varchar(255),
    date date,
    weight int,
    RPE int,
    FOREIGN KEY (exercise_id) REFERENCES tExercise(id) on DELETE cascade 
);
Insert into tExercise (name) values ("Benchpress");
Insert into tTrack (exercise_id, name, date, weight, RPE) VALUES (1, "KEVIN", "2022-12-11", 110, 10)

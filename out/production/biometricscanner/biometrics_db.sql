--
-- Database: `biometrics_db`
--
DROP DATABASE IF EXISTS `biometrics_db`;
CREATE DATABASE IF NOT EXISTS `biometrics_db`;
USE `biometrics_db`;


-- User Table
DROP TABLE IF EXISTS admin;
CREATE TABLE IF NOT EXISTS admin(adminId int primary key, adminName varchar(25), password varchar(25));

-- Class Table
DROP TABLE IF EXISTS class;
CREATE TABLE IF NOT EXISTS class(classId int primary key, className varchar(25));

-- Student Table
DROP TABLE IF EXISTS student;
CREATE TABLE IF NOT EXISTS student(studentId int primary key, studentName varchar(25), class varchar(10), password varchar(25));

-- Lecturer Table
DROP TABLE IF EXISTS lecturer;
CREATE TABLE IF NOT EXISTS lecturer(lecId int primary key, lecName varchar(25), password varchar(50));

-- Attendance Table
DROP TABLE IF EXISTS attendance;
CREATE TABLE IF NOT EXISTS attendance(studentId int, dt date, status varchar(15), class varchar(15));

-- Creating admin user
INSERT INTO admin VALUES(100, 'admin', 'Admin');


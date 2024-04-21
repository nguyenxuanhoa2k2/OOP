CREATE DATABASE db;

CREATE TABLE `admintable` (
  `adminID` INT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `dob` date DEFAULT NULL,
  `address` VARCHAR(100) NOT NULL,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`adminID`)
);

LOCK TABLES `admintable` WRITE;
INSERT INTO `admintable` VALUES (1101,'Nguyen Van A','1980-01-01','Ha Noi','admin','admin');
UNLOCK TABLES;

CREATE TABLE `teachers` (
  `name` varchar(50) DEFAULT NULL,
  `teacherID` int NOT NULL,
  `dob` date DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `qualification` varchar(50) DEFAULT NULL,
  `teachingSubject` varchar(50) DEFAULT NULL,
  `teachingCourse` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`teacherID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `students` (
  `name` varchar(50) DEFAULT NULL,
  `studentID` int NOT NULL,
  `dob` date DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`studentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `courses` (
  `courseName` varchar(45) NOT NULL,
  `courseCode` int NOT NULL,
  `subject` varchar(45) NOT NULL,
  `schedule` varchar(45) NOT NULL,
  `teacherID` int NOT NULL,
  `maxStudents` int NOT NULL,
  `remainingStudents` int NOT NULL,
  PRIMARY KEY (`courseCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE enrollments (
    enrollmentID INT NOT NULL AUTO_INCREMENT,
    studentID int NOT NULL,
    courseCode int NOT NULL,
    midtermGrade DOUBLE,
    finalGrade DOUBLE,
    averageGrade DOUBLE,
    PRIMARY KEY (enrollmentID),
    FOREIGN KEY (studentID) REFERENCES students(studentID),
    FOREIGN KEY (courseCode) REFERENCES courses(courseCode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


select*from admintable;
select*from teachers;
select*from students;
select*from courses;
select*from enrollments;

CREATE DATABASE db;

CREATE TABLE `admintable` (
  `MANV` int NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MANV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `admintable` WRITE;
INSERT INTO `admintable` VALUES (1101,'admin','123456');
UNLOCK TABLES;

CREATE TABLE `teacher` (
  `MANV` int NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MANV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `teacher` WRITE;
INSERT INTO `teacher` VALUES (350,'tung','123456');
UNLOCK TABLES;

CREATE TABLE `student` (
  `MANV` int NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MANV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `student` WRITE;
INSERT INTO `student` VALUES (158,'hoa','123456');
UNLOCK TABLES;

CREATE TABLE `courses` (
  `course_id` int NOT NULL,
  `course_name` varchar(45) NOT NULL,
  `fee` decimal(10,2) NOT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



select*from admintable;
select*from teacher;
select*from student;
select*from courses;
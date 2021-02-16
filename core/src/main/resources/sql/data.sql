SET
    foreign_key_checks = 0;


DROP TABLE IF EXISTS `address`;

CREATE TABLE `address`
(
    `addr_id` int         NOT NULL AUTO_INCREMENT,
    `street`  varchar(50) NOT NULL,
    `city`    varchar(50) NOT NULL,
    `state`   varchar(50) NOT NULL,
    `zip`     varchar(10) DEFAULT NULL,
    `country` varchar(50) NOT NULL,
    PRIMARY KEY (`addr_id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4;


INSERT INTO `address`
VALUES (1, '4891 Pacific Hwy', 'San Diego', 'CA', '92110', 'San Diego'),
       (2, '2400 N Jefferson St', 'Perry', 'FL', '32347', 'Taylor'),
       (3, '710 N Cable Rd', 'Lima', 'OH', '45825', 'Allen'),
       (4, '5108 W Gore Blvd', 'Lawton', 'OK', '32365', 'Comanche');


DROP TABLE IF EXISTS `student`;

CREATE TABLE `student`
(
    `stud_id` int         NOT NULL AUTO_INCREMENT,
    `name`    varchar(50) NOT NULL,
    `email`   varchar(50) NOT NULL,
    `phone`   varchar(15) DEFAULT NULL,
    `dob`     date        DEFAULT NULL,
    `bio`     longtext,
    `pic`     blob,
    `addr_id` int         DEFAULT NULL,
    PRIMARY KEY (`stud_id`),
    UNIQUE KEY (`email`),
    FOREIGN KEY (`addr_id`) REFERENCES `address` (`addr_id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4;


INSERT INTO `student`
VALUES (1, 'Timothy', 'timothy@gmail.com', '123-123-1234', '1988-04-25', NULL, NULL, 3),
       (2, 'Douglas', 'douglas@gmail.com', '789-456-1234', '1990-08-15', NULL, NULL, 4);



DROP TABLE IF EXISTS `tutor`;

CREATE TABLE `tutor`
(
    `tutor_id` int         NOT NULL AUTO_INCREMENT,
    `name`     varchar(50) NOT NULL,
    `email`    varchar(50) NOT NULL,
    `phone`    varchar(15) DEFAULT NULL,
    `dob`      date        DEFAULT NULL,
    `bio`      longtext,
    `pic`      blob,
    `addr_id`  int         DEFAULT NULL,
    PRIMARY KEY (`tutor_id`),
    UNIQUE KEY (`email`),
    FOREIGN KEY (`addr_id`) REFERENCES `address` (`addr_id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4;


INSERT INTO `tutor`
VALUES (1, 'sivaprasad', 'sivaprasad@gmail.com', '111-222-3333', '1980-05-20', NULL, NULL, 1),
       (2, 'Paul', 'paul@gmail.com', '123-321-4444', '1981-03-15', NULL, NULL, 2),
       (3, 'siva', 'siva@gmail.com', NULL, NULL, NULL, NULL, NULL);


DROP TABLE IF EXISTS `course`;

CREATE TABLE `course`
(
    `course_id`   int          NOT NULL AUTO_INCREMENT,
    `name`        varchar(100) NOT NULL,
    `description` varchar(512) DEFAULT NULL,
    `start_date`  date         DEFAULT NULL,
    `end_date`    date         DEFAULT NULL,
    `tutor_id`    int          NOT NULL,
    PRIMARY KEY (`course_id`),
    KEY (`tutor_id`),
    FOREIGN KEY (`tutor_id`) REFERENCES `tutor` (`tutor_id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4;


INSERT INTO `course`
VALUES (1, 'Quickstart Core Java', 'Core Java Programming', '2013-03-01', '2013-04-15', 1),
       (2, 'Quickstart JavaEE6', 'Enterprise App Development using JavaEE6', '2013-04-01', '2013-08-30', 1),
       (3, 'MyBatis3 Premier', 'MyBatis 3 framework', '2013-06-01', '2013-07-15', 2);


DROP TABLE IF EXISTS `course_enrollment`;

CREATE TABLE `course_enrollment`
(
    `course_id` int NOT NULL,
    `stud_id`   int NOT NULL,
    PRIMARY KEY (`course_id`, `stud_id`),
    KEY (`stud_id`),
    FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
    FOREIGN KEY (`stud_id`) REFERENCES `student` (`stud_id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4;


INSERT INTO `course_enrollment`
VALUES (1, 1),
       (1, 2),
       (2, 2);

SET
    foreign_key_checks = 1;

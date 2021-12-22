DROP TABLE IF EXISTS TBL_STUDENTS;

CREATE TABLE TBL_STUDENTS (
  student_id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  department VARCHAR(250) NOT NULL,
  country VARCHAR(250) DEFAULT NULL
);


CREATE TABLE if not exists users(
  user_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
);
 
//CREATE TABLE if not exists roles (
//  role_id int(11) NOT NULL AUTO_INCREMENT,
//  role varchar(45) NOT NULL,
//  PRIMARY KEY (`id`)
//#);
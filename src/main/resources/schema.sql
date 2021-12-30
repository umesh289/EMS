DROP TABLE IF EXISTS employees;

CREATE TABLE employees (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  firstname VARCHAR(250) NOT NULL,
  lastname  VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);


CREATE TABLE if not exists users(
  user_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
);
 
CREATE TABLE if not exists roles (
  role_id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

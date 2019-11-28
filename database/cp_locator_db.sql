--creates CP Locator database and tables

CREATE DATABASE cp_locator;

CREATE TABLE classes (
  class_id int NOT NULL AUTO_INCREMENT,
  class_name varchar(255) NOT NULL,
  class_code varchar(255) NOT NULL,  --this is the class passphrase (validation)
  PRIMARY KEY (class_id)
);

CREATE TABLE cps (
  cp_id int NOT NULL AUTO_INCREMENT,
  cp_name varchar(255) NOT NULL,  --will return this for interfacing
  cp_email varchar(255) NOT NULL,  --login w/ email and password?
  cp_password varchar(255) NOT NULL,
  PRIMARY KEY (cp_id)
);

CREATE TABLE current_cps (
  FOREIGN KEY (cp_id) REFERENCES cps(cp_id),
  FOREIGN KEY (class_id) REFERENCES classes(class_id)
);

CREATE TABLE student_queue (
  student_id int NOT NULL AUTO_INCREMENT,
  student_name varchar(255) NOT NULL,
  student_email varchar(255),  --allowed to submit w/o email
  FOREIGN KEY (class_id) REFERENCES classes(class_id)
  PRIMARY KEY (student_id)
);

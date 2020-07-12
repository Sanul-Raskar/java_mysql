# CURD operations using MySQL and Java Swing

This repository is basic example to perform curd operations using Java swing and mysql database.

## Create new mysql user
``` sql
CREATE USER 'newuser'@'localhost' IDENTIFIED BY ‘password';

GRANT ALL PRIVILEGES ON * . * TO 'newuser'@'localhost';

FLUSH PRIVILEGES;
```


## Start sql server
``` bash
$ mysql.server start
$ brew services start mysql

$ mysql -u root -p
$ mysql -u sanul -p
```

## Create Database
``` sql
create database student;
use student;
```

## Create Table
``` sql
CREATE TABLE branch (
    branch_id int NOT NULL AUTO_INCREMENT,
    branch_name varchar(10) NOT NULL,
    PRIMARY KEY (branch_id)
);
```
create table branch(branch_id int not null auto_increment,branch_name varchar(10) not null, primary key(branch_id));

``` sql
describe branch;
```

``` sql
CREATE TABLE students (
    stud_id int NOT NULL AUTO_INCREMENT,
    name varchar(30) NOT NULL,
    email varchar(60), 
    mobile varchar(15),
    gender varchar(8),
    branch_id int,
    PRIMARY KEY (stud_id),
    FOREIGN KEY (branch_id) REFERENCES branch(branch_id)
);
```
create table students(stud_id int not null auto_increment, name varchar(30) not null, email varchar(60), mobile varchar(15),gender varchar(8),branch_id int, primary key(stud_id), foreign key (branch_id) references branch(branch_id));

## Insert into branch table
``` sql
insert into branch(branch_name)values('IT'),('COMP'),('ENTC'),('CIVIL'),('MECH');
```
insert into branch(branch_name)values('IT'),('COMP'),('ENTC'),('CIVIL'),('MECH');

## Create table for student count
``` sql
CREATE TABLE stats (
    id int NOT NULL,
    total_students int NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);
```

create table stats(id int not null,total_students int not null default 0, primary key(id));

## Insert into stats table
insert into stats(id)values(1);


## Download MySQL connector

https://dev.mysql.com/downloads/

https://dev.mysql.com/downloads/connector/j/

Select Connector/J
Select Operating System: Platform Independent
Download zip file
Unzip it and copy mysql-connector-java.jar file to netbeans project.

Link jar file 
Project Properties > Libraries > Compile time libraries> add jar



## AFTER INSERT Trigger
``` sql
DELIMITER $$

CREATE TRIGGER after_student_insert
AFTER INSERT
ON students FOR EACH ROW
BEGIN
DECLARE studcount INT;
SELECT COUNT(*) INTO studcount FROM students;
UPDATE stats SET total_students = studcount WHERE id =1;
END$$

DELIMITER ;
```

delimiter $$

create trigger after_student_insert after insert on students for each row begin declare studcount int; select count(*) into studcount from students; update stats set total_students = studcount where id=1; end$$

delimiter ;

## AFTER DELETE Trigger
``` sql
DELIMITER $$
CREATE TRIGGER after_student_delete
AFTER DELETE
ON students FOR EACH ROW
BEGIN
DECLARE studcount INT;
SELECT COUNT(*) INTO studcount FROM students;
UPDATE stats SET total_students = studcount WHERE id =1;
END$$

DELIMITER ;
```

delimiter $$

create trigger after_student_delete after delete on students for each row begin declare studcount int; select count(*) into studcount from students; update stats set total_students = studcount where id=1; end$$

delimiter ;
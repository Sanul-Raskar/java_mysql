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

## Insert into branch table
``` sql
insert into branch(branch_name)values('IT'),('COMP'),('ENTC'),('CIVIL'),('MECH');
```
insert into branch(branch_name)values('IT'),('COMP'),('ENTC'),('CIVIL'),('MECH');

## Create students table
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

## Insert into students table
``` sql
INSERT INTO students(name,email,mobile,gender,branch_id)values('Sanul','sanul@test.com','9884534542','Male',1);
```

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


## Retrieve data from students table
```sql
select * from students;
```

## Update data from students table
```sql
UPDATE students SET name = 'newName',email = 'new@test.com',mobile = '9845377893',gender = 'Female',branch_id = '3' WHERE stud_id = 1;
```

## Delete data from students table
```sql
DELETE FROM students WHERE stud_id = 1;
```

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


## Connection Code
```java
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public static Connection createConnection() {
        Connection con = null;
        String URL = "jdbc:mysql://localhost:3306/student";
        String user = "sanul";
        String password = "sanul123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, user, password);
            System.out.println("Connected to MySQL");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Java_mysql.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }
```

## Drop database
``` sql
DROP DATABASE databasename;
```

## Drop table
``` sql
DROP TABLE table_name;
```

## Show triggers
```sql
show triggers from student;
```

## Drop trigger
```sql
DROP TRIGGER before_billing_update;
```

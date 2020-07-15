# CURD operations using MySQL and Java Swing

This repository is basic example to perform curd operations using Java swing and mysql database.


## Start MySQL service
On Linux:
```bash
$ sudo systemctl start mysql
```
On macOS:
```zsh
% brew services start mysql
```

## Login to MySQL shell with root user
```bash
$ mysql -u root -p
```

## Create new MySQL user
``` sql
CREATE USER 'newuser'@'localhost' IDENTIFIED BY ‘password';

GRANT ALL PRIVILEGES ON * . * TO 'newuser'@'localhost';

FLUSH PRIVILEGES;
```

## Login with new user
Use newly created user in java code and while creating database.
```bash
$ mysql -u newuser -p
```

## Create Database
``` sql
create database student;
use student;
```

<p align="center">
<img src="/output/tables.png">
<h6 style="text-align:center">Tables</h6>
</p>

## Create Table
``` sql
CREATE TABLE branch (
    branch_id int NOT NULL AUTO_INCREMENT,
    branch_name varchar(10) NOT NULL,
    PRIMARY KEY (branch_id)
);
```

``` sql
describe branch;
```

## Insert into branch table
``` sql
INSERT INTO branch(branch_name)VALUES('IT'),('COMP'),('ENTC'),('CIVIL'),('MECH');
```

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

## Insert into students table
``` sql
INSERT INTO students(name,email,mobile,gender,branch_id)VALUES('Sanul','sanul@test.com','9884534542','Male',1);
```

## Create table for student count
``` sql
CREATE TABLE stats (
    id int NOT NULL,
    total_students int NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);
```

## Insert into stats table
```sql
INSERT INTO stats(id)values(1);
```

## Retrieve data from students table
```sql
SELECT * FROM students;
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

Select Connector/J
Select Operating System: Platform Independent
Download ZIP file
Unzip it and copy mysql-connector-java.jar file to netbeans project.

Link jar file 
Project Properties > Libraries > Compile time libraries > add jar



## AFTER INSERT Trigger
Update total_students value in 'stats' table whenever new record is inserted in 'students' table

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


## AFTER DELETE Trigger
Update total_students value in 'stats' table whenever a record is deleted in 'students' table

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


## Connection Code

Replace database name, username and password in Java_mysql.java
Here,
MySQL Port: 3306
Database name: student
MySQL User: sanul
MySQL Password: sanul123

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
DROP TRIGGER trigger_name;
```

## Output

<p align="center">
<img style="padding:10px" src="/output/home.png"/>
<img style="padding:10px" src="/output/createRecord.png" /> 
<img style="padding:10px" src="/output/viewCreated.png"/>
<img style="padding:10px" src="/output/updateRecord.png" width="400" /> 
<img style="padding:10px" src="/output/updateSuccess.png" width="400"/>
<img style="padding:10px" src="/output/viewUpdated.png" /> 
<img style="padding:10px" src="/output/deleteRecord.png" />
<img style="padding:10px" src="/output/viewDeleted.png" /> 
</p>
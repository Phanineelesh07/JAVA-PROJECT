Run in MySQL Command Line Client (only once):

SOURCE C:/Users/STARFIRE/Downloads/OnlineStudentResultManagementSystem/create_database.sql;
__________________________________________________________________________________________________________________________________

To navigate to project directory:

cd C:\Users\STARFIRE\Downloads\OnlineStudentResultManagementSystem
----------------------------------------------------------------------------------------------------------------------------------

To Delete old out folder to avoid stale files (deletes old class files):

if (Test-Path out) { Remove-Item -Recurse -Force out }
----------------------------------------------------------------------------------------------------------------------------------

To Compile all Java files with the MySQL connector again:

javac -cp "lib\mysql-connector-j-9.5.0.jar" -d out src\Main.java src\model\*.java src\dao\*.java src\service\*.java src\util\*.java
----------------------------------------------------------------------------------------------------------------------------------

To Run the project:

java -cp "out;lib\mysql-connector-j-9.5.0.jar" Main
__________________________________________________________________________________________________________________________________

Run in MySQL Command Line Client:

SHOW DATABASES;

USE srms_db;

SHOW TABLES;

DESCRIBE results;

DESCRIBE students;

SELECT * FROM results;

SELECT * FROM students;
__________________________________________________________________________________________________________________________________
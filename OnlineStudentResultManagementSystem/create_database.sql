CREATE DATABASE IF NOT EXISTS srms_db;
USE srms_db;

CREATE TABLE IF NOT EXISTS students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    roll_number VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS results (
    result_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    subject1_marks DECIMAL(5,2),
    subject2_marks DECIMAL(5,2),
    subject3_marks DECIMAL(5,2),
    subject4_marks DECIMAL(5,2),
    subject5_marks DECIMAL(5,2),
    total_marks DECIMAL(6,2),
    percentage DECIMAL(5,2),
    grade VARCHAR(2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE
);
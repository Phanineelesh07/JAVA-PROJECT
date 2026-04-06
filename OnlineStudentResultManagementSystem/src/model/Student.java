package model;

import java.time.LocalDateTime;

public class Student {
    private int studentId;
    private String name;
    private String rollNumber;
    private String email;
    private String phone;
    private LocalDateTime createdAt;

    public Student() {
    }

    public Student(String name, String rollNumber, String email, String phone) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.email = email;
        this.phone = phone;
    }

    public Student(int studentId, String name, String rollNumber, String email, String phone) {
        this.studentId = studentId;
        this.name = name;
        this.rollNumber = rollNumber;
        this.email = email;
        this.phone = phone;
    }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-20s | Roll: %-10s | Email: %-25s | Phone: %s",
                studentId, name, rollNumber, email, phone);
    }
}
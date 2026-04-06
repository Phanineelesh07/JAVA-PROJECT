package model;

import java.time.LocalDateTime;

public class Result {
    private int resultId;
    private int studentId;
    private double subject1Marks;
    private double subject2Marks;
    private double subject3Marks;
    private double subject4Marks;
    private double subject5Marks;
    private double totalMarks;
    private double percentage;
    private String grade;
    private LocalDateTime createdAt;

    public Result() {
    }

    public Result(int studentId, double s1, double s2, double s3, double s4, double s5) {
        this.studentId = studentId;
        this.subject1Marks = s1;
        this.subject2Marks = s2;
        this.subject3Marks = s3;
        this.subject4Marks = s4;
        this.subject5Marks = s5;
    }

    public int getResultId() { return resultId; }
    public void setResultId(int resultId) { this.resultId = resultId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public double getSubject1Marks() { return subject1Marks; }
    public void setSubject1Marks(double subject1Marks) { this.subject1Marks = subject1Marks; }

    public double getSubject2Marks() { return subject2Marks; }
    public void setSubject2Marks(double subject2Marks) { this.subject2Marks = subject2Marks; }

    public double getSubject3Marks() { return subject3Marks; }
    public void setSubject3Marks(double subject3Marks) { this.subject3Marks = subject3Marks; }

    public double getSubject4Marks() { return subject4Marks; }
    public void setSubject4Marks(double subject4Marks) { this.subject4Marks = subject4Marks; }

    public double getSubject5Marks() { return subject5Marks; }
    public void setSubject5Marks(double subject5Marks) { this.subject5Marks = subject5Marks; }

    public double getTotalMarks() { return totalMarks; }
    public void setTotalMarks(double totalMarks) { this.totalMarks = totalMarks; }

    public double getPercentage() { return percentage; }
    public void setPercentage(double percentage) { this.percentage = percentage; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return String.format("Result ID: %d | Student ID: %d | Total: %.2f | Percentage: %.2f%% | Grade: %s",
                resultId, studentId, totalMarks, percentage, grade);
    }
}
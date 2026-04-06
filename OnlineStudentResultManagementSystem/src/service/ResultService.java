package service;

import model.Result;
import model.Student;
import dao.ResultDAO;
import dao.StudentDAO;
import util.InputValidator;

public class ResultService {
    private ResultDAO resultDAO;
    private StudentDAO studentDAO;

    public ResultService() {
        this.resultDAO = new ResultDAO();
        this.studentDAO = new StudentDAO();
    }

    public String addResult(int studentId, double s1, double s2, double s3, double s4, double s5) {
        // Validate marks
        if (!InputValidator.isValidMarks(s1) || !InputValidator.isValidMarks(s2) ||
            !InputValidator.isValidMarks(s3) || !InputValidator.isValidMarks(s4) ||
            !InputValidator.isValidMarks(s5)) {
            return "All marks must be between 0 and 100";
        }

        // Calculate total and percentage
        double total = GradeCalculator.calculateTotal(s1, s2, s3, s4, s5);
        double percentage = GradeCalculator.calculatePercentage(total);
        String grade = GradeCalculator.calculateGrade(percentage);

        // Create result object
        Result result = new Result(studentId, s1, s2, s3, s4, s5);
        result.setTotalMarks(total);
        result.setPercentage(percentage);
        result.setGrade(grade);

        // Save to database
        if (resultDAO.addResult(result)) {
            return "Result saved successfully!\nTotal: " + total + " | Percentage: " + 
                   String.format("%.2f", percentage) + "% | Grade: " + grade;
        } else {
            return "Failed to save result";
        }
    }

    public Result getResultByStudentId(int studentId) {
        return resultDAO.getResultByStudentId(studentId);
    }

    public void displayResult(int studentId) {
        Result result = resultDAO.getResultByStudentId(studentId);
        if (result != null) {
            Student student = studentDAO.getStudentById(studentId);
            System.out.println("\n" + "=".repeat(70));
            System.out.println("RESULT CARD");
            System.out.println("=".repeat(70));
            System.out.println("Student Name: " + student.getName());
            System.out.println("Roll Number: " + student.getRollNumber());
            System.out.println("Email: " + student.getEmail());
            System.out.println("-".repeat(70));
            System.out.printf("Subject 1: %.2f | Subject 2: %.2f | Subject 3: %.2f | Subject 4: %.2f | Subject 5: %.2f\n",
                    result.getSubject1Marks(), result.getSubject2Marks(), result.getSubject3Marks(),
                    result.getSubject4Marks(), result.getSubject5Marks());
            System.out.println("-".repeat(70));
            System.out.printf("Total Marks: %.2f/500\n", result.getTotalMarks());
            System.out.printf("Percentage: %.2f%%\n", result.getPercentage());
            System.out.printf("Grade: %s (%s)\n", result.getGrade(), 
                    GradeCalculator.getGradeDescription(result.getGrade()));
            System.out.println("=".repeat(70) + "\n");
        } else {
            System.out.println("✗ No result found for this student");
        }
    }
}
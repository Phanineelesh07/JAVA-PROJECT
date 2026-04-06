import model.Result;
import model.Student;
import model.DatabaseConnection;
import dao.StudentDAO;
import dao.ResultDAO;
import service.ResultService;
import service.GradeCalculator;
import util.InputValidator;
import java.util.Scanner;
import java.util.List;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentDAO studentDAO = new StudentDAO();
    private static ResultDAO resultDAO = new ResultDAO();
    private static ResultService resultService = new ResultService();

    public static void main(String[] args) throws Exception {
    try {
        DatabaseConnection.getConnection();
        mainMenu();
    } finally {
        DatabaseConnection.closeConnection();
    }
}

    private static void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n" + "=".repeat(70));
            System.out.println("      ONLINE STUDENT RESULT MANAGEMENT SYSTEM (SRMS)");
            System.out.println("=".repeat(70));
            System.out.println("1. Add Student");
            System.out.println("2. Add Result");
            System.out.println("3. View Student Result");
            System.out.println("4. View All Students");
            System.out.println("5. View All Results");
            System.out.println("6. Update Student");
            System.out.println("7. Delete Student");
            System.out.println("8. Search by Grade");
            System.out.println("9. Exit");
            System.out.println("=".repeat(70));
            System.out.print("Enter your choice (1-9): ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        addResult();
                        break;
                    case 3:
                        viewStudentResult();
                        break;
                    case 4:
                        viewAllStudents();
                        break;
                    case 5:
                        viewAllResults();
                        break;
                    case 6:
                        updateStudent();
                        break;
                    case 7:
                        deleteStudent();
                        break;
                    case 8:
                        searchByGrade();
                        break;
                    case 9:
                        running = false;
                        System.out.println("\n✓ Thank you for using SRMS. Goodbye!");
                        break;
                    default:
                        System.out.println("✗ Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input. Please enter a valid number.");
            }
        }
    }

    private static void addStudent() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("ADD NEW STUDENT");
        System.out.println("-".repeat(70));

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Roll Number: ");
        String rollNumber = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone (10 digits): ");
        String phone = scanner.nextLine();

        String validationError = InputValidator.validateStudentInput(name, rollNumber, email, phone);
        if (validationError != null) {
            System.out.println("✗ " + validationError);
            return;
        }

        Student student = new Student(name, rollNumber, email, phone);
        if (studentDAO.addStudent(student)) {
            System.out.println("✓ Student added successfully!");
        } else {
            System.out.println("✗ Failed to add student");
        }
    }

    private static void addResult() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("ADD STUDENT RESULT");
        System.out.println("-".repeat(70));

        System.out.print("Enter Student Roll Number: ");
        String rollNumber = scanner.nextLine();

        Student student = studentDAO.getStudentByRollNumber(rollNumber);
        if (student == null) {
            System.out.println("✗ Student not found");
            return;
        }

        System.out.println("Student: " + student.getName());
        System.out.println("-".repeat(70));

        double[] marks = new double[5];
        String[] subjects = {"Java", "Maths", "OS", "AI", "AMSD"};

        for (int i = 0; i < 5; i++) {
            while (true) {
                System.out.print("Enter " + subjects[i] + " Marks (0-100): ");
                String input = scanner.nextLine();
                String error = InputValidator.validateMarksInput(input);
                if (error != null) {
                    System.out.println("✗ " + error);
                } else {
                    marks[i] = Double.parseDouble(input);
                    break;
                }
            }
        }

        String result = resultService.addResult(student.getStudentId(), marks[0], marks[1], marks[2], marks[3], marks[4]);
        System.out.println("✓ " + result);
    }

    private static void viewStudentResult() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("VIEW STUDENT RESULT");
        System.out.println("-".repeat(70));

        System.out.print("Enter Student Roll Number: ");
        String rollNumber = scanner.nextLine();

        Student student = studentDAO.getStudentByRollNumber(rollNumber);
        if (student == null) {
            System.out.println("✗ Student not found");
            return;
        }

        resultService.displayResult(student.getStudentId());
    }

    private static void viewAllStudents() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("ALL STUDENTS");
        System.out.println("-".repeat(70));

        List<Student> students = studentDAO.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found");
            return;
        }

        System.out.printf("%-5s | %-20s | %-15s | %-25s | %-10s\n", "ID", "Name", "Roll", "Email", "Phone");
        System.out.println("-".repeat(70));
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void viewAllResults() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("ALL RESULTS");
        System.out.println("-".repeat(70));

        List<Result> results = resultDAO.getAllResults();
        if (results.isEmpty()) {
            System.out.println("No results found");
            return;
        }

        System.out.printf("%-10s | %-12s | %-10s | %-12s | %-7s\n", "Result ID", "Student ID", "Total", "Percentage", "Grade");
        System.out.println("-".repeat(70));
        for (Result result : results) {
            System.out.printf("%-10d | %-12d | %-10.2f | %-12.2f | %-7s\n",
                    result.getResultId(), result.getStudentId(), result.getTotalMarks(),
                    result.getPercentage(), result.getGrade());
        }
    }

    private static void updateStudent() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("UPDATE STUDENT");
        System.out.println("-".repeat(70));

        System.out.print("Enter Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        Student student = studentDAO.getStudentById(studentId);
        if (student == null) {
            System.out.println("✗ Student not found");
            return;
        }

        System.out.println("Current Details: " + student);
        System.out.print("Enter New Name (or press Enter to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            student.setName(name);
        }

        System.out.print("Enter New Email (or press Enter to keep current): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            student.setEmail(email);
        }

        System.out.print("Enter New Phone (or press Enter to keep current): ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) {
            student.setPhone(phone);
        }

        if (studentDAO.updateStudent(student)) {
            System.out.println("✓ Student updated successfully!");
        } else {
            System.out.println("✗ Failed to update student");
        }
    }

    private static void deleteStudent() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("DELETE STUDENT");
        System.out.println("-".repeat(70));

        System.out.print("Enter Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Are you sure? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            if (studentDAO.deleteStudent(studentId)) {
                System.out.println("✓ Student deleted successfully!");
            } else {
                System.out.println("✗ Failed to delete student");
            }
        } else {
            System.out.println("Deletion cancelled");
        }
    }

    private static void searchByGrade() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("SEARCH RESULTS BY GRADE");
        System.out.println("-".repeat(70));

        System.out.println("Available Grades: A+, A, B+, B, C, D, F");
        System.out.print("Enter Grade: ");
        String grade = scanner.nextLine().toUpperCase();

        List<Result> results = resultDAO.getResultsByGrade(grade);
        if (results.isEmpty()) {
            System.out.println("No results found for grade: " + grade);
            return;
        }

        System.out.printf("Found %d results with grade %s\n", results.size(), grade);
        System.out.printf("%-10s | %-12s | %-10s | %-12s\n", "Result ID", "Student ID", "Total", "Percentage");
        System.out.println("-".repeat(70));
        for (Result result : results) {
            System.out.printf("%-10d | %-12d | %-10.2f | %-12.2f\n",
                    result.getResultId(), result.getStudentId(), result.getTotalMarks(), result.getPercentage());
        }
    }
}
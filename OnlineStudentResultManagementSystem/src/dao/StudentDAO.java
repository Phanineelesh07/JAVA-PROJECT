package dao;

import model.Student;
import model.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (name, roll_number, email, phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getRollNumber());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getPhone());
            
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("✗ Error adding student: " + e.getMessage());
            return false;
        }
    }

    public Student getStudentById(int studentId) {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Student student = new Student(
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("roll_number"),
                    rs.getString("email"),
                    rs.getString("phone")
                );
                return student;
            }
        } catch (SQLException e) {
            System.err.println("✗ Error retrieving student: " + e.getMessage());
        }
        return null;
    }

    public Student getStudentByRollNumber(String rollNumber) {
        String sql = "SELECT * FROM students WHERE roll_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, rollNumber);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Student student = new Student(
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("roll_number"),
                    rs.getString("email"),
                    rs.getString("phone")
                );
                return student;
            }
        } catch (SQLException e) {
            System.err.println("✗ Error retrieving student: " + e.getMessage());
        }
        return null;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Student student = new Student(
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("roll_number"),
                    rs.getString("email"),
                    rs.getString("phone")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            System.err.println("✗ Error retrieving students: " + e.getMessage());
        }
        return students;
    }

    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, email = ?, phone = ? WHERE student_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setString(3, student.getPhone());
            pstmt.setInt(4, student.getStudentId());
            
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("✗ Error updating student: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, studentId);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("✗ Error deleting student: " + e.getMessage());
            return false;
        }
    }
}
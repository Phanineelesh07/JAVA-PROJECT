package dao;

import model.Result;
import model.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO {

    public boolean addResult(Result result) {
        String sql = "INSERT INTO results (student_id, subject1_marks, subject2_marks, subject3_marks, " +
                     "subject4_marks, subject5_marks, total_marks, percentage, grade) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, result.getStudentId());
            pstmt.setDouble(2, result.getSubject1Marks());
            pstmt.setDouble(3, result.getSubject2Marks());
            pstmt.setDouble(4, result.getSubject3Marks());
            pstmt.setDouble(5, result.getSubject4Marks());
            pstmt.setDouble(6, result.getSubject5Marks());
            pstmt.setDouble(7, result.getTotalMarks());
            pstmt.setDouble(8, result.getPercentage());
            pstmt.setString(9, result.getGrade());
            
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("✗ Error adding result: " + e.getMessage());
            return false;
        }
    }

    public Result getResultByStudentId(int studentId) {
        String sql = "SELECT * FROM results WHERE student_id = ? ORDER BY created_at DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Result result = new Result();
                result.setResultId(rs.getInt("result_id"));
                result.setStudentId(rs.getInt("student_id"));
                result.setSubject1Marks(rs.getDouble("subject1_marks"));
                result.setSubject2Marks(rs.getDouble("subject2_marks"));
                result.setSubject3Marks(rs.getDouble("subject3_marks"));
                result.setSubject4Marks(rs.getDouble("subject4_marks"));
                result.setSubject5Marks(rs.getDouble("subject5_marks"));
                result.setTotalMarks(rs.getDouble("total_marks"));
                result.setPercentage(rs.getDouble("percentage"));
                result.setGrade(rs.getString("grade"));
                return result;
            }
        } catch (SQLException e) {
            System.err.println("✗ Error retrieving result: " + e.getMessage());
        }
        return null;
    }

    public List<Result> getAllResults() {
        List<Result> results = new ArrayList<>();
        String sql = "SELECT * FROM results ORDER BY created_at DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Result result = new Result();
                result.setResultId(rs.getInt("result_id"));
                result.setStudentId(rs.getInt("student_id"));
                result.setSubject1Marks(rs.getDouble("subject1_marks"));
                result.setSubject2Marks(rs.getDouble("subject2_marks"));
                result.setSubject3Marks(rs.getDouble("subject3_marks"));
                result.setSubject4Marks(rs.getDouble("subject4_marks"));
                result.setSubject5Marks(rs.getDouble("subject5_marks"));
                result.setTotalMarks(rs.getDouble("total_marks"));
                result.setPercentage(rs.getDouble("percentage"));
                result.setGrade(rs.getString("grade"));
                results.add(result);
            }
        } catch (SQLException e) {
            System.err.println("✗ Error retrieving results: " + e.getMessage());
        }
        return results;
    }

    public boolean updateResult(Result result) {
        String sql = "UPDATE results SET subject1_marks = ?, subject2_marks = ?, subject3_marks = ?, " +
                     "subject4_marks = ?, subject5_marks = ?, total_marks = ?, percentage = ?, grade = ? " +
                     "WHERE result_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, result.getSubject1Marks());
            pstmt.setDouble(2, result.getSubject2Marks());
            pstmt.setDouble(3, result.getSubject3Marks());
            pstmt.setDouble(4, result.getSubject4Marks());
            pstmt.setDouble(5, result.getSubject5Marks());
            pstmt.setDouble(6, result.getTotalMarks());
            pstmt.setDouble(7, result.getPercentage());
            pstmt.setString(8, result.getGrade());
            pstmt.setInt(9, result.getResultId());
            
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("✗ Error updating result: " + e.getMessage());
            return false;
        }
    }

    public List<Result> getResultsByGrade(String grade) {
        List<Result> results = new ArrayList<>();
        String sql = "SELECT * FROM results WHERE grade = ? ORDER BY percentage DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, grade);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Result result = new Result();
                result.setResultId(rs.getInt("result_id"));
                result.setStudentId(rs.getInt("student_id"));
                result.setTotalMarks(rs.getDouble("total_marks"));
                result.setPercentage(rs.getDouble("percentage"));
                result.setGrade(rs.getString("grade"));
                results.add(result);
            }
        } catch (SQLException e) {
            System.err.println("✗ Error retrieving results by grade: " + e.getMessage());
        }
        return results;
    }
}
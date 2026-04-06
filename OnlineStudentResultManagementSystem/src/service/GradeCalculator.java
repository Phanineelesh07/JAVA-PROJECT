package service;

public class GradeCalculator {

    /**
     * Grade Calculation Logic:
     * 90-100: A+
     * 80-89:  A
     * 70-79:  B+
     * 60-69:  B
     * 50-59:  C
     * 40-49:  D
     * 0-39:   F (Fail)
     */

    public static String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B+";
        } else if (percentage >= 60) {
            return "B";
        } else if (percentage >= 50) {
            return "C";
        } else if (percentage >= 40) {
            return "D";
        } else {
            return "F";
        }
    }

    public static double calculateTotal(double s1, double s2, double s3, double s4, double s5) {
        return s1 + s2 + s3 + s4 + s5;
    }

    public static double calculatePercentage(double total) {
        double maxMarks = 500; // 5 subjects x 100 marks each
        return (total / maxMarks) * 100;
    }

    public static String getGradeDescription(String grade) {
        switch (grade) {
            case "A+":
                return "Outstanding";
            case "A":
                return "Excellent";
            case "B+":
                return "Very Good";
            case "B":
                return "Good";
            case "C":
                return "Average";
            case "D":
                return "Below Average";
            case "F":
                return "Fail";
            default:
                return "Unknown";
        }
    }
}
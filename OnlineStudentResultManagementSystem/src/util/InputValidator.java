package util;

import java.util.regex.Pattern;

public class InputValidator {

    public static boolean isValidMarks(double marks) {
        return marks >= 0 && marks <= 100;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(emailRegex, email);
    }

    public static boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.matches("[a-zA-Z\\s]+");
    }

    public static boolean isValidRollNumber(String rollNumber) {
        return rollNumber != null && !rollNumber.trim().isEmpty() && rollNumber.length() <= 20;
    }

    public static String validateMarksInput(String input) {
        try {
            double marks = Double.parseDouble(input);
            if (!isValidMarks(marks)) {
                return "Marks must be between 0 and 100";
            }
            return null;
        } catch (NumberFormatException e) {
            return "Invalid marks format. Enter a valid number";
        }
    }

    public static String validateStudentInput(String name, String roll, String email, String phone) {
        if (!isValidName(name)) {
            return "Invalid name. Use only alphabets and spaces";
        }
        if (!isValidRollNumber(roll)) {
            return "Invalid roll number";
        }
        if (!isValidEmail(email)) {
            return "Invalid email format";
        }
        if (!isValidPhone(phone)) {
            return "Phone must be 10 digits";
        }
        return null;
    }
}
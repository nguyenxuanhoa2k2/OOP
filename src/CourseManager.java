import java.sql.*;
import java.util.Scanner;

public class CourseManager {
    private Connection connection;
    private Scanner scanner;

    public CourseManager(Connection connection) {
        this.connection = connection;
        scanner = new Scanner(System.in);
    }

    
    public void addCourse() throws SQLException {


        System.out.println("Enter course name:");
        String courseName = scanner.nextLine();

        System.out.println("Enter course code:");
        String courseCode = scanner.nextLine();

        System.out.println("Enter subject:");
        String subject = scanner.nextLine();

        System.out.println("Enter teacher ID:");
        String teacherID = scanner.nextLine();

        System.out.println("Enter schedule:");
        String schedule = scanner.nextLine();

        System.out.println("Enter max number of students:");
        int maxStudents = scanner.nextInt();

        int remainingStudents = maxStudents;

        String query = "INSERT INTO courses (courseName, courseCode, subject, teacherID, schedule, maxStudents, remainingStudents) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, courseName);
        statement.setString(2, courseCode);
        statement.setString(3, subject);
        statement.setString(4, teacherID);
        statement.setString(5, schedule);
        statement.setInt(6, maxStudents);
        statement.setInt(7, remainingStudents);
        statement.executeUpdate();

        String updateTeacherQuery = "UPDATE teachers SET teachingCourse = ? WHERE teacherID = ?";
        PreparedStatement updateTeacherStatement = connection.prepareStatement(updateTeacherQuery);
        updateTeacherStatement.setString(1, courseCode);
        updateTeacherStatement.setString(2, teacherID);
        updateTeacherStatement.executeUpdate();
        
        System.out.println("Course added successfully.");

        
    }

    public void deleteCourse() throws SQLException {
        System.out.println("Enter course code to delete:");
        String courseCode = scanner.nextLine();

        String query = "DELETE FROM courses WHERE courseCode = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, courseCode);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Course deleted successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }

    public void updateCourse() throws SQLException {
        System.out.println("Enter course code to update:");
        String courseCode = scanner.nextLine();

        System.out.println("Enter new max number of students:");
        int newMaxStudents = scanner.nextInt();

        String query = "UPDATE courses SET maxStudents = ? WHERE courseCode = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, newMaxStudents);
        statement.setString(2, courseCode);
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Course updated successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }


}
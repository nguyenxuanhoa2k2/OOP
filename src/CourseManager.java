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

        Course course = new Course(courseName, courseCode, subject, teacherID, schedule, maxStudents, maxStudents);

        String query = "INSERT INTO courses (courseName, courseCode, subject, teacherID, schedule, maxStudents, remainingStudents) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, course.getCourseName());
        statement.setString(2, course.getCourseCode());
        statement.setString(3, course.getSubject());
        statement.setString(4, course.getTeacherID());
        statement.setString(5, course.getSchedule());
        statement.setInt(6, course.getMaxStudents());
        statement.setInt(7, course.getRemainingStudents());
        statement.executeUpdate();

        String updateTeacherQuery = "UPDATE teachers SET teachingCourse = ? WHERE teacherID = ?";
        PreparedStatement updateTeacherStatement = connection.prepareStatement(updateTeacherQuery);
        updateTeacherStatement.setString(1, course.getCourseCode());
        updateTeacherStatement.setString(2, course.getTeacherID());
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

        Course course = getCourseByCode(courseCode);
        if (course != null) {
            course.setMaxStudents(newMaxStudents);
            course.setRemainingStudents(newMaxStudents);

            String query = "UPDATE courses SET maxStudents = ?, remainingStudents = ? WHERE courseCode = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, course.getMaxStudents());
            statement.setInt(2, course.getRemainingStudents());
            statement.setString(3, course.getCourseCode());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Course updated successfully.");
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    private Course getCourseByCode(String courseCode) throws SQLException {
        String query = "SELECT * FROM courses WHERE courseCode = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, courseCode);
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            return new Course(
                resultSet.getString("courseName"),
                resultSet.getString("courseCode"),
                resultSet.getString("subject"),
                resultSet.getString("teacherID"),
                resultSet.getString("schedule"),
                resultSet.getInt("maxStudents"),
                resultSet.getInt("remainingStudents")
                );
        } else {
            return null;
        }
    }

    public void displayCourses() throws SQLException {
        String query = "SELECT * FROM courses";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
    
        System.out.println("Courses:");
        System.out.println("Course Code\tCourse Name\tSubject\t\tSchedule\tTeacher ID\tMax Students\tRemaining Students");
    
        while (resultSet.next()) {
            String courseName = resultSet.getString("courseName");
            String courseCode = resultSet.getString("courseCode");
            String subject = resultSet.getString("subject");
            String schedule = resultSet.getString("schedule");
            String teacherID = resultSet.getString("teacherID");
            int maxStudents = resultSet.getInt("maxStudents");
            int remainingStudents = resultSet.getInt("remainingStudents");
            System.out.printf("%-20s %-10s %-16s %-16s %-16s %-16d %-16d%n", courseCode, courseName , subject, schedule, teacherID, maxStudents, remainingStudents);
        }
    
        resultSet.close();
        statement.close();
    }
}
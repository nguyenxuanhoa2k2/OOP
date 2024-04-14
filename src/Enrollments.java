import java.sql.*;

public class Enrollments {
    private Connection connection;

    public Enrollments(Connection connection) {
        this.connection = connection;
    }

    public void enrollStudent(String studentID, String courseCode) throws SQLException {
        // Check if the course exists
        String checkCourseQuery = "SELECT * FROM courses WHERE courseCode = ?";
        PreparedStatement checkCourseStatement = connection.prepareStatement(checkCourseQuery);
        checkCourseStatement.setString(1, courseCode);
        ResultSet checkCourseResultSet = checkCourseStatement.executeQuery();

        if (!checkCourseResultSet.next()) {
            System.out.println("Course not found.");
            return;
        }

        // Check if there are still available spots for the student to enroll
        String checkEnrollmentQuery = "SELECT * FROM enrollments WHERE studentID = ? AND courseCode = ?";
        PreparedStatement checkEnrollmentStatement = connection.prepareStatement(checkEnrollmentQuery);
        checkEnrollmentStatement.setString(1, studentID);
        checkEnrollmentStatement.setString(2, courseCode);
        ResultSet checkEnrollmentResultSet = checkEnrollmentStatement.executeQuery();

        if (checkEnrollmentResultSet.next()) {
            System.out.println("Student is already enrolled in this course.");
            return;
        }

        checkCourseResultSet.close();

        // Check if there are still available spots in the course
        String getCourseQuery = "SELECT remainingStudents FROM courses WHERE courseCode = ?";
        PreparedStatement getCourseStatement = connection.prepareStatement(getCourseQuery);
        getCourseStatement.setString(1, courseCode);
        ResultSet getCourseResultSet = getCourseStatement.executeQuery();

        if (!getCourseResultSet.next()) {
            System.out.println("Course not found.");
            return;
        }

        int remainingStudents = getCourseResultSet.getInt("remainingStudents");
        if (remainingStudents <= 0) {
            System.out.println("This course is full. No more enrollments are allowed.");
            getCourseResultSet.close();
            return;
        }

        // Update the course with the new remaining students
        String updateCourseQuery = "UPDATE courses SET remainingStudents = ? WHERE courseCode = ?";
        PreparedStatement updateCourseStatement = connection.prepareStatement(updateCourseQuery);
        int newRemainingStudents = remainingStudents - 1;
        updateCourseStatement.setInt(1, newRemainingStudents);
        updateCourseStatement.setString(2, courseCode);
        updateCourseStatement.executeUpdate();

        // Insert the new enrollment
        String insertEnrollmentQuery = "INSERT INTO enrollments (studentID, courseCode) VALUES (?, ?)";
        PreparedStatement insertEnrollmentStatement = connection.prepareStatement(insertEnrollmentQuery);
        insertEnrollmentStatement.setString(1, studentID);
        insertEnrollmentStatement.setString(2, courseCode);
        insertEnrollmentStatement.executeUpdate();

        System.out.println("Student enrolled successfully.");

        getCourseResultSet.close();
        updateCourseStatement.close();
        insertEnrollmentStatement.close();
    }

    public void dropStudent(String studentID, String courseCode) throws SQLException {
        // Check if the student is enrolled in the course
        String checkEnrollmentQuery = "SELECT * FROM enrollments WHERE studentID = ? AND courseCode = ?";
        PreparedStatement checkEnrollmentStatement = connection.prepareStatement(checkEnrollmentQuery);
        checkEnrollmentStatement.setString(1, studentID);
        checkEnrollmentStatement.setString(2, courseCode);
        ResultSet checkEnrollmentResultSet = checkEnrollmentStatement.executeQuery();
    
        if (!checkEnrollmentResultSet.next()) {
            System.out.println("Student is not enrolled in this course.");
            return;
        }
    
        // Check if the course still has students enrolled
        String getCourseQuery = "SELECT remainingStudents FROM courses WHERE courseCode = ?";
        PreparedStatement getCourseStatement = connection.prepareStatement(getCourseQuery);
        getCourseStatement.setString(1, courseCode);
        ResultSet getCourseResultSet = getCourseStatement.executeQuery();
    
        if (!getCourseResultSet.next()) {
            System.out.println("Course not found.");
            return;
        }
    
        int remainingStudents = getCourseResultSet.getInt("remainingStudents");
        if (remainingStudents == 1) {
            System.out.println("This is the last student enrolled in the course. You cannot drop the last student.");
            getCourseResultSet.close();
            return;
        }
    
        // Update the course with the new remaining students
        int newRemainingStudents = remainingStudents + 1;
        String updateCourseQuery = "UPDATE courses SET remainingStudents = ? WHERE courseCode = ?";
        PreparedStatement updateCourseStatement = connection.prepareStatement(updateCourseQuery);
        updateCourseStatement.setInt(1, newRemainingStudents);
        updateCourseStatement.setString(2, courseCode);
        updateCourseStatement.executeUpdate();
    
        // Delete the enrollment
        String deleteEnrollmentQuery = "DELETE FROM enrollments WHERE studentID = ? AND courseCode = ?";
        PreparedStatement deleteEnrollmentStatement = connection.prepareStatement(deleteEnrollmentQuery);
        deleteEnrollmentStatement.setString(1, studentID);
        deleteEnrollmentStatement.setString(2, courseCode);
        deleteEnrollmentStatement.executeUpdate();
    
        System.out.println("Student dropped from course successfully.");
    
        getCourseResultSet.close();
        updateCourseStatement.close();
        deleteEnrollmentStatement.close();
    }

    public void displayEnrollments(String studentID) throws SQLException {
        String query = "SELECT e.studentID, c.courseName, e.courseCode FROM enrollments e JOIN courses c ON e.courseCode = c.courseCode WHERE e.studentID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, studentID);
        ResultSet resultSet = statement.executeQuery();
        
        System.out.println("Enrollments:");
        System.out.printf("%-10s %-20s %-10s%n", "Student ID", "Course Name", "Course Code");
        
        while (resultSet.next()) {
            String studentIDFromResultSet = resultSet.getString("studentID");
            String courseName = resultSet.getString("courseName");
            String courseCode = resultSet.getString("courseCode");
            
            System.out.printf("%-10s %-20s %-10s%n", studentIDFromResultSet, courseName, courseCode);
        }
        
        resultSet.close();
        statement.close();
    }
}
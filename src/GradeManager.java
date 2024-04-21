import java.sql.*;
import java.util.Scanner;

public class GradeManager {
    private Connection connection;
    private Scanner scanner;

    public GradeManager(Connection connection) {
        this.connection = connection;
        scanner = new Scanner(System.in);
    }

    public void gradeCourse(String teacherID) {
        try {
            // Hiển thị danh sách các khóa học mà giáo viên đang dạy
            displayTeachingCourse(teacherID);

            // Nhập mã khóa học cần chấm điểm
            System.out.println("Enter course code to grade:");
            String courseCode = scanner.nextLine();

            displayStudentsInCourse(courseCode);

            // Chọn loại điểm cần nhập
            int choice = getGradeTypeChoice();

            // Chấm điểm cho khóa học
            if (choice == 1) {
                gradeMidterm(courseCode);
            } else if (choice == 2) {
                gradeFinal(courseCode);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayTeachingCourse(String teacherID) throws SQLException {
        String query = "SELECT c.courseCode, c.courseName, c.subject, c.schedule, c.maxStudents, c.remainingStudents " +
                       "FROM courses c " +
                       "WHERE c.teacherID = ? " +
                       "ORDER BY c.courseCode";
    
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, teacherID);
    
        ResultSet resultSet = statement.executeQuery();
        System.out.println("Course you are teaching");
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.printf("| %-12s | %-15s | %-10s | %-10s | %-12s | %-18s |%n", "Course Code", "Course Name", "Subject", "Schedule", "Max Students", "Remaining Students");
        System.out.println("------------------------------------------------------------------------------------------------");
    
        while (resultSet.next()) {
            int courseCode = resultSet.getInt("courseCode");
            String courseName = resultSet.getString("courseName");
            String subject = resultSet.getString("subject");
            String schedule = resultSet.getString("schedule");
            int maxStudents = resultSet.getInt("maxStudents");
            int remainingStudents = resultSet.getInt("remainingStudents");
            System.out.printf("| %-12d | %-15s | %-10s | %-10s | %-12d | %-18d |%n", courseCode, courseName, subject, schedule, maxStudents, remainingStudents);
            System.out.println("------------------------------------------------------------------------------------------------");

    
        }
    
        resultSet.close();
        statement.close();
    }

    public void displayStudentsInCourse(String courseCode) throws SQLException {
        String query = "SELECT s.studentID, s.name, e.midtermGrade, e.finalGrade, e.averageGrade " +
                       "FROM students s " +
                       "INNER JOIN enrollments e ON s.studentID = e.studentID " +
                       "WHERE e.courseCode = ? " +
                       "ORDER BY s.studentID";
    
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, courseCode);
    
        ResultSet resultSet = statement.executeQuery();
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.printf("%-12s | %-25s | %-16s | %-16s | %-16s |%n", "Student ID", "Student Name", "Midterm Grade", "Final Grade", "Average Grade");
        System.out.println("---------------------------------------------------------------------------------------------------");
    
        while (resultSet.next()) {
            String studentID = resultSet.getString("studentID");
            String studentName = resultSet.getString("name");
            double midtermGrade = resultSet.getDouble("midtermGrade");
            double finalGrade = resultSet.getDouble("finalGrade");
            double averageGrade = resultSet.getDouble("averageGrade");
            System.out.printf("%-12s | %-25s | %-16.2f | %-16.2f | %-16.2f |%n", studentID, studentName, midtermGrade, finalGrade, averageGrade);
            System.out.println("---------------------------------------------------------------------------------------------------");
        }
    
        resultSet.close();
        statement.close();
    }

    public void studentViewGrades(String studentID) throws SQLException {
        String query = "SELECT c.courseCode, c.courseName, e.midtermGrade, e.finalGrade, e.averageGrade " +
                       "FROM courses c " +
                       "INNER JOIN enrollments e ON c.courseCode = e.courseCode " +
                       "WHERE e.studentID = ? " +
                       "ORDER BY c.courseCode";
    
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, studentID);
    
        ResultSet resultSet = statement.executeQuery();
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("| %-12s | %-15s | %-14s | %-14s | %-15s |%n", "Course Code", "Course Name", "Midterm Grade", "Final Grade", "Average Grade");
        System.out.println("--------------------------------------------------------------------------------------");
        while (resultSet.next()) {
            String courseCode = resultSet.getString("courseCode");
            String courseName = resultSet.getString("courseName");
            double midtermGrade = resultSet.getDouble("midtermGrade");
            double finalGrade = resultSet.getDouble("finalGrade");
            double averageGrade = resultSet.getDouble("averageGrade");
            System.out.printf("| %-12s | %-15s | %-14.2f | %-14.2f | %-15.2f |%n", courseCode, courseName, midtermGrade, finalGrade, averageGrade);
            System.out.println("--------------------------------------------------------------------------------------");
    
        }
    
        resultSet.close();
        statement.close();
    }

    private int getGradeTypeChoice() {
        System.out.println("Choose grade type:");
        System.out.println("1. Midterm Grade");
        System.out.println("2. Final Grade");
        return scanner.nextInt();
    }

    private void gradeMidterm(String courseCode) throws SQLException {
        gradeStudents(courseCode, "midtermGrade");
    }

    private void gradeFinal(String courseCode) throws SQLException {
        gradeStudents(courseCode, "finalGrade");
    }

    // private void gradeStudents(String courseCode, String gradeType) throws SQLException {
    //     try {
    //         // Lấy danh sách sinh viên trong khoá học
    //         String query = "SELECT s.studentID, s.name FROM students s INNER JOIN enrollments cs ON s.studentID = cs.studentID WHERE cs.courseCode = ?";
    //         PreparedStatement statement = connection.prepareStatement(query);
    //         statement.setString(1, courseCode);
    //         ResultSet resultSet = statement.executeQuery();

    //         // Duyệt qua từng sinh viên và chấm điểm
    //         while (resultSet.next()) {
    //             String studentID = resultSet.getString("studentID");
    //             String studentName = resultSet.getString("name");
    //             double grade = getGrade(gradeType, studentName);
    //             saveGrade(studentID, courseCode, gradeType, grade);
    //         }

    //         resultSet.close();
    //         statement.close();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    private void gradeStudents(String courseCode, String gradeType) throws SQLException {
        try {
    
            boolean exit = false;
            while(!exit){
            // Get the studentID from the user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the studentID: ");
            String studentID = scanner.nextLine();
    
            // Check if the student is enrolled in the course
            String query = "SELECT COUNT(*) FROM enrollments WHERE courseCode = ? AND studentID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, courseCode);
            statement.setString(2, studentID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count == 0) {
                System.out.println("The student is not enrolled in the course.");
                return;
            }
            // Get the student name
            query = "SELECT name FROM students WHERE studentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, studentID);
            resultSet = statement.executeQuery();
            resultSet.next();
            String studentName = resultSet.getString("name");
    
            // Grade the student
            double grade = getGrade(gradeType, studentName);
            saveGrade(studentID, courseCode, gradeType, grade);
            System.out.println("The student " + studentName + " has been graded with " + grade + " in " + gradeType + " for course " + courseCode);
    
            System.out.println("Enter 1 to continue grading ");
            System.out.println("Enter 0 to exit ");
    
            int choice;
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
    
            switch (choice) {
                case 0:
                    exit = true;
                    break;  
                case 1:
                    gradeStudents(courseCode, gradeType);
                break;
            }
    
            resultSet.close();
            statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    private double getGrade(String gradeType, String studentName) {
        System.out.println("Enter " + gradeType + " grade for student " + studentName + ":");
        return scanner.nextDouble();
    }
    private double getFinalGrade(String studentID, String courseCode) throws SQLException {
        // Truy vấn để lấy điểm cuối kỳ của sinh viên
        String query = "SELECT finalGrade FROM enrollments WHERE studentID = ? AND courseCode = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, studentID);
        statement.setString(2, courseCode);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getDouble("finalGrade");
        }
        return -1; // Trả về -1 nếu chưa có điểm cuối kỳ
    }
    
    private void updateAverageGrade(String studentID, String courseCode, double averageGrade) throws SQLException {
        // Cập nhật điểm trung bình vào cơ sở dữ liệu
        String updateQuery = "UPDATE enrollments SET averageGrade = ? WHERE studentID = ? AND courseCode = ?";
        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
        updateStatement.setDouble(1, averageGrade);
        updateStatement.setString(2, studentID);
        updateStatement.setString(3, courseCode);
        updateStatement.executeUpdate();
    
        updateStatement.close();
    }

    private void saveGrade(String studentID, String courseCode, String gradeType, double grade) throws SQLException {
        // Cập nhật điểm vào cơ sở dữ liệu
        String updateQuery = "UPDATE enrollments SET " + gradeType + " = ? WHERE studentID = ? AND courseCode = ?";
        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
        updateStatement.setDouble(1, grade);
        updateStatement.setString(2, studentID);
        updateStatement.setString(3, courseCode);
        updateStatement.executeUpdate();
    
        // Kiểm tra nếu có đủ cả hai điểm giữa kỳ và cuối kỳ
        if (gradeType.equals("midtermGrade")) {
            double finalGrade = getFinalGrade(studentID, courseCode);
        if (finalGrade != -1) {
            double averageGrade = (grade + finalGrade) / 2.0;
            updateAverageGrade(studentID, courseCode, averageGrade);
        }
    }
        updateStatement.close();
    }
    
}
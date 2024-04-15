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
            displayStudentsInCourse(teacherID);

            // Nhập mã khóa học cần chấm điểm
            System.out.println("Enter course code to grade:");
            String courseCode = scanner.nextLine();

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

    public void displayStudentsInCourse(String courseCode) throws SQLException {
        String query = "SELECT s.studentID, s.name, e.midtermGrade, e.finalGrade, averageGrade " +
                       "FROM students s " +
                       "INNER JOIN enrollments e ON s.studentID = e.studentID " +
                       "WHERE e.courseCode = ? " +
                       "ORDER BY s.studentID";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, courseCode);
        ResultSet resultSet = statement.executeQuery();
    
        System.out.println("Student ID\tStudent Name\t\tMidterm Grade\tFinal Grade\tAverage Grade");
        while (resultSet.next()) {
            String studentID = resultSet.getString("studentID");
            String studentName = resultSet.getString("name");
            double midtermGrade = resultSet.getDouble("midtermGrade");
            double finalGrade = resultSet.getDouble("finalGrade");
            double averageGrade = resultSet.getDouble("averageGrade");

            System.out.println(studentID + "\t\t" + studentName + "\t" + midtermGrade + "\t\t" + finalGrade + "\t\t" + averageGrade);
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
    
        System.out.println("Course Code\tCourse Name\tMidterm Grade\tFinal Grade\tAverage Grade");
        while (resultSet.next()) {
            String courseCode = resultSet.getString("courseCode");
            String courseName = resultSet.getString("courseName");
            double midtermGrade = resultSet.getDouble("midtermGrade");
            double finalGrade = resultSet.getDouble("finalGrade");
            double averageGrade = resultSet.getDouble("averageGrade");
    
            System.out.println(courseCode + "\t\t" + courseName + "\t\t" + midtermGrade + "\t\t" + finalGrade + "\t\t" + averageGrade);
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

    private void gradeStudents(String courseCode, String gradeType) throws SQLException {
        try {
            // Lấy danh sách sinh viên trong khoá học
            String query = "SELECT s.studentID, s.name FROM students s INNER JOIN enrollments cs ON s.studentID = cs.studentID WHERE cs.courseCode = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, courseCode);
            ResultSet resultSet = statement.executeQuery();

            // Duyệt qua từng sinh viên và chấm điểm
            while (resultSet.next()) {
                String studentID = resultSet.getString("studentID");
                String studentName = resultSet.getString("name");
                double grade = getGrade(gradeType, studentName);
                saveGrade(studentID, courseCode, gradeType, grade);
            }

            resultSet.close();
            statement.close();
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
    
        System.out.println("Grade for student " + studentID + " saved successfully.");

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
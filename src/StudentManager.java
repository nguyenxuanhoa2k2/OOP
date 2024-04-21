import java.sql.*;
import java.util.Scanner;

public class StudentManager {
    private Connection connection;
    private Scanner scanner;

    public StudentManager(Connection connection) {
        this.connection = connection;
        scanner = new Scanner(System.in);
    }

    public void addStudent() throws SQLException {
        System.out.println("Enter student's name:");
        String name = scanner.nextLine();

        System.out.println("Enter student's ID:");
        String studentID = scanner.nextLine();

        System.out.println("Enter student's date of birth (YYYY-MM-DD):");
        String dob = scanner.nextLine();

        System.out.println("Enter student's address:");
        String address = scanner.nextLine();

        System.out.println("Enter student's username:");
        String username = scanner.nextLine();

        System.out.println("Enter student's password:");
        String password = scanner.nextLine();

        Student student = new Student(name, studentID, dob, address, username, password);

        String query = "INSERT INTO students (name, studentID, dob, address, username, password) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, student.getName());
        statement.setString(2, student.getStudentID());
        statement.setString(3, student.getDob());
        statement.setString(4, student.getAddress());
        statement.setString(5, student.getUsername());
        statement.setString(6, student.getPassword());
        statement.executeUpdate();
        System.out.println("Student added successfully.");
    }

    public void deleteStudent() throws SQLException {
        System.out.println("Enter student's ID to delete:");
        String studentID = scanner.nextLine();

        String query = "DELETE FROM students WHERE studentID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, studentID);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void updateStudent() throws SQLException {
        System.out.println("Enter student's ID to update:");
        String studentID = scanner.nextLine();

        System.out.println("Choose information to update:");
        System.out.println("1. Name");
        System.out.println("2. Date of Birth");
        System.out.println("3. Address");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String columnToUpdate = null;
        String newValue = null;

        Student student = new Student(studentID);

        switch (choice) {
            case 1:
                columnToUpdate = "name";
                System.out.println("Enter new name:");
                newValue = scanner.nextLine();
                student.setName(newValue);
                break;
            case 2:
                columnToUpdate = "dob";
                System.out.println("Enter new date of birth (YYYY-MM-DD):");
                newValue = scanner.nextLine();
                student.setDob(newValue);
                break;
            case 3:
                columnToUpdate = "address";
                System.out.println("Enter new address:");
                newValue = scanner.nextLine();
                student.setAddress(newValue);
                break;
            default:
                System.out.println("Invalid choice. No updates performed.");
                return;
        }

        String query = "UPDATE students SET " + columnToUpdate + " = ? WHERE studentID = ?";
        PreparedStatement statement =connection.prepareStatement(query);
        statement.setString(1, newValue);
        statement.setString(2, studentID);
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void displayStudents() throws SQLException {
        try (Connection connection = JDBC.getConnection()) {
            String sql = "SELECT * FROM students";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
    
            System.out.println("----------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s | %-20s | %-18s | %-16s | %-16s | %-15s |%n", "StudentID", "Name", "DoB", "Address", "Username", "Password");
            System.out.println("----------------------------------------------------------------------------------------------------------------");    
            while (resultSet.next()) {
                int id = resultSet.getInt("studentID");
                String name = resultSet.getString("name");
                String dob = resultSet.getString("dob");
                String address = resultSet.getString("address");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.printf("%-10s | %-20s | %-18s | %-16s | %-16s | %-15s |%n", id, name, dob, address, username, password);
                System.out.println("----------------------------------------------------------------------------------------------------------------");
            }
    
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
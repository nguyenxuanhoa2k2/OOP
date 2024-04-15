import java.sql.*;
import java.util.Scanner;

public class TeacherManager {
    private Connection connection;
    private Scanner scanner;

    public TeacherManager(Connection connection) {
        this.connection = connection;
        scanner = new Scanner(System.in);
    }

    public void addTeacher() throws SQLException {
        System.out.println("Enter teacher's name:");
        String name = scanner.nextLine();

        System.out.println("Enter teacher's ID:");
        String teacherID = scanner.nextLine();

        System.out.println("Enter teacher's date of birth (YYYY-MM-DD):");
        String dob = scanner.nextLine();

        System.out.println("Enter teacher's address:");
        String address = scanner.nextLine();

        System.out.println("Enter teacher's qualification:");
        String qualification = scanner.nextLine();

        System.out.println("Enter teacher's teaching subject:");
        String teachingSubject = scanner.nextLine();

        System.out.println("Enter teacher's username:");
        String username = scanner.nextLine();

        System.out.println("Enter teacher's password:");
        String password = scanner.nextLine();

        String query = "INSERT INTO teachers (name, teacherID, dob, address, qualification, teachingSubject ,username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, teacherID);
        statement.setString(3, dob);
        statement.setString(4, address);
        statement.setString(5, qualification);
        statement.setString(6, teachingSubject);
        statement.setString(7, username);
        statement.setString(8, password);
        statement.executeUpdate();
        System.out.println("Teacher added successfully.");
    }

    public void deleteTeacher() throws SQLException {
        System.out.println("Enter teacher's ID to delete:");
        String teacherID = scanner.nextLine();

        String query = "DELETE FROM teachers WHERE teacherID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, teacherID);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Teacher deleted successfully.");
        } else {
            System.out.println("Teacher not found.");
        }
    }

    public void updateTeacher() throws SQLException {
        System.out.println("Enter teacher's ID to update:");
        String teacherID = scanner.nextLine();

        System.out.println("Choose information to update:");
        System.out.println("1. Name");
        System.out.println("2. Date of Birth");
        System.out.println("3. Address");
        System.out.println("4. Qualification");
        System.out.println("5. Teaching Subject");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String columnToUpdate = null;
        String newValue = null;

        switch (choice) {
            case 1:
                columnToUpdate = "name";
                System.out.println("Enter new name:");
                newValue = scanner.nextLine();
                break;
            case 2:
                columnToUpdate = "dob";
                System.out.println("Enter new date of birth (YYYY-MM-DD):");
                newValue = scanner.nextLine();
                break;
            case 3:
                columnToUpdate = "address";
                System.out.println("Enter new address:");
                newValue = scanner.nextLine();
                break;
            case 4:
                columnToUpdate = "qualification";
                System.out.println("Enter new qualification:");
                newValue = scanner.nextLine();
                break;
            case 5:
                columnToUpdate = "teachingSubject";
                System.out.println("Enter new teaching subject:");
                newValue = scanner.nextLine();
                break;

            default:
                System.out.println("Invalid choice. No updates performed.");
                return;
        }

        String query = "UPDATE teachers SET " + columnToUpdate + " = ? WHERE teacherID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, newValue);
        statement.setString(2, teacherID);
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Teacher updated successfully.");
        } else {
            System.out.println("Teacher not found.");
        }
    }

    public void displayTeachers() throws SQLException {
        try {
            Connection connection = JDBC.getConnection();
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM teachers";
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("TEACHERS LIST");
            System.out.println("TeacherID\tName\t\t\tDoB\t\tAddress\t\tQualification\t\tTeachingSubject\t\tUsername\tPassword");
            while (resultSet.next()) {
                int id = resultSet.getInt("teacherID");
                String name = resultSet.getString("name");
                String dob = resultSet.getString("dob");
                String address = resultSet.getString("address");
                String qualification = resultSet.getString("qualification");
                String teachingSubject = resultSet.getString("teachingSubject");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.printf("%-10s%-25s%-21s%-16s%-24s%-24s%-16s%-15s%n", id, name, dob, address, qualification, teachingSubject, username, password);
                
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
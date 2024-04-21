import java.sql.*;
import java.util.Scanner;

public class AdminManager {
    private Connection connection;
    private Scanner scanner;

    public AdminManager(Connection connection) {
        this.connection = connection;
        scanner = new Scanner(System.in);
    }

    public void addAdmin() throws SQLException {
        System.out.println("Enter admin's name:");
        String name = scanner.nextLine();

        System.out.println("Enter admin's ID:");
        String adminID = scanner.nextLine();

        System.out.println("Enter admin's date of birth (YYYY-MM-DD):");
        String dob = scanner.nextLine();

        System.out.println("Enter admin's address:");
        String address = scanner.nextLine();

        System.out.println("Enter admin's username:");
        String username = scanner.nextLine();

        System.out.println("Enter admin's password:");
        String password = scanner.nextLine();

        Admin admin = new Admin(name, adminID, dob, address, username, password);
        String query = "INSERT INTO admintable (name, adminID, dob, address, username, password) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, admin.getName());
        statement.setString(2, admin.getAdminID());
        statement.setString(3, admin.getDob());
        statement.setString(4, admin.getAddress());
        statement.setString(5, admin.getUsername());
        statement.setString(6, admin.getPassword());
        statement.executeUpdate();
        System.out.println("Admin added successfully.");
    }

    public void deleteAdmin() throws SQLException {
        System.out.println("Enter admin's ID to delete:");
        String adminID = scanner.nextLine();

        String query = "DELETE FROM admintable WHERE adminID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, adminID);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Admin deleted successfully.");
        } else {
            System.out.println("Admin not found.");
        }
    }

    public void updateAdmin() throws SQLException {
        System.out.println("Enter admin's ID to update:");
        String adminID = scanner.nextLine();

        System.out.println("Choose information to update:");
        System.out.println("1. Name");
        System.out.println("2. Date of Birth");
        System.out.println("3. Address");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        String columnToUpdate = null;
        String newValue = null;

        Admin admin = new Admin(adminID);

        switch (choice) {
            case 1:
                columnToUpdate = "name";
                System.out.println("Enter new name:");
                newValue = scanner.nextLine();
                admin.setName(newValue);
                break;
            case 2:
                columnToUpdate = "dob";
               System.out.println("Enter new date of birth (YYYY-MM-DD):");
                newValue = scanner.nextLine();
                admin.setDob(newValue);
                break;
            case 3:
                columnToUpdate = "address";
                System.out.println("Enter new address:");
                newValue = scanner.nextLine();
                admin.setAddress(newValue);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        String query = "UPDATE admintable SET " + columnToUpdate + " = ? WHERE adminID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, newValue);
        statement.setString(2, adminID);
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Admin updated successfully.");
        } else {
            System.out.println("Admin not found.");
        }
    }

    public void displayAdmins() throws SQLException {
        String query = "SELECT * FROM admintable";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        System.out.printf("%-10s%-20s%-18s%-16s%-16s%-15s%n", "ID", "Name", "DOB", "Address", "Username", "Password");

        while (resultSet.next()) {
            String id = resultSet.getString("adminID");
            String name = resultSet.getString("name");
            String dob = resultSet.getString("dob");
            String address = resultSet.getString("address");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            System.out.printf("%-10s%-20s%-18s%-16s%-16s%-15s%n", id, name, dob, address, username, password);
        }

        resultSet.close();
        statement.close();
    }
}
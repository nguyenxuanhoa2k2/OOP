import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class ListStudent {
    private ArrayList<Student> students;
    Scanner sc = new Scanner(System.in);

    public ListStudent() {
        students = new ArrayList<>();
    }

    public void loadStudents() throws SQLException {
        try {
            Connection connection = JDBC.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");

            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                Student student = new Student(studentId, name, username, password);
                students.add(student);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) throws SQLException {
        try {
            Connection connection = JDBC.getConnection();
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO students (student_id, name, username, password) VALUES (" + student.getStudentId() + ", '" + student.getName() + "', '" + student.getUsername() + "', '" + student.getPassword() + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                student.setStudentId(id);
                students.add(student);
            }
            System.out.println("ADD new student successfully");

            generatedKeys.close();
            statement.close();
            connection.close();
            System.out.print("Press Enter to exit...");
            sc.nextLine();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent() throws SQLException {
        try {
            System.out.print("Enter student ID to update: ");
            int ID = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter new name: ");
            String name = sc.nextLine();

            System.out.print("Enter new username: ");
            String username = sc.nextLine();

            System.out.print("Enter new password: ");
            String password = sc.nextLine();

            Connection connection = JDBC.getConnection();
            Statement statement = connection.createStatement();

            String sql = "UPDATE students SET name='" + name + "', username='" + username + "', password='" + password + "' WHERE student_id=" + ID;
            statement.executeUpdate(sql);
            System.out.println("Update successful");
            statement.close();
            connection.close();
            System.out.println("Press Enter to exit...");
            sc.nextLine();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent() throws SQLException {
        try {
            System.out.print("Enter student ID to delete: ");
            int studentId = sc.nextInt();
            Connection connection = JDBC.getConnection();
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM students WHERE student_id=" + studentId;
            statement.executeUpdate(sql);
            System.out.println("Delete successful");
            statement.close();
            connection.close();
            sc.nextLine();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayStudents() throws SQLException {
        try {
            Connection connection = JDBC.getConnection();
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM students";
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("STUDENTS LIST");
            System.out.println("ID\tName\t\t\tUsername\tPassword");
            while (resultSet.next()) {
                int id = resultSet.getInt("student_id");
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.println(id + "\t" + name + "\t\t" + username + "\t\t" + password);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
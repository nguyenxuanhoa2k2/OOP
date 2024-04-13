import java.sql.*;
import java.util.Scanner;

public class Userlogin {

    public static void AdminLogin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = sc.next();
        System.out.println("Enter password: ");
        String password = sc.next();
        Connection d= JDBC.getConnection();
        try{
            PreparedStatement ps = d.prepareStatement("SELECT username, password FROM admintable WHERE password = ?");
            ps.setString(1, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String dbUser = rs.getString("username");
                if (username.equals(dbUser)) {
                    System.out.println("Login successful");
                        int choice;
                        boolean t = true ;
                        while (t) {
                            System.out.println("\n-----Welcome to school management page for admin -----");
                            System.out.println("1. Students Management");
                            System.out.println("2. Teachers Management");
                            System.out.println("3. Course Management");
                            System.out.println("0. Log out");
                            System.out.print("Choose a function: ");
                            choice = sc.nextInt();
                
                            // Xử lý lựa chọn của người dùng
                            switch (choice) {
                                case 0:
                                t = false;
                                break;
                              
                                case 1:
                                System.out.println("Students Management");
                                try {
                                    Connection connection = JDBC.getConnection();
                                    StudentManager manager = new StudentManager(connection);

                                    boolean exit = false;
                        
                                    while (!exit) {
                                        System.out.println("1. Add Student");
                                        System.out.println("2. Delete Student");
                                        System.out.println("3. Update Student");
                                        System.out.println("4. Display All Students");
                                        System.out.println("5. Exit");
                                        System.out.println("Choose an option:");
                        
                                        int choice1 = sc.nextInt();
                                        sc.nextLine(); // Consume newline
                        
                                        switch (choice1) {
                                            case 1:
                                                manager.addStudent();
                                                break;
                                            case 2:
                                                manager.deleteStudent();
                                                break;
                                            case 3:
                                                manager.updateStudent();
                                                break;
                                            case 4:
                                               manager.displayStudents();
                                            case 5:
                                                exit = true;
                                                break;
                                            default:
                                                System.out.println("Invalid choice. Please choose again.");
                                        }
                                    }
                        
                                    JDBC.closeConnection(connection);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            
                    
                                case 2:
                                    System.out.println("Teachers Management");
                                    try {
                                        Connection connection = JDBC.getConnection();
                                        TeacherManager manager = new TeacherManager(connection);
                                        Scanner scanner = new Scanner(System.in);
                                        boolean exit = false;
                            
                                        while (!exit) {
                                            System.out.println("1. Add Teacher");
                                            System.out.println("2. Delete Teacher");
                                            System.out.println("3. Update Teacher");
                                            System.out.println("4. Display All Teacher");
                                            System.out.println("5. Exit");
                                            System.out.println("Choose an option:");
                            
                                            int choice2 = scanner.nextInt();
                                            scanner.nextLine(); 
                                            switch (choice2) {
                                                case 1:
                                                manager.addTeacher();
                                                    break;
                                                case 2:
                                                    manager.deleteTeacher();
                                                    break;
                                                case 3:
                                                    manager.updateTeacher();
                                                    break;
                                                case 4:
                                                    manager.displayTeachers();
                                                case 5:
                                                    exit = true;
                                                    break;
                                                default:
                                                    System.out.println("Invalid choice. Please choose again.");
                                            }
                                        }
                                        JDBC.closeConnection(connection);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 3:
                                System.out.println("Course Management");
                                // Process Add/Edit/Delete Course functionality
                                try {
                                    Connection connection = JDBC.getConnection();
                                    CourseManager manager = new CourseManager(connection);
                                    Scanner scanner = new Scanner(System.in);
                                    boolean exit = false;
                        
                                    while (!exit) {
                                        System.out.println("1. Add Course");
                                        System.out.println("2. Delete Course");
                                        System.out.println("3. Update Course");
                                        System.out.println("4. Exit");
                                        System.out.println("Choose an option:");
                        
                                        int choice3 = scanner.nextInt();
                                        scanner.nextLine(); // Consume newline
                        
                                        switch (choice3) {
                                            case 1:
                                                manager.addCourse();
                                                break;
                                            case 2:
                                                manager.deleteCourse();
                                                break;
                                            case 3:
                                                manager.updateCourse();
                                                break;
                                            case 4:
                                                exit = true;
                                                break;
                                            default:
                                                System.out.println("Invalid choice. Please choose again.");
                                        }
                                    }
                                    JDBC.closeConnection(connection);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                    break;
                            }
                        }
                    }
                    else {
                        System.out.println("Login failed");
                    }
                }
            else{ System.out.println("Login failed"); }
                
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }    
    }

    public static void TeacherLogin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = sc.next();
        System.out.println("Enter password: ");
        String password = sc.next();
        Connection d= JDBC.getConnection();
        try{
            PreparedStatement ps = d.prepareStatement("SELECT username, password FROM teachers WHERE password = ?");
            ps.setString(1, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String dbUser = rs.getString("username");
                if (username.equals(dbUser)) {
                    System.out.println("Login successful");
                        int choice;
                        boolean t = true;
                        while (t) {
                            System.out.println("\n-----Welcome to school management page for teacher -----");
                            System.out.println("1. View student in course");
                            System.out.println("2. Grade student");
                            System.out.println("0. Log out");
                            System.out.print("Choose a function: ");
                            choice = sc.nextInt();

                            switch (choice) {
                                case 0:
                                    t = false;
                                    break;  
                                case 1:
                                    System.out.println("View student in couse");
                                    break;
                                case 2:
                                    System.out.println("Grade student");
                                    break;
                                    default:
                                    if (choice != 0) {
                                        System.out.println("Function doesn't exist !!!");
                                    } else {
                                        System.out.println("End");
                                    }
                                    break;
                            }
                        }
                    }
                    else {
                        System.out.println("Login failed");
                    }
                }
            else{ System.out.println("Login failed"); }
                
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }    
    }

    public static void StudentLogin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = sc.next();
        System.out.println("Enter password: ");
        String password = sc.next();
        Connection d= JDBC.getConnection();
        try{
            PreparedStatement ps = d.prepareStatement("SELECT username, password FROM students WHERE password = ?");
            ps.setString(1, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String dbUser = rs.getString("username");
                if (username.equals(dbUser)) {
                    System.out.println("Login successful");
                        int choice;
                        boolean t = true;
                        while (true) {
                            System.out.println("\n-----Welcome to school management page for student -----");
                            System.out.println("1. View grade");
                            System.out.println("2. Enroll for course");
                            System.out.println("0. Log out");
                            System.out.print("Choose a function: ");
                                choice = sc.nextInt();
                                switch (choice) {
                                    case 0:
                                        t = false;
                                        break;
                                    case 1:
                                        System.out.println("View grade");
                                        break;
                                    case 2:
                                        System.out.println("Enroll for course");
                                        break;
                                        default:
                                    if (choice != 0) {
                                        System.out.println("Function doesn't exist  !!!");
                                    } else {
                                        System.out.println("End");
                                    }
                                    break;
                                }
                            }
                        }
                        else {
                            System.out.println("Login failed");
                        }
                    }
                else{ System.out.println("Login failed"); }
                        
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }    
    }
}
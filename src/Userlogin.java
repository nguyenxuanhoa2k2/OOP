import java.sql.*;
import java.util.Scanner;

public class Userlogin {

    public static void AdminLogin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap username: ");
        String username = sc.next();
        System.out.println("Nhap password: ");
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
                    ListCourse lc = new ListCourse();
                        int choice;
                        boolean t = true ;
                        while (t) {
                            System.out.println("\n-----Welcome to school management page for admin -----");
                            System.out.println("1. Add student");
                            System.out.println("2. View all student");
                            System.out.println("3. Add teacher");
                            System.out.println("4. View all teacher");
                            System.out.println("5. Create course");
                            System.out.println("0. Log out");
                            System.out.print("Choose a function: ");
                            choice = sc.nextInt();
                
                            // Xử lý lựa chọn của người dùng
                            switch (choice) {
                                case 0:
                                t = false;
                                break;
                                case 1:
                                    System.out.println("Add student");
                                    break;
                                case 2:
                                    System.out.println("View all student");
                                    break;
                                case 3:
                                    System.out.println("Add teacher");
                                    break;
                                case 4:
                                    System.out.println("View all teacher");
                                    break;
                                case 5:
                                    System.out.println("Create Course");
                                    // Process Add/Edit/Delete Course functionality
                                    int choice1;
                                    do {
                                        System.out.println("1. Add course");
                                        System.out.println("2. Edit course");
                                        System.out.println("3. Delete course");
                                        System.out.println("4. Display list of courses");
                                        System.out.println("0. Exit");
                                        System.out.print("Select a function: ");
                                        choice1 = sc.nextInt();
                                        switch (choice1) {
                                        case 1: // Add course
                                            System.out.println("Enter information for the new course:");
                                            System.out.print("ID: ");
                                            int cID = sc.nextInt();
                                            sc.nextLine();
                                            System.out.print("Course name: ");
                                            String cName = sc.nextLine();
                                            System.out.print("Fee: ");
                                            double fee = sc.nextDouble();
                                            Course course = new Course(cID, cName, fee);
                                            lc.addCourse(course); // Add new course
                                            break;
                                        case 2: // Edit course
                                            lc.updateCourse();
                                            break;
                                        case 3: // Delete course
                                            lc.deleteCourse();
                                            break;
                                        case 4: // Display list of courses
                                            lc.displayCourses();
                                            break;
                                        default:
                                            if (choice1 != 0) {
                                                System.out.println("Invalid selection!");
                                            } else {
                                                System.out.println("Ending session!");
                                            }
                                            break;
                                        }
                                            } while (choice1 != 0);
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

    public static void TeacherLogin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap username: ");
        String username = sc.next();
        System.out.println("Nhap password: ");
        String password = sc.next();
        Connection d= JDBC.getConnection();
        try{
            PreparedStatement ps = d.prepareStatement("SELECT username, password FROM teacher WHERE password = ?");
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
        System.out.println("Nhap username: ");
        String username = sc.next();
        System.out.println("Nhap password: ");
        String password = sc.next();
        Connection d= JDBC.getConnection();
        try{
            PreparedStatement ps = d.prepareStatement("SELECT username, password FROM student WHERE password = ?");
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
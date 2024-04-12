import java.util.Scanner;
import java.sql.*;


public class Main {

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
                                    break;
                                    default:
                                    if (choice != 0) {
                                        System.out.println("Function don't exit !!!");
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
                                        System.out.println("Function don't exit !!!");
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
                                        System.out.println("Function don't exit !!!");
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

                
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("|----------|VUI LONG DANG NHAP|---------|");
            System.out.println("|---------1->Admin Login.------------|");
            System.out.println("|---------2->Teacher Login.------------|");
            System.out.println("|---------3->Student Login.------------|");
            System.out.println("|---------0->Exit.---------------------|");
            System.out.println("|---------------------------------------|");
            System.out.println("Choose: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                AdminLogin();
                    break;
                case 2:
                TeacherLogin();
                    break;
                case 3:
                StudentLogin();
                    break;
                    default:
                    if (choice != 0) {
                        System.out.println("Function don't exit !!!");
                    } else {
                        System.out.println("End");
                    }
                    break;
            }
        } while (choice != 0);
    }

}

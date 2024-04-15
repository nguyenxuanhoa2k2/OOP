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
                                        sc.nextLine(); 

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
                                        
                                        boolean exit = false;
                            
                                        while (!exit) {
                                            System.out.println("1. Add Teacher");
                                            System.out.println("2. Delete Teacher");
                                            System.out.println("3. Update Teacher");
                                            System.out.println("4. Display All Teacher");
                                            System.out.println("5. Exit");
                                            System.out.println("Choose an option:");
                            
                                            int choice2 = sc.nextInt();
                                            sc.nextLine(); 
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
                                try {
                                    Connection connection = JDBC.getConnection();
                                    CourseManager manager = new CourseManager(connection);
                                    boolean exit = false;
                        
                                    while (!exit) {
                                        System.out.println("1. Add Course");
                                        System.out.println("2. Delete Course");
                                        System.out.println("3. Update Course");
                                        System.out.println("4. Display Course");
                                        System.out.println("5. Exit");
                                        System.out.println("Choose an option:");
                        
                                        int choice3 = sc.nextInt();
                                        sc.nextLine(); 

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
                                                manager.displayCourses();
                                                break;
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
            PreparedStatement ps = d.prepareStatement("SELECT teacherID, username, password FROM teachers WHERE password = ?");
            ps.setString(1, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String dbUser = rs.getString("username");
                if (username.equals(dbUser)) {
                    System.out.println("Login successful");
                    int teacherID = rs.getInt("teacherID"); 

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
                                    System.out.println("Enter course code to view students:");
                                    String courseCode = sc.next();
                                    try {
                                        Connection connection = JDBC.getConnection();
                                        GradeManager gradeManager = new GradeManager(connection);
                                        System.out.println("Viewing students in course " + courseCode);
                                        gradeManager.displayStudentsInCourse(courseCode);
                                        connection.close();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 2:
                                try {
                                    Connection connection = JDBC.getConnection();
                                    GradeManager gradeManager = new GradeManager(connection);
                                    System.out.println("Grade student");
                                        gradeManager.gradeCourse(Integer.toString(teacherID));
                            
                                        connection.close();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                
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
            PreparedStatement ps = d.prepareStatement("SELECT studentID, username, password FROM students WHERE password = ?");
            ps.setString(1, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String dbUser = rs.getString("username");
                if (username.equals(dbUser)) {
                    System.out.println("Login successful");
                    int studentID = rs.getInt("studentID"); 
                        int choice;
                        boolean t = true;
                        while (t) {
                            System.out.println("\n-----Welcome to school management page for student -----");
                            System.out.println("1. View grade");
                            System.out.println("2. Enrollments");
                            System.out.println("0. Log out");
                            System.out.print("Choose a function: ");
                                choice = sc.nextInt();
                                switch (choice) {
                                    case 0:
                                        t = false;
                                        break;
                                    case 1:
                                        System.out.println("Enter student ID to view grades:");
                                        try {
                                            Connection connection = JDBC.getConnection();
                                            GradeManager gradeManager = new GradeManager(connection);
                                            System.out.println("Viewing grades for student " + studentID);
                                            gradeManager.studentViewGrades(Integer.toString(studentID));
                                            connection.close();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                        case 2:
                                        System.out.println("Enrollments Management");
                                        try {
                                            Connection connection = JDBC.getConnection();
                                            Enrollments enrollments = new Enrollments(connection);
                                            CourseManager manager = new CourseManager(connection);
                                    
                                            boolean exit = false;
                                    
                                            while (!exit) {
                                                System.out.println("1. Enroll in Course");
                                                System.out.println("2. Drop from Course");
                                                System.out.println("3. Display All Enrollments");
                                                System.out.println("4. Display All Course");
                                                System.out.println("5. Exit");
                                                System.out.println("Choose an option:");
                                    
                                                int choice2 = sc.nextInt();
                                                sc.nextLine(); 

                                                switch (choice2) {
                                                    case 1:
                                                        System.out.println("Enter course code:");
                                                        String courseCode = sc.nextLine();
                                                        enrollments.enrollStudent(Integer.toString(studentID), courseCode);
                                                        break;
                                                    case 2:
                                                        System.out.println("Enter course code:");
                                                        String courseCodeToDrop = sc.nextLine();
                                                        enrollments.dropStudent(Integer.toString(studentID), courseCodeToDrop);
                                                        break;
                                                    case 3:
                                                        enrollments.displayEnrollments(Integer.toString(studentID));
                                                        break;
                                                    case 4:
                                                        manager.displayCourses();
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
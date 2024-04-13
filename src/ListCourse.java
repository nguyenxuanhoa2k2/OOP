import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class ListCourse {
    private ArrayList<Course> courses;
    Scanner sc = new Scanner(System.in);
  
    public ListCourse() {
      courses = new ArrayList<>();
    }
  
    public void loadCourses() throws SQLException {
      try {
        Connection connection = JDBC.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM courses");
  
        while (resultSet.next()) {
          int courseId = resultSet.getInt("course_id");
          String courseName = resultSet.getString("course_name");
          double fee = resultSet.getDouble("fee");
          Course course = new Course(courseId, courseName, fee);
          courses.add(course);
        }
  
        resultSet.close();
        statement.close();
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  
    public void addCourse(Course course) throws SQLException {
      try {
        Connection connection = JDBC.getConnection();
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO courses (course_id, course_name, fee) VALUES (" + course.getCourseId() + ", '" + course.getCourseName() + "', " + course.getFee() + ")";
        statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
  
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
          int id = generatedKeys.getInt(1);
          course.setCourseId(id);
          courses.add(course);
        }
        System.out.println("ADD new course successfully");
  
        generatedKeys.close();
        statement.close();
        connection.close();
        System.out.print("Press Enter to exit...");
        sc.nextLine();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  
    public void updateCourse() throws SQLException {
      try {
        System.out.print("Nhập course_id cần sửa: ");
        int ID = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhập course_name mới: ");
        String courseName = sc.nextLine();
  
        System.out.print("Nhập fee mới: ");
        double fee = sc.nextDouble();
        sc.nextLine();
  
        Connection connection = JDBC.getConnection();
        Statement statement = connection.createStatement();
  
        String sql = "UPDATE courses SET course_name='" + courseName + "', fee=" + fee + " WHERE course_id='" + ID + "'";
        statement.executeUpdate(sql);
        System.out.println("Update successfully");
        statement.close();
        connection.close();
        System.out.println("Press Enter to exit...");
        sc.nextLine();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  
    public void deleteCourse() throws SQLException {
      try {
        System.out.print("Nhập ID course cần xóa: ");
        int course_id = sc.nextInt();
        Connection connection = JDBC.getConnection();
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM courses WHERE course_id=" + course_id;
        statement.executeUpdate(sql);
        System.out.println("Delete successfully");
        statement.close();
        connection.close();
        sc.nextLine();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  
    public void displayCourses() throws SQLException {
      try {
        Connection connection = JDBC.getConnection();
        Statement statement = connection.createStatement();
  
        String sql = "SELECT * FROM courses";
        ResultSet resultSet2 = statement.executeQuery(sql);
  
        while (resultSet2.next()) {
          Course course = new Course(
              resultSet2.getInt("course_id"),
              resultSet2.getString("course_name"),
              resultSet2.getDouble("fee")
          );
  
          System.out.println(course.toString());
          System.out.println("------------------------");
        }
        resultSet2.close();
        statement.close();
        connection.close();
        System.out.print("Press Enter to exit...");
        sc.nextLine();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
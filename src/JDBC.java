import java.sql.*;
public class JDBC {
    public static Connection getConnection() {
        Connection c = null;
        try {
            String jdbcurl = "jdbc:mysql://localhost:3306/db";
            String username = "root";
            String password = "1234";
            c = DriverManager.getConnection(jdbcurl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
public static void closeConnection(Connection c){
try{
    if(c!=null){ c.close();}
}
catch( Exception e){
    e.printStackTrace();
}
}
}
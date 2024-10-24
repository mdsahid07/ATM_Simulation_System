package Data_Access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class MainDAL {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmsystem", "root", "123456");
            Statement statement = con.createStatement();
            ResultSet query = statement.executeQuery("Select * from User");

            while (query.next()) {
                System.out.println(query.getString("name"));


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ResultSet read(String sql){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmsystem", "root", "123456");
            Statement statement = con.createStatement();
            ResultSet query = statement.executeQuery(sql);
            return query;

        } catch (SQLException e) {
//            System.out.println(e.getMessage()?);
//            throw new RuntimeException(e)
              e.printStackTrace();
              return null;
        }
    }
    public static boolean write(String sql){
        try {
            // Establish a connection to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmsystem", "root", "123456");

            // Create a statement object to execute the query
            Statement statement = con.createStatement();

            // Execute the SQL statement (INSERT, UPDATE, DELETE)
            int rowsAffected = statement.executeUpdate(sql);

            // If rowsAffected > 0, the operation was successful
            return rowsAffected > 0;

        } catch (SQLException e) {
            // If an exception occurs, print the error and return false
            e.printStackTrace();
            return false;
        }
    }
    public static int getLastInsertId() {
        try {
            ResultSet rs = read("SELECT LAST_INSERT_ID();");
            if (rs.next()) {
                return rs.getInt("Id");
            }
            return -1;  // Return -1 or handle the error case appropriately
        }catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

}

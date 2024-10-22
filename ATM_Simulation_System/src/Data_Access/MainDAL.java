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
}

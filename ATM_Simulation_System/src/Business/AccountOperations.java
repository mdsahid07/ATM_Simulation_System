package Business;

import java.sql.*;

public class AccountOperations {
    public static void Account() {

    }

    public static Integer getAccountNumber() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmsystem", "root", "123456");
            Statement statement = con.createStatement();
            int userId = 0;
            ResultSet query = statement.executeQuery("Select * from LoginSession");
            while (query.next()) {
                userId = query.getInt("UserId");
            }
            int accountNumber = 0;
            if (userId != 0) {
                ResultSet query1 = statement.executeQuery("Select * from Account where UserId = " + userId);
                while (query1.next()) {
                    accountNumber = query1.getInt("AccNumber");
                }
            }
            return accountNumber;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getUserName() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmsystem", "root", "123456");
            Statement statement = con.createStatement();
            int userId = 0;
            ResultSet query = statement.executeQuery("Select * from LoginSession");
            while (query.next()) {
                userId = query.getInt("UserId");
            }
            String userName = "";
            if (userId != 0) {
                ResultSet query1 = statement.executeQuery("Select * from User where Id = " + userId);
                while (query1.next()) {
                    userName = query1.getString("Name");
                }
            }
            return userName;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public boolean changePin(String userName, String newPin) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmsystem", "root", "123456");
            Statement statement = con.createStatement();
            int userId = 0;
            ResultSet query = statement.executeQuery("Select * from LoginSession");
            while (query.next()) {
                userId = query.getInt("UserId");
            }
            /*int accNumber=0;
            ResultSet query2 = statement.executeQuery("Select * from Account where UserId = " + userId);
            while (query2.next()) {
                accNumber = query2.getInt("AccNumber");
            }*/
            String ss = "Update User set Password = '" + newPin + "' where Id = " + userId + "";
            boolean updateAcc = statement.execute("Update User set Password = '" + newPin + "' where Id = " + userId + "");
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

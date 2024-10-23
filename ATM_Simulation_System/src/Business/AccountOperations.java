package Business;

import java.sql.*;

public class AccountOperations {
    public static void Account() {

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

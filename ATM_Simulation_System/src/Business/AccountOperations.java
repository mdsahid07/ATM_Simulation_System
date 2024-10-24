package Business;

import Data_Access.MainDAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public static Integer getUserId() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmsystem", "root", "123456");
            Statement statement = con.createStatement();
            int userId = 0;
            ResultSet query = statement.executeQuery("Select * from LoginSession");
            while (query.next()) {
                userId = query.getInt("UserId");
            }
            return userId;
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

    public boolean updateAmountFromAcc(int toAcc, double amount) {
        try {
            double currentBalance = 0;
            ResultSet set = MainDAL.read("Select * from Account where AccNumber = " + getAccountNumber());
            if (set.next()) {
                currentBalance = set.getDouble("Balance");
            }
            currentBalance -= amount;
            String sql = "UPDATE account SET Balance = " + currentBalance + "WHERE ACCNUMBER=" + getAccountNumber(); // Change to your table and columns
            if (MainDAL.write(sql)) {
                addTransaction(getAccountNumber(), toAcc, TransactionType.TRANSFER, amount);
                return true;
            } else
                return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTransaction(int sender, int receiver, TransactionType type, double amount) {
        String sql = "INSERT INTO TRANSACTION (Amount,SenderAcc, ReceiverAcc, TransactionType,UserId) VALUES (" + amount + "," + sender + "," + receiver + ",'" + type.toString() + "',"+getUserId()+")";
//        System.out.println(sql);
        MainDAL.write(sql);
    }

    public boolean updateAmountToAcc(int toAcc, double amount) {
        try {
            double currentBalance = 0;
            ResultSet set = MainDAL.read("Select * from Account where AccNumber = " + toAcc);
            if (set.next()) {
                currentBalance = set.getDouble("Balance");
            }
            double newAmount = amount + currentBalance;
            String sql = "UPDATE account SET Balance = " + newAmount + "WHERE ACCNUMBER=" + toAcc; // Change to your table and columns
            if (MainDAL.write(sql)) {
                addTransaction(getAccountNumber(), toAcc, TransactionType.DEPOSITE, amount);

                return true;
            } else
                return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkAcc(int acc) {
        try {
            ResultSet set = MainDAL.read("SELECT * FROM  Account WHERE AccNumber = " + acc + "");
            if (set.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<NewAccount> accountLIst() {
        try {
            List<NewAccount> accountList = new ArrayList<>();
            ResultSet set = MainDAL.read("SELECT * FROM  transaction WHERE UserId = " + getUserId() + " order by Date Desc limit 15");
            while (set.next()) {
                NewAccount acc=new NewAccount();
                acc.Date=set.getTimestamp("Date");
                acc.Type=set.getString("TransactionType");
                acc.balance=set.getDouble("Amount");
                accountList.add(acc);
            }
            return accountList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<NewAccount> allAccountLIst() {
        try {
            List<NewAccount> accountList = new ArrayList<>();
            ResultSet set = MainDAL.read("SELECT * FROM  transaction");
            while (set.next()) {
                NewAccount acc=new NewAccount();
                acc.Date=set.getTimestamp("Date");
                acc.Type=set.getString("TransactionType");
                acc.balance=set.getDouble("Amount");


                accountList.add(acc);
            }
            return accountList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

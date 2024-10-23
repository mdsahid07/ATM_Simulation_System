package Business;

import Data_Access.MainDAL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accountNumber;


    private double balance;
    private String pin;
    public User holderName;
    private List<Transcation> transactions;
    public String address;
    public String phone;

    public Account(User holderName , int accountNumber,double balance,String address,String phone) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.address = address;
        this.phone = phone;
        this.balance = balance;
    }


    public String getPin() {
        return pin;
    }

    public List<Transcation> getTransactions() {
        return transactions;
    }

    public double checkBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
       if(amount<=balance) {
           balance -= amount;
           addTransaction( TransactionType.WITHDRAWAL, amount);
           return true;
       }
       else
           return false;
    }


    public void deposit(double amount) throws SQLException {
        this.balance += amount;
        String sql = "SELECT BALANCE FROM account WHERE ACCNUMBER=="+this.accountNumber; // Change to your table and columns
        ResultSet reslut = MainDAL.read(sql);
        double lastBalance = 0.0;
        if (reslut.next())
            lastBalance = reslut.getDouble(1);

        lastBalance +=  this.balance;

        sql = "UPDATE account SET Balance = "+lastBalance+ "WHERE ACCNUMBER=="+this.accountNumber; // Change to your table and columns
        if (MainDAL.write(sql))
            JOptionPane.showMessageDialog(null, "Money is successful deposited");

        addTransaction( TransactionType.DEPOSITE, amount);

    }

    public void cahngePin(String pin) {
        this.pin = pin;
    }

    public boolean transfer(Account receiver , double amount) throws SQLException {
        if(amount<=balance) {
            balance -= amount;
            receiver.deposit(amount);
            addTransaction(receiver, TransactionType.TRANSFER, amount);
            return true;
        }
        else
            return false;
    }

    void addTransaction(Account receiver, TransactionType type, double amount)
    {
        if (transactions==null)
            transactions =   new ArrayList<>();

        Transcation trans = new Transcation(this, receiver, type,amount);
        transactions.add(trans);
    }

    void addTransaction( TransactionType type, double amount)
    {
        if (transactions==null)
            transactions =   new ArrayList<>();

        Transcation trans = new Transcation(this, type,amount);
        transactions.add(trans);
    }
    public int getAccountNumber(){
        return this.accountNumber;
    }
    public double getBalanceDefault(){
        return this.balance;
    }
    public User getUser(){
        return this.holderName;
    }

    public static double getBalance() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmsystem", "root", "123456");
            Statement statement = con.createStatement();
            int userId = 0;
            ResultSet query = statement.executeQuery("Select * from LoginSession");
            while (query.next()) {
                userId = query.getInt("UserId");
            }
            double totalBalance=0;
            ResultSet query2 = statement.executeQuery("Select * from Account where UserId = " + userId);
            while (query2.next()) {
                totalBalance = query2.getDouble("Balance");
            }
            return totalBalance;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

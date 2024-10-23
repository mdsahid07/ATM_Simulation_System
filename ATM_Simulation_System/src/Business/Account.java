package Business;

import Data_Access.MainDAL;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accountNumber;


    private double balance;
    private String pin;
    private User holderName;
    private List<Transcation> transactions;

    public Account(User holderName , int accountNumber) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
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


    public boolean deposit(double amount) throws SQLException {
        boolean deposited = false;
        this.balance += amount;
        String sql = "SELECT BALANCE FROM account WHERE ACCNUMBER="+this.accountNumber; // Change to your table and columns
        ResultSet reslut = MainDAL.read(sql);
        double lastBalance = 0.0;
        if (reslut.next())
            lastBalance = reslut.getDouble(1);

        lastBalance +=  this.balance;

        sql = "UPDATE account SET Balance = "+lastBalance+ "WHERE ACCNUMBER="+this.accountNumber; // Change to your table and columns
        if (MainDAL.write(sql)) {
            JOptionPane.showMessageDialog(null, "Money is deposited successfully");
            sql = "INSERT INTO transaction (Amount, SenderAcc, TransactionType)" + "VALUES (" + amount + "," + this.accountNumber + ",'" + TransactionType.DEPOSITE.toString() + "')";
            if (MainDAL.write(sql))
                JOptionPane.showMessageDialog(null, "Transaction is add successfully");
            deposited = true;
        }
        else {
            JOptionPane.showMessageDialog(null, "Failed to deposite!");
        }

        addTransaction( TransactionType.DEPOSITE, amount);

        return deposited;
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

    
}

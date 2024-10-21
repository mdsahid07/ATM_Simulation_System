package Business;

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


    public void deposit(double amount) {
        this.balance += amount;
        addTransaction( TransactionType.DEPOSITE, amount);

    }

    public void cahngePin(String pin) {
        this.pin = pin;
    }

    public boolean transfer(Account receiver , double amount) {
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

package Business;

import java.util.Date;

public class NewAccount {
    public Date Date;
    public String Type;
    public double balance;
    public String Name;

    public Date getDate() {
        return Date;
    }
    public void setDate(Date date) {
        this.Date = date;
    }
    public String getType() {
        return Type;
    }
    public void setType(String type) {
        Type = type;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
}

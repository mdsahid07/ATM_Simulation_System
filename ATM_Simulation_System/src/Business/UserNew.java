package Business;

public class UserNew {
    private String name;
    private String accountNumber;
    private double balance;
    private String ssn;
    private String zip;
    private int Id;

    // Constructor
    public UserNew(String name, String accountNumber, double balance, String ssn, String zip, int Id) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.ssn = ssn;
        this.zip = zip;
        this.Id = Id;
    }

    // Getters and Setters
    public Integer getId(){
        return Id;
    }
    public void setId(int Id){
        this.Id = Id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getSSN() {
        return ssn;
    }

    public void setSSN(String ssn) {
        this.ssn = ssn;
    }

    public String getZIP() {
        return zip;
    }

    public void setZIP(String zip) {
        this.zip = zip;
    }
}

package Business;

import java.time.LocalDate;

public class Transcation {
    private Account SenderAccount;
    private Account receiverAccount;
    private TransactionType type;
    private LocalDate date;
    private double amount;

    public Transcation(Account senderAccount, Account receiverAccount, TransactionType type, double amount) {
        SenderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.type = type;
        this.amount = amount;
    }

    public Transcation(Account senderAccount, TransactionType type, double amount) {
        SenderAccount = senderAccount;
        this.receiverAccount = null;
        this.type = type;
        this.amount = amount;
    }

}

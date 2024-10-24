package UI;

import Business.Account;
import Business.User;
import Data_Access.MainDAL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransferForm extends JFrame{
    private JPanel MainPanel;
    private JTextField ToAccountTextField;
    private JTextField FromAccountTextField;
    private JTextField amountTextField;
    private JButton transferButton;

    public TransferForm() {
        setContentPane(MainPanel);
        setTitle("Deposit Form");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    makeTransfer();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) {
        new TransferForm();
    }


    private void makeTransfer() throws SQLException {
        int fromAccountNo;
        int toAccountNo;
        double amount;

        try {
            fromAccountNo = Integer.parseInt(FromAccountTextField.getText());
            toAccountNo = Integer.parseInt(ToAccountTextField.getText());
            amount = Double.parseDouble(amountTextField.getText());
            if (amount<=0)
            {
                JOptionPane.showMessageDialog(null, "The amount must be greater than zero");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Invalid account number");
            return; // Handle error appropriately
        }

        String sql = "SELECT USERID FROM LOGINSESSION";
        int id = 0;

        try (ResultSet result = MainDAL.read(sql)) {
            if (result.next()) {
                id = result.getInt(1);
            }
        }

        String senderName = "";
        int senderAccountNo = 0;
        double balance = 0.0;

        sql = "SELECT NAME, ACCNUMBER, BALANCE FROM ACCOUNT WHERE USERID=? AND ACCNUMBER=?";
        try (PreparedStatement pstmt = MainDAL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, fromAccountNo);

            try (ResultSet result2 = pstmt.executeQuery()) {
                if (result2.next()) {
                    senderName = result2.getString(1);
                    senderAccountNo = result2.getInt(2);
                    balance = result2.getInt(3);
                }
            }
        }

        String receiverName = "";
        int receiverAccountNo = 0;


        sql = "SELECT NAME, ACCNUMBER FROM ACCOUNT WHERE ACCNUMBER=?";
        try (PreparedStatement pstmt = MainDAL.getConnection().prepareStatement(sql)) {

            pstmt.setInt(1, toAccountNo);

            try (ResultSet result3 = pstmt.executeQuery()) {
                if (result3.next()) {
                    receiverName = result3.getString(1);
                    receiverAccountNo = result3.getInt(2);
                }
            }
        }


        if (balance >0 &&  receiverAccountNo!=0) {
            User sender = new User(senderName, id);
            Account senderAccount = new Account(sender, fromAccountNo,0,"","");

            User receiver  = new User(senderName, id);
            Account receiverAccount = new Account(receiver, toAccountNo,0,"","");

//            System.out.println(senderAccount.getAccountNumber());
//            System.out.println(receiverAccount.getAccountNumber());


            // if(senderAccount.withdraw(amount) && receiverAccount.deposit(amount))
            if(senderAccount.transfer(receiverAccount, amount))
            {

                JOptionPane.showMessageDialog(null, "Your transaction is successful.");
                new MainWindow();
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Account number does not match.");
        }
    }


}

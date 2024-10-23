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
            fromAccountNo = Integer.parseInt(accountTextField.getText());
            toAccountNo = Integer.parseInt(accountTextField.getText());
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

        String name = "";
        int acc = 0;

        sql = "SELECT NAME, ACCNUMBER FROM ACCOUNT WHERE USERID=? AND ACCNUMBER=?";
        try (PreparedStatement pstmt = MainDAL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, accountNo);

            try (ResultSet result2 = pstmt.executeQuery()) {
                if (result2.next()) {
                    name = result2.getString(1);
                    acc = result2.getInt(2);
                }
            }
        }

        if (acc == accountNo) {
            User user = new User(name, id);
            Account account = new Account(user, accountNo);
            if(account.deposit(amount))
            {
                new MainWindow();
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Account number does not match.");
        }
    }


}

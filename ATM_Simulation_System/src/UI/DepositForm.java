package UI;

import Business.Account;
import Business.AccountOperations;
import Business.SessionManager;
import Business.User;
import Data_Access.MainDAL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static javax.swing.UIManager.getString;

public class DepositForm extends JFrame {
    private JFrame frame;
    private JPanel MainPanel;
    private JTextField accountTextField;
    private JTextField amountTextField;
    private JButton depositButton;
    private JButton cancelButton;
    private JLabel accountLabel;
    private JLabel amountLabel;


    public DepositForm() {
        setContentPane(MainPanel);
        setTitle("Deposit Form");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));
        setSize(400, 400);
        setLocationRelativeTo(null);


        // Add action listener for the add button
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    makeDeposit();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Add action listener for the back button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ATMWelcomePage settings = new ATMWelcomePage(AccountOperations.getUserName(),AccountOperations.getAccountNumber());
                frame.setVisible(true);
                frame.dispose(); // Close this window
            }
        });


    }

    private void makeDeposit() throws SQLException {
        int accountNo = Integer.valueOf(accountTextField.getText());
        double amount = Double.valueOf(amountTextField.getText());

        String sql = "SELECT USERID FROM LOGINSESSION";
        int  id = 0;

        try (ResultSet result = MainDAL.read(sql)) {
            if (result.next()) {
                id = result.getInt(1);
            }
        }

        String name = "";
        int acc = 0;

        sql = "SELECT NAME, ACCNUMBER FROM ACCOUNT WHERE  USERID="+id; // Change to your table and columns
        try(ResultSet reslut2 =MainDAL.read(sql)) {

            if(reslut2.next())
            {
                name = reslut2.getString(1);
                acc = reslut2.getInt(2);
            }
        }

        if (acc==accountNo) {
            User user = new User(name, Integer.valueOf(id));
            Account account = new Account(user, accountNo,0,"","");
            if(account.deposit(amount))
                JOptionPane.showMessageDialog(null, "Money is successful deposited");

        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DepositForm form = new DepositForm();
            form.setVisible(true);
        });
    }


}
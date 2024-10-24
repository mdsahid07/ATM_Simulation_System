package UI;

import Business.Account;
import Business.AccountOperations;
import Business.User;
import Data_Access.MainDAL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepositNew {

    private JFrame frame;
    private JTextField txtDepositAmount;
    private JLabel lblCurrentBalance;
    private double accountBalance;

    // Constructor to initialize the deposit form with the current balance
    public DepositNew() {
        //this.accountBalance = initialBalance;
        this.accountBalance = Account.getBalance();
        initialize();
    }

    // Initialize the contents of the frame
    private void initialize() {
        // Create the main frame
        frame = new JFrame("ATM Deposit Form");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setLayout(null); // Use absolute layout for custom positioning

        // Label for current balance
        JLabel lblBalance = new JLabel("Current Balance:");
        lblBalance.setFont(new Font("Arial", Font.PLAIN, 16));
        lblBalance.setBounds(50, 50, 150, 25);
        frame.add(lblBalance);

        lblCurrentBalance = new JLabel("$" + accountBalance);
        lblCurrentBalance.setFont(new Font("Arial", Font.BOLD, 16));
        lblCurrentBalance.setBounds(200, 50, 150, 25);
        frame.add(lblCurrentBalance);

        // Label for deposit amount
        JLabel lblDeposit = new JLabel("Deposit Amount:");
        lblDeposit.setFont(new Font("Arial", Font.PLAIN, 16));
        lblDeposit.setBounds(50, 100, 150, 25);
        frame.add(lblDeposit);

        // Text field to enter deposit amount
        txtDepositAmount = new JTextField();
        txtDepositAmount.setBounds(200, 100, 150, 25);
        frame.add(txtDepositAmount);

        // Button to confirm deposit
        JButton btnDeposit = new JButton("Deposit");
        btnDeposit.setBounds(80, 160, 100, 30);
        frame.add(btnDeposit);

        // Button to cancel and close the form
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(220, 160, 100, 30);
        frame.add(btnCancel);

        // ActionListener for the Deposit button
        btnDeposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performDeposit();
                ATMWelcomePage settings = new ATMWelcomePage(AccountOperations.getUserName(),AccountOperations.getAccountNumber());
                frame.setVisible(true);
                frame.dispose();
            }
        });

        // ActionListener for the Cancel button
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ATMWelcomePage settings = new ATMWelcomePage(AccountOperations.getUserName(),AccountOperations.getAccountNumber());
                frame.setVisible(true);
                frame.dispose();  // Close the form
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to perform deposit
    private void performDeposit() {
        try {
            String depositAmountText = txtDepositAmount.getText();
            double depositAmount = Double.parseDouble(depositAmountText);

            if (depositAmount <= 0) {
                JOptionPane.showMessageDialog(frame, "Deposit amount must be greater than 0.");
                return;
            }

            // Update the account balance
            accountBalance += depositAmount;
            int userId=0;
            ResultSet result = MainDAL.read("Select * from LoginSession");
            if (result.next()){
                userId = result.getInt("UserId");
            }
            User user = new User(AccountOperations.getUserName(), userId);
            Account account = new Account(user, AccountOperations.getAccountNumber(),0,"","");
            if(account.deposit(depositAmount))
                JOptionPane.showMessageDialog(frame, "Successfully deposited $" + depositAmount);


            lblCurrentBalance.setText("$" + accountBalance);


            txtDepositAmount.setText(""); // Clear the input field

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid deposit amount.");
        }
    }

    // Main method to launch the deposit form
    public static void main(String[] args) {
        new DepositNew(); // Initialize the deposit form with an example balance of $1000
    }
}
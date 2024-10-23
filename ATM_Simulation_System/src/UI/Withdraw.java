package UI;

import Business.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Withdraw {
    private JFrame frame;
    private JTextField amountField;
    private double currentBalance;

    public Withdraw() {
        this.currentBalance = Account.getBalance();
        initialize();
        frame.setVisible(true);
    }

    // Initialize the contents of the frame
    private void initialize() {
        // Create the main frame
        frame = new JFrame("ATM - Withdraw");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setLayout(null); // Use absolute layout for custom positions

        // Title label
        JLabel titleLabel = new JLabel("Withdraw Money");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(100, 20, 200, 30);
        frame.add(titleLabel);

        // Label and input for Withdraw Amount
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(50, 80, 100, 25);
        frame.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(150, 80, 150, 25);
        frame.add(amountField);

        // Withdraw button
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(80, 150, 100, 30);
        frame.add(withdrawButton);

        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 150, 100, 30);
        frame.add(cancelButton);

        // ActionListener for the Withdraw button
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWithdrawAction();
            }
        });

        // ActionListener for the Cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow settings = new MainWindow();
                settings.setVisible(true);
                frame.dispose(); // Close the window when Cancel is clicked
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Handle the Withdraw button action
    private void handleWithdrawAction() {
        String amountText = amountField.getText();

        // Validate the input amount
        if (amountText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter an amount.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double withdrawAmount;
        try {
            withdrawAmount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (withdrawAmount <= 0) {
            JOptionPane.showMessageDialog(frame, "Amount must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (withdrawAmount > currentBalance) {
            JOptionPane.showMessageDialog(frame, "Insufficient funds. Current Balance: $" + currentBalance, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Successful withdrawal
        currentBalance -= withdrawAmount;
        if (Account.withdraw(currentBalance)){
            JOptionPane.showMessageDialog(frame, "Withdrawal successful! New Balance: $" + currentBalance);
            MainWindow settings = new MainWindow();
            settings.setVisible(true);
            frame.dispose(); // Close the window after success
        }
        else {
            JOptionPane.showMessageDialog(frame, "Withdrawal Failed", "Error", JOptionPane.ERROR_MESSAGE);
           // frame.dispose(); // Close the window after success
        }


    }

    // Main method to launch the application
    public static void main(String[] args) {
        // Simulate an account with an initial balance of $1,000
        new Withdraw();
    }

    public void setVisible(boolean b) {
    }
}

package UI;

import Business.AccountOperations;
import Business.SystemModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMWelcomePage {

    private JFrame frame;
    private String userName;
    private int accountNumber;

    // Constructor to initialize the welcome page with the user's name and account number
    public ATMWelcomePage(String userName, int accountNumber) {
        this.userName = userName;
        this.accountNumber = accountNumber;
        initialize();

    }

    // Initialize the contents of the frame
    private void initialize() {
        // Create the main frame
        frame = new JFrame("ATM - Welcome");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setLayout(null); // Use absolute layout for custom positions

        // Welcome message with user's name
        JLabel welcomeLabel = new JLabel("Welcome, " + userName + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBounds(80, 30, 300, 30);
        frame.add(welcomeLabel);

        // Display the account number
        JLabel accountNumberLabel = new JLabel("Account Number: " + accountNumber);
        accountNumberLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        accountNumberLabel.setBounds(100, 70, 250, 25);
        frame.add(accountNumberLabel);

        // Balance Inquiry button
        JButton balanceInquiryButton = new JButton("Balance Inquiry");
        balanceInquiryButton.setBounds(120, 120, 150, 30);
        frame.add(balanceInquiryButton);

        // Withdraw button
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(120, 170, 150, 30);
        frame.add(withdrawButton);

        // Deposit button
        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(120, 220, 150, 30);
        frame.add(depositButton);

        // Change PIN button
        JButton changePinButton = new JButton("Change PIN");
        changePinButton.setBounds(120, 270, 150, 30);
        frame.add(changePinButton);

        // Change PIN button
        JButton transferButton = new JButton("Transfer Money");
        transferButton.setBounds(120, 320, 150, 30);
        frame.add(transferButton);

        // Logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(120, 370, 150, 30);
        frame.add(logoutButton);

        // ActionListener for the Balance Inquiry button
        balanceInquiryButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Navigate to Balance Inquiry page
//                JOptionPane.showMessageDialog(frame, "Navigating to Balance Inquiry page...");
//                // Open the actual Balance Inquiry form here
//            }
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckBalance settings = new CheckBalance();
                settings.setVisible(true);
                frame.dispose();
            }
        });

        // ActionListener for the Withdraw button
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Withdraw settings = new Withdraw();
                settings.setVisible(true);
                frame.dispose();
            }
        });

        // ActionListener for the Deposit button
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DepositForm settings = new DepositForm();
                settings.setVisible(true);
                frame.dispose();
            }
        });

        // ActionListener for the Change PIN button
        changePinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings settings = new Settings();
                settings.setVisible(true);
                frame.dispose();
            }
        });

        // ActionListener for the Change PIN button
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransferForm settings = new TransferForm();
                settings.setVisible(true);
                frame.dispose();
            }
        });

        // ActionListener for the Logout button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (SystemModel.Log_Out()) {
                    JOptionPane.showMessageDialog(frame, "You have been logged out.");

                    Login login = new Login();
                    login.setVisible(true);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to log out.");
                }
                 // Close the window and log the user out
                // Optionally, navigate to the Login page again
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Main method to launch the Welcome Page
    public static void main(String[] args) {
        // Simulate passing the logged-in user's name and account number
        new ATMWelcomePage(AccountOperations.getUserName(), AccountOperations.getAccountNumber());
    }
}
package UI;

import Business.Account;
import Business.AccountOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferNew {

    private JFrame frame;
    private JTextField txtRecipientAccount, txtTransferAmount;
    private JLabel lblCurrentBalance;
    private double accountBalance;

    // Constructor to initialize the transfer form with the current balance
    public TransferNew() {
        this.accountBalance = Account.getBalance();
        initialize();
    }

    // Initialize the contents of the frame
    private void initialize() {
        // Create the main frame
        frame = new JFrame("ATM Transfer Money Form");
        frame.setSize(450, 350);
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

        // Label for recipient account number
        JLabel lblRecipientAccount = new JLabel("Recipient Account:");
        lblRecipientAccount.setFont(new Font("Arial", Font.PLAIN, 16));
        lblRecipientAccount.setBounds(50, 100, 150, 25);
        frame.add(lblRecipientAccount);

        // Text field for recipient account number
        txtRecipientAccount = new JTextField();
        txtRecipientAccount.setBounds(200, 100, 200, 25);
        frame.add(txtRecipientAccount);

        // Label for transfer amount
        JLabel lblTransferAmount = new JLabel("Transfer Amount:");
        lblTransferAmount.setFont(new Font("Arial", Font.PLAIN, 16));
        lblTransferAmount.setBounds(50, 150, 150, 25);
        frame.add(lblTransferAmount);

        // Text field to enter transfer amount
        txtTransferAmount = new JTextField();
        txtTransferAmount.setBounds(200, 150, 200, 25);
        frame.add(txtTransferAmount);

        // Button to confirm transfer
        JButton btnTransfer = new JButton("Transfer");
        btnTransfer.setBounds(80, 220, 100, 30);
        frame.add(btnTransfer);

        // Button to cancel and close the form
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(220, 220, 100, 30);
        frame.add(btnCancel);

        // ActionListener for the Transfer button
        btnTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String recipientAccount = txtRecipientAccount.getText();
                    String transferAmountText = txtTransferAmount.getText();
                    double transferAmount = Double.parseDouble(transferAmountText);
                    AccountOperations accountOperations = new AccountOperations();
                    // Validation for transfer amount
                    if (recipientAccount.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please enter a recipient account number.");
                        frame.setVisible(true);
                        return;
                    }
                    if (transferAmountText.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid amount.");
                        frame.setVisible(true);
                        return;
                    }
                    if (transferAmount <= 0) {
                        JOptionPane.showMessageDialog(frame, "Transfer amount must be greater than 0.");
                        frame.setVisible(true);
                        return;
                    }

                    if (transferAmount > accountBalance) {
                        JOptionPane.showMessageDialog(frame, "Insufficient balance for this transfer.");
                        frame.setVisible(true);
                        return;
                    }
                    if (!accountOperations.checkAcc(Integer.valueOf(recipientAccount))){
                        JOptionPane.showMessageDialog(frame, "Invalid recipient account number.");
                        frame.setVisible(true);
                        return;
                    }

                    // Update the account balance after the transfer
                    accountBalance -= transferAmount;

                    accountOperations.updateAmountFromAcc(Integer.valueOf(recipientAccount),transferAmount);
                    accountOperations.updateAmountToAcc(Integer.valueOf(recipientAccount),transferAmount);
                    lblCurrentBalance.setText("$" + accountBalance);

                    JOptionPane.showMessageDialog(frame, "Successfully transferred $" + transferAmount + " to account: " + recipientAccount);

                    // Clear the input fields after a successful transfer
                    txtRecipientAccount.setText("");
                    txtTransferAmount.setText("");

                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid transfer amount.");
                    frame.setVisible(true);
                }
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
                frame.dispose(); // Close the form
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }



    // Main method to launch the transfer form
    public static void main(String[] args) {
        new TransferNew(); // Initialize the transfer form with an example balance of $1000
    }
}

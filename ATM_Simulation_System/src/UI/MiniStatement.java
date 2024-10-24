package UI;

import Business.AccountOperations;
import Business.NewAccount;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiniStatement {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private String[] columnNames = {"Date", "Transaction Type", "Amount"};

    // Constructor to initialize the mini statement form
    public MiniStatement() {
        initialize();
    }

    // Initialize the contents of the frame
    private void initialize() {
        // Create the main frame
        frame = new JFrame("ATM Mini Statement");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setLayout(null); // Use absolute layout for custom positioning

        // Label for Mini Statement
        JLabel lblMiniStatement = new JLabel("Mini Statement");
        lblMiniStatement.setFont(new Font("Arial", Font.BOLD, 18));
        lblMiniStatement.setBounds(180, 20, 200, 30);
        frame.add(lblMiniStatement);

        // Create the table model and table to display the mini statement
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 70, 400, 200);
        frame.add(scrollPane);

        // Load mock data into the table (in a real application, you'd load actual transaction data)
        loadTransactionData();

        // Button to cancel and close the form
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(200, 300, 100, 30);
        frame.add(btnCancel);

        // ActionListener for the Cancel button
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ATMWelcomePage settings = new ATMWelcomePage(AccountOperations.getUserName(), AccountOperations.getAccountNumber());
                frame.setVisible(true);
                frame.dispose();// Close the form
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to load mock transaction data into the table
    private void loadTransactionData() {
        // In a real-world application, you'd retrieve this data from a database or an API

        AccountOperations aco = new AccountOperations();

        for (NewAccount transaction : aco.accountLIst()) {
            Object[] rowData = {transaction.getDate(), transaction.getType(), "$" + transaction.getBalance()};
            tableModel.addRow(rowData);
        }
    }

    // Main method to launch the mini statement form
    public static void main(String[] args) {
        new MiniStatement(); // Display the Mini Statement form
    }
}

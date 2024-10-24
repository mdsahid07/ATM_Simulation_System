package UI.Managment;

import Business.AccountOperations;
import Business.NewAccount;
import UI.ATMWelcomePage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AllTransaction {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private String[] columnNames = {"Date","User Name", "Transaction Type", "Amount"};

    // Constructor to initialize the mini statement form
    public AllTransaction() {
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
        JLabel lblMiniStatement = new JLabel("All Transaction");
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
        Object[][] transactions = {
                {"2024-10-10", "Deposit", "$500.00"},
                {"2024-10-12", "Withdrawal", "$200.00"},
                {"2024-10-15", "Deposit", "$300.00"},
                {"2024-10-18", "Withdrawal", "$100.00"},
                {"2024-10-20", "Deposit", "$600.00"}
        };
        AccountOperations aco = new AccountOperations();

        for (NewAccount transaction : aco.allAccountLIst()) {
            Object[] rowData = {transaction.getDate(),transaction.getName(), transaction.getType(), "$" + transaction.getBalance()};
            tableModel.addRow(rowData);
        }
    }

    // Main method to launch the mini statement form
    public static void main(String[] args) {
        new AllTransaction(); // Display the Mini Statement form
    }
}

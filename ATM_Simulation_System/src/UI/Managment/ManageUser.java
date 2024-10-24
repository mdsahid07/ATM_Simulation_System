package UI.Managment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ManageUser {

    // List to store users (this can be replaced by a database in a real-world application)
    private List<User> userList = new ArrayList<>();

    // Main frame
    private JFrame frame;
    private JTextField txtUserId, txtUserName, txtAccountNumber, txtBalance;
    private JTable userTable;
    private DefaultTableModel tableModel;

    // Constructor to initialize the frame
    public ManageUser() {
        initialize();
    }

    // Initialize the contents of the frame
    private void initialize() {
        // Create the main frame
        frame = new JFrame("ATM User Management System");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // Labels for user fields
        JLabel lblUserId = new JLabel("User ID:");
        lblUserId.setBounds(20, 20, 100, 25);
        frame.add(lblUserId);

        JLabel lblUserName = new JLabel("Name:");
        lblUserName.setBounds(20, 60, 100, 25);
        frame.add(lblUserName);

        JLabel lblAccountNumber = new JLabel("Account Number:");
        lblAccountNumber.setBounds(20, 100, 120, 25);
        frame.add(lblAccountNumber);

        JLabel lblBalance = new JLabel("Balance:");
        lblBalance.setBounds(20, 140, 100, 25);
        frame.add(lblBalance);

        // Text fields for user input
        txtUserId = new JTextField();
        txtUserId.setBounds(140, 20, 150, 25);
        frame.add(txtUserId);

        txtUserName = new JTextField();
        txtUserName.setBounds(140, 60, 150, 25);
        frame.add(txtUserName);

        txtAccountNumber = new JTextField();
        txtAccountNumber.setBounds(140, 100, 150, 25);
        frame.add(txtAccountNumber);

        txtBalance = new JTextField();
        txtBalance.setBounds(140, 140, 150, 25);
        frame.add(txtBalance);

        // Table for displaying users
        String[] columnNames = {"User ID", "Name", "Account Number", "Balance"};
        tableModel = new DefaultTableModel(columnNames, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBounds(20, 200, 550, 200);
        frame.add(scrollPane);

        // Buttons for CRUD operations
        JButton btnAdd = new JButton("Add User");
        btnAdd.setBounds(350, 20, 100, 25);
        frame.add(btnAdd);

        JButton btnUpdate = new JButton("Update User");
        btnUpdate.setBounds(350, 60, 120, 25);
        frame.add(btnUpdate);

        JButton btnDelete = new JButton("Delete User");
        btnDelete.setBounds(350, 100, 120, 25);
        frame.add(btnDelete);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(350, 140, 100, 25);
        frame.add(btnClear);

        // ActionListener for Add User button
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        // ActionListener for Update User button
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });

        // ActionListener for Delete User button
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });

        // ActionListener for Clear button
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        // Add mouse click listener to JTable to populate the text fields with selected row data
        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = userTable.getSelectedRow();
                if (selectedRow != -1) {
                    txtUserId.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    txtUserName.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    txtAccountNumber.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    txtBalance.setText(tableModel.getValueAt(selectedRow, 3).toString());
                }
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to add a new user
    private void addUser() {
        try {
            int id = Integer.parseInt(txtUserId.getText());
            String name = txtUserName.getText();
            String accountNumber = txtAccountNumber.getText();
            double balance = Double.parseDouble(txtBalance.getText());

            User user = new User(id, name, accountNumber, balance);
            userList.add(user);

            // Add the user to the table
            tableModel.addRow(new Object[]{id, name, accountNumber, balance});
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid data.");
        }
    }

    // Method to update an existing user
    private void updateUser() {
        try {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = Integer.parseInt(txtUserId.getText());
                String name = txtUserName.getText();
                String accountNumber = txtAccountNumber.getText();
                double balance = Double.parseDouble(txtBalance.getText());

                // Update user in the list
                User user = userList.get(selectedRow);
                user.setId(id);
                user.setName(name);
                user.setAccountNumber(accountNumber);
                user.setBalance(balance);

                // Update the user in the table
                tableModel.setValueAt(id, selectedRow, 0);
                tableModel.setValueAt(name, selectedRow, 1);
                tableModel.setValueAt(accountNumber, selectedRow, 2);
                tableModel.setValueAt(balance, selectedRow, 3);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a user to update.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid data.");
        }
    }

    // Method to delete a user
    private void deleteUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow != -1) {
            userList.remove(selectedRow);
            tableModel.removeRow(selectedRow);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a user to delete.");
        }
    }

    // Method to clear input fields
    private void clearFields() {
        txtUserId.setText("");
        txtUserName.setText("");
        txtAccountNumber.setText("");
        txtBalance.setText("");
    }

    // Main method to run the application
    public static void main(String[] args) {
        new ManageUser();
    }
}

// User class to represent a user
class User {
    private int id;
    private String name;
    private String accountNumber;
    private double balance;

    public User(int id, String name, String accountNumber, double balance) {
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

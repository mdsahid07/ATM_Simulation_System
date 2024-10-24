package UI.Managment;

import Business.Admin;
import Business.SystemModel;
import Business.UserNew;
import Data_Access.MainDAL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageUser {

    private JFrame frame;
    private JTextField txtName, txtBalance, txtSSN, txtZIP, txtAccount;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<UserNew> userList = new ArrayList<>();

    // Constructor to initialize the User CRUD form
    public ManageUser() {
        loadDataFromDatabase();
        initialize();
    }

    // Initialize the contents of the frame
    private void initialize() {
        // Create the main frame
        frame = new JFrame("ATM User Management System");
        frame.setSize(700, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setLayout(null); // Use absolute layout for custom positioning

        // Form Labels and TextFields
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(30, 30, 80, 25);
        frame.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(120, 30, 200, 25);
        frame.add(txtName);


        JLabel lblBalance = new JLabel("Balance:");
        lblBalance.setBounds(30, 70, 80, 25);
        frame.add(lblBalance);

        txtBalance = new JTextField();
        txtBalance.setBounds(120, 70, 200, 25);
        frame.add(txtBalance);

        JLabel lblSSN = new JLabel("SSN:");
        lblSSN.setBounds(30, 110, 80, 25);
        frame.add(lblSSN);

        txtSSN = new JTextField();
        txtSSN.setBounds(120, 110, 200, 25);
        frame.add(txtSSN);

        JLabel lblZIP = new JLabel("ZIP:");
        lblZIP.setBounds(30, 150, 80, 25);
        frame.add(lblZIP);

        txtZIP = new JTextField();
        txtZIP.setBounds(120, 150, 200, 25);
        frame.add(txtZIP);

        // CRUD Buttons (Add, Update, Delete, Clear)
        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(350, 30, 100, 25);
        frame.add(btnAdd);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(350, 70, 100, 25);
        frame.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(350, 110, 100, 25);
        frame.add(btnDelete);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(350, 150, 100, 25);
        frame.add(btnClear);

        // Table to display users
        tableModel = new DefaultTableModel(new String[]{"Name", "Account No", "Balance", "SSN", "ZIP"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 250, 620, 200);
        frame.add(scrollPane);

        // Button Action Listeners
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        // Table row selection to load the selected user details into the form
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                loadSelectedUser();
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Add user to the table and list
    private void addUser() {
        String name = txtName.getText();

        String balance = txtBalance.getText();
        String ssn = txtSSN.getText();
        String zip = txtZIP.getText();

        if (name.isEmpty() || balance.isEmpty() || ssn.isEmpty() || zip.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields are required.");
            return;
        }
        Admin admin = (Admin) SystemModel.role;
        if (admin.checkUserName(name)) {
            JOptionPane.showMessageDialog(null, "Name is already exist", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (admin.checkSSN(ssn)) {
            JOptionPane.showMessageDialog(null, "SSN is already exist", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //UserNew user = admin.add_user(name, ssn, zip, "");

        // Add new user to the list and table
        // UserNew user = new UserNew(name,"", Double.parseDouble(balance), ssn, zip);
//        userList.add(user);
//        tableModel.addRow(new Object[]{user.getName(), user.getAccountNumber(), user.getBalance(), user.getSSN(), user.getZIP()});

        clearForm();
        JOptionPane.showMessageDialog(frame, "User added successfully.");
    }

    // Update selected user in the table and list
    private void updateUser() {
        int selectedRow = table.getSelectedRow();
        String txtAccount11 = "0";
        if (selectedRow != -1) {
            txtAccount11 = (String) table.getValueAt(selectedRow, 1);
            JOptionPane.showMessageDialog(frame, "Please select a user to update.");
            return;
        }


        String name = txtName.getText();
        String account = txtAccount11;
        String balance = txtBalance.getText();
        String ssn = txtSSN.getText();
        String zip = txtZIP.getText();

        if (name.isEmpty() || balance.isEmpty() || ssn.isEmpty() || zip.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields are required.");
            return;
        }


        // Update user in the list and table
        Admin admin = (Admin) SystemModel.role;
       // admin.update_user(name, ssn, zip, "", Double.parseDouble(balance), Integer.parseInt(account));

        UserNew user = userList.get(selectedRow);
        user.setName(name);
        user.setAccountNumber(account);
        user.setBalance(Double.parseDouble(balance));
        user.setSSN(ssn);
        user.setZIP(zip);

        tableModel.setValueAt(user.getName(), selectedRow, 0);
        tableModel.setValueAt(user.getAccountNumber(), selectedRow, 1);
        tableModel.setValueAt(user.getBalance(), selectedRow, 2);
        tableModel.setValueAt(user.getSSN(), selectedRow, 3);
        tableModel.setValueAt(user.getZIP(), selectedRow, 4);

        clearForm();
        JOptionPane.showMessageDialog(frame, "User updated successfully.");
    }

    // Delete selected user from the table and list
    private void deleteUser() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a user to delete.");
            return;
        }

        // Remove user from the list and table
        userList.remove(selectedRow);
        tableModel.removeRow(selectedRow);

        clearForm();
        JOptionPane.showMessageDialog(frame, "User deleted successfully.");
    }

    // Clear the form fields
    private void clearForm() {
        txtName.setText("");

        txtBalance.setText("");
        txtSSN.setText("");
        txtZIP.setText("");
        table.clearSelection();
    }

    // Load selected user details into the form
    private void loadSelectedUser() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            UserNew user = userList.get(selectedRow);
            txtName.setText(user.getName());

            txtBalance.setText(String.valueOf(user.getBalance()));
            txtSSN.setText(user.getSSN());
            txtZIP.setText(user.getZIP());
        }
    }
    private void loadDataFromDatabase() {
        try {

            String sql="select u.Name,a.AccNumber,a.Balance,a.SSN,a.Address from User u join Account a on u.Id=a.UserId";
            ResultSet resultSet= MainDAL.read(sql);

            while (resultSet.next()) {

                    String name=    resultSet.getString("Name");
                int accNoo=    resultSet.getInt("AccNumber");
                double blnc=     resultSet.getDouble("Balance");
                String ssns=     resultSet.getString("SSN");
                String addr=      resultSet.getString("Address");
                //tableModel.addRow(new Object[]{ name, accNoo, blnc, ssns, addr});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Main method to launch the User CRUD form
    public static void main(String[] args) {
        new ManageUser(); // Launch the User Management System
    }

    // User class to represent a user in the system

}
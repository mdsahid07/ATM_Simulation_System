package UI;

import Business.Account;
import Data_Access.MainDAL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepositForm extends JFrame {
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
                addToDatabase();
            }
        });

        // Add action listener for the back button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
                dispose(); // Close this window
            }
        });


    }

    private void addToDatabase() {
        int accountNo = Integer.valueOf(accountTextField.getText());
        double amount = Double.valueOf(amountTextField.getText());


        Account account = new Account(accountNo);
        account.deposit(amount);


//        try (Connection conn = DriverManager.getConnection(url, user, password);
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setString(1, text1);
//            pstmt.setString(2, text2);
//
//            pstmt.executeUpdate();
//            JOptionPane.showMessageDialog(this, "Data added successfully!");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
//        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DepositForm form = new DepositForm();
            form.setVisible(true);
        });
    }


}
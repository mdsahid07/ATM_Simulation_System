package UI;

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
                dispose(); // Close this window
            }
        });


    }

    private void addToDatabase() {
        String text1 = accountTextField.getText();
        String text2 = amountTextField.getText();


        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/your_database"; // Change to your DB name
        String user = "your_username"; // Change to your DB username
        String password = "your_password"; // Change to your DB password

        String sql = "INSERT INTO your_table (column1, column2, column3) VALUES (?, ?, ?)"; // Change to your table and columns

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, text1);
            pstmt.setString(2, text2);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data added successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DepositForm form = new DepositForm();
            form.setVisible(true);
        });
    }


}
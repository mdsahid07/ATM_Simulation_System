package UI;

import Business.Account;
import Business.SessionManager;
import Business.User;
import Data_Access.MainDAL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static javax.swing.UIManager.getString;

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
                try {
                    makeDeposit();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
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

    private void makeDeposit() throws SQLException {
        int accountNo = Integer.valueOf(accountTextField.getText());
        double amount = Double.valueOf(amountTextField.getText());

        String sql = "SELECT USERID FROM LOGINSESSION";
        ResultSet reslut = MainDAL.read(sql);
        int  id = 0;
        String name = "";
        int acc = 0;
        if (reslut.next()){
            sql = "SELECT USER.ID, USER.NAME, ACCOUNT.ACCNUMBER FROM USER INNER JOIN ACCOUNT ON USER.ID  ACCOUNT.USERID"; // Change to your table and columns
            ResultSet reslut2 =MainDAL.read(sql);
            if(reslut2.next())
            {
                id = reslut2.getInt(1);
                name = reslut2.getString(2);
                acc = reslut2.getInt(3);
            }
        }

        if (acc==accountNo) {
            User user = new User(name, Integer.valueOf(id));
            Account account = new Account(user, accountNo);
            account.deposit(amount);
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DepositForm form = new DepositForm();
            form.setVisible(true);
        });
    }


}
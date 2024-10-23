package UI.Managment;

import UI.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsPage extends  JFrame{
    private JButton userListButton;
    private JButton accountListButton;
    private JButton transactionsButton;
    private JPanel panel1;

    public OptionsPage() {
        setContentPane(panel1);
        // Default visibility is false. You have enabled visibility true
        setVisible(true);
        // Terminates the Application when the frame is closed.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Account List");
        // Provide the frame width and height
        setSize(500, 500);
        // Make your screen center
        setLocationRelativeTo(null);
        setResizable(false);// If you wish
    userListButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            UserList userList = new UserList();
            userList.setVisible(true);
            dispose();
        }
    });
    accountListButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            AccountList accountList = new AccountList();
            accountList.setVisible(true);
            dispose();
        }
    });
    transactionsButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
}
    public static void main(String[] args) {
        new OptionsPage();
    }


}

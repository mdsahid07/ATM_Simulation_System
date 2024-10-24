package UI.Managment;

import Business.SystemModel;
import UI.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OptionsPage extends  JFrame{
    private JButton userListButton;
    private JButton transactionsButton;
    private JPanel panel1;
    private JButton logoutButton;
    private JFrame frame;
    public OptionsPage() {
        setContentPane(panel1);
        // Default visibility is false. You have enabled visibility true
        setVisible(true);
        // Terminates the Application when the frame is closed.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ADMIN");
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
    transactionsButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new TransactionList();
            dispose();
        }
    });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (SystemModel.Log_Out()) {
                    JOptionPane.showMessageDialog(frame, "You have been logged out.");

                    Login login = new Login();
                    login.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to log out.");
                }
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SystemModel.Log_Out();
            }
        });
//        logoutButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                SystemModel.Log_Out();
//                new Login();
//              //  dispose();
//            }
//        });
    }
    public static void main(String[] args) {
        new OptionsPage();
    }


}

package UI.Managment;

import javax.swing.*;
import java.awt.*;

public class AccountList extends JFrame {
    private JTable table1;
    private JButton searchButton;
    private JTextField textField1;
    private JPanel panel1;
    private JButton deleteButton;

    public AccountList() {
        setContentPane(panel1);
        // Default visibility is false. You have enabled visibility true
        setVisible(true);
        // Terminates the Application when the frame is closed.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Login Form");
        // Provide the frame width and height
        setSize(500, 500);
        // Make your screen center
        setLocationRelativeTo(null);
        setResizable(false);// If you wish

    }
    public static void main(String[] args) {
 /*
 While it is not mandatory to use EventQueue.invokeLater,
 it is a best practice for all Swing applications to ensure
 thread safety and avoid potential concurrency issues.
 */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                AccountList mf = new AccountList();
            }
        });
    }
}

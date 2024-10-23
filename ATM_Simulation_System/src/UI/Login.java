package UI;

import Business.SystemModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField uname;
    private JButton submit;
    private JPasswordField password;
    private JPanel mypanel;

    public Login() {
        // Default visibility is false. You have enabled visibility true
        setVisible(true);
        // Terminates the application when the frame is closed.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Login Form");
        // Provide the frame width and height
        setSize(400, 400);
        // Make your screen center
        setLocationRelativeTo(null);
        setResizable(false);// If you wish
        setContentPane(mypanel);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemModel sm = new SystemModel();
                // Read the Username and Password
                String username = uname.getText();
                char[] pwd = password.getPassword();
                String passwordStr = new String(pwd);
                // Perform validation
                if (username.isEmpty() || pwd.length == 0) {
                    JOptionPane.showMessageDialog(null, "Please enter both username and password", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (sm.VerifyLogin(username, passwordStr) != null) {
                    JOptionPane.showMessageDialog(null, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
//                else if (username.equals("admin") && Arrays.equals(pwd,"admin123".toCharArray())) {
//                    JOptionPane.showMessageDialog(null, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
//                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        /*
        While it is not mandatory to use EventQueue.invokeLater,
        it is a best practice for all Swing applications to ensure
        thread safety and avoid potential concurrency issues.
         */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login mf = new Login();
            }
        });
    }
}

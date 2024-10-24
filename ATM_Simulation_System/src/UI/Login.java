package UI;

import Business.ROLE_TYPE;
import Business.Role;
import Business.SystemModel;
import Data_Access.MainDAL;
import UI.Managment.OptionsPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    /*private JTextField uname;
    private JButton submit;
    private JPasswordField password;
    private JPanel mypanel;*/

    private JFrame frame;
    private JTextField uname;
    private JPasswordField password;

    public Login() {
        // Default visibility is false. You have enabled visibility true
       /* setVisible(true);
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
                    Role role = sm.VerifyLogin(username, passwordStr);
                    JOptionPane.showMessageDialog(null, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    if(role.getType() == ROLE_TYPE.ADMIN){
                        new OptionsPage();
                    }else{
                        new MainWindow();
                    }
                }
//                else if (username.equals("admin") && Arrays.equals(pwd,"admin123".toCharArray())) {
//                    JOptionPane.showMessageDialog(null, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
//                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        */




//        MainDAL.write("DELETE FROM LOGINSESSION");

        frame = new JFrame("ATM - Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setLayout(null); // Use absolute layout for custom positions

        // Title label
        JLabel titleLabel = new JLabel("ATM Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(130, 20, 150, 30);
        frame.add(titleLabel);

        // Label and input for Card Number
        JLabel cardNumberLabel = new JLabel("User Name:");
        cardNumberLabel.setBounds(50, 70, 100, 25);
        frame.add(cardNumberLabel);

        uname = new JTextField();
        uname.setBounds(160, 70, 150, 25);
        frame.add(uname);

        // Label and input for PIN
        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setBounds(50, 110, 100, 25);
        frame.add(pinLabel);

        password = new JPasswordField();
        password.setBounds(160, 110, 150, 25);
        frame.add(password);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(80, 180, 100, 30);
        frame.add(loginButton);

        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 180, 100, 30);
        frame.add(cancelButton);

        // ActionListener for the Login button
        loginButton.addActionListener(new ActionListener() {
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
                    Role role = sm.VerifyLogin(username, passwordStr);
                    if(role.getType() == ROLE_TYPE.ADMIN){
                        new OptionsPage();
                    }else{
                        JOptionPane.showMessageDialog(null, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        uname.setText("");
                        password.setText("");
                        new MainWindow();
                    }
                }
//                else if (username.equals("admin") && Arrays.equals(pwd,"admin123".toCharArray())) {
//                    JOptionPane.showMessageDialog(null, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
//                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ActionListener for the Cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the window when Cancel is clicked
            }
        });

        // Make the frame visible
        frame.setVisible(true);
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

package UI;

import Business.AccountOperations;
import Business.SessionManager;
import Business.SystemModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame {
    private JPasswordField newPin;
    private JButton submit;
    private JPasswordField confirmPin;
    private JPanel mypanel;
    private String userName;

    public Settings() {
        //get User Name from session
        String username = SessionManager.getInstance().getUsername();
        // Default visibility is false. You have enabled visibility true
        setVisible(true);
        // Terminates the application when the frame is closed.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Settings Form");
        // Provide the frame width and height
        setSize(500, 500);
        // Make your screen center
        setLocationRelativeTo(null);
        setResizable(false);// If you wish
        setContentPane(mypanel);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountOperations ao = new AccountOperations();
                // Read the Username and Password

                char[] newPin1 = newPin.getPassword();
                char[] conPin=confirmPin.getPassword();
                String newPinStr = new String(newPin1);
                String conPinStr = new String(conPin);
                // Perform validation
                if (newPinStr.isEmpty() || conPinStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter pin on both field", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!newPinStr.equals(conPinStr)) {
                    JOptionPane.showMessageDialog(null, "Confirm Pin does not match", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (newPinStr.length() !=4) {
                    JOptionPane.showMessageDialog(null, "Pin must be 4 character", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (ao.changePin(newPinStr, conPinStr)) {
                    JOptionPane.showMessageDialog(null, "Pin changed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid", "Error", JOptionPane.ERROR_MESSAGE);
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
                Settings mf = new Settings();
            }
        });
    }
}

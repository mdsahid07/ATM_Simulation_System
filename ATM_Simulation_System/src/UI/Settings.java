package UI;

import Business.AccountOperations;
import Business.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame {
    /*private JPasswordField newPin;
    private JButton submit;
    private JPasswordField confirmPin;
    private JPanel mypanel;*/
    //private String userName;

    private JFrame frame;

    private JPasswordField newPin;
    private JPasswordField confirmPin;

    public Settings() {
        //get User Name from session
        String username = SessionManager.getInstance().getUsername();
        // Default visibility is false. You have enabled visibility true
       /* setVisible(true);
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

                else if (ao.changePin(newPinStr, conPinStr)) {
                    JOptionPane.showMessageDialog(null, "Pin changed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new MainWindow();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
*/


        // Create the main frame
        frame = new JFrame("ATM - Change PIN");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setLayout(null); // Use absolute layout for custom positions

        // Title label
        JLabel titleLabel = new JLabel("Change PIN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(130, 20, 150, 30);
        frame.add(titleLabel);

        // Label and input for New PIN
        JLabel newPinLabel = new JLabel("New PIN:");
        newPinLabel.setBounds(50, 110, 100, 25);
        frame.add(newPinLabel);

        newPin = new JPasswordField();
        newPin.setBounds(160, 110, 150, 25);
        frame.add(newPin);

        // Label and input for Confirm New PIN
        JLabel confirmPinLabel = new JLabel("Confirm PIN:");
        confirmPinLabel.setBounds(50, 150, 100, 25);
        frame.add(confirmPinLabel);

        confirmPin = new JPasswordField();
        confirmPin.setBounds(160, 150, 150, 25);
        frame.add(confirmPin);

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(80, 200, 100, 30);
        frame.add(submitButton);

        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 200, 100, 30);
        frame.add(cancelButton);

        // ActionListener for the Submit button
        submitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AccountOperations ao = new AccountOperations();
                // Read the Username and Password

                char[] newPin1 = newPin.getPassword();
                char[] conPin = confirmPin.getPassword();
                String newPinStr = new String(newPin1);
                String conPinStr = new String(conPin);
                // Perform validation
                if (newPinStr.isEmpty() || conPinStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter pin on both field", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!newPinStr.equals(conPinStr)) {
                    JOptionPane.showMessageDialog(null, "Confirm Pin does not match", "Error", JOptionPane.ERROR_MESSAGE);
                }
                /*else if (newPinStr.length() !=4) {
                    JOptionPane.showMessageDialog(null, "Pin must be 4 character", "Error", JOptionPane.ERROR_MESSAGE);
                }*/
                else if (ao.changePin(newPinStr, conPinStr)) {
                    JOptionPane.showMessageDialog(null, "Pin changed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    newPin.setText("");
                    confirmPin.setText("");
                    ATMWelcomePage settings = new ATMWelcomePage(AccountOperations.getUserName(),AccountOperations.getAccountNumber());
                    frame.setVisible(true);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ActionListener for the Cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ATMWelcomePage settings = new ATMWelcomePage(AccountOperations.getUserName(),AccountOperations.getAccountNumber());
                frame.setVisible(true);
                frame.dispose();
                 // Close the window when Cancel is clicked
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
                Settings mf = new Settings();
            }
        });
    }
}

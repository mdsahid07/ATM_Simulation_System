package UI;

import Business.Account;
import Business.SystemModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckBalance extends JFrame {
    private JFrame frame;
    //private JPanel mypanel;
    //private JTextField balance;
    private JLabel balanceLabel;
    private double currentBalance;

    public CheckBalance() {
        // Default visibility is false. You have enabled visibility true
        /*setVisible(true);
        // Terminates the application when the frame is closed.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Check Balance Form");
        // Provide the frame width and height
        setSize(400, 400);
        // Make your screen center
        setLocationRelativeTo(null);
        setResizable(false);// If you wish
        setContentPane(mypanel);
        double currBalance = Account.getBalance();
        balance.setText(String.valueOf(currBalance));*/


        currentBalance = Account.getBalance();
        frame = new JFrame("ATM - Current Balance");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setLayout(null); // Use absolute layout for custom positions

        // Label for the current balance
        JLabel titleLabel = new JLabel("Current Balance");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(120, 30, 200, 30);
        frame.add(titleLabel);

        // Display the actual balance
        balanceLabel = new JLabel("$" + String.format("%.2f", currentBalance));
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        balanceLabel.setBounds(150, 80, 150, 30);
        frame.add(balanceLabel);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(80, 150, 100, 30);
        frame.add(backButton);

        // Logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(200, 150, 100, 30);
        frame.add(logoutButton);

        // ActionListener for the Back button
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow settings = new MainWindow();
                settings.setVisible(true);
                dispose();
            }
        });

        // ActionListener for the Logout button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SystemModel.Log_Out()){
                    JOptionPane.showMessageDialog(frame, "You have been logged out.");
                    frame.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Failed to log out.");
                }
                // Perform logout (for this example, we'll just close the window)

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
                CheckBalance mf = new CheckBalance();
            }
        });
    }
}

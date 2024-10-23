package UI;

import Business.SystemModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JButton makeADepositeButton;
    private JButton withdrawCashButton;
    private JButton transferMoneyButton;
    private JButton checkBalanceButton;
    private JButton changePinButton;
    private JPanel MainPanal;
    private JButton exitButton;
    private JFrame frame;

    public MainWindow() {
        setContentPane(MainPanal);
        setVisible(true);
        setTitle("Home");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        makeADepositeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DepositForm depositForm = new DepositForm();
                depositForm.setVisible(true);
                dispose();
            }
        });

        withdrawCashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Withdraw settings = new Withdraw();
                settings.setVisible(true);
                dispose();
            }
        });
        transferMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
// Create a new instance of the other form here

                dispose();
            }
        });
        checkBalanceButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CheckBalance settings = new CheckBalance();
                settings.setVisible(true);
                dispose();
            }
        });
        changePinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings settings = new Settings();
                settings.setVisible(true);
                dispose();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Login login =  new Login();
                // login.setVisible(true);
                //dispose();

                if (SystemModel.Log_Out()) {
                    JOptionPane.showMessageDialog(frame, "You have been logged out.");
                    dispose();
                    Login login = new Login();
                    login.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to log out.");
                }


            }
        });
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}

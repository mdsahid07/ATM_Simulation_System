package UI;

import javax.swing.*;
import java.awt.*;
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
// Create a new instance of the other form here

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
// Create a new instance of the other form here

                dispose();
            }
        });
        changePinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
// Create a new instance of the other form here

                dispose();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login =  new Login();
                login.setVisible(true);
                dispose();


            }
        });
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}

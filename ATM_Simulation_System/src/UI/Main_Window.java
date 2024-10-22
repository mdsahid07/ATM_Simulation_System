package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Window extends JFrame{
    private JButton depositButton;

    public Main_Window() {
        setVisible(true);
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    DepositForm depositForm =  new DepositForm();
                    depositForm.setVisible(true);
                    depositForm.setTitle("Deposit Cash");
                } catch (Exception ex) {
                    ex.printStackTrace();
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
                Main_Window mf = new Main_Window();
            }
        });
    }





}

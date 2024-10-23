package UI.Managment;

import Business.Admin;
import Business.SystemModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUser extends JFrame{
    private JPanel panel1;
    private JTextField nameField;
    private JTextField AdressField;
    private JTextField phoneField;
    private JButton confrim;
    private JButton cancel;
    private JTextField SSNField;

    public AddUser() {
        setContentPane(panel1);
        // Default visibility is false. You have enabled visibility true
        setVisible(true);
        // Terminates the Application when the frame is closed.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Account List");
        // Provide the frame width and height
        setSize(500, 500);
        // Make your screen center
        setLocationRelativeTo(null);
        setResizable(false);// If you wish
        Admin admin = (Admin) SystemModel.role;

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        confrim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = AdressField.getText();
                String phone = phoneField.getText();
                String SSN = SSNField.getText();

                if (name != null && address != null && phone != null) {
                    // Display the input back to the user
//                    JOptionPane.showMessageDialog(null, "You entered: " + input);
                    if(admin.add_user(name,SSN,address,phone)){
                        JOptionPane.showMessageDialog(null, "Add Successfully", "Message", JOptionPane.PLAIN_MESSAGE);
                        dispose();
                        new UserList();
                    }else{
                        JOptionPane.showMessageDialog(null, "Add Error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // If the user canceled the input
                    JOptionPane.showMessageDialog(null, "No input entered.");
                }
            }
        });
    }
    public static void main(String[] args) {
        new AddUser();
    }
}

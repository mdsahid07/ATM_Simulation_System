package UI.Managment;

import Business.Admin;
import Business.Role;
import Business.SystemModel;
import Business.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class UserList extends JFrame{
    private JPanel panel1;
    private JButton addButton;
    private JButton createAccountButton;
    private JTable table1;
    private JButton deleteButton;

    private int selectedRow;
    public UserList() {
        setContentPane(panel1);
        // Default visibility is false. You have enabled visibility true
        setVisible(true);
        // Terminates the Application when the frame is closed.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("User List");
        // Provide the frame width and height
        setSize(500, 500);
        // Make your screen center
        setLocationRelativeTo(null);
        setResizable(false);// If you wish

        getList();
        Admin admin = (Admin) SystemModel.role;


        // Add a listener for row selection
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Ignore if the event is adjusting (which happens when it's still in progress)
                if (!e.getValueIsAdjusting()) {
                    // Get the selected row index
                     selectedRow = table1.getSelectedRow();

                }
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) {
                    if (admin.add_account((String) table1.getValueAt(selectedRow,1), (int)table1.getValueAt(selectedRow,0))){
                        JOptionPane.showMessageDialog(null, "CreateAccount Success", "Success", JOptionPane.PLAIN_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "CreateAccount Error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) {
                    if (admin.delete_user((int)table1.getValueAt(selectedRow,0))){
                        getList();
                    }else{
                        JOptionPane.showMessageDialog(null, "Delete Error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "User name:", "Input", JOptionPane.QUESTION_MESSAGE);

                // Check if the input is not null (user didn't cancel)
                if (input != null) {
                    // Display the input back to the user
//                    JOptionPane.showMessageDialog(null, "You entered: " + input);
                    if(admin.add_user(input)){
                        getList();
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
    public void getList()  {
        List<User> list =  SystemModel.getUserList();
        String[] columnNames = {"index","Name"};
        Object[][] data =  new Object[list.size()][2];
        for(int i=0; i< list.size(); i++){
            data[i][0] = list.get(i).getID();
            data[i][1] = list.get(i).getName();
        }

        DefaultTableModel model = new DefaultTableModel(data,columnNames);
        System.out.println(data);
        table1.setModel(model);

    }
    public static void main(String[] args) {
 /*
 While it is not mandatory to use EventQueue.invokeLater,
 it is a best practice for all Swing applications to ensure
 thread safety and avoid potential concurrency issues.
 */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                UserList mf = new UserList();
            }
        });
    }
}

package UI.Managment;

import Business.Admin;
import Business.Role;
import Business.SystemModel;
import Business.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class UserList extends JFrame{
    private JPanel panel1;
    private JButton addButton;
    private JButton createAccountButton;
    private JTable table1;
    private JButton deleteButton;

    public UserList() {
        setContentPane(panel1);
        // Default visibility is false. You have enabled visibility true
        setVisible(true);
        // Terminates the Application when the frame is closed.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Login Form");
        // Provide the frame width and height
        setSize(500, 500);
        // Make your screen center
        setLocationRelativeTo(null);
        setResizable(false);// If you wish

    }
    public static void getList()  {
        List<User> list =  SystemModel.getUserList();
        List<String[]> data= new ArrayList<>();
        for(User user:list){
            List<String> row = new ArrayList<>();
            row.add(user.getID(),user.getName() );
            data.add((String[]) row.toArray());
        }
        data.toArray();

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
                getList();
            }
        });
    }
}

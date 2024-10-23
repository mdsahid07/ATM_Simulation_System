package UI.Managment;

import Business.Admin;
import Business.SystemModel;
import Business.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AccountList extends JFrame {
    private JTable table1;
    private JPanel panel1;
    private JButton deleteButton;
    private int selectedRow;

    public AccountList() {
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

        getList();
        Admin admin = (Admin) SystemModel.role;
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) {
                    if (admin.delete_account((int)table1.getValueAt(selectedRow,0))){
                        getList();
                    }else{
                        JOptionPane.showMessageDialog(null, "Delete Error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
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
    }
    public  void getList()  {
        List<Object[]> list =  SystemModel.getAccountList();
        String[] columnNames = {"AccNumber","Balance","Name"};
        Object[][] data = new Object[list.size()][3];

        for(int i=0; i<list.size();i++){
            data[i] = list.get(i);
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
                AccountList mf = new AccountList();
            }
        });
    }
}

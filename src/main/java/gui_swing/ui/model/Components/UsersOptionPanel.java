package gui_swing.ui.model.Components;

import gui_swing.ui.model.ApiConnector;
import gui_swing.ui.model.User;
import gui_swing.ui.model.tableModels.GradientButton;
import gui_swing.ui.model.tableModels.UserOptionTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UsersOptionPanel extends JFrame {

    private JPanel mainJPanel;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel topTopPanel;
    private JPanel topBottomPanel;

    private JScrollPane scrollPane;
    private JTable table;

    private JLabel errorLabel;

    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;

    private ApiConnector apiConnector;

    private ArrayList<UserForm> userForms;
    private UsersOptionPanel usersOptionPanel;


    public UsersOptionPanel(){
        super();
        buildForm();

        insertData();

        showForm();

    }

    public void insertData() {
        ArrayList<User> userList  = apiConnector.getAllEnabledUsers();
       DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
       tableModel.setRowCount(0);
        /*for (int i = tableModel.getRowCount()-1; i >=0 ; i--) {
            tableModel.removeRow(i);
        }*/
        for (User u: userList
             ) {
            tableModel.addRow(new Object[]{u.getId().intValue(),u.getLogin(),u.getEmail(),u.getUserRole().toString()});
        }
        errorLabel.setText("Liczba użytkowników: "+userList.size()+".");
    }

    private void showForm() {

        this.setMinimumSize(new Dimension(600,650));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
        this.setTitle("Zarządzanie użytkownikami...");
    }

    private void buildForm() {
        apiConnector = new ApiConnector();
        userForms = new ArrayList<>();
        mainJPanel = new JPanel();
        getContentPane().add(mainJPanel);
        mainJPanel.setLayout(new BorderLayout());
        topPanel = new JPanel();
        centerPanel = new JPanel();
        mainJPanel.add(topPanel,BorderLayout.NORTH);
        mainJPanel.add(centerPanel,BorderLayout.CENTER);
        topPanel.setLayout(new BorderLayout());
        topTopPanel = new JPanel();
        topBottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(topTopPanel,BorderLayout.NORTH);
        topPanel.add(topBottomPanel,BorderLayout.SOUTH);
        errorLabel = new JLabel("",SwingConstants.RIGHT);
        topBottomPanel.add(errorLabel);
        table = new JTable(new UserOptionTableModel());
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoCreateRowSorter(true);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500,500));
        centerPanel.add(scrollPane);
        addButton = new GradientButton("Dodaj użytkownika" , Color.GREEN.darker());
        editButton = new GradientButton("Edytuj użytkownika" , Color.YELLOW.darker());
        deleteButton = new GradientButton("Usuń użytkownika" , Color.RED.darker());
        topTopPanel.add(addButton);
        topTopPanel.add(editButton);
        topTopPanel.add(deleteButton);
        usersOptionPanel = this;
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    userForms.add( new UserForm(-1,usersOptionPanel));

            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(table.getSelectedRow()>=0){
                    userForms.add( new UserForm((Integer) table.getValueAt(table.getSelectedRow(),0),usersOptionPanel));
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow()>=0){
                   if ( JOptionPane.showConfirmDialog(null,
                            "Czy napewno usunąć: '"+table.getValueAt(table.getSelectedRow(),1)+"'?",
                            "Uwaga",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                     Boolean result =  apiConnector.hideUser((Integer)table.getValueAt(table.getSelectedRow(),0));
                       if (result){
                           insertData();
                       }
                   }

                }
            }
        });
    }
}

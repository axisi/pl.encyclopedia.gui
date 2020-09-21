package gui_swing.ui.model.Components;

import gui_swing.ui.model.ApiConnector;
import gui_swing.ui.model.pojo.Author;
import gui_swing.ui.model.pojo.User;
import gui_swing.ui.model.tableModels.AddressBookAuthorsTableModel;
import gui_swing.ui.model.tableModels.AddressBookUsersTableModel;
import gui_swing.ui.model.tableModels.GradientButton;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AddressBookPanel extends JFrame {

    private ApiConnector apiConnector = new ApiConnector();

    private JPanel mainJPanel;
    private JPanel topJPanel;
    private JPanel bottomJPanel;
    private JPanel bottomTJPanel;
    private JPanel bottomBJPanel;
    private JPanel tLeftJPanel;
    private JPanel tRightJPanel;

    private JTable authorsTable;
    private JTable usersTable;

    private JScrollPane lScrollPane;
    private JScrollPane rScrollPane;

    private  boolean isTo;

    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    private JEditorPane textField;
    private JButton deleteButton;
    private JButton saveButton;
    private JLabel selectedLabel;

    private DefaultTableModel authorTableModel ;
    private  DefaultTableModel userTableModel ;

    private SendMailPanel sendMailPanel;




    public AddressBookPanel(SendMailPanel sendMailPanel, boolean b)  {

        isTo = b;
        this.sendMailPanel = sendMailPanel;

        prepareForm();

        initializeListeners();

        fillData();

        initializeForm();
    }

    private void initializeListeners() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });
        usersTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() ==2 && usersTable.getSelectedRow()!=-1){
                    textField.setText(textField.getText()+";" + usersTable.getValueAt(usersTable.getSelectedRow(),1));

                }
            }
        });
        authorsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() ==2 && authorsTable.getSelectedRow()!=-1){
                    textField.setText(textField.getText()+";"+authorsTable.getValueAt(authorsTable.getSelectedRow(),2));

                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isTo){
                    sendMailPanel.addAddressesTo(textField.getText());

                }else{
                    sendMailPanel.addAddressesCC(textField.getText());
                }
                dispose();
            }
        });



    }


    private void initializeForm() {
        this.setMinimumSize(new Dimension((int) (dim.width*0.8), (int) (dim.height * 0.8)));
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);

        this.setTitle(String.format("Dodaj odbiorców do pola %s.", isTo? "Do":"DW") );

    }

    private void fillData() {

        ArrayList<Author> authorArrayList = apiConnector.getAllAuthorsObject();
        ArrayList<User> userArrayList = apiConnector.getAllEnabledUsers();

        for ( Author a: authorArrayList
             ) {
            authorTableModel.addRow(new Object[]{a.getName(),a.getSurname(),a.getEmail()});
        }
        for ( User u: userArrayList
        ) {
            userTableModel.addRow(new Object[]{u.getLogin(),u.getEmail()});
        }


    }

    private void prepareForm() {
        mainJPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainJPanel);
        topJPanel = new JPanel(new BorderLayout());
        mainJPanel.add(topJPanel,BorderLayout.NORTH);
        tLeftJPanel = new JPanel();
        tRightJPanel = new JPanel();
        topJPanel.add(tLeftJPanel,BorderLayout.WEST);
        topJPanel.add(tRightJPanel,BorderLayout.EAST);
        authorsTable = new JTable(new AddressBookAuthorsTableModel());
        usersTable = new JTable(new AddressBookUsersTableModel());
        lScrollPane = new JScrollPane(authorsTable);
        rScrollPane = new JScrollPane(usersTable);
        tLeftJPanel.add(lScrollPane);
        tRightJPanel.add(rScrollPane);
        usersTable.getTableHeader().setReorderingAllowed(false);
        authorsTable.getTableHeader().setReorderingAllowed(false);
        usersTable.setAutoCreateRowSorter(true);
        authorsTable.setAutoCreateRowSorter(true);
        bottomJPanel = new JPanel(new BorderLayout());
        mainJPanel.add(bottomJPanel,BorderLayout.CENTER);
        bottomTJPanel = new JPanel();
        bottomBJPanel = new JPanel();
        bottomJPanel.add(bottomTJPanel,BorderLayout.NORTH);
        bottomJPanel.add(bottomBJPanel,BorderLayout.SOUTH);
        selectedLabel = new JLabel("Wybrane:");
        bottomTJPanel.add(selectedLabel);
        textField = new JEditorPane();
       //textField.setContentType("text/html");
        JScrollPane textScroll = new JScrollPane(textField);
        textScroll.setPreferredSize(new Dimension((int) (dim.getWidth()*0.4),150));
        textField.setPreferredSize(new Dimension((int) (dim.getWidth()*0.4),150));
        bottomTJPanel.add(textScroll);
        //textField.setLineWrap(true);
        deleteButton = new GradientButton("Wyczyść",Color.red);
        saveButton = new GradientButton("OK",Color.GREEN);
        textField.setEnabled(false);
        bottomBJPanel.add(saveButton);
        bottomBJPanel.add(deleteButton);

        userTableModel = (DefaultTableModel) usersTable.getModel();
        authorTableModel = (DefaultTableModel) authorsTable.getModel();

    }
}

package gui_swing.ui.model.Components;

import gui_swing.ui.model.ApiConnector;
import gui_swing.ui.model.User;
import gui_swing.ui.model.UserRole;
import gui_swing.ui.model.tableModels.GradientButton;
import org.glassfish.jersey.internal.jsr166.Flow;
import org.xlsx4j.sml.Col;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserForm extends  JFrame{
    private UsersOptionPanel parentPanel;
    private Integer id;

    private JPanel mainJPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private ApiConnector apiConnector;

    private JLabel idLabelLeft;
    private JLabel idLabelRight;
    private JLabel loginLabel;
    private JLabel emailLabel;
    private JLabel roleLabel;
    private JLabel errorLabel;

    private JTextField loginText;
    private JTextField emailText;
    private JComboBox roleComboBox;

    private JButton saveButton;





    public UserForm(int id, UsersOptionPanel usersOptionPanel) {
        super();
        this.parentPanel = usersOptionPanel;
        this.id = id;
        buildForm();
        if(id>0)
            insertData();
        showForm();

    }

    private void insertData() {
        User user = apiConnector.getUser(id);
        idLabelRight.setText(user.getId().toString());
        loginText.setText(user.getLogin());
        emailText.setText(user.getEmail());
        roleComboBox.setSelectedItem(user.getUserRole().toString());
    }

    private void showForm() {
        this.setMinimumSize(new Dimension(350,250));
        this.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
        if(id>0)
            this.setTitle("Edytuj użytkownika");
        else
            this.setTitle("Dodaj użytkownika");
    }

    private void buildForm() {
        apiConnector = new ApiConnector();
        mainJPanel = new JPanel(new BorderLayout());
        topPanel = new JPanel(new GridLayout(0,2));
        bottomPanel = new JPanel();
        getContentPane().add(mainJPanel);
        mainJPanel.add(topPanel,BorderLayout.NORTH);
        mainJPanel.add(bottomPanel,BorderLayout.SOUTH);

        idLabelLeft = new JLabel("ID: ",SwingConstants.RIGHT);
        idLabelRight = new JLabel("",SwingConstants.LEFT);
        loginLabel = new JLabel("Login: ",SwingConstants.RIGHT);
        loginText = new JTextField();
        emailLabel = new JLabel("Email: ",SwingConstants.RIGHT);
        emailText = new JTextField();
        roleLabel = new JLabel("Rola: ",SwingConstants.RIGHT);
        ArrayList<String> rolesName =apiConnector.getAllRolesName();
        rolesName.add(0,"");
        roleComboBox = new JComboBox(rolesName.toArray());
        saveButton = new GradientButton("Zapisz", Color.GREEN.darker());
        errorLabel = new JLabel("",SwingConstants.RIGHT);
        errorLabel.setForeground(Color.RED.darker());

        topPanel.add(idLabelLeft);
        topPanel.add(idLabelRight);
        topPanel.add(loginLabel);
        topPanel.add(loginText);
        topPanel.add(emailLabel);
        topPanel.add(emailText);
        topPanel.add(roleLabel);
        topPanel.add(roleComboBox);
        topPanel.add(errorLabel);
        bottomPanel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean decision = true;
                if(loginText.getText().length()==0){
                    decision = false;
                    errorLabel.setText("Wypełnij login");
                }
                if(emailText.getText().length()==0){
                    decision = false;
                    errorLabel.setText("Wypełnij email");
                }
                if(roleComboBox.getItemAt(roleComboBox.getSelectedIndex()).toString()==""){
                    decision = false;
                    errorLabel.setText("Wybierz role");
                }

                if(decision){
                    User user = new User();
                    user.setLogin(loginText.getText());
                    user.setEmail(emailText.getText());
                    user.setUserRole(new UserRole(roleComboBox.getItemAt(roleComboBox.getSelectedIndex()).toString()));
                    user.setHidden(false);
                    if(id>0){
                        user.setId(Long.valueOf(idLabelRight.getText()));
                        apiConnector.updateUser(user);
                    }else{
                        apiConnector.addUser(user);
                    }
                    parentPanel.insertData();
                    dispose();
                }
            }
        });



    }

}

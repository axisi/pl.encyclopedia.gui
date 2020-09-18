package gui_swing.ui.model.Components;

import gui_swing.ui.model.ApiConnector;

import gui_swing.ui.model.pojo.UserRole;
import gui_swing.ui.model.tableModels.GradientButton;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RolesEditPanel extends JFrame {

    private JPanel mainJPanel;
    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private ApiConnector apiConnector;
    private JTextField newRoleTextField;

    private JLabel CBLabel;
    private JComboBox rolesCB;
    private JButton addButton;
    private JButton deleteButton;
    private JButton saveChangesButton;

    private Dimension dim;
    private Dimension widthDim;

    private JPanel usersPanel;
    private JCheckBox usersVisible;
    private JCheckBox usersEditable;
    private JCheckBox rolesModification;

    private JPanel settingsPanel;
    private JCheckBox authorsManagement;
    private JCheckBox subCategoriesManagement;
    private JCheckBox tagsManagement;

    private JPanel termsPanel;
    private JCheckBox editCategory1;
    private JCheckBox editCategory2;
    private JCheckBox editCategory3;
    private JCheckBox editCategory4;
    private JCheckBox editCategory5;
    private JCheckBox editCategory6;
    private JCheckBox editCategory7;
    private JCheckBox editCategory8;
    private JCheckBox editCategory9;
    private JCheckBox editCategory10;
    private JCheckBox editCategory11;

    private JLabel errolLabel;




    public RolesEditPanel(){
        super();
        apiConnector = new ApiConnector();
        buildForm();
        addListeners();
        insertData();
        showForm();
    }

    private void addListeners() {

        rolesCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearErrorLabel();

                JComboBox comboBox = (JComboBox) e.getSource();
                if(comboBox.getModel().getSize()>0) {
                    if (!("".equals(comboBox.getSelectedItem().toString()))) {
                        UserRole userRole = apiConnector.getUserRole(comboBox.getSelectedItem().toString());
                        fillForm(userRole);
                    } else {
                        fillForm(null);
                    }
                }
            }
        });

        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearErrorLabel();
                if(!("".equals(rolesCB.getSelectedItem().toString()))){
                    UserRole userRole = new UserRole();
                    userRole.setName(rolesCB.getSelectedItem().toString());
                    userRole.setUsersVisible(usersVisible.isSelected());
                    userRole.setUsersEditable(usersEditable.isSelected());
                    userRole.setRolesModification(rolesModification.isSelected());
                    userRole.setAuthorsManagement(authorsManagement.isSelected());
                    userRole.setSubCategoriesManagement(subCategoriesManagement.isSelected());
                    userRole.setTagsManagement(tagsManagement.isSelected());
                    userRole.setEditCategory1(editCategory1.isSelected());
                    userRole.setEditCategory2(editCategory2.isSelected());
                    userRole.setEditCategory3(editCategory3.isSelected());
                    userRole.setEditCategory4(editCategory4.isSelected());
                    userRole.setEditCategory5(editCategory5.isSelected());
                    userRole.setEditCategory6(editCategory6.isSelected());
                    userRole.setEditCategory7(editCategory7.isSelected());
                    userRole.setEditCategory8(editCategory8.isSelected());
                    userRole.setEditCategory9(editCategory9.isSelected());
                    userRole.setEditCategory10(editCategory10.isSelected());
                    userRole.setEditCategory11(editCategory11.isSelected());
                    if(apiConnector.updateUserRole(userRole)){
                        errolLabel.setText("Rola: '"+rolesCB.getSelectedItem().toString()+"' - została zaktualizowana.");
                    }
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearErrorLabel();
                if(!("".equals(newRoleTextField.getText()))){
                    if(!apiConnector.isUserRoleExist(newRoleTextField.getText())){
                        apiConnector.addUserRole(newRoleTextField.getText());
                        errolLabel.setText("Dodana została rola: '"+newRoleTextField.getText() +"'.");
                        insertData();
                        rolesCB.setSelectedItem(newRoleTextField.getText());
                        newRoleTextField.setText("");

                    }else{
                        errolLabel.setText("Rola o takiej nazwie już istnieje.");
                    }
                }else{
                    errolLabel.setText("Wpisz nazwę roli.");
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearErrorLabel();
                String name = rolesCB.getSelectedItem().toString();
                if(!("".equals(name))){

                    if(!apiConnector.isUserRoleHasUsers(name)){
                        apiConnector.deleteUserRole(name);
                        insertData();
                        errolLabel.setText("Rola '"+name+"' została usunięta.");
                    }else{
                        errolLabel.setText("Do roli przypisani są użytkownicy - nie można usunąć.");
                    }
                }else{
                    errolLabel.setText("Zaznacz role którą chesz usunąć");
                }
            }
        });
    }

    private void fillForm(UserRole userRole) {
        if(userRole != null) {
            usersVisible.setSelected(userRole.getUsersVisible());
            usersEditable.setSelected(userRole.getUsersEditable());
            rolesModification.setSelected(userRole.getRolesModification());
            authorsManagement.setSelected(userRole.getAuthorsManagement());
            subCategoriesManagement.setSelected(userRole.getSubCategoriesManagement());
            tagsManagement.setSelected(userRole.getTagsManagement());
            editCategory1.setSelected(userRole.getEditCategory1());
            editCategory2.setSelected(userRole.getEditCategory2());
            editCategory3.setSelected(userRole.getEditCategory3());
            editCategory4.setSelected(userRole.getEditCategory4());
            editCategory5.setSelected(userRole.getEditCategory5());
            editCategory6.setSelected(userRole.getEditCategory6());
            editCategory7.setSelected(userRole.getEditCategory7());
            editCategory8.setSelected(userRole.getEditCategory8());
            editCategory9.setSelected(userRole.getEditCategory9());
            editCategory10.setSelected(userRole.getEditCategory10());
            editCategory11.setSelected(userRole.getEditCategory11());
            for (Component c: usersPanel.getComponents()
            ) {
                c.setEnabled(true);
            }
            for (Component c: settingsPanel.getComponents()
            ) {
                c.setEnabled(true);
            }
            for (Component c: termsPanel.getComponents()
            ) {
                c.setEnabled(true);
            }
        }else{
            usersVisible.setSelected(false);
            usersEditable.setSelected(false);
            rolesModification.setSelected(false);
            authorsManagement.setSelected(false);
            subCategoriesManagement.setSelected(false);
            tagsManagement.setSelected(false);
            editCategory1.setSelected(false);
            editCategory2.setSelected(false);
            editCategory3.setSelected(false);
            editCategory4.setSelected(false);
            editCategory5.setSelected(false);
            editCategory6.setSelected(false);
            editCategory7.setSelected(false);
            editCategory8.setSelected(false);
            editCategory9.setSelected(false);
            editCategory10.setSelected(false);
            editCategory11.setSelected(false);
            for (Component c: usersPanel.getComponents()
                 ) {
                c.setEnabled(false);
            }
            for (Component c: settingsPanel.getComponents()
            ) {
                c.setEnabled(false);
            }
            for (Component c: termsPanel.getComponents()
            ) {
                c.setEnabled(false);
            }
        }
    }

    private void insertData() {
        ArrayList<UserRole> roleArrayList = apiConnector.getUserRoles();
        rolesCB.removeAllItems();
        rolesCB.addItem("");
        for (UserRole u:roleArrayList
             ) {
            rolesCB.addItem(u.getName());
        }
    }



    private void showForm() {
        this.setMinimumSize(new Dimension((int) (dim.width*0.4-20), (int) (dim.height * 0.4)));
        this.setMaximumSize(new Dimension((int) (dim.width*0.7-20), (int) (dim.height * 0.7)));
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.pack();
        this.setVisible(true);
        this.setTitle("Zarządzanie rolami");
    }

    private void buildForm() {

        dim = Toolkit.getDefaultToolkit().getScreenSize();
        widthDim = new Dimension((int) (dim.width*0.6), (int) (dim.height * 0.6));
        mainJPanel = new JPanel(new BorderLayout());
        topPanel= new JPanel();
        getContentPane().add(mainJPanel);
        mainJPanel.add(topPanel,BorderLayout.NORTH);
        leftPanel = new JPanel(new GridLayout(2,0));
        rightPanel= new JPanel(new GridLayout(1,0));
        leftPanel.setPreferredSize(new Dimension((int) (dim.width*0.2), (int) (dim.height * 0.3)));
        rightPanel.setPreferredSize(new Dimension((int) (dim.width*0.2), (int) (dim.height * 0.3)));

        mainJPanel.add(leftPanel,BorderLayout.WEST);
        mainJPanel.add(rightPanel,BorderLayout.EAST);
        rolesCB = new JComboBox();
        CBLabel = new JLabel("Rola");
        topPanel.add(CBLabel);
        topPanel.add(rolesCB);
        newRoleTextField = new JTextField();
        newRoleTextField.setPreferredSize(new Dimension(150,30));
        addButton = new GradientButton("Dodaj",Color.GREEN.darker());
        deleteButton = new GradientButton("Usuń",Color.RED.darker());
        topPanel.add(newRoleTextField);
        topPanel.add(addButton);
        topPanel.add(deleteButton);
        bottomPanel = new JPanel(new GridLayout(2,0));
        mainJPanel.add(bottomPanel, BorderLayout.SOUTH);
        saveChangesButton = new GradientButton("Zapisz zmiany",Color.GREEN.darker());
        bottomPanel.add(saveChangesButton);
        usersPanel = new JPanel(new GridLayout(3,0));
        leftPanel.add(usersPanel);
        usersVisible = new JCheckBox("Widoczność użytkowników");
        usersEditable = new JCheckBox("Edytowanie użytkowników");
        rolesModification = new JCheckBox( "Zarządzanie rolami");
        usersPanel.add(usersVisible);
        usersPanel.add(usersEditable);
        usersPanel.add(rolesModification);
        usersPanel.setBorder(BorderFactory.createTitledBorder("Zarządzanie użytkownikami"));
        settingsPanel = new JPanel(new GridLayout(3,0));
        leftPanel.add(settingsPanel);
        authorsManagement = new JCheckBox("Zarządzanie autorami");
        subCategoriesManagement = new JCheckBox("Zarządzanie podkategoriami");
        tagsManagement = new JCheckBox("Zarządzanie tagami");
        settingsPanel.add(authorsManagement);
        settingsPanel.add(tagsManagement);
        settingsPanel.add(subCategoriesManagement);
        settingsPanel.setBorder(BorderFactory.createTitledBorder("Zarządzanie aplikacją"));
        termsPanel = new JPanel(new GridLayout(11,0));
        rightPanel.add(termsPanel);
        editCategory1 = new JCheckBox("Życie polityczne");
        editCategory2=new JCheckBox("Życie gospodarcze");
        editCategory3=new JCheckBox("Mniejszości o stowarzyszenia");
        editCategory4=new JCheckBox("Sztuka");
        editCategory5=new JCheckBox("Literatura, teatr, muzyka");
        editCategory6=new JCheckBox("Nauka i biografistyka");
        editCategory7=new JCheckBox("Historia i kalendarium");
        editCategory8=new JCheckBox("Cracovia sacra");
        editCategory9=new JCheckBox("Geografia i środowisko przyrodnicze");
        editCategory10=new JCheckBox("Architektura i urbanizacja");
        editCategory11=new JCheckBox("Odsyłaczowe");
        termsPanel.add(editCategory1);
        termsPanel.add(editCategory2);
        termsPanel.add(editCategory3);
        termsPanel.add(editCategory4);
        termsPanel.add(editCategory5);
        termsPanel.add(editCategory6);
        termsPanel.add(editCategory7);
        termsPanel.add(editCategory8);
        termsPanel.add(editCategory9);
        termsPanel.add(editCategory10);
        termsPanel.add(editCategory11);
        termsPanel.setBorder(BorderFactory.createTitledBorder("Zarządzanie hasłąmi"));
        errolLabel = new JLabel("");
        bottomPanel.add(errolLabel);



    }
    private void clearErrorLabel(){
        errolLabel.setText("");
    }
}

package gui_swing.ui.controller;

import gui_swing.ui.model.ApiConnector;
import gui_swing.ui.model.User;
import gui_swing.ui.model.tableModels.GradientButton;
import gui_swing.ui.view.LoginFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class PasswordChangeController extends JFrame {


    private User user;
    private JPanel mainJPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JLabel loginLabelLeft;
    private JLabel loginLabelRight;
    private JLabel passwordLabel1;
    private JLabel passwordLabel2;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JLabel errolLabel;
    private JButton saveButton;
    private JButton exitButton;
    private ApiConnector apiConnector;

    public PasswordChangeController(User user){
        this.user= user ;
        initComponents();
        fillData();
        showWindow();
    }

    private void fillData() {
        loginLabelRight.setText(user.getLogin());
    }

    private void showWindow() {
        this.setMinimumSize(new Dimension(350, 250));
        this.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setVisible(true);
        this.setTitle("Zmień hasło");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        apiConnector = new ApiConnector();
        mainJPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainJPanel);
        topPanel= new JPanel(new GridLayout(0, 2));
        mainJPanel.add(topPanel,BorderLayout.NORTH);
        bottomPanel=new JPanel(new BorderLayout());
        mainJPanel.add(bottomPanel,BorderLayout.SOUTH);

        loginLabelLeft=new JLabel("Login: ",SwingConstants.RIGHT);
        loginLabelRight=new JLabel("");
        passwordLabel1=new JLabel("Nowe hasło: ",SwingConstants.RIGHT);
        passwordLabel2=new JLabel("Powtórz hasło: ",SwingConstants.RIGHT);
        passwordLabel2.setBorder(new EmptyBorder(2,0,2,0));
        passwordField1 = new JPasswordField();
        passwordField1.setBorder(new EmptyBorder(2,0,2,0));
        passwordField2 = new JPasswordField();
        passwordField2.setBorder(new EmptyBorder(2,0,2,0));
        errolLabel= new JLabel("Wymagania hasła*"   );
        errolLabel.setForeground(Color.ORANGE.darker());
        errolLabel.setToolTipText("<html>" +
                "Hasło powinno zawierać: <br>" +
                "<ul>" +
                "<li> - co najmniej 8 znaków</li>" +
                "<li> - co najmniej 1 dużą literę </li>" +
                "<li> - co najmniej 1 małą literę </li>" +
                "<li> - co najmniej 1 cyfrę </li> " +
                "<li> - co najmniej 1 znak specjalny: \"!@#$%^&+=*\" </li>" +
                "<li> - hasło nie może zawierać białych znaków </li>" +
                "</ul>" +
                "</html>");
        saveButton= new GradientButton("Zapisz",Color.green.darker());
        exitButton= new GradientButton("Anuluj",Color.pink.darker());

        topPanel.add(loginLabelLeft);
        topPanel.add(loginLabelRight);
        topPanel.add(passwordLabel1);
        topPanel.add(passwordField1);
        topPanel.add(passwordLabel2);
        topPanel.add(passwordField2);
        bottomPanel.add(errolLabel,BorderLayout.NORTH);
        bottomPanel.add(saveButton,BorderLayout.CENTER);
        bottomPanel.add(exitButton,BorderLayout.SOUTH);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=*])(?=\\S+$).{8,}$", String.valueOf(passwordField1.getPassword()))
                        && String.valueOf(passwordField1.getPassword()).equals(String.valueOf(passwordField2.getPassword()))){
                    apiConnector.setNewPassword(user.getId(),String.valueOf(passwordField1.getPassword()));
                    ApplicationFrameController.getApplicationFrame().setVisible(true);
                    dispose();
                }else
                {
                    errolLabel.setText("Błędne hasło*");
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainController.loginFrameController =  new LoginFrameController(MainController.mainController);
                MainController.loginFrameController.showMainFrameWindow();
                dispose();
            }
        });
    }


}

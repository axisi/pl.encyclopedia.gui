package gui_swing.ui.view;

import javax.swing.*;

public class LoginFrame extends  JFrame {

    public static final int HEIGHT = 500;
    public static final int WIDTH = 650;


    public LoginFrame() {

        setSize(WIDTH, HEIGHT);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);


    }
    private JPanel mainPanel;
    private JPanel loginTopPanel;
    private JPanel loginStatusPanel;
    private JPanel loginRightPanel;
    private JTextField loginTextField;
    private JPasswordField loginPasswordField;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JLabel imageLabel;
    private JButton loginButton;
    private JButton exitButton;
    private JLabel statusLabel;

    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }

    public void setButtonsPanel(JPanel buttonsPanel) {
        this.buttonsPanel = buttonsPanel;
    }

    private JPanel buttonsPanel;


    public JLabel getStatusLabel() {
        return statusLabel;
    }



    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getLoginTopPanel() {
        return loginTopPanel;
    }

    public JPanel getLoginStatusPanel() {
        return loginStatusPanel;
    }

    public JPanel getLoginRightPanel() {
        return loginRightPanel;
    }

    public JTextField getLoginTextField() {
        return loginTextField;
    }

    public JPasswordField getLoginPasswordField() {
        return loginPasswordField;
    }

    public JLabel getLoginLabel() {
        return loginLabel;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public JLabel getImageLabel() {
        return imageLabel;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }



}

package gui_swing.ui.controller;

import gui_swing.ui.model.ConfigManager;
import gui_swing.ui.view.LoginFrame;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

public class LoginFrameController {
    private LoginFrame loginFrame;
    private JPanel mainPanel;
    private JPanel loginTopPanel;
    private JPanel loginLeftPanel;
    private JPanel loginRightPanel;
    private JTextField loginTextField;
    private JPasswordField loginPasswordField;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JLabel imageLabel;
    private JButton loginButton;
    private JButton exitButton;
    private JLabel statusLabel;
    private MainController mainController;
    private String login;
    private String password;






    public LoginFrameController(MainController mainController) {
        initComponents(mainController);
        initListeners();
    }

    private void initListeners() {
        loginButton.addActionListener(new LoginListener());
        exitButton.addActionListener(new CloseApplication());
    }


    private void initComponents(MainController mainController) {
        loginFrame = new LoginFrame();
        loginButton = loginFrame.getLoginButton();
        exitButton = loginFrame.getExitButton();
        statusLabel = loginFrame.getStatusLabel();

        this.mainController =mainController;
    }

    public void showMainFrameWindow() {
        loginFrame.setVisible(true);
    }

    private class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            login = loginFrame.getLoginTextField().getText();
            password = new String(loginFrame.getLoginPasswordField().getPassword());
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost postRequest = new HttpPost("http://localhost:8080/login");
                StringEntity input = new StringEntity("{\"login\":\"" + login + "\",\"password\":\"" + password + "\"}");
                input.setContentType("application/json");
                postRequest.setEntity(input);

                HttpResponse response = httpClient.execute(postRequest);
/*
                if (response.getStatusLine().getStatusCode() != 201) {

                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatusLine().getStatusCode());

                }*/

                BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));
                String output;
                //System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        if (output.contains("Wrong password")) {
                            loginFrame.getStatusLabel().setForeground(Color.RED);
                            loginFrame.getStatusLabel().setText("Wrong password");
                        } else {
                            if (output.contains("Wrong login")) {

                                loginFrame.getStatusLabel().setForeground(Color.RED);
                                loginFrame.getStatusLabel().setText("Wrong login");
                            } else {
                                loginFrame.getStatusLabel().setForeground(Color.GREEN);
                                loginFrame.getStatusLabel().setText("Logged-in");
                                ConfigManager.setJwtToken(output);
                                ConfigManager.setLoggedUser(login);
                                mainController.getApplicationFrameController().setValuesInLoggedUserLabel();
                                //mainController.getJwtToken().setJwtToken(output);
                                //JOptionPane.showMessageDialog(null,"Udało się zalogować do systemu." );
                                //loginFrame.setVisible(true);

                                mainController.getApplicationFrameController().showMainFrameWindow();
                                loginFrame.dispose();

                            }
                        }
                        //System.out.println(output);
                    }
                }

                httpClient.getConnectionManager().shutdown();

            } catch (MalformedURLException ex) {
                loginFrame.getStatusLabel().setText(ex.getMessage());
                ex.printStackTrace();

            } catch (IOException ex1) {
                loginFrame.getStatusLabel().setText(ex1.getMessage());
                ex1.printStackTrace();
            }
        }
    }
    private  class CloseApplication implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            loginFrame.dispose();
        }
    }
}

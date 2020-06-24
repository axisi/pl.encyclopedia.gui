package gui_swing.ui.model.Components;

import gui_swing.ui.model.Actions.ShowWaitAction;
import gui_swing.ui.model.ApiConnector;
import gui_swing.ui.model.User;
import gui_swing.ui.model.UserRole;
import gui_swing.ui.model.tableModels.GradientButton;
import org.glassfish.jersey.internal.jsr166.Flow;
//import org.xlsx4j.sml.Col;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class UserForm extends  JFrame {
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
    private JLabel passwordLabel;

    private JTextField loginText;
    private JTextField emailText;
    private JPasswordField passwordField;
    private JComboBox roleComboBox;

    private JButton saveButton;
    private JButton resetPasswordButton;


    public UserForm(int id, UsersOptionPanel usersOptionPanel) {
        super();
        this.parentPanel = usersOptionPanel;
        this.id = id;
        buildForm();
        if (id > 0)
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
        this.setMinimumSize(new Dimension(350, 250));
        this.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.pack();
        this.setVisible(true);
        if (id > 0) {
            this.setTitle("Edytuj użytkownika");
            resetPasswordButton.setVisible(true);
            passwordLabel.setVisible(false);
            passwordField.setVisible(false);
        }
        else{
            this.setTitle("Dodaj użytkownika");
            resetPasswordButton.setVisible(false);
            passwordLabel.setVisible(true);
            passwordField.setVisible(true);
        }

    }

    private void buildForm() {
        apiConnector = new ApiConnector();
        mainJPanel = new JPanel(new BorderLayout());
        topPanel = new JPanel(new GridLayout(0, 2));
        bottomPanel = new JPanel();
        getContentPane().add(mainJPanel);
        mainJPanel.add(topPanel, BorderLayout.NORTH);
        mainJPanel.add(bottomPanel, BorderLayout.SOUTH);

        idLabelLeft = new JLabel("ID: ", SwingConstants.RIGHT);
        idLabelRight = new JLabel("", SwingConstants.LEFT);
        loginLabel = new JLabel("Login: ", SwingConstants.RIGHT);
        loginText = new JTextField();
        emailLabel = new JLabel("Email: ", SwingConstants.RIGHT);
        emailText = new JTextField();
        roleLabel = new JLabel("Rola: ", SwingConstants.RIGHT);
        ArrayList<String> rolesName = apiConnector.getAllRolesName();
        rolesName.add(0, "");
        roleComboBox = new JComboBox(rolesName.toArray());
        saveButton = new GradientButton("Zapisz", Color.GREEN.darker());
        resetPasswordButton = new GradientButton("Resetuj hasło", Color.ORANGE.darker());
        //saveButton = new JButton(new ShowWaitAction("Zapisz"));
        errorLabel = new JLabel("", SwingConstants.RIGHT);
        errorLabel.setForeground(Color.RED.darker());
        passwordLabel = new JLabel("Hasło (tymczasowe): ", SwingConstants.RIGHT);
        passwordField = new JPasswordField();

        topPanel.add(idLabelLeft);
        topPanel.add(idLabelRight);
        topPanel.add(loginLabel);
        topPanel.add(loginText);
        topPanel.add(emailLabel);
        topPanel.add(emailText);
        topPanel.add(roleLabel);
        topPanel.add(roleComboBox);

        topPanel.add(passwordLabel);
        topPanel.add(passwordField);

        topPanel.add(errorLabel);

        bottomPanel.add(saveButton);
        bottomPanel.add(resetPasswordButton);


        /*saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });*/
        saveButton.addActionListener(new ShowWaitAction("Zapisz"));


        resetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordLabel.setVisible(!passwordLabel.isVisible());
                passwordField.setVisible(!passwordField.isVisible());
            }
        });
    }


    public class ShowWaitAction extends AbstractAction {
        protected static final long SLEEP_TIME = 10 * 1000;

        public ShowWaitAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {

                    Boolean success = true;
                    String errorComment ="";

                    // mimic some long-running process here...
                    Boolean decision = true;
                    if (loginText.getText().length() == 0) {
                        decision = false;
                        errorLabel.setText("Wypełnij login");
                    }
                    if (emailText.getText().length() == 0) {
                        decision = false;
                        errorLabel.setText("Wypełnij email");
                    }
                    if (roleComboBox.getItemAt(roleComboBox.getSelectedIndex()).toString() == "") {
                        decision = false;
                        errorLabel.setText("Wybierz role");
                    }
                    if(passwordLabel.isVisible()){
                        if(Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=*])(?=\\S+$).{8,}$", String.valueOf(passwordField.getPassword()))){
                            errorLabel.setToolTipText("");
                        }else{
                            decision= false;
                            errorLabel.setText("Błędne hasło*");
                            errorLabel.setToolTipText("<html>" +
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
                        }
                        if(decision)
                            errorLabel.setText("Dane poprawne!");
                    }


                    if (decision) {

                        User user = new User();
                        user.setLogin(loginText.getText());
                        user.setEmail(emailText.getText());
                        user.setUserRole(new UserRole(roleComboBox.getItemAt(roleComboBox.getSelectedIndex()).toString()));
                        user.setHidden(false);
                        if (id > 0) {
                            user.setChangeRequired(false);
                            user.setId(Long.valueOf(idLabelRight.getText()));
                            if(passwordLabel.isVisible()){
                                user.setPassword(String.valueOf(passwordField.getPassword()));
                                user.setChangeRequired(true);
                            }
                             if(apiConnector.updateUser(user)){

                                 parentPanel.insertData();
                                 dispose();
                             }else{
                                 success = false;
                             }
                            errorComment = "Hasło aktualnie używane";

                        } else {
                            if(!apiConnector.isUserExist(loginText.getText())){
                                user.setChangeRequired(true);
                                user.setPassword(String.valueOf(passwordField.getPassword()));
                                apiConnector.addUser(user);

                            }else{
                                success = false;
                                errorComment = "User o takim loginie istnieje w bazie";
                            }

                           // Thread.sleep(SLEEP_TIME);

                        }
                        if(success) {
                            parentPanel.insertData();
                            dispose();
                        }else
                        {
                            errorLabel.setText(errorComment);
                        }
                    }
                    // mimic some long-running process here...


                    return null;
                }
            };

            Window win = SwingUtilities.getWindowAncestor((AbstractButton) evt.getSource());
            final JDialog dialog = new JDialog(win, "Zakładanie konta", Dialog.ModalityType.APPLICATION_MODAL);

            mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {

                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if (evt.getPropertyName().equals("state")) {
                        if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                            dialog.dispose();
                        }
                    }
                }
            });
            mySwingWorker.execute();

            JProgressBar progressBar = new JProgressBar();
            progressBar.setIndeterminate(true);
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(progressBar, BorderLayout.CENTER);
            panel.add(new JLabel("Proszę czekać......."), BorderLayout.PAGE_START);
            dialog.add(panel);
            dialog.pack();
            dialog.setLocationRelativeTo(win);
            dialog.setVisible(true);
        }


    }
}

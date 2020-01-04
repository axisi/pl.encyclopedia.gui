package gui_swing.ui.controller;

import gui_swing.ui.model.ConfigManager;
import gui_swing.ui.view.ApplicationFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;

public class ApplicationFrameController {
    //    private final static String termsPanel = "bottomPropertiesTermsPanel";
//    private final static String authorsPanel = "bottomPropertiesAuthorsPanel";
    private ApplicationFrame applicationFrame;
    private static Point point = new Point();
    private MainController mainController;
    private JPanel topPanel;
    private JList list1;
    private JPanel bottomPropertiesPanel;
    private JPanel bottomPropertiesTermsPanel;
    private JPanel bottomPropertiesAuthorsPanel;
    private JPanel bottomPropertiesBlankPanel;
    private JPanel bottomPropertiesAgreementsPanel;
    private JPanel bottomPropertiesIllustrationsPanel;
    private JPanel bottomPropertiesSettingsPanel;
    private JLabel iconLabel;
    private JLabel loggedUserLabel;
    private JLabel logoutLabel;
    private JLabel exitLabel;
    private boolean iconLabelFlag= true;


    public ApplicationFrameController(MainController mainController) {

        initComponents(mainController);
        initListeners();

    }

    private void initComponents(MainController mainController) {
        applicationFrame = new ApplicationFrame();
        this.mainController = mainController;
        topPanel = applicationFrame.getTopPanel();
        bottomPropertiesPanel = applicationFrame.getBottomPropertiesPanel();
        list1 = applicationFrame.getList1();
        bottomPropertiesTermsPanel = applicationFrame.getBottomPropertiesTermsPanel();
        bottomPropertiesAuthorsPanel = applicationFrame.getBottomPropertiesAuthorsPanel();
        bottomPropertiesAgreementsPanel = applicationFrame.getBottomPropertiesAgreementsPanel();
        bottomPropertiesBlankPanel = applicationFrame.getBottomPropertiesBlankPanel();
        bottomPropertiesIllustrationsPanel = applicationFrame.getBottomPropertiesIllustrationsPanel();
        bottomPropertiesSettingsPanel = applicationFrame.getBottomPropertiesSettingsPanel();
        backToMainPanel();
        loggedUserLabel= applicationFrame.getLoggedUserLabel();
        iconLabel = applicationFrame.getIconLabel();
        exitLabel= applicationFrame.getExitLabel();
        logoutLabel = applicationFrame.getLogoutLabel();

    }

    public void showMainFrameWindow() {
        applicationFrame.setVisible(true);
    }
    public void setValuesInLoggedUserLabel(){
        loggedUserLabel.setText("<html>Zalogowany użytkownik: <br/>" + ConfigManager.getLoggedUser()+"</html>");
    }

    private void initListeners() {
        topPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                point.x = e.getX();
                point.y = e.getY();
            }
        });
        topPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = applicationFrame.getLocation();
                applicationFrame.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                CardLayout cardLayout = (CardLayout) (bottomPropertiesPanel.getLayout());
                if(iconLabelFlag){
                    switch (list1.getSelectedValue().toString()) {
                        case "Hasła":
                            cardLayout.show(bottomPropertiesPanel, bottomPropertiesTermsPanel.getName());
                            break;
                        case "Autorzy":
                            cardLayout.show(bottomPropertiesPanel, bottomPropertiesAuthorsPanel.getName());
                            break;
                        case "Umowy":
                            cardLayout.show(bottomPropertiesPanel, bottomPropertiesAgreementsPanel.getName());
                            break;
                        case "Ilustracje":
                            cardLayout.show(bottomPropertiesPanel, bottomPropertiesIllustrationsPanel.getName());
                            break;
                        case "Ustawienia":
                            cardLayout.show(bottomPropertiesPanel, bottomPropertiesSettingsPanel.getName());
                            break;
                    }

                }


            }
        });
        iconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                backToMainPanel();

            }
        });
        exitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               int closeOrNot = JOptionPane.showConfirmDialog(null,"Czy zamknąć aplikację?", "Zamknięcie aplikacji",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
                if (closeOrNot ==0) {
                    applicationFrame.dispose();
                }
            }
        });
        logoutLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
            backToMainPanel();
            ConfigManager.setLoggedUser("");
            ConfigManager.setJwtToken("");
            applicationFrame.setVisible(false);
            mainController.setLoginFrameController(new LoginFrameController(mainController));
            mainController.getLoginFrameController().showMainFrameWindow();
        }
        });

    }

    private void backToMainPanel() {
        CardLayout cardLayout = (CardLayout) (bottomPropertiesPanel.getLayout());
        cardLayout.show(bottomPropertiesPanel, bottomPropertiesBlankPanel.getName());
        iconLabelFlag=false;
        list1.clearSelection();
        iconLabelFlag=true;
    }
}






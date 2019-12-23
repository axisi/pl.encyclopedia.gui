package gui_swing.ui.view;

import javax.swing.*;

public class ApplicationFrame extends JFrame{

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 800;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel containerContentPanel;
    private JPanel topContentPanel;
    private JPanel bottomContainerContentPanel;
    private JPanel leftContainerPropertiesPanel;
    private JPanel topPropertiesPanel;
    private JPanel bottomPropertiesPanel;
    private JPanel rightContainerDetailsPanel;
    private JPanel topDetailsPanel;
    private JPanel bottomDetailsPanel;
    private JList list1;
    private JPanel bottomPropertiesTermsPanel;
    private JPanel bottomPropertiesAuthorsPanel;
    private JPanel bottomPropertiesBlankPanel;
    private JPanel bottomPropertiesAgreementsPanel;
    private JPanel bottomPropertiesIllustrationsPanel;
    private JPanel bottomPropertiesSettingsPanel;
    private JLabel iconLabel;
    private JLabel loggedUserLabel;
    private JPanel leftBottomPanel;
    private JPanel leftMiddlePanel;
    private JPanel leftTopPanel;
    private JLabel logoutLabel;
    private JLabel exitLabel;
    private JButton button1;
    private JRadioButton radioButton1;
    private JPanel card1;
    private JPanel card2;

    public JPanel getLeftBottomPanel() {
        return leftBottomPanel;
    }

    public void setLeftBottomPanel(JPanel leftBottomPanel) {
        this.leftBottomPanel = leftBottomPanel;
    }

    public JPanel getLeftMiddlePanel() {
        return leftMiddlePanel;
    }

    public void setLeftMiddlePanel(JPanel leftMiddlePanel) {
        this.leftMiddlePanel = leftMiddlePanel;
    }

    public JPanel getLeftTopPanel() {
        return leftTopPanel;
    }

    public void setLeftTopPanel(JPanel leftTopPanel) {
        this.leftTopPanel = leftTopPanel;
    }

    public JLabel getLogoutLabel() {
        return logoutLabel;
    }

    public void setLogoutLabel(JLabel logoutLabel) {
        this.logoutLabel = logoutLabel;
    }

    public JLabel getExitLabel() {
        return exitLabel;
    }

    public void setExitLabel(JLabel exitLabel) {
        this.exitLabel = exitLabel;
    }

    public JLabel getLoggedUserLabel() {
        return loggedUserLabel;
    }

    public void setLoggedUserLabel(JLabel loggedUserLabel) {
        this.loggedUserLabel = loggedUserLabel;
    }

    public JLabel getIconLabel() {
        return iconLabel;
    }

    public void setIconLabel(JLabel iconLabel) {
        this.iconLabel = iconLabel;
    }

    public JPanel getBottomPropertiesBlankPanel() {
        return bottomPropertiesBlankPanel;
    }

    public void setBottomPropertiesBlankPanel(JPanel bottomPropertiesBlankPanel) {
        this.bottomPropertiesBlankPanel = bottomPropertiesBlankPanel;
    }

    public JPanel getBottomPropertiesAgreementsPanel() {
        return bottomPropertiesAgreementsPanel;
    }

    public void setBottomPropertiesAgreementsPanel(JPanel bottomPropertiesAgreementsPanel) {
        this.bottomPropertiesAgreementsPanel = bottomPropertiesAgreementsPanel;
    }

    public JPanel getBottomPropertiesIllustrationsPanel() {
        return bottomPropertiesIllustrationsPanel;
    }

    public void setBottomPropertiesIllustrationsPanel(JPanel bottomPropertiesIllustrationsPanel) {
        this.bottomPropertiesIllustrationsPanel = bottomPropertiesIllustrationsPanel;
    }

    public JPanel getBottomPropertiesSettingsPanel() {
        return bottomPropertiesSettingsPanel;
    }

    public void setBottomPropertiesSettingsPanel(JPanel bottomPropertiesSettingsPanel) {
        this.bottomPropertiesSettingsPanel = bottomPropertiesSettingsPanel;
    }


    public JPanel getCard1() {
        return card1;
    }

    public void setCard1(JPanel card1) {
        this.card1 = card1;
    }

    public JPanel getCard2() {
        return card2;
    }

    public void setCard2(JPanel card2) {
        this.card2 = card2;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public JPanel getLeftPanel() {
        return leftPanel;
    }

    public void setLeftPanel(JPanel leftPanel) {
        this.leftPanel = leftPanel;
    }

    public JPanel getContainerContentPanel() {
        return containerContentPanel;
    }

    public void setContainerContentPanel(JPanel containerContentPanel) {
        this.containerContentPanel = containerContentPanel;
    }

    public JPanel getTopContentPanel() {
        return topContentPanel;
    }

    public void setTopContentPanel(JPanel topContentPanel) {
        this.topContentPanel = topContentPanel;
    }

    public JPanel getBottomContainerContentPanel() {
        return bottomContainerContentPanel;
    }

    public void setBottomContainerContentPanel(JPanel bottomContainerContentPanel) {
        this.bottomContainerContentPanel = bottomContainerContentPanel;
    }

    public JPanel getLeftContainerPropertiesPanel() {
        return leftContainerPropertiesPanel;
    }

    public void setLeftContainerPropertiesPanel(JPanel leftContainerPropertiesPanel) {
        this.leftContainerPropertiesPanel = leftContainerPropertiesPanel;
    }

    public JPanel getTopPropertiesPanel() {
        return topPropertiesPanel;
    }

    public void setTopPropertiesPanel(JPanel topPropertiesPanel) {
        this.topPropertiesPanel = topPropertiesPanel;
    }

    public JPanel getRightContainerDetailsPanel() {
        return rightContainerDetailsPanel;
    }

    public void setRightContainerDetailsPanel(JPanel rightContainerDetailsPanel) {
        this.rightContainerDetailsPanel = rightContainerDetailsPanel;
    }

    public JPanel getTopDetailsPanel() {
        return topDetailsPanel;
    }

    public void setTopDetailsPanel(JPanel topDetailsPanel) {
        this.topDetailsPanel = topDetailsPanel;
    }

    public JPanel getBottomDetailsPanel() {
        return bottomDetailsPanel;
    }

    public void setBottomDetailsPanel(JPanel bottomDetailsPanel) {
        this.bottomDetailsPanel = bottomDetailsPanel;
    }

    public JList getList1() {
        return list1;
    }

    public void setList1(JList list1) {
        this.list1 = list1;
    }

    public JPanel getBottomPropertiesTermsPanel() {
        return bottomPropertiesTermsPanel;
    }

    public void setBottomPropertiesTermsPanel(JPanel bottomPropertiesTermsPanel) {
        this.bottomPropertiesTermsPanel = bottomPropertiesTermsPanel;
    }

    public JPanel getBottomPropertiesAuthorsPanel() {
        return bottomPropertiesAuthorsPanel;
    }

    public void setBottomPropertiesAuthorsPanel(JPanel bottomPropertiesAuthorsPanel) {
        this.bottomPropertiesAuthorsPanel = bottomPropertiesAuthorsPanel;
    }

    public JButton getButton1() {
        return button1;
    }

    public void setButton1(JButton button1) {
        this.button1 = button1;
    }

    public JRadioButton getRadioButton1() {
        return radioButton1;
    }

    public void setRadioButton1(JRadioButton radioButton1) {
        this.radioButton1 = radioButton1;
    }


    public JPanel getBottomPropertiesPanel() {
        return bottomPropertiesPanel;
    }

    public void setBottomPropertiesPanel(JPanel bottomPropertiesPanel) {
        this.bottomPropertiesPanel = bottomPropertiesPanel;
    }


    public JPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(JPanel topPanel) {
        this.topPanel = topPanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }



    public ApplicationFrame() {

        setSize(WIDTH, HEIGHT);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);


    }


    private void createUIComponents() {
 /*       bottomPropertiesPanel = new JPanel(new CardLayout());
         card1 = new JPanel();
         card2 = new JPanel();
        card1.setBackground(Color.RED);
        card2.setBackground(Color.GREEN);
        bottomPropertiesPanel.add(card1,"test1");
        bottomPropertiesPanel.add(card2,"test2");
*/

    }
}

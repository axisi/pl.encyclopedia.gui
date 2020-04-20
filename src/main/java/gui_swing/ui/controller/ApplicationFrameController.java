package gui_swing.ui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import gui_swing.ui.model.*;
import gui_swing.ui.model.Listeners.MouseListeners;
import gui_swing.ui.model.filters.*;
import gui_swing.ui.model.tableModels.ObjectTableModel;
import gui_swing.ui.view.ApplicationFrame;
import net.atlanticbb.tantlinger.shef.HTMLEditorPane;
//import sun.java2d.pipe.AlphaPaintPipe;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ApplicationFrameController {
    //    private final static String termsPanel = "bottomPropertiesTermsPanel";
//    private final static String authorsPanel = "bottomPropertiesAuthorsPanel";
    private ApplicationFrame applicationFrame;
    private static Point point = new Point();
    private MainController mainController;
    private JPanel topPanel;
    private JList list1;
    private JList termsList;
    private JPanel bottomPropertiesPanel;
    private JPanel bottomPropertiesTermsPanel;
    private JPanel bottomPropertiesAuthorsPanel;
    private JPanel bottomPropertiesBlankPanel;
    private JPanel bottomPropertiesAgreementsPanel;
    private JPanel bottomPropertiesIllustrationsPanel;
    private JPanel bottomPropertiesSettingsPanel;
    private JPanel topDetailsPanel;
    private JPanel topDetailsPanelBlank;
    private JPanel topDetailsPanelFilters;
    private JPanel topTermDetailsPanel;
    private JLabel termsErrorLabel;
    boolean tags1 = true;
    boolean tags2 = true;
    String newTermSuccessesAdded;
    private JButton markReferenceButton;
    private Integer lettersOnVerse = 52;

    private JList categoryJList;
    private JList subcategoryJList;
    private JList tagsJList;
    private JList statusesJList;
    private JPanel shefPanel;

    private JButton searchButton;
    private JLabel iconLabel;
    private JLabel loggedUserLabel;
    private JLabel logoutLabel;
    private JLabel exitLabel;
    private JPanel bottomDetailsTermsPanel;
    private JTable termsTable;
    private JPanel bottomDetailsBlankPanel;
    private JPanel bottomDetailsPanel;
    private boolean iconLabelFlag = true;
    private JPanel bottomDetailsTermPanel;
    ApiConnector apiConnector = new ApiConnector();
    private ObjectTableModel tm;
    //String[] headersStr = {"Id", "Tytuł", "Ilość wersji", "Aktualna wersja", "Autorzy", "Podpis"};
    // private TermTableModel  termTableModel = new TermTableModel(headersStr,null) ;
    CardLayout cardLayout1;
    CardLayout cardLayout;
    CardLayout cardLayout2;
    private ArrayList<String> tagsStrings;
    private RenderTermsFilters renderTermsFilters;
    private JPanel termErrorLabelPanel;
    private JButton addTermButton;
    Boolean statusesWereSet = false;
    private JLabel versesTermLabel;
    private JLabel termHistoryVersionLabel;
    private JEditorPane editorPane1;
    HTMLEditorPane htmlEditorPane = new HTMLEditorPane();
    PaginationDataProvider<TermTable> dataProvider;
    private JTable authorsTable;
    private JList tagsTermJList;
    private JList statusesTermJList;
    private MouseListeners.CheckListItemMouseListenerStatuses checkListItemMouseListenerStatuses = new MouseListeners.CheckListItemMouseListenerStatuses();
    public  static Boolean termsTableShown = false;

    private JComboBox categoryComboBox;
    private JComboBox subcategoryComboBox;
    private DefaultTableModel authorsTableModelInTermDetails;
    private JTextField textField1;
    private JCheckBox isSignedCheckBox;

    private JLabel actualTermHistoryVersionLabel;
    private JLabel termDetailsIdLabel;
    private String defaultSubcategoryForNewTerm = "Propozycja nowego hasła";
    public  List<TermTable> list ;

    private JButton updateTermButton;
    private JButton sendTermToNextStepButton;
    private JButton manageTermStatusesButton;
    private JButton manageTermVersionsButton;
    private JButton redirectTermToIndicatedAuthorButton;
    private JButton manageTermReferenceButton;
    private JLabel editedTermHistoryVersionLabel;


    //Authors begin------------------------------------------------------------------------------------------------------------------------------------------------
    private JList authorsList;

    private JPanel bottomDetailsAuthorsPanel;
    private JTable authorsMenageTable;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField signTextField;
    private JTextField emailTextField;
    private JButton addAuthorButton;
    private JButton updateAuthorButton;
    private JButton deleteAuthorButton;
    private JScrollPane editorPane;
    private JLabel authorsIdLabel;
    private JLabel authorPanelErrorLabel;
    private Long selectedAuthorId = -1L;
    private DefaultTableModel am;
    private static  JFrame frame;

    //Authors end----------------------------------------------------------------END-----------------------------------------------------------------------------


    // settings tab----------------------------------------------------------START-------------------------------------------------------------------------------

    private JList settingsList;

    private JPanel bottomDetailsSettingsPanel;
    private JPanel left;
    private JButton addSubcategoryButton;
    private JTextField subcategoryTextField;
    private JButton deleteSubcategoryButton;
    private JPanel leftCenter;
    private JPanel rightCenter;
    private JPanel right;
    private JList subcategorySettingsList;
    private JList allStatusesSettingsList;
    private JList chosenStatusesSettingsList;
    private JButton upButton;
    private JButton downButton;
    private JButton leftButton;
    private JButton rightButton;
    private JLabel subcategoryErrorLabel;
    private JButton saveSubcategoryStatuses;

    //------------------------------------------------tags
    private JPanel bottomDetailsTagsPanel;
    private JPanel topDetailsTagsPanel;
    private JTextField tagsNameField;
    private JButton addTagButton;
    private JButton deleteTagButton;
    private JList tagsSettingsList;
    private JTable termsTagsTable;
    private JButton choseTagColorButton;
    private JLabel tagIconLabel;
    private JLabel tagsSettingsErrorLabel;
    private DefaultTableModel tagsModel;
    private Color selectedTagColor;
    private String selectedTagColorName;
    private JButton saveTermsToTagButton;
    private JButton updateTagButton;
    //------------------------------------------------tags
    // settings tab----------------------------------------------------------END---------------------------------------------------------------------------------


    //--------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------


    private JButton getStatusesForSubcategoryButton;


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
        bottomDetailsBlankPanel = applicationFrame.getBottomDetailsBlankPanel();
        bottomDetailsTermsPanel = applicationFrame.getBottomDetailsTermsPanel();
        bottomDetailsPanel = applicationFrame.getBottomDetailsPanel();
        topDetailsPanel = applicationFrame.getTopDetailsPanel();
        topDetailsPanelBlank = applicationFrame.getTopDetailsPanelBlank();
        topDetailsPanelFilters = applicationFrame.getTopDetailsPanelFilters();
        categoryJList = applicationFrame.getCategoryJList();
        subcategoryJList = applicationFrame.getSubcategoryJList();
        tagsJList = applicationFrame.getTagsJList();
        statusesJList = applicationFrame.getStatusesJList();
        markReferenceButton = applicationFrame.getMarkReferenceButton();
        list= applicationFrame.getListOfTermTable();


        //termTableModel= applicationFrame.getTermTableModel();
        bottomDetailsTermPanel = applicationFrame.getBottomDetailsTermPanel();
        cardLayout1 = (CardLayout) (bottomDetailsPanel.getLayout());
        cardLayout1.show(bottomDetailsPanel, bottomDetailsBlankPanel.getName());
        cardLayout = (CardLayout) (bottomPropertiesPanel.getLayout());
        cardLayout.show(bottomPropertiesPanel, bottomPropertiesBlankPanel.getName());
        cardLayout2 = (CardLayout) (topDetailsPanel.getLayout());
        cardLayout2.show(topDetailsPanel, topDetailsPanelBlank.getName());
        termsTable = applicationFrame.getTermsTable();
        tm = (ObjectTableModel) termsTable.getModel();
        termsTable.setAutoCreateRowSorter(true);

        tagsStrings = applicationFrame.getTagsStrings();
        searchButton = applicationFrame.getSearchButton();
        termsErrorLabel = applicationFrame.getTermsErrorLabel();
        termErrorLabelPanel = applicationFrame.getTermErrorLabelPanel();
        topTermDetailsPanel = applicationFrame.getTopTermDetailsPanel();
        addTermButton = applicationFrame.getAddTermButton();

        termHistoryVersionLabel = applicationFrame.getTermHistoryVersionLabel();
        authorsTable = applicationFrame.getAuthorsTable();
        tagsTermJList = applicationFrame.getTagsTermJList();
        statusesTermJList = applicationFrame.getStatusesTermJList();
        categoryComboBox = applicationFrame.getCategoryComboBox();
        subcategoryComboBox = applicationFrame.getSubcategoryComboBox();
        getStatusesForSubcategoryButton = applicationFrame.getGetStatusesForSubcategoryButton();
        versesTermLabel = applicationFrame.getVersesTermLabel();
        //editorPane1 = applicationFrame.getEditorPane1();
        editorPane1 = new JEditorPane();
        authorsTableModelInTermDetails = (DefaultTableModel) authorsTable.getModel();
        authorsTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        textField1 = applicationFrame.getTextField1();
        isSignedCheckBox = applicationFrame.getIsSignedCheckBox();
        actualTermHistoryVersionLabel = applicationFrame.getActualTermHistoryVersionLabel();
        termDetailsIdLabel = applicationFrame.getTermDetailsIdLabel();

        shefPanel = applicationFrame.getShefPanel();
        JMenuBar jMenuBar = new JMenuBar();
                /*JFrame frame = new JFrame();
                frame.getContentPane().add(htmlEditorPane);
                frame.setVisible(true);*/
        //editorPane1 =null;
        jMenuBar.add(htmlEditorPane.getEditMenu());
        jMenuBar.add(htmlEditorPane.getFormatMenu());
        jMenuBar.add(htmlEditorPane.getInsertMenu());
        shefPanel.add(jMenuBar, BorderLayout.PAGE_START);
        shefPanel.add(htmlEditorPane, BorderLayout.CENTER);
        htmlEditorPane.setAutoscrolls(true);
        htmlEditorPane.setOpaque(false);


        updateTermButton = applicationFrame.getUpdateTermButton();
        sendTermToNextStepButton = applicationFrame.getSendTermToNextStepButton();
        manageTermStatusesButton = applicationFrame.getManageTermStatusesButton();
        manageTermVersionsButton = applicationFrame.getManageTermVersionsButton();
        redirectTermToIndicatedAuthorButton = applicationFrame.getRedirectTermToIndicatedAuthorButton();
        manageTermReferenceButton = applicationFrame.getManageTermReferenceButton();
        editedTermHistoryVersionLabel = applicationFrame.getEditedTermHistoryVersionLabel();


        //Authors--------------------------------------------------------------------START------------------------------------------------------------------------

        authorsList = applicationFrame.getAuthorsList();
        bottomDetailsAuthorsPanel = applicationFrame.getBottomDetailsAuthorsPanel();
        addAuthorButton = applicationFrame.getAddAuthorButton();
        deleteAuthorButton = applicationFrame.getDeleteAuthorButton();
        updateAuthorButton = applicationFrame.getUpdateAuthorButton();
        authorPanelErrorLabel = applicationFrame.getAuthorPanelErrorLabel();
        nameTextField = applicationFrame.getNameTextField();
        surnameTextField = applicationFrame.getSurnameTextField();
        emailTextField = applicationFrame.getEmailTextField();
        signTextField = applicationFrame.getSignTextField();
        authorsIdLabel = applicationFrame.getAuthorsIdLabel();
        authorsMenageTable = applicationFrame.getAuthorsMenageTable();
        am = (DefaultTableModel) authorsMenageTable.getModel();
        //Authors end-------------------------------------------------------------END--------------------------------------------------------------------------------


        // settings tab----------------------------------------------------------START-------------------------------------------------------------------------------

        settingsList = applicationFrame.getSettingsList();
        bottomDetailsSettingsPanel = applicationFrame.getBottomDetailsSettingsPanel();
        subcategoryErrorLabel = applicationFrame.getSettingsErrorLabel();
        subcategorySettingsList = applicationFrame.getList2();
        allStatusesSettingsList = applicationFrame.getList3();
        chosenStatusesSettingsList = applicationFrame.getList4();
        addSubcategoryButton = applicationFrame.getAddSubcategoryButton();
        subcategoryTextField = applicationFrame.getSubcategoryTextField();
        deleteSubcategoryButton = applicationFrame.getDeleteSubcategoryButton();
        upButton = applicationFrame.getUpButton();
        downButton = applicationFrame.getDownButton();
        leftButton = applicationFrame.getLeftButton();
        rightButton = applicationFrame.getRightButton();
        saveSubcategoryStatuses = applicationFrame.getSaveSubcategoryStatuses();

        //------------------------------------------------tags
        bottomDetailsTagsPanel = applicationFrame.getBottomDetailsTagsPanel();

        tagsSettingsList = applicationFrame.getList5();
        tagsNameField = applicationFrame.getTagsNameField();
        termsTagsTable = applicationFrame.getTermsTagsTable();
        tagsModel = (DefaultTableModel) termsTagsTable.getModel();
        termsTagsTable.getTableHeader().setReorderingAllowed(false);
        addTagButton = applicationFrame.getAddTagButton();
        choseTagColorButton = applicationFrame.getChoseTagColorButton();
        tagIconLabel = applicationFrame.getTagIconLabel();
        tagsSettingsErrorLabel = applicationFrame.getTagsSettingsErrorLabel();
        deleteTagButton = applicationFrame.getDeleteTagButton();
        updateTagButton = applicationFrame.getUpdateTagButton();
        saveTermsToTagButton = applicationFrame.getSaveTermsToTagButton();




        //------------------------------------------------tags
        // settings tab----------------------------------------------------------END---------------------------------------------------------------------------------


        termsList = applicationFrame.getTermsList();

        backToMainPanel();
        loggedUserLabel = applicationFrame.getLoggedUserLabel();
        iconLabel = applicationFrame.getIconLabel();
        exitLabel = applicationFrame.getExitLabel();
        logoutLabel = applicationFrame.getLogoutLabel();

        renderTermsFilters = new RenderTermsFilters(new JList[]{applicationFrame.getCategoryJList(), applicationFrame.getSubcategoryJList(), applicationFrame.getStatusesJList()}, 1);
        termsTable.getTableHeader().setReorderingAllowed(false);
        authorsMenageTable.getTableHeader().setReorderingAllowed(false);
        authorsTable.getTableHeader().setReorderingAllowed(false);

    }

    private void comboboxFill(JComboBox comboBox, ArrayList<String> stringValuesToInsert) {
        DefaultComboBoxModel defaultComboBoxModel = (DefaultComboBoxModel) comboBox.getModel();
        defaultComboBoxModel.removeAllElements();
        comboBox.addItem("");
        for (String s : stringValuesToInsert
        ) {
            comboBox.addItem(s);
        }
    }

    public void showMainFrameWindow() {
        applicationFrame.setVisible(true);
    }

    public void setValuesInLoggedUserLabel() {
        loggedUserLabel.setText("<html>Zalogowany użytkownik: <br/>" + ConfigManager.getLoggedUser() + "</html>");
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
                if (e.getValueIsAdjusting()) {
                    CardLayout cardLayout = (CardLayout) (bottomPropertiesPanel.getLayout());
                    if (iconLabelFlag) {
                        switch (list1.getSelectedValue().toString()) {
                            case "Hasła":
                                backToMainPanel();
                                cardLayout.show(bottomPropertiesPanel, bottomPropertiesTermsPanel.getName());
                                cardLayout1.show(bottomDetailsPanel, bottomDetailsBlankPanel.getName());
                                break;
                            case "Autorzy":
                                backToMainPanel();
                                cardLayout.show(bottomPropertiesPanel, bottomPropertiesAuthorsPanel.getName());
                                cardLayout1.show(bottomDetailsPanel, bottomDetailsBlankPanel.getName());
                                break;
                            case "Umowy":
                                backToMainPanel();
                                cardLayout.show(bottomPropertiesPanel, bottomPropertiesAgreementsPanel.getName());
                                cardLayout1.show(bottomDetailsPanel, bottomDetailsBlankPanel.getName());
                                break;
                            case "Ilustracje":
                                backToMainPanel();
                                cardLayout.show(bottomPropertiesPanel, bottomPropertiesIllustrationsPanel.getName());
                                cardLayout1.show(bottomDetailsPanel, bottomDetailsBlankPanel.getName());
                                break;
                            case "Ustawienia":
                                backToMainPanel();
                                cardLayout.show(bottomPropertiesPanel, bottomPropertiesSettingsPanel.getName());
                                cardLayout1.show(bottomDetailsPanel, bottomDetailsBlankPanel.getName());
                                break;
                        }

                    }
                }


            }
        });

        termsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    if (iconLabelFlag) {
                        switch (termsList.getSelectedValue().toString()) {
                            case "Pokaż wszystkie":
                                //System.out.println( bottomDetailsTermsPanel.getName());
                               if(!termsTableShown){
                                   hideFiltersSearchPanels();
                                   try {
                                       apiConnector.getAllTerm();

                                   } catch (Exception ex) {
                                       logoutBecauseOfError();
                                   }
                                   renderTermTable();
                               }else{
                                   //JOptionPane.showMessageDialog(applicationFrame,"Jest już otwarte oknko z hasłami, zamknij je aby otworzyć nowe","Uwaga!",JOptionPane.WARNING_MESSAGE);
                                   JOptionPane.showMessageDialog(applicationFrame,"Jest już otwarte oknko z hasłami, zamknij je aby otworzyć nowe","Uwaga!",JOptionPane.WARNING_MESSAGE);
                               }
                                break;
                            case "Pokaż według filtrów":
                                hideFiltersSearchPanels();
                                // System.out.println( bottomDetailsBlankPanel.getName());
                                cardLayout1.show(bottomDetailsPanel, bottomDetailsBlankPanel.getName());
                                searchButton.setVisible(true);
                                JList[] jlist = new JList[3];
                                jlist[0] = categoryJList;
                                jlist[1] = subcategoryJList;
                                jlist[2] = statusesJList;
                                try {
                                    ArrayList<String> tagsName = apiConnector.getAllTags();

                                    renderTermsFilters.setTagsJList(tagsJList, tagsName);
                                    if (tags2) {
                                        renderTermsFilters.setJListCheckBoxFeatures(tagsJList, 1);
                                        tags2 = false;
                                    }
                                    DefaultListModel defaultListModel = (DefaultListModel) subcategoryJList.getModel();
                                    defaultListModel.removeAllElements();
                                    ArrayList<String> subcategoriesList = apiConnector.getAllSubcategoriesString();
                                    for (String s : subcategoriesList
                                    ) {
                                        defaultListModel.addElement(new CheckListItem(s));
                                    }

                                    cardLayout2.show(topDetailsPanel, topDetailsPanelFilters.getName());
                                } catch (Exception ex) {

                                    System.out.println(ex.getMessage());
                                    logoutBecauseOfError();
                                }
                                //System.out.println(topDetailsPanel.getWidth());
                                break;
                            case "Pokaż według autorów":
                                hideFiltersSearchPanels();
                                cardLayout1.show(bottomDetailsPanel, bottomDetailsBlankPanel.getName());
                                break;

                            case "Nowe hasło":
                                hideFiltersSearchPanels();
                                cardLayout1.show(bottomDetailsPanel, bottomDetailsTermPanel.getName());

                                addTermButton.setVisible(true);

                                prepareTermForm(true);


                                comboboxFill(categoryComboBox, apiConnector.getAllCategoriesString());
                                comboboxFill(subcategoryComboBox, apiConnector.getAllSubcategoriesString());

                                subcategoryComboBox.setSelectedItem(defaultSubcategoryForNewTerm);
                                cardLayout2.show(topDetailsPanel, topTermDetailsPanel.getName());
                                CheckListItem checkListItem = (CheckListItem) statusesTermJList.getModel().getElementAt(0);
                                checkListItem.setSelected(true);
                                break;
                        }
                    }
                }
            }
        });
        // Authors begin---------------------------------------------------------------------------------------------------------

        /*markReferenceButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HTMLEditorPane htmlEditorPane = new HTMLEditorPane();
                *//*JFrame frame = new JFrame();
                frame.getContentPane().add(htmlEditorPane);
                frame.setVisible(true);*//*
                //editorPane1 =null;
                shefPanel= htmlEditorPane;
                shefPanel.setVisible(true);
                //shefPanel.setMinimumSize(new Dimension(100,100));
            }
        });*/
        authorsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    switch (authorsList.getSelectedValue().toString()) {
                        case "Zarządzanie autorami":
                            selectedAuthorId = -1L;
                            cardLayout1.show(bottomDetailsPanel, bottomDetailsAuthorsPanel.getName());
                            addAuthorButton.setVisible(true);
                            renderAuthorTable();
                            break;


                    }

                }
            }
        });
        addAuthorButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {


                //System.out.println(nameTextField.getText());
                if (nameTextField.equals("") || surnameTextField.getText().length() == 0) {
                    authorPanelErrorLabel.setText("Pola imię oraz nazwisko muszą zostać wypełnione.");
                } else {
                    if (!apiConnector.isAuthorExist(nameTextField.getText(), surnameTextField.getText())) {

                        Author author = new Author();
                        author.setName(nameTextField.getText());
                        author.setSurname(surnameTextField.getText());
                        author.setEmail(emailTextField.getText());
                        author.setSign(signTextField.getText());

                        authorPanelErrorLabel.setText(apiConnector.addNewAuthor(author));

                        //deleteAuthorButton.setVisible(true);
                        //updateAuthorButton.setVisible(true);
                        selectedAuthorId = apiConnector.getSelectedAuthorId();

                        authorsIdLabel.setText("ID: " + selectedAuthorId.intValue());
                        clearAuthorPanel();
                        renderAuthorTable();
                    } else
                        authorPanelErrorLabel.setText("Autor o takim imieniu i nazwisku istnieje już w bazie.");

                }
            }
        });
        authorsMenageTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    Author author = apiConnector.getAuthor((Integer) authorsMenageTable.getValueAt(authorsMenageTable.getSelectedRow(), 0));
                    Float idAuthor = (author.getId());
                    selectedAuthorId =
                            (idAuthor.longValue());

                    authorsIdLabel.setText("ID: " + selectedAuthorId.intValue());
                    nameTextField.setText(author.getName());
                    surnameTextField.setText(author.getSurname());
                    emailTextField.setText(author.getEmail());
                    signTextField.setText(author.getSign());
                    if (!updateAuthorButton.isVisible()) {
                        updateAuthorButton.setVisible(true);
                    }
                    if (!deleteAuthorButton.isVisible()) {
                        deleteAuthorButton.setVisible(true);
                    }
                }
            }
        });
        deleteAuthorButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (apiConnector.isAuthorRemovable(selectedAuthorId)) {
                    authorPanelErrorLabel.setText(apiConnector.deleteAuthor(selectedAuthorId.intValue()));
                    hideButtonsAndRefreshViewAfterAuthorsAction();
                } else
                    authorPanelErrorLabel.setText("Autor o id: '" + selectedAuthorId + "' nie może zostać usunięty ponieważ są do niego przypisane hasła.");
            }
        });
        updateAuthorButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = -2;
                Author author = apiConnector.getAuthor(selectedAuthorId.intValue());
                if (!(Objects.equals(author.getName(), (nameTextField.getText())) && Objects.equals(author.getSurname(), (surnameTextField.getText()))
                        && Objects.equals(author.getEmail(), (emailTextField.getText())) && Objects.equals(author.getSign(), (emailTextField.getText())))) {
                    String message = "Czy chcesz zaktualizować autora o id: '" + (int) author.getId() + "' \nObecne imię : '" +
                            author.getName() + "', nazwisko: '" + author.getSurname() + "', podpis: '" + author.getSign() + "', email: '" +
                            author.getEmail() + "'.\n Nowe dane autora będą następujące - imię: '" + nameTextField.getText() + "', nazwisko: '" +
                            surnameTextField.getText() + "', podpis: '" + signTextField.getText() + "', email: '" + emailTextField.getText() + "'?";
                    result = JOptionPane.showOptionDialog(applicationFrame, message, "Aktualizacja autora", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                } else
                    authorPanelErrorLabel.setText("Dla autora o id " + (int) author.getId() + "  nie zostały zmienione żadne dane.");

                if (result == 0) {
                    author.setName(nameTextField.getText());
                    author.setSurname(surnameTextField.getText());
                    author.setSign(signTextField.getText());
                    author.setEmail(emailTextField.getText());
                    authorPanelErrorLabel.setText(apiConnector.updateAuthor(author));
                    hideButtonsAndRefreshViewAfterAuthorsAction();
                }

            }
        });
        // Authors end---------------------------------------------------------------------------------------------------------
        getStatusesForSubcategoryButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (subcategoryComboBox.getSelectedIndex() != 0)
                    renderTermsFilters.setTagsJList(statusesTermJList, apiConnector.getStatusesOfSubcategory(subcategoryComboBox.getSelectedItem().toString()));

            }
        });

        /*termsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                StringBuilder output = new StringBuilder();

                if (lsm.isSelectionEmpty()) {
                    output.append(" <none>");
                } else {
                    // Find out which indexes are selected.
                    int minIndex = lsm.getMinSelectionIndex();
                    int maxIndex = lsm.getMaxSelectionIndex();
                    for (int i = minIndex; i <= maxIndex; i++) {
                        if (lsm.isSelectedIndex(i)) {
                            output.append(" " + termsTable.getValueAt(i,1));
                        }
                    }
                }

               if( e.getValueIsAdjusting())
                   if( termsTable.getSelectedRow()>=0){
                      // System.out.println(termsTable.getValueAt(termsTable.getSelectedRow(),1));
                       //System.out.println(output);
                   }


            }
        });*/

        // Klilnięcie na rekord w tabeli haseł

        termsTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {

                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    hideFiltersSearchPanels();
                    Integer integerId = Integer.valueOf(termsTable.getValueAt(termsTable.getSelectedRow(), 0).toString());
                    fillTermFormWithFreshData(integerId);
                }
            }
        });
       /* termsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                termsTable.setSelectionBackground(Color.RED);
            }
        });*/
        settingsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    if (iconLabelFlag) {
                        switch (settingsList.getSelectedValue().toString()) {
                            case "Podkategorie":

                                settingsCardClear();
                                cardLayout1.show(bottomDetailsPanel, bottomDetailsSettingsPanel.getName());
                                renderSubcategoriesJList();
                                break;

                            case "Tagi":
                                settingsCardClear();
                                cardLayout1.show(bottomDetailsPanel, bottomDetailsTagsPanel.getName());
                                //

                                renderTagsJList();

                                //tagsModel.addRow(new Object[]{true,1,"szikakaka"});


                                break;

                            case "Użytkownicy":

                            case "Kategorie":
                                settingsCardClear();

                                break;


                        }
                    }
                }
            }
        });
//-----------------------------------------------------------------------------------------------TermBUTTONS-----START-------------------------------------------------------------------------------
        searchButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!termsTableShown){
                    // ArrayList<String>[] searchMatrix= new ArrayList<String>[4];
                    ListsF searchMatrix = new ListsF();
                    CategoryF<String> categoriesList = new CategoryF<String>();
                    SubcategoryF<String> subcategoriesList = new SubcategoryF<String>();
                    TagF<String> tagsList = new TagF<String>();
                    StatusesF<String> statusesList = new StatusesF<>();


                    generateLists(categoriesList, categoryJList);
                    generateLists(subcategoriesList, subcategoryJList);
                    generateLists(tagsList, tagsJList);
                    generateLists(statusesList, statusesJList);
                    if (!(categoriesList.isEmpty() && subcategoriesList.isEmpty() && tagsList.isEmpty() && statusesList.isEmpty())) {
                        searchMatrix.setCategoryF(categoriesList);
                        searchMatrix.setSubcategoryF(subcategoriesList);
                        searchMatrix.setTagF(tagsList);
                        searchMatrix.setStatusesF(statusesList);

                        try {
                            apiConnector.getTermsByFilter(searchMatrix);
                        } catch (JsonProcessingException ex) {
                            ex.printStackTrace();
                        }
                        if (!apiConnector.getEmpty()) {
                            renderTermTable();

                        } else {
                            termsErrorLabel.setText("Żadne z haseł nie spełnia zadanych kryteriów.");
                            cardLayout1.show(bottomDetailsPanel, bottomDetailsTermsPanel.getName());

                        /*if (tm.getRowCount() != 0) {
                            for (int i = tm.getRowCount() - 1; i > -1; i--) {
                                tm.removeRow(i);

                            }
                        }*/
                        }
                    }
                }else
                {
                    JOptionPane.showMessageDialog(applicationFrame,"Jest już otwarte oknko z hasłami, zamknij je aby otworzyć nowe","Uwaga!",JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        addTermButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (String.valueOf(categoryComboBox.getSelectedItem()) == "") {
                    versesTermLabel.setText("Hasło musi mieć ustawioną kategorię.");
                } else {


                    Term term = new Term();
                    TermHistory termHistory = new TermHistory();
                    termHistory.setVersion(1L);
                    term.setActualVersion(termHistory.getVersion());
                    term.setTitle(textField1.getText());
                    term.setSigned(isSignedCheckBox.isSelected());
                    termHistory.setContent(htmlEditorPane.getText());
                    term.setCategory(String.valueOf(categoryComboBox.getSelectedItem()));
                    term.setSubcategory(String.valueOf(subcategoryComboBox.getSelectedItem()));
                    //System.out.println("a");

                    packFormDataToEntity(termHistory, term);

                    prepareTermForm(true);
                    try {

                        versesTermLabel.setText(apiConnector.addNewTerm(term));
                        tagsStrings.clear();
                        categoryComboBox.setSelectedIndex(0);
                        subcategoryComboBox.setSelectedIndex(0);
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                        logoutBecauseOfError();
                    }


                }
            }
        });

        updateTermButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String category = categoryComboBox.getModel().getSelectedItem().toString();
                String subcategory = subcategoryComboBox.getModel().getSelectedItem().toString();

                Long termId = Long.valueOf(termDetailsIdLabel.getText().substring(4));
                Long termHistoryId;
                Term oldTerm = apiConnector.getTerm(termId.intValue());
                Term newTerm = new Term(termId, isSignedCheckBox.isSelected(), textField1.getText(), category, subcategory);
                TermHistory termHistory = new TermHistory();
                Long actualTermHistoryVersion = Long.valueOf(actualTermHistoryVersionLabel.getText());
                for (TermHistory t : oldTerm.getTermHistories()
                ) {
                    if (t.getVersion() == actualTermHistoryVersion) {
                        termHistoryId = t.getId();
                        termHistory = apiConnector.getTermHistory(termHistoryId);
                    }
                }
                termHistory.setContent(htmlEditorPane.getText());

                packFormDataToEntity(termHistory, newTerm);
                try {
                    Integer termId1 = apiConnector.updateTerm(newTerm);
                    fillTermFormWithFreshData(termId1);
                    versesTermLabel.setText("Pomyślnie zaktualizowano hasło o id '" + termId1 + "'.");
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                    logoutBecauseOfError();
                }


            }
        });
//-----------------------------------------------------------------------------------------------TermBUTTONS-----STOP-------------------------------------------------------------------------------
//---------------------------------------------------------------------Settings subcategory START ----------------------------------------------------------------------------
        addSubcategoryButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (subcategoryTextField.getText().length() != 0) {
                    if (apiConnector.isSubcategoryExist(subcategoryTextField.getText())) {
                        subcategoryErrorLabel.setText("Podkategoria: '" + subcategoryTextField.getText() + "' już istnieje. Wybierz inną nazwę.");
                    } else {
                        subcategoryErrorLabel.setText(apiConnector.createSubcategory(subcategoryTextField.getText()));
                        renderSubcategoriesJList();
                        subcategoryTextField.setText("");
                    }
                } else {
                    subcategoryErrorLabel.setText("Nazwa podkategorii nie może być pusta.");
                }

            }
        });

        deleteSubcategoryButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(bottomDetailsSettingsPanel.getHeight());
                if (subcategorySettingsList.getSelectedValue() != null) {
                    Boolean isRemovable = apiConnector.isSubcategoryRemovable(subcategorySettingsList.getSelectedValue().toString());

                    if (Objects.equals(subcategorySettingsList.getSelectedValue().toString(), ("Propozycja nowego hasła"))) {

                        isRemovable = false;
                    }

                    if (isRemovable) {
                        subcategoryErrorLabel.setText(subcategorySettingsList.getSelectedValue() + "' o ID: '" + apiConnector.deleteSubcategory(apiConnector.getSubcategoryId(subcategorySettingsList.getSelectedValue().toString())));
                        renderSubcategoriesJList();
                    } else {
                        subcategoryErrorLabel.setText("Nie można usunąć podkategorii, ponieważ istnieją hasła które są do niej przypisane.");
                    }
                } else {
                    subcategoryErrorLabel.setText("Nie została zanaznaczona żadna podkategoria, do usunięcia");
                }

            }
        });

        subcategorySettingsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList) e.getSource();
                if (e.getClickCount() == 1) {
                    String chosenSubcategory = list.getModel().getElementAt(list.locationToIndex(e.getPoint())).toString();
                    DefaultListModel defaultListModelAll = (DefaultListModel) allStatusesSettingsList.getModel();
                    DefaultListModel defaultListModelChosen = (DefaultListModel) chosenStatusesSettingsList.getModel();
                    defaultListModelAll.removeAllElements();
                    defaultListModelChosen.removeAllElements();
                    ArrayList<String> allStatuses = apiConnector.getAllStatusesString();

                    ArrayList<String> chosenStatuses = apiConnector.getStatusesOfSubcategory(chosenSubcategory);
                    for (String s : chosenStatuses
                    ) {
                        for (int i = 0; i < allStatuses.size(); i++) {
                            if (Objects.equals(s, allStatuses.get(i))) {
                                allStatuses.remove(i);
                                break;
                            }
                        }

                        defaultListModelChosen.addElement(s);
                    }

                    for (String s : allStatuses
                    ) {
                        defaultListModelAll.addElement(s);
                    }

                }
            }
        });

        upButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultListModel defaultListModelChosen = (DefaultListModel) chosenStatusesSettingsList.getModel();
                if (!chosenStatusesSettingsList.isSelectionEmpty()) {
                    int index = chosenStatusesSettingsList.getSelectedIndex();
                    String temp = "";
                    if (index > 0) {

                        temp = defaultListModelChosen.getElementAt(index - 1).toString();
                        defaultListModelChosen.set(index - 1, defaultListModelChosen.get(index));
                        defaultListModelChosen.set(index, temp);
                        chosenStatusesSettingsList.setSelectedIndex(index - 1);

                    }
                }
            }
        });
        downButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel defaultListModelChosen = (DefaultListModel) chosenStatusesSettingsList.getModel();
                if (!chosenStatusesSettingsList.isSelectionEmpty()) {
                    int index = chosenStatusesSettingsList.getSelectedIndex();
                    String temp = "";
                    if (index < defaultListModelChosen.getSize() - 1) {

                        temp = defaultListModelChosen.getElementAt(index + 1).toString();
                        defaultListModelChosen.set(index + 1, defaultListModelChosen.get(index));
                        defaultListModelChosen.set(index, temp);
                        chosenStatusesSettingsList.setSelectedIndex(index + 1);

                    }
                }
            }
        });
        rightButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel defaultListModelChosen = (DefaultListModel) chosenStatusesSettingsList.getModel();
                DefaultListModel defaultListModelAll = (DefaultListModel) allStatusesSettingsList.getModel();
                int index = allStatusesSettingsList.getSelectedIndex();
                if (!allStatusesSettingsList.isSelectionEmpty()) {

                    defaultListModelChosen.addElement(defaultListModelAll.get(index));
                    defaultListModelAll.remove(index);

                }
                if (defaultListModelAll.getSize() >= index) {
                    allStatusesSettingsList.setSelectedIndex(index);
                } else {
                    allStatusesSettingsList.setSelectedIndex(defaultListModelAll.getSize() - 2);
                }
            }
        });
        leftButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel defaultListModelChosen = (DefaultListModel) chosenStatusesSettingsList.getModel();
                DefaultListModel defaultListModelAll = (DefaultListModel) allStatusesSettingsList.getModel();
                int index = chosenStatusesSettingsList.getSelectedIndex();
                if (!chosenStatusesSettingsList.isSelectionEmpty()) {

                    defaultListModelAll.addElement(defaultListModelChosen.get(index));
                    defaultListModelChosen.remove(index);

                }
                if (defaultListModelChosen.getSize() >= index) {
                    chosenStatusesSettingsList.setSelectedIndex(index);
                } else {
                    chosenStatusesSettingsList.setSelectedIndex(defaultListModelChosen.getSize() - 2);
                }
            }
        });

        saveSubcategoryStatuses.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!subcategorySettingsList.isSelectionEmpty()) {
                    ArrayList<String> statuses = new ArrayList<>();
                    for (int i = 0; i < chosenStatusesSettingsList.getModel().getSize(); i++) {
                        statuses.add(chosenStatusesSettingsList.getModel().getElementAt(i).toString());
                    }
                    subcategoryErrorLabel.setText(apiConnector.updateSubcategoryStatuses(subcategorySettingsList.getSelectedValue().toString(), statuses));
                }
            }
        });

        //---------------------------------------------------------------Tags START---------------------------------------------------------------------------------------------
        addTagButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(tagsNameField.getText(), "") || selectedTagColor == null) {
                    tagsSettingsErrorLabel.setText("Tag musi mieć nazwę, oraz musi mieć wybrany kolor.");
                } else {
                    if (apiConnector.isTagExist(tagsNameField.getText())) {
                        tagsSettingsErrorLabel.setText("Tag o wpisanej nazwie już istnieje");
                    } else {
                        Tag tag = new Tag();
                        tag.setName(tagsNameField.getText());
                        tag.setIconName(selectedTagColorName + ".png");
                        tagsSettingsErrorLabel.setText("Pomyślnie udało się założyć tag o id: '" + apiConnector.addNewTag(tag) + "'.");
                        renderTagsJList();

                    }

                }
                // IndexColorModel indexColorModel = new IndexColorModel();
                //BufferedImage bufferedImage = new BufferedImage(10,10,BufferedImage.TYPE_INT_RGB);
                //Graphics2D cg = bufferedImage.createGraphics();

            }
        });

        choseTagColorButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                selectedTagColor = JColorChooser.showDialog(
                        bottomDetailsTagsPanel,
                        "Wybierz kolor",
                        Color.WHITE);
                if (selectedTagColor != null) {
                    selectedTagColorName = selectedTagColor.getRed() + "-" + selectedTagColor.getGreen() + "-" + selectedTagColor.getBlue();
                    String tagIconPath = getColorTagIcon(selectedTagColor, selectedTagColorName);
                    tagIconLabel.setIcon(new ImageIcon(tagIconPath));
                }

            }
        });
        deleteTagButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tagsSettingsList.isSelectionEmpty()) {
                    tagsSettingsErrorLabel.setText("Wybierz tag do usunięcia.");
                } else {
                    if (apiConnector.isTagRemovable(tagsSettingsList.getSelectedValue().toString())) {
                        // System.out.println("usunąć "+tagsSettingsList.getSelectedValue().toString());
                        tagsSettingsErrorLabel.setText("Pomyślnie usunięto tago id: '" + apiConnector.deleteTag(tagsSettingsList.getSelectedValue().toString()) + "'.");
                        renderTagsJList();
                    } else {
                        tagsSettingsErrorLabel.setText("Nie można usunąć tagu, ponieważ są do niego przypsane hasła.");
                    }
                }
            }
        });
        updateTagButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tagsSettingsList.isSelectionEmpty()) {
                    tagsSettingsErrorLabel.setText("Wybierz tag do aktualizacji.");
                } else {
                    if (selectedTagColor == null && tagsNameField.getText().isEmpty()) {
                        tagsSettingsErrorLabel.setText("Aby tag został zaktualizowany wybierz nową nazwę lub zmień kolor.");
                    } else {
                        Long id = apiConnector.getTagId(tagsSettingsList.getSelectedValue().toString());
                        Tag tag = new Tag(id.floatValue(), tagsNameField.getText(), selectedTagColorName + ".png");
                        tagsSettingsErrorLabel.setText("Pomyślnie zaktualizowano tago o id '" + apiConnector.updateTag(tag) + "'.");
                        renderTagsJList();

                    }
                }
            }
        });
        tagsSettingsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                clickOnTagTable();
            }

            /*@Override
            public void mouseClicked(MouseEvent e) {

                clickOnTagTable();
            }*/
        });

        saveTermsToTagButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long tagId = apiConnector.getTagId(tagsSettingsList.getSelectedValue().toString());
                for (int i = 0; i < tagsModel.getRowCount(); i++) {
                    Long termId = Long.valueOf(tagsModel.getValueAt(i, 1).toString());
                    Boolean result = Boolean.valueOf(tagsModel.getValueAt(i, 0).toString());
                    apiConnector.updateTagMarksTerm(tagId, termId, result);
                }
            }
        });


        //---------------------------------------------------------------Tags END---------------------------------------------------------------------------------------------

//---------------------------------------------------------------------Settings subcategory END ----------------------------------------------------------------------------

        iconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                backToMainPanel();

            }
        });
        exitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int closeOrNot = JOptionPane.showConfirmDialog(null, "Czy zamknąć aplikację?", "Zamknięcie aplikacji", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (closeOrNot == 0) {

                    applicationFrame.dispose();
                    frame.dispose();
                    termsTableShown=false;
                }
            }
        });
        logoutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logoutBecauseOfError();
            }
        });

        authorsTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

            }
        });


        editorPane1.getDocument().addDocumentListener(new DocumentListener() {
            String newline = "\n";

            public void insertUpdate(DocumentEvent e) {
                updateLog(e, "inserted into");
            }

            public void removeUpdate(DocumentEvent e) {
                updateLog(e, "removed from");
            }

            public void changedUpdate(DocumentEvent e) {
                //Plain text components do not fire these events
            }

            public void updateLog(DocumentEvent e, String action) {
                Document doc = (Document) e.getDocument();
                int changeLength = e.getLength();
                Integer sum = 0;
                for (int i = 0; i < authorsTableModelInTermDetails.getRowCount(); i++) {
                    if ((boolean) authorsTableModelInTermDetails.getValueAt(i, 1) && authorsTableModelInTermDetails.getValueAt(i, 3) != null) {
                        String verseString = authorsTableModelInTermDetails.getValueAt(i, 3).toString();
                        sum += Integer.valueOf(verseString);
                    }
                }
                versesTermLabel.setText("Hasło ma aktualnie " + doc.getLength() + " znaków. Odpowiada to " + getAnInt(doc, lettersOnVerse) + " pełnym wersetom.");
                if (getAnInt(doc, lettersOnVerse) >= sum) {
                    versesTermLabel.setText(versesTermLabel.getText() + " Długość hasłą przekroczyła zakontraktowaną ilość wersetów (" + sum + ")");
                } else {
                    versesTermLabel.setText(versesTermLabel.getText() + " Zakontraktowana ilość wersetów dla tego hasła to: (" + sum + ")");
                }

                System.out.println(
                        changeLength + " character" +
                                ((changeLength == 1) ? " " : "s ") +
                                action + doc.getProperty("name") + "." + newline +
                                "  Text length = " + doc.getLength() + newline);
            }

            private int getAnInt(Document doc, Integer lettersOnVerse) {
                return doc.getLength() / lettersOnVerse;
            }
        });
    }

    private void fillTermFormWithFreshData(Integer integerId) {
        termsList.clearSelection();
        prepareTermForm(false);

        //System.out.println(termsTable.getValueAt(termsTable.getSelectedRow(),1));
        cardLayout1.show(bottomDetailsPanel, bottomDetailsTermPanel.getName());
        cardLayout2.show(topDetailsPanel, topTermDetailsPanel.getName());

        Term term = apiConnector.getTerm(integerId);
        term.setCategory(apiConnector.getTermCategory(integerId));
        term.setSubcategory(apiConnector.getTermSubcategory(integerId));
        term.setSigned(apiConnector.isSigned(integerId));

        //----------------------------------


        TableColumnModel tableColumnModel = authorsTable.getColumnModel();


        if (authorsTableModelInTermDetails.getRowCount() != 0) {
            for (int i = authorsTableModelInTermDetails.getRowCount() - 1; i > -1; i--) {
                authorsTableModelInTermDetails.removeRow(i);
            }
        }


        tableColumnModel.getColumn(0).setMaxWidth(40);
        tableColumnModel.getColumn(1).setMaxWidth(40);
        tableColumnModel.getColumn(3).setPreferredWidth(30);
        comboboxFill(categoryComboBox, apiConnector.getAllCategoriesString());
        comboboxFill(subcategoryComboBox, apiConnector.getAllSubcategoriesString());


        //----------------------------------

        fillDataToTermForm(term, false, integerId);
    }

    private void packFormDataToEntity(TermHistory termHistory, Term term) {
        ArrayList<String> statuses = new ArrayList<>();
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<String> authors = new ArrayList<>();
        if (statusesTermJList.getModel().getSize() > 0)
            termHistory.setStatus(statusesTermJList.getModel().getElementAt(0).toString());
        for (int i = 0; i < statusesTermJList.getModel().getSize(); i++) {
            statuses.add(statusesTermJList.getModel().getElementAt(i).toString());
            CheckListItem checkListItem = (CheckListItem) statusesTermJList.getModel().getElementAt(i);
            if (checkListItem.isSelected())
                termHistory.setStatus(checkListItem.toString());
        }
        for (int i = 0; i < tagsTermJList.getModel().getSize(); i++) {
            CheckListItem checkListItem = (CheckListItem) tagsTermJList.getModel().getElementAt(i);
            if (checkListItem.isSelected())
                tags.add(checkListItem.toString());
        }
        for (int i = 0; i < authorsTableModelInTermDetails.getRowCount(); i++) {
            if ((boolean) authorsTableModelInTermDetails.getValueAt(i, 1)) {

                Integer numberOfVerses = 0;
                if (!(authorsTableModelInTermDetails.getValueAt(i, 3) == null)) {
                    Object numbersOfVersesLong = authorsTableModelInTermDetails.getValueAt(i, 3);
                    numberOfVerses = Integer.valueOf(numbersOfVersesLong.toString());
                }
                authors.add(authorsTableModelInTermDetails.getValueAt(i, 0) + "|" + numberOfVerses);
            }
        }
        ArrayList<TermHistory> termHistories = new ArrayList<>();
        termHistories.add(termHistory);

        term.setTermHistories(termHistories);
        term.setAuthors(authors);
        term.setTagsString(tags);
        term.setStatuses(statuses);
    }

    private void clearAuthorPanel() {
        nameTextField.setText("");
        surnameTextField.setText("");
        emailTextField.setText("");
        signTextField.setText("");

        authorsIdLabel.setText("");
    }

    private void clearTermPanel() {
        termHistoryVersionLabel.setText("");
        //editorPane1.setText("");
        htmlEditorPane.getGraphics();
        htmlEditorPane.setText("");
        textField1.setText("");
        actualTermHistoryVersionLabel.setText("");
        isSignedCheckBox.setSelected(false);
        //versesTermLabel.setText("");
        termDetailsIdLabel.setText("");


    }

    private void clickOnTagTable() {
        if (tagsModel.getRowCount() != 0) {
            for (int i = tagsModel.getRowCount() - 1; i > -1; i--) {
                tagsModel.removeRow(i);
            }
        }
        //tagsSettingsList.setEnabled(false);
        tagsNameField.setText(tagsSettingsList.getSelectedValue().toString());
        Long tagId = apiConnector.getTagId(tagsSettingsList.getSelectedValue().toString());
        Tag tag = apiConnector.getTag(apiConnector.getTagId(tagsSettingsList.getSelectedValue().toString()));
        String[] rgbFromDatabase = tag.getIconName().split("-");
        rgbFromDatabase[2] = rgbFromDatabase[2].substring(0, rgbFromDatabase[2].indexOf("."));
        String tagColorName = tag.getIconName().substring(0, tag.getIconName().indexOf("."));
        Color color = new Color(Integer.valueOf(rgbFromDatabase[0]), Integer.valueOf(rgbFromDatabase[1]), Integer.valueOf(rgbFromDatabase[2]));
        selectedTagColor = color;
        selectedTagColorName = selectedTagColor.getRed() + "-" + selectedTagColor.getGreen() + "-" + selectedTagColor.getBlue();
        String tagIconPath = getColorTagIcon(color, tagColorName);
        tagIconLabel.setIcon(new ImageIcon(tagIconPath));
        List<Term> terms;
        List<Term> termsMatched;

            terms  = apiConnector.getAllTerm();
            termsMatched = apiConnector.getTermsMatchedToTag(tag.getId());

        for (Term t : terms
        ) {
            Boolean isTagMarksTerm = false;
            for (Term tt: termsMatched
                 ) {
                if (tt.getId()== t.getId())
                    isTagMarksTerm = true;
            }
            tagsModel.addRow(new Object[]{isTagMarksTerm, t.getId().intValue(), t.getTitle()});
        }
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(termsTagsTable.getModel());
        termsTagsTable.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();

        int columnIndexToSort = 0;
        sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));

        sorter.setSortKeys(sortKeys);
        sorter.sort();

        try {
            Thread.currentThread().sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //tagsSettingsList.setEnabled(true);
    }

    private String getColorTagIcon(Color selectedTagColor, String selectedTagColorName) {
        BufferedImage bufferedImage = new BufferedImage(12, 12, BufferedImage.TYPE_INT_RGB);
        //imageIcons.add(new ImageIcon("src/main/resources/img/tags/"+tag.getIconName()));

        /*String path = getClass().getResource("/img/tags/0-0-0.png").getPath();
        path = path.replace("%20"," ");
        path = path.substring(path.indexOf("C:"));
        path     = path.substring(0,path.length()-9);*/
        String path = ConfigManager.getTagsFolder();
        File iconFile = new File(path + selectedTagColorName + ".png");
        if (!iconFile.exists()) {
            // System.out.println("mamy to");
            //System.out.println(path);

            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.setColor(selectedTagColor);
            g2d.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
            //tagIconLabel.setIcon();
            try {
                if (ImageIO.write(bufferedImage, "png", new File(path + selectedTagColorName + ".png"))) {
                    //System.out.println("-- saved");
                    return path + selectedTagColorName + ".png";
                }
            } catch (IOException exception) {
                // TODO Auto-generated catch block
                exception.printStackTrace();
            }

        }
        return path + selectedTagColorName + ".png";
    }


    private void renderTagsJList() {

        ArrayList<String> tagsStrings = apiConnector.getAllTags();
        DefaultListModel listModel = (DefaultListModel) tagsSettingsList.getModel();

        listModel.removeAllElements();
        for (String s : tagsStrings
        ) {
            listModel.addElement(s);
        }

    }

    private void settingsCardClear() {
        subcategoryErrorLabel.setText("");
        subcategoryTextField.setText("");
        tagsNameField.setText("");
        DefaultListModel defaultListModel = (DefaultListModel) allStatusesSettingsList.getModel();
        DefaultListModel defaultListModel1 = (DefaultListModel) chosenStatusesSettingsList.getModel();
        defaultListModel.removeAllElements();
        defaultListModel1.removeAllElements();
        subcategorySettingsList.clearSelection();
        selectedTagColorName = null;
        tagIconLabel.setIcon(null);


    }

    private void renderSubcategoriesJList() {
        DefaultListModel listModel = (DefaultListModel) subcategorySettingsList.getModel();
        listModel.removeAllElements();
        ArrayList<String> strings = apiConnector.getAllSubcategoriesString();
        for (String s : strings
        ) {
            listModel.addElement(s);
        }

    }

    private void prepareTermForm(Boolean isNewTerm) {
        try {
            clearTermPanel();
            ArrayList<String> tagsName = apiConnector.getAllTags();

            renderTermsFilters.setTagsJList(tagsTermJList, tagsName);

            if (tags1) {
                renderTermsFilters.setJListCheckBoxFeatures(tagsTermJList, 1);
                tags1 = false;
            }

            // Wczytanie wszystkich statusów

            String subcategoryString = "Propozycja nowego hasła";
            //Wczytanie statusów dla podkategorii
            ArrayList<String> statusesOfSubcategory = apiConnector.getStatusesOfSubcategory(subcategoryString);


            if (isNewTerm)
                renderTermsFilters.setTagsJList(statusesTermJList, statusesOfSubcategory);

            if (!statusesWereSet) {
                statusesWereSet = true;
                renderTermsFilters.setJListCheckBoxFeatures(statusesTermJList, 0);
                statusesTermJList.addMouseListener(checkListItemMouseListenerStatuses);
            }


        } catch (Exception ex) {

            System.out.println(ex.getMessage());
            logoutBecauseOfError();
        }
        ArrayList<Author> authors = apiConnector.getAllAuthorsObject();
        // ArrayList<String> authors = apiConnector.getAllAuthors();
        TableColumnModel tableColumnModel = authorsTable.getColumnModel();


        if (authorsTableModelInTermDetails.getRowCount() != 0) {
            for (int i = authorsTableModelInTermDetails.getRowCount() - 1; i > -1; i--) {
                authorsTableModelInTermDetails.removeRow(i);
            }
        }


        for (Author s : authors
        ) {
            Float idAuthor = s.getId();
            authorsTableModelInTermDetails.addRow(new Object[]{idAuthor.intValue(), false, s.getName() + " " + s.getSurname(), null});
        }
        tableColumnModel.getColumn(0).setMaxWidth(40);
        tableColumnModel.getColumn(1).setMaxWidth(40);
        tableColumnModel.getColumn(3).setPreferredWidth(30);
    }

    private void fillDataToTermForm(Term term, boolean isEditable, Integer termId) {
        //System.out.println(term.toString());
        textField1.setText(term.getTitle());
        isSignedCheckBox.setSelected(term.getSigned());
        actualTermHistoryVersionLabel.setText("" + term.getActualVersion().intValue());
        termDetailsIdLabel.setText("ID: " + term.getId().intValue());
        fillTermAuthorTable(termId);
        fillTermTagTable(termId);
        fillActualTermHistoryStatusAndContent(termId);

        categoryComboBox.setSelectedItem(term.getCategory());
        subcategoryComboBox.setSelectedItem(term.getSubcategory());
        getStatusesForSubcategoryButton.setVisible(true);
        updateTermButton.setVisible(true);
        /*if(subcategoryComboBox.getSelectedIndex()!=0)
            renderTermsFilters.setTagsJList(statusesTermJList, apiConnector.getStatusesOfSubcategory(subcategoryComboBox.getSelectedItem().toString()));*/
    }

    private void fillActualTermHistoryStatusAndContent(Integer termId) {
        Term term = apiConnector.getTerm(termId);
        TermHistory termHistory = apiConnector.getActualTermHistoryOfTerm(termId);
        ArrayList<String> statuses = apiConnector.getStatusesOfTerm(termId);
        String actualStatus = apiConnector.getStatusOfTermHistory(termHistory.getId());
        editedTermHistoryVersionLabel.setText("" + termHistory.getVersion().intValue());
        if (statuses.size() > 0) {
            CheckListItem[] checkListItems = new CheckListItem[statuses.size()];
            for (int i = 0; i < statuses.size(); i++) {
                if (statuses.get(i).equals(actualStatus))
                    checkListItems[i] = new CheckListItem(statuses.get(i), true);
                else
                    checkListItems[i] = new CheckListItem(statuses.get(i), false);

            }
            //statusesTermJList.setSelectionModel(new NoSelectionModel());
            statusesTermJList.setListData(checkListItems);
            //statusesTermJList.removeMouseListener(checkListItemMouseListenerStatuses);

            //renderTermsFilters.setTagsJList(statusesTermJList,statuses);

        }
        termHistoryVersionLabel.setText("Numer widocznej wersji: '" + termHistory.getVersion().intValue() + "'");
        htmlEditorPane.setText(termHistory.getContent());
    }

    private void fillTermTagTable(Integer termId) {

        for (int i = 0; i < tagsTermJList.getModel().getSize(); i++) {
            CheckListItem checkListItem = (CheckListItem) tagsTermJList.getModel().getElementAt(i);
            checkListItem.setSelected(apiConnector.isTagMarksTerm(apiConnector.getTagId(checkListItem.toString()), termId.longValue()));
        }
    }

    private void fillTermAuthorTable(Integer integerId) {
        ArrayList<Float> authors = apiConnector.getAllAuthorsIdsFloat();
        for (Float f : authors
        ) {
            Author author = apiConnector.getAuthor(f.intValue());
            String authorFullName = author.getName() + " " + author.getSurname();
            Float authorId = author.getId();
            Long isAuthorAssignToTerm = apiConnector.isAuthorAssignToTerm(f, integerId);

            if (isAuthorAssignToTerm >= -0L)

                authorsTableModelInTermDetails.addRow(new Object[]{authorId.intValue(), true, authorFullName, isAuthorAssignToTerm});
            else
                authorsTableModelInTermDetails.addRow(new Object[]{authorId.intValue(), false, authorFullName, null});

        }
    }

    private void hideButtonsAndRefreshViewAfterAuthorsAction() {
        renderAuthorTable();
        deleteAuthorButton.setVisible(false);
        updateAuthorButton.setVisible(false);
        authorsIdLabel.setText("ID:");
        nameTextField.setText("");
        surnameTextField.setText("");
        emailTextField.setText("");
        signTextField.setText("");
    }

    private void renderAuthorTable() {
        if (am.getRowCount() != 0) {
            for (int i = am.getRowCount() - 1; i > -1; i--) {
                am.removeRow(i);
            }
        }
        TableColumnModel tableColumnModel = authorsMenageTable.getColumnModel();
        tableColumnModel.getColumn(0).setPreferredWidth(30);
        tableColumnModel.getColumn(1).setPreferredWidth(150);
        tableColumnModel.getColumn(2).setPreferredWidth(150);
        tableColumnModel.getColumn(3).setPreferredWidth(200);
        tableColumnModel.getColumn(4).setPreferredWidth(200);
        tableColumnModel.getColumn(5).setPreferredWidth(150);
        try {
            ArrayList<Author> authors = apiConnector.getAllAuthorsObject();
            for (Author a : authors
            ) {
                Float id = a.getId();
                Integer howManyTerms = apiConnector.howManyTerms(id.intValue());
                am.addRow(new Object[]{id.intValue(), a.getName(), a.getSurname(), a.getSign(), a.getEmail(), howManyTerms});
            }
        } catch (Exception ex) {
            logoutBecauseOfError();
            ex.printStackTrace();
        }


    }

    private void logoutBecauseOfError() {
        backToMainPanel();
        ConfigManager.setLoggedUser("");
        ConfigManager.setJwtToken("");
        applicationFrame.setVisible(false);
        mainController.setLoginFrameController(new LoginFrameController(mainController));
        mainController.getLoginFrameController().showMainFrameWindow();
        if(frame!=null){
            frame.dispose();
            termsTableShown= false;

        }
    }

    private void generateLists(List<String> myList, JList myJList) {
        for (int i = 0; i < myJList.getModel().getSize(); i++) {
            CheckListItem checkListItem = (CheckListItem) myJList.getModel().getElementAt(i);
            if (checkListItem.isSelected()) {
                myList.add(checkListItem.toString());
            }
        }

    }

    private void hideFiltersSearchPanels() {
        addTermButton.setVisible(false);
        searchButton.setVisible(false);
        getStatusesForSubcategoryButton.setVisible(false);
        updateTermButton.setVisible(false);
        cardLayout2.show(topDetailsPanel, topDetailsPanelBlank.getName());
    }

    private void renderTermTable() {
        tagsStrings.clear();
        termsErrorLabel.setText("");
        //cardLayout1.show(bottomDetailsPanel, bottomDetailsTermsPanel.getName());

        /*if(tm.getRowCount() !=0){
           for(int i = tm.getRowCount() -1 ; i > -1 ; i--){
              tm.removeRow(i);
           }
       }*/

        dataProvider = createDataProvide();
        PaginatedTableDecorator<TermTable> paginatedDecorator = PaginatedTableDecorator.decorate(termsTable,
                dataProvider, new int[]{5, 10, 20, 50, 75, 100}, 50);
        JFrame frame = createFrame();
        paginatedDecorator.getContentPanel();
        frame.add(paginatedDecorator.getContentPanel());
        frame.setLocationRelativeTo(null);
        termsTableShown=true;
        frame.setVisible(true);

    }

    private static JFrame createFrame() {
         frame = new JFrame("Hasła");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 600));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                termsTableShown = false;
            }
        });
        return frame;
    }

    private PaginationDataProvider<TermTable> createDataProvide() {
        int iteratorTollTip = 0;
        list.clear();

        Long LpLong = 1L;
        for (Term t : apiConnector.getResponseList()) {

            Integer counter = 0;
            //Object[] terms = {"Lp", "Id", "Tytuł", "Ilość wersji", "Aktualna wersja", "Autorzy", "Podpis", "Tagi"};
            /*for (TermHistory t1 : t.getTermHistories()) {
                counter++;
            }*/
            //tm.setColumnIdentifiers(terms);

            /*ImageIcon icon = new ImageIcon("src/main/resources/img/tags/tag-black.png");
            ImageIcon icon2 = new ImageIcon("src/main/resources/img/tags/tag-blue.png");*/




            List<Tag> tagList = t.getTags();
            List<ImageIcon> imageIcons = new ArrayList<>();

            String tagString = "";
            if (!tagList.isEmpty()) {
                for (Tag tag : tagList) {

                    String[] rgbFromDatabase = tag.getIconName().split("-");
                    rgbFromDatabase[2] = rgbFromDatabase[2].substring(0, rgbFromDatabase[2].indexOf("."));
                    Color color = new Color(Integer.valueOf(rgbFromDatabase[0]), Integer.valueOf(rgbFromDatabase[1]), Integer.valueOf(rgbFromDatabase[2]));
                    String tagColorName = tag.getIconName().substring(0, tag.getIconName().indexOf("."));
                    //imageIcons.add(new ImageIcon("src/main/resources/img/tags/"+tag.getIconName()));
                    String iconPath = getColorTagIcon(color, tagColorName);
                    imageIcons.add(new ImageIcon(iconPath));

                }

            }
            // Wstawianie Authorów
           /* List<Author> authorList = apiConnector.getAuthorsOfTerm(t.getId());
            String authorsToTable = "";
            Boolean isFirst = true;
            for (Author a : authorList
            ) {
                if (isFirst) {
                    authorsToTable += a.getName() + " " + a.getSurname();
                    isFirst = false;
                } else
                    authorsToTable += ", " + a.getName() + " " + a.getSurname();
            }*/

            //Koniec wstawiania

            TagIcon tagIcon = new TagIcon(imageIcons);

             //tm.addRow(new Object[]{tm.getRowCount()+1,t.getId(),t.getTitle(),counter,t.getActualVersion(),authorsToTable,apiConnector.isSigned(t.getId().intValue()),tagIcon});
            TermTable termTable = new TermTable();
           // Integer lp  = tm.getRowCount()+1;

            termTable.setLp(LpLong++);
            termTable.setId(t.getId());
            //termTable.setTitle(t.getTitle());
            termTable.setTitle(t.getTitle());
            //termTable.setVersionCount(counter.longValue());
            //termTable.setActualVersion(t.getActualVersion());
            //termTable.setAuthors(authorsToTable);
            //termTable.setAuthors("");
            //termTable.setSigned(apiConnector.isSigned(t.getId().intValue()));
            termTable.setTags(tagIcon);


            if (!tagList.isEmpty()) {
                for (Tag tag : tagList) {
                    tagString += tag.getName() + ", ";
                }
            }

            tagsStrings.add(iteratorTollTip, tagString);
            //System.out.println(tagsStrings.get(iteratorTollTip));
            iteratorTollTip++;
            list.add(termTable);
        }
        TableColumnModel tableModel = termsTable.getColumnModel();
        tableModel.getColumn(0).setPreferredWidth(100);
        tableModel.getColumn(0).setMaxWidth(100);
        tableModel.getColumn(1).setPreferredWidth(450);
        tableModel.getColumn(1).setMaxWidth(450);
        tableModel.getColumn(2).setPreferredWidth(450);
       /* tableModel.getColumn(3).setPreferredWidth(450);*/
        /*tableModel.getColumn(3).setMaxWidth(30);
        tableModel.getColumn(4).setPreferredWidth(30);
        tableModel.getColumn(4).setMaxWidth(30);
        tableModel.getColumn(5).setPreferredWidth(145);
        tableModel.getColumn(6).setPreferredWidth(25);
        tableModel.getColumn(6).setMaxWidth(25);
        tableModel.getColumn(7).setPreferredWidth(54);*/

        termsErrorLabel.setText("Znaleziono " + apiConnector.getResponseList().size() + " haseł.");

        return new PaginationDataProvider<TermTable>() {
            @Override
            public int getTotalRowCount() {
                return list.size();
            }

            @Override
            public List<TermTable> getRows(int startIndex, int endIndex) {
                return list.subList(startIndex, endIndex);
            }
        };
    }


    private void backToMainPanel() {


        cardLayout.show(bottomPropertiesPanel, bottomPropertiesBlankPanel.getName());
        hideFiltersSearchPanels();
        cardLayout1.show(bottomDetailsPanel, bottomDetailsBlankPanel.getName());
        iconLabelFlag = false;
        list1.clearSelection();
        addAuthorButton.setVisible(false);
        updateAuthorButton.setVisible(false);
        deleteAuthorButton.setVisible(false);
        termsList.clearSelection();
        authorsList.clearSelection();
        settingsList.clearSelection();

        /*if(termsList.getValueIsAdjusting()){

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    termsList.clearSelection();
                }
            });
        }*/
        iconLabelFlag = true;
    }

}



package gui_swing.ui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import gui_swing.ui.model.*;
import gui_swing.ui.model.filters.*;
import gui_swing.ui.view.ApplicationFrame;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

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

    private Integer lettersOnVerse = 55;

    private JList categoryJList;
    private  JList subcategoryJList;
    private  JList tagsJList;
    private  JList statusesJList;

    private JButton searchButton;
    private JLabel iconLabel;
    private JLabel loggedUserLabel;
    private JLabel logoutLabel;
    private JLabel exitLabel;
    private JPanel bottomDetailsTermsPanel;
    private JTable termsTable;
    private JPanel bottomDetailsBlankPanel;
    private JPanel bottomDetailsPanel;
    private boolean iconLabelFlag= true;
    private JPanel bottomDetailsTermPanel;
    ApiConnector apiConnector = new ApiConnector();
    private DefaultTableModel tm;
    String[] headersStr =  {"Id", "Tytuł", "Ilość wersji", "Aktualna wersja", "Autorzy", "Podpis"};
   // private TermTableModel  termTableModel = new TermTableModel(headersStr,null) ;
    CardLayout cardLayout1;
    CardLayout cardLayout;
    CardLayout cardLayout2;
    private ArrayList<String> tagsStrings ;
    private RenderTermsFilters renderTermsFilters;
    private JPanel termErrorLabelPanel;
    private JButton addTermButton;
    Boolean statusesWereSet = false;
    private JLabel versesTermLabel;

    private JEditorPane editorPane1;


    private JTable authorsTable;
    private JList tagsTermJList;
    private JList statusesTermJList;


    private JComboBox categoryComboBox;
    private JComboBox subcategoryComboBox;
    private DefaultTableModel tm1;
    private JTextField textField1;
    private JCheckBox isSignedCheckBox;

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
        topDetailsPanelFilters= applicationFrame.getTopDetailsPanelFilters();
        categoryJList = applicationFrame.getCategoryJList();
        subcategoryJList = applicationFrame.getSubcategoryJList();
        tagsJList=applicationFrame.getTagsJList();
        statusesJList = applicationFrame.getStatusesJList();

        //termTableModel= applicationFrame.getTermTableModel();
        bottomDetailsTermPanel = applicationFrame.getBottomDetailsTermPanel();
        cardLayout1 = (CardLayout) (bottomDetailsPanel.getLayout());
        cardLayout1.show(bottomDetailsPanel, bottomDetailsBlankPanel.getName());
        cardLayout = (CardLayout) (bottomPropertiesPanel.getLayout());
        cardLayout.show(bottomPropertiesPanel, bottomPropertiesBlankPanel.getName());
        cardLayout2 = (CardLayout)(topDetailsPanel.getLayout());
        cardLayout2.show(topDetailsPanel,topDetailsPanelBlank.getName());
        termsTable = applicationFrame.getTermsTable();
        tm= (DefaultTableModel) termsTable.getModel();
        tagsStrings = applicationFrame.getTagsStrings();
        searchButton = applicationFrame.getSearchButton();
        termsErrorLabel = applicationFrame.getTermsErrorLabel();
        termErrorLabelPanel = applicationFrame.getTermErrorLabelPanel();
        topTermDetailsPanel =applicationFrame.getTopTermDetailsPanel();
        addTermButton = applicationFrame.getAddTermButton();


          authorsTable = applicationFrame.getAuthorsTable();
          tagsTermJList = applicationFrame.getTagsTermJList();
         statusesTermJList = applicationFrame.getStatusesTermJList();
         categoryComboBox = applicationFrame.getCategoryComboBox();
         subcategoryComboBox = applicationFrame.getSubcategoryComboBox();
         getStatusesForSubcategoryButton = applicationFrame.getGetStatusesForSubcategoryButton();
         versesTermLabel = applicationFrame.getVersesTermLabel();
        editorPane1 = applicationFrame.getEditorPane1();
        tm1 = (DefaultTableModel) authorsTable.getModel();
        authorsTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        textField1 = applicationFrame.getTextField1();
        isSignedCheckBox = applicationFrame.getIsSignedCheckBox();










        backToMainPanel();
        loggedUserLabel= applicationFrame.getLoggedUserLabel();
        iconLabel = applicationFrame.getIconLabel();
        exitLabel= applicationFrame.getExitLabel();
        logoutLabel = applicationFrame.getLogoutLabel();
        termsList = applicationFrame.getTermsList();
        renderTermsFilters = new RenderTermsFilters(new JList[]{applicationFrame.getCategoryJList(),applicationFrame.getSubcategoryJList(),applicationFrame.getStatusesJList()},1);
        termsTable.getTableHeader().setReorderingAllowed(false);

    }

    private void comboboxFill(JComboBox comboBox, ArrayList<String> stringValuesToInsert) {
    DefaultComboBoxModel defaultComboBoxModel =      (DefaultComboBoxModel)comboBox.getModel();
    defaultComboBoxModel.removeAllElements();
    comboBox.addItem("");
        for (String s: stringValuesToInsert
             ) {
            comboBox.addItem(s);
        }
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
                if(e.getValueIsAdjusting()) {
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
                if(e.getValueIsAdjusting()){
                    if (iconLabelFlag) {
                        switch (termsList.getSelectedValue().toString()) {
                            case "Pokaż wszystkie":
                                //System.out.println( bottomDetailsTermsPanel.getName());
                                hideFiltersSearchPanels();
                                try {
                                    apiConnector.getAllTerm();
                                }catch (Exception ex){
                                    logoutBecauseOfError();
                                }
                                renderTermTable();
                                break;
                            case "Pokaż według filtrów":
                                hideFiltersSearchPanels();
                               // System.out.println( bottomDetailsBlankPanel.getName());
                                cardLayout1.show(bottomDetailsPanel, bottomDetailsBlankPanel.getName());
                                searchButton.setVisible(true);
                                JList[] jlist = new JList[3];
                                jlist[0]=categoryJList;
                                jlist[1]=subcategoryJList;
                                jlist[2]=statusesJList;
                                try {
                                    ArrayList<String> tagsName = apiConnector.getAllTags();

                                    renderTermsFilters.setTagsJList(tagsJList, tagsName);
                                    if(tags2) {
                                        renderTermsFilters.setJListCheckBoxFeatures(tagsJList, 1);
                                        tags2 = false;
                                    }

                                    cardLayout2.show(topDetailsPanel, topDetailsPanelFilters.getName());
                                }catch (Exception ex){

                                    System.out.println( ex.getMessage());
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
                                cardLayout1.show(bottomDetailsPanel,bottomDetailsTermPanel.getName());

                                addTermButton.setVisible(true);

                                try {
                                    ArrayList<String> tagsName = apiConnector.getAllTags();

                                    renderTermsFilters.setTagsJList(tagsTermJList, tagsName);

                                    if(tags1) {
                                        renderTermsFilters.setJListCheckBoxFeatures(tagsTermJList, 1);
                                        tags1 = false;
                                    }

                                    // Wczytanie wszystkich statusów
                                    ArrayList<String> allStatuses = apiConnector.getAllStatusesString();
                                    String subcategoryString = "Propozycja nowego hasła";
                                    //Wczytanie statusów dla podkategorii
                                    ArrayList<String> statusesOfSubcategory = apiConnector.getStatusesOfSubcategory(subcategoryString);



                                    renderTermsFilters.setTagsJList(statusesTermJList, statusesOfSubcategory);
                                    if(!statusesWereSet) {
                                        statusesWereSet = true;
                                        renderTermsFilters.setJListCheckBoxFeatures(statusesTermJList, 0);
                                        statusesTermJList.addMouseListener(new MouseAdapter() {
                                            @Override
                                            public void mouseClicked(MouseEvent event) {
                                                JList list = (JList) event.getSource();
                                                int index = list.locationToIndex(event.getPoint());// Get index of item
                                                // clicked
                                                for (int i = 0; i < list.getModel().getSize(); i++) {
                                                    CheckListItem item = (CheckListItem) list.getModel()
                                                            .getElementAt(i);
                                                    item.setSelected(false);
                                                    list.repaint(list.getCellBounds(i, i));
                                                    //System.out.println(item.isSelected());

                                                }
                                                CheckListItem item = (CheckListItem) list.getModel()
                                                        .getElementAt(index);
                                                item.setSelected(true); // Toggle selected state
                                                list.repaint(list.getCellBounds(index, index));// Repaint cell
                                            }
                                        });

                                    }


                                }catch (Exception ex){

                                    System.out.println( ex.getMessage());
                                    logoutBecauseOfError();
                                }

                                ArrayList<String> authors = apiConnector.getAllAuthors();
                                TableColumnModel tableColumnModel = authorsTable.getColumnModel();



                                if(tm1.getRowCount() !=0){
                                    for(int i = tm1.getRowCount() -1 ; i > -1 ; i--){
                                        tm1.removeRow(i);
                                    }
                                }


                                for (String s: authors
                                     ) {
                                    tm1.addRow(new Object[]{false,s,null});
                                }
                                tableColumnModel.getColumn(0).setPreferredWidth(10);
                                tableColumnModel.getColumn(2).setPreferredWidth(30);
                                comboboxFill(categoryComboBox,apiConnector.getAllCategoriesString());
                                comboboxFill(subcategoryComboBox,apiConnector.getAllSubcategoriesString());
                                subcategoryComboBox.setSelectedIndex(5);
                                cardLayout2.show(topDetailsPanel,topTermDetailsPanel.getName());

                                break;
                        }
                    }
                }
            }
        });

        getStatusesForSubcategoryButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(subcategoryComboBox.getSelectedIndex()!=0)
                    renderTermsFilters.setTagsJList(statusesTermJList, apiConnector.getStatusesOfSubcategory(subcategoryComboBox.getSelectedItem().toString()));
            }
        });

        termsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
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
        });
        termsTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    System.out.println(termsTable.getValueAt(termsTable.getSelectedRow(),1));
                }
            }
        });
       /* termsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                termsTable.setSelectionBackground(Color.RED);
            }
        });*/

        searchButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

               // ArrayList<String>[] searchMatrix= new ArrayList<String>[4];
                ListsF searchMatrix = new ListsF();
                CategoryF<String> categoriesList =  new CategoryF<String>();
                SubcategoryF <String> subcategoriesList =  new SubcategoryF<String>();
                TagF<String> tagsList =  new TagF<String>();
                StatusesF<String> statusesList =  new StatusesF<>();


                generateLists(categoriesList,  categoryJList );
                generateLists(subcategoriesList,subcategoryJList);
                generateLists(tagsList, tagsJList);
                generateLists( statusesList,  statusesJList);
                if(!(categoriesList.isEmpty() && subcategoriesList.isEmpty()&&tagsList.isEmpty()&&statusesList.isEmpty())) {
                    searchMatrix.setCategoryF(categoriesList);
                    searchMatrix.setSubcategoryF(subcategoriesList);
                    searchMatrix.setTagF(tagsList);
                    searchMatrix.setStatusesF(statusesList);

                    try {
                        apiConnector.getTermsByFilter(searchMatrix);
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                    }
                    if(!apiConnector.getEmpty())
                        renderTermTable();
                    else {
                        termsErrorLabel.setText("Żadne z haseł nie spełnia zadanych kryteriów.");
                        cardLayout1.show(bottomDetailsPanel, bottomDetailsTermsPanel.getName());

                        if (tm.getRowCount() != 0) {
                            for (int i = tm.getRowCount() - 1; i > -1; i--) {
                                tm.removeRow(i);
                            }
                        }
                    }
                }

               /* for (Object s: categoriesList
                     ) {
                    System.out.println(s);
                }System.out.println();
                for (Object s: subcategoriesList
                     ) {
                    System.out.println(s);
                }System.out.println();
                for (Object s: tagsList
                     ) {
                    System.out.println(s);
                }
                System.out.println();
                for (Object s: statusesList
                     ) {
                    System.out.println(s);
                }System.out.println();*/

            }
        });

        addTermButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (String.valueOf(categoryComboBox.getSelectedItem()) == "") {
                    versesTermLabel.setText("Hasło musi mieć ustawioną kategorie.");
                } else {


                    Term term = new Term();
                    TermHistory termHistory = new TermHistory();
                    termHistory.setVersion(1L);
                    term.setActualVersion(termHistory.getVersion());
                    term.setTitle(textField1.getText());
                    term.setSigned(isSignedCheckBox.isSelected());
                    termHistory.setContent(editorPane1.getText());
                    term.setCategory(String.valueOf(categoryComboBox.getSelectedItem()));
                    term.setSubcategory(String.valueOf(subcategoryComboBox.getSelectedItem()));
                    //System.out.println("a");
                    ArrayList<String> statuses = new ArrayList<>();
                    ArrayList<String> tags = new ArrayList<>();
                    ArrayList<String> authors = new ArrayList<>();
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
                    for (int i = 0; i < tm1.getRowCount(); i++) {
                        if ((boolean) tm1.getValueAt(i, 0))
                            authors.add(tm1.getValueAt(i, 1) + "|" + tm1.getValueAt(i, 2));
                    }
                    ArrayList<TermHistory> termHistories = new ArrayList<>();
                    termHistories.add(termHistory);

                    term.setTermHistories(termHistories);
                    term.setAuthors(authors);
                    term.setTagsString(tags);
                    term.setStatuses(statuses);

                    try {

                        versesTermLabel.setText(apiConnector.addNewTerm(term));
                        tagsStrings.clear();
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                        logoutBecauseOfError();
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
                logoutBecauseOfError();
            }
        });

        authorsTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

            }
        });

        editorPane1.getDocument().addDocumentListener(new DocumentListener(){
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
                Document doc = (Document)e.getDocument();
                int changeLength = e.getLength();
                Long sum =0L;
                for (int i = 0; i < tm1.getRowCount() ; i++) {
                    if( (boolean)tm1.getValueAt(i,0) && tm1.getValueAt(i,2)!=null){

                        sum+=(Long)tm1.getValueAt(i,2);
                    }
                }
                versesTermLabel.setText("Hasło ma aktualnie "+doc.getLength()+" znaków. Odpowiada to "+ getAnInt(doc,lettersOnVerse) +" pełnym wersetom.");
                if (getAnInt(doc, lettersOnVerse) >= sum) {
                    versesTermLabel.setText(versesTermLabel.getText() + " Długość hasłą przekroczyła zakontraktowaną ilość wersetów (" + sum + ")");
                } else {
                    versesTermLabel.setText(versesTermLabel.getText() + " Zakontraktowana ilość wersetów dla tego hasła to: (" + sum + ")");
                }

                /*System.out.println(
                        changeLength + " character" +
                                ((changeLength == 1) ? " " : "s ") +
                                action + doc.getProperty("name") + "." + newline +
                                "  Text length = " + doc.getLength() + newline);*/
            }

            private int getAnInt(Document doc , Integer lettersOnVerse) {
                return doc.getLength()/lettersOnVerse;
            }
        });
    }

    private void logoutBecauseOfError() {
        backToMainPanel();
        ConfigManager.setLoggedUser("");
        ConfigManager.setJwtToken("");
        applicationFrame.setVisible(false);
        mainController.setLoginFrameController(new LoginFrameController(mainController));
        mainController.getLoginFrameController().showMainFrameWindow();
    }

    private void generateLists(List<String> myList,  JList myJList) {
        for (int i = 0; i < myJList.getModel().getSize(); i++) {
            CheckListItem checkListItem= (CheckListItem) myJList.getModel().getElementAt(i);
            if(checkListItem.isSelected()){
                myList.add(checkListItem.toString());
            }
        }

    }

    private void hideFiltersSearchPanels() {
        addTermButton.setVisible(false);
        searchButton.setVisible(false);
        cardLayout2.show(topDetailsPanel, topDetailsPanelBlank.getName());
    }

    private void renderTermTable() {
        termsErrorLabel.setText("");
        cardLayout1.show(bottomDetailsPanel, bottomDetailsTermsPanel.getName());

        if(tm.getRowCount() !=0){
           for(int i = tm.getRowCount() -1 ; i > -1 ; i--){
              tm.removeRow(i);
           }
       }

        int iteratorTollTip = 0;
        for (Term t : apiConnector.getResponseList()) {

            int counter = 0;
            Object[] terms = {"Lp","Id", "Tytuł", "Ilość wersji", "Aktualna wersja", "Autorzy", "Podpis","Tagi"};
            for (TermHistory t1: t.getTermHistories()) {
                counter++;
            }
            tm.setColumnIdentifiers(terms);

            /*ImageIcon icon = new ImageIcon("src/main/resources/img/tags/tag-black.png");
            ImageIcon icon2 = new ImageIcon("src/main/resources/img/tags/tag-blue.png");*/



            TableColumnModel tableModel= termsTable.getColumnModel();
            tableModel.getColumn(0).setPreferredWidth(15);
            tableModel.getColumn(1).setPreferredWidth(15);
            tableModel.getColumn(2).setPreferredWidth(230);
            tableModel.getColumn(3).setPreferredWidth(25);
            tableModel.getColumn(4).setPreferredWidth(40);
            tableModel.getColumn(5).setPreferredWidth(145);
            tableModel.getColumn(6).setPreferredWidth(25);
             tableModel.getColumn(7).setPreferredWidth(30);

             List<Tag> tagList = t.getTags();
           List<ImageIcon> imageIcons= new ArrayList<>();

            String tagString = "";
            if(!tagList.isEmpty()){
                for (Tag tag: tagList) {

                   imageIcons.add(new ImageIcon("src/main/resources/img/tags/"+tag.getIconName()));

                }

            }
            // Wstawianie Authorów
            List<Author> authorList = apiConnector.getAuthorsOfTerm(t.getId());
            String authorsToTable ="";
            for (Author a: authorList
                 ) {
                authorsToTable += a.getName()+" "+ a.getSurname();
            }

            //Koniec wstawiania

            TagIcon tagIcon= new TagIcon(imageIcons);
            tm.addRow(new Object[]{tm.getRowCount()+1,t.getId(),t.getTitle(),counter,t.getActualVersion(),authorsToTable,t.getSigned(),tagIcon});

            if(!tagList.isEmpty()){
                for (Tag tag: tagList) {


                    tagString +=tag.getName() +", ";
                }

            }

            tagsStrings.add(iteratorTollTip,tagString);
            //System.out.println(tagsStrings.get(iteratorTollTip));
            iteratorTollTip++;


        }
        termsErrorLabel.setText("Znaleziono " + apiConnector.getResponseList().size() + " haseł.");
    }


    private void backToMainPanel() {


        cardLayout.show(bottomPropertiesPanel, bottomPropertiesBlankPanel.getName());
        hideFiltersSearchPanels();
        cardLayout1.show(bottomDetailsPanel, bottomDetailsBlankPanel.getName());
        iconLabelFlag=false;
        list1.clearSelection();
        /*if(termsList.getValueIsAdjusting()){

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    termsList.clearSelection();
                }
            });
        }*/
        iconLabelFlag=true;
    }

}






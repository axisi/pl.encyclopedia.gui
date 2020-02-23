package gui_swing.ui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import gui_swing.ui.model.*;
import gui_swing.ui.model.filters.*;
import gui_swing.ui.view.ApplicationFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.net.ConnectException;
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
    private JLabel termsErrorLabel;

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




        backToMainPanel();
        loggedUserLabel= applicationFrame.getLoggedUserLabel();
        iconLabel = applicationFrame.getIconLabel();
        exitLabel= applicationFrame.getExitLabel();
        logoutLabel = applicationFrame.getLogoutLabel();
        termsList = applicationFrame.getTermsList();
        renderTermsFilters = new RenderTermsFilters(new JList[]{applicationFrame.getCategoryJList(),applicationFrame.getSubcategoryJList(),applicationFrame.getStatusesJList()});

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
                                    cardLayout2.show(topDetailsPanel, topDetailsPanelFilters.getName());
                                }catch (Exception ex){

                                    System.out.println( ex.getMessage());
                                    logoutBecauseOfError();
                                }
                               //System.out.println(topDetailsPanel.getWidth());
                                break;
                            case "Pokaż według autorów":
                                hideFiltersSearchPanels();
                                break;
                        }
                    }
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
            tm.addRow(new Object[]{tm.getRowCount()+1,t.getId(),t.getTitle(),counter,t.getActualVersion(),authorsToTable,t.getIsSigned(),tagIcon});

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






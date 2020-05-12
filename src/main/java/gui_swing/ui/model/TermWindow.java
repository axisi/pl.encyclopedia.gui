package gui_swing.ui.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import gui_swing.ui.controller.ApplicationFrameController;
import gui_swing.ui.model.Listeners.MouseListeners;
import gui_swing.ui.model.Listeners.TermListeners;
import gui_swing.ui.model.tableModels.AuthorTermTableModel;
import gui_swing.ui.view.ApplicationFrame;
import net.atlanticbb.tantlinger.shef.HTMLEditorPane;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TermWindow {
    private ArrayList<String> tagsStrings = ApplicationFrameController.getApplicationFrame().getTagsStrings();
    public  List<TermTable> list = ApplicationFrameController.getApplicationFrame().getListOfTermTable();

    private JFrame frame;
    private JTable termsTable = ApplicationFrameController.getApplicationFrame().getTermsTable();
    private JFrame jFrame;
    private JPanel topPanel;
    private JPanel bottomBottomPanel;
    private JPanel bottomMainPanel;
    private JPanel bottomTopPanel;

    private JPanel topMainPanel;
    private JPanel textPanel;
    private JPanel topDetailsPanel;
    private JLabel detailsLabel;

    private JPanel topLeftPanel;
    private JPanel topCenterPanel;
    private JPanel topRightPanel;
    private JLabel authorsTableLabel;
    private JTable authorsTable;
    private JLabel tagsLabel;
    private JLabel statusesLabel;
    private JList tagsList;
    private JList statusesList;
    private JScrollPane authorsScrollPane;
    private JScrollPane tagsScrollPane;
    private JScrollPane statusesScrollPane;
    private Integer termId;
    private HTMLEditorPane htmlEditorPane;

    private JLabel idLabel;
    private JTextField titleTextField;
    private JComboBox categoryComboBox;
    private JComboBox subCategoryComboBox;
    private JCheckBox isSignedCheckBox;

    private JLabel actualVersionLabel;
    private JLabel editedVersionLabel;
    private  ApiConnector apiConnector;

    private  JButton updateTermButton;
    private JButton referencesButton;

    private ReferencesPanel referencesPanel;




    public TermWindow(Integer id){

        setTermId(id);
        prepareForm();
        fillForm();

    }

    private void fillForm() {
        apiConnector = new ApiConnector();
        ApplicationFrameController.comboboxFill(categoryComboBox, apiConnector.getAllCategoriesString());
        ApplicationFrameController.comboboxFill(subCategoryComboBox, apiConnector.getAllSubcategoriesString());
        Term term = apiConnector.getTerm(getTermId());

        ArrayList<String> tagsName = apiConnector.getAllTags();

        ApplicationFrameController.renderTermsFilters.setTagsJList(tagsList, tagsName);
        ApplicationFrameController.renderTermsFilters.setJListCheckBoxFeatures(tagsList, 1);

        idLabel.setText((term.getId().intValue())+"");
        term.setSigned(apiConnector.isSigned(termId));
        actualVersionLabel.setText(""+term.getActualVersion().intValue());
        DefaultTableModel tableModel = (DefaultTableModel) authorsTable.getModel();
        fillAuthorsTable(tableModel, apiConnector, termId);

        for (int i = 0; i < tagsList.getModel().getSize(); i++) {
            CheckListItem checkListItem = (CheckListItem) tagsList.getModel().getElementAt(i);
            checkListItem.setSelected(apiConnector.isTagMarksTerm(apiConnector.getTagId(checkListItem.toString()), termId.longValue()));
        }
        TermHistory termHistory = apiConnector.getActualTermHistoryOfTerm(termId);
        ApplicationFrameController.renderTermsFilters.setJListCheckBoxFeatures(statusesList, 0);
        statusesList.addMouseListener(new MouseListeners.CheckListItemMouseListenerStatuses());
        fillTermHistoryDetailsIntoForm(apiConnector, termId, editedVersionLabel, statusesList);

        editedVersionLabel.setText( termHistory.getVersion().intValue() + "");
        htmlEditorPane.setText(termHistory.getContent());
        titleTextField.setText(term.getTitle());
        categoryComboBox.setSelectedItem(apiConnector.getTermCategory(termId));
        subCategoryComboBox.setSelectedItem(apiConnector.getTermSubcategory(termId));

    }

    public static void fillTermHistoryDetailsIntoForm(ApiConnector apiConnector, Integer termId, JLabel editedVersionLabel, JList statusesList) {
        TermHistory termHistory = apiConnector.getActualTermHistoryOfTerm(termId);
        ArrayList<String> statuses = apiConnector.getStatusesOfTerm(termId);
        String actualStatus = apiConnector.getStatusOfTermHistory(termHistory.getId());
        editedVersionLabel.setText("" + termHistory.getVersion().intValue());
        if (statuses.size() > 0) {
            CheckListItem[] checkListItems = new CheckListItem[statuses.size()];
            for (int i = 0; i < statuses.size(); i++) {
                if (statuses.get(i).equals(actualStatus))
                    checkListItems[i] = new CheckListItem(statuses.get(i), true);
                else
                    checkListItems[i] = new CheckListItem(statuses.get(i), false);

            }
            //statusesTermJList.setSelectionModel(new NoSelectionModel());
            statusesList.setListData(checkListItems);
            //statusesTermJList.removeMouseListener(checkListItemMouseListenerStatuses);

            //renderTermsFilters.setTagsJList(statusesTermJList,statuses);

        }
    }

    public static void fillAuthorsTable(DefaultTableModel tableModel, ApiConnector apiConnector, Integer termId) {
        ArrayList<Float> authors = apiConnector.getAllAuthorsIdsFloat();
        for (Float f : authors
        ) {
            Author author = apiConnector.getAuthor(f.intValue());
            String authorFullName = author.getName() + " " + author.getSurname();
            Float authorId = author.getId();
            Long isAuthorAssignToTerm = apiConnector.isAuthorAssignToTerm(f, termId);

            if (isAuthorAssignToTerm >= -0L)

               tableModel.addRow(new Object[]{authorId.intValue(), true, authorFullName, isAuthorAssignToTerm});
            else
                tableModel.addRow(new Object[]{authorId.intValue(), false, authorFullName, null});

        }
    }

    private void prepareForm() {
        this.setFrame(new JFrame());
        this.setTopPanel(new JPanel());
        this.setTextPanel(new JPanel());
        this.setTopLeftPanel(new JPanel());
        this.setTopCenterPanel(new JPanel());
        this.setTopRightPanel(new JPanel());
        this.setTopMainPanel(new JPanel());
        this.setBottomBottomPanel(new JPanel());
        this.setBottomMainPanel(new JPanel());
        this.setBottomTopPanel(new JPanel());

        this.setTopDetailsPanel(new JPanel());

       /* topDetailsPanel.setBackground(Color.PINK);
        topLeftPanel.setBackground(Color.YELLOW);
        topCenterPanel.setBackground(Color.ORANGE);
        topRightPanel.setBackground(Color.BLUE);
        topPanel.setBackground(Color.RED);
        textPanel.setBackground(Color.GREEN);*/
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        jFrame.setUndecorated(false);
        jFrame.setResizable(true);
        //jFrame.setMinimumSize(new Dimension(300,300));
        jFrame.setLayout(new BorderLayout());
        topMainPanel.setLayout(new BorderLayout());
        jFrame.add(topMainPanel,BorderLayout.PAGE_START);
        jFrame.add(textPanel,BorderLayout.CENTER);
        topMainPanel.add(topPanel,BorderLayout.NORTH);

        topMainPanel.add(bottomMainPanel,BorderLayout.SOUTH);
        bottomMainPanel.setLayout(new BorderLayout());
        bottomMainPanel.add(bottomBottomPanel,BorderLayout.SOUTH);
        bottomMainPanel.add(bottomTopPanel,BorderLayout.NORTH);


        topPanel.setLayout(new GridLayout(1,4));
        topPanel.add(topDetailsPanel);
        topPanel.add(topLeftPanel);
        topPanel.add(topCenterPanel);
        topCenterPanel.setPreferredSize(new Dimension(300,150));
        topPanel.add(topRightPanel);
        //detailsPanel start
        topDetailsPanel.setLayout(new GridLayout(0,2));
        topDetailsPanel.add(new JLabel("ID:"));
        idLabel= new JLabel();
        topDetailsPanel.add(idLabel);
        topDetailsPanel.add(new JLabel("Nagłówek:"));
        titleTextField = new JTextField();
        topDetailsPanel.add(titleTextField);
        topDetailsPanel.add(new JLabel("Kategoria:"));
        categoryComboBox = new JComboBox();
        topDetailsPanel.add(categoryComboBox);

        subCategoryComboBox = new JComboBox();
        topDetailsPanel.add(new JLabel("Podkategoria:"));
        topDetailsPanel.add(subCategoryComboBox);
        topDetailsPanel.add(new JLabel("Czy hasło ma być podpisane?"));
        isSignedCheckBox = new JCheckBox();
        isSignedCheckBox.setOpaque(false);
        topDetailsPanel.add(isSignedCheckBox);
        topDetailsPanel.add(new JLabel("Wersja hasła oznaczona jako aktywna"));
        actualVersionLabel= new JLabel();
        topDetailsPanel.add(actualVersionLabel);
        topDetailsPanel.add(new JLabel("Wersja hasła aktualnie edytowana"));
        editedVersionLabel= new JLabel();
        topDetailsPanel.add(editedVersionLabel);



        //detailPanel end
        // authors Panel
        authorsTableLabel= new JLabel();
        authorsTableLabel.setText("Autorzy");
        topLeftPanel.setLayout(new BorderLayout());
        topLeftPanel.add(authorsTableLabel,BorderLayout.NORTH);

        Vector authorHeaders = new Vector();

        authorHeaders.addElement("Id");
        authorHeaders.addElement("Wybierz");
        authorHeaders.addElement("Imię i nazwisko");
        authorHeaders.addElement("Ilość wersetów");

        authorsTable = new JTable(new AuthorTermTableModel(authorHeaders,0));
        authorsTable.getTableHeader().setReorderingAllowed(false);
        //topLeftPanel.add(authorsTable,BorderLayout.CENTER);
        this.setAuthorsScrollPane(new JScrollPane(authorsTable));
        //authorsScrollPane.setMaximumSize(new Dimension(1000,300));
        authorsScrollPane.setPreferredSize(new Dimension(1000,150));
        topLeftPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        topLeftPanel.add(authorsScrollPane,BorderLayout.CENTER);
        // end of authors Panel
        //tags Panel
        tagsLabel = new JLabel("Tagi");
        topCenterPanel.setLayout(new BorderLayout());
        topCenterPanel.add(tagsLabel,BorderLayout.NORTH);
        tagsList=new JList();
        tagsScrollPane = new JScrollPane(tagsList);
        tagsScrollPane.setPreferredSize(new Dimension(500,150));
        topCenterPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        topCenterPanel.add(tagsScrollPane,BorderLayout.CENTER);
        //end of tags Panel
        //start of statuses Panel
        statusesLabel = new JLabel("Ścieżka statusów hasła (zanzaczony aktualny etap)");
        topRightPanel.setLayout(new BorderLayout());
        topRightPanel.add(statusesLabel,BorderLayout.NORTH);
        statusesList=new JList();
        statusesScrollPane= new JScrollPane(statusesList);
        statusesScrollPane.setPreferredSize(new Dimension(500,150));
        topRightPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        topRightPanel.add(statusesScrollPane,BorderLayout.CENTER);
        //end of statuses Panel
        // buttonsPanel start
        updateTermButton = new JButton("Aktualizuj hasło");
        bottomTopPanel.setLayout(new GridLayout(0,12));
        updateTermButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long termHistoryId;
                String category = categoryComboBox.getModel().getSelectedItem().toString();
                String subcategory = subCategoryComboBox.getModel().getSelectedItem().toString();
                Term oldTerm = apiConnector.getTerm(termId);
                Term newTerm = new Term(termId.longValue(), isSignedCheckBox.isSelected(), titleTextField.getText(), category, subcategory);
                TermHistory termHistory = new TermHistory();
                Long actualTermHistoryVersion = Long.valueOf(actualVersionLabel.getText());
                for (TermHistory t : oldTerm.getTermHistories()
                ) {
                    if (t.getVersion() == actualTermHistoryVersion) {
                        termHistoryId = t.getId();
                        termHistory = apiConnector.getTermHistory(termHistoryId);
                    }
                }
                termHistory.setContent(NCRConverter.convertNcrToText(htmlEditorPane.getText()));
                packFormDataToEntity(termHistory, newTerm,statusesList,tagsList,authorsTable);
                try {
                    Integer termId1 = apiConnector.updateTerm(newTerm);
                    new TermWindow(termId);
                    jFrame.dispose();

                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();

                }
            }
        });
        bottomTopPanel.add(updateTermButton);

        //buttonsPanel end
        // fullTextPanel
        bottomBottomPanel.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));
        bottomBottomPanel.setLayout(new BorderLayout());
        JTextField fullTextSearchField = new JTextField();
        fullTextSearchField.setPreferredSize(new Dimension(250,20));
        JButton fullTextSearchButton = new JButton(new ImageIcon("src/main/resources/img/arrows/search.png"));
        fullTextSearchButton.setPreferredSize(new Dimension(30,30));
        //JRadioButton findInCurrentText = new JRadioButton("W tym haśle",null,true);
        JRadioButton findInOtherTerms= new JRadioButton("Wszystkie..,",null,true);
        JRadioButton findInOtherTermsAllVersions = new JRadioButton("Wszystkie*...");
        //findInCurrentText.setToolTipText("Szukaj w tym haśle");
        findInOtherTerms.setToolTipText("Szukaj w innych hasłach");
        findInOtherTermsAllVersions.setToolTipText("Szukaj we wszystkich wersjach innych haseł");

        JPanel fullTextPanel = new JPanel(new FlowLayout());
        JLabel fullTextLabel = new JLabel();
        fullTextLabel.setForeground(Color.RED);
        fullTextPanel.add(fullTextLabel);
        //fullTextPanel.add(findInCurrentText);
        fullTextPanel.add(findInOtherTerms);
        fullTextPanel.add(findInOtherTermsAllVersions);
        fullTextPanel.add(fullTextSearchField);

        fullTextSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fullTextLabel.setText("");
                ArrayList<Long> idsOfFoundTerms= new ArrayList<>();

                //htmlEditorPane.setText(removeSearchTags());
                //htmlEditorPane.setText(apiConnector.getContentOfTerm(termId.longValue(),Long.valueOf(editedVersionLabel.getText())));
                if(findInOtherTerms.isSelected()||findInOtherTermsAllVersions.isSelected()){
                    if(fullTextSearchField.getText().length()>0){
                        String text = fullTextSearchField.getText();
                        if(findInOtherTerms.isSelected()){
                            idsOfFoundTerms.addAll(apiConnector.findTermsWitchAllVersionContentContains(text));
                        }else{
                            idsOfFoundTerms.addAll(apiConnector.findTermsWitchActualVersionContentContains(text));
                        }
                        if(idsOfFoundTerms.size()>0){
                            idsOfFoundTerms = ApplicationFrameController.removeDuplicates(idsOfFoundTerms);
                            idsOfFoundTerms.sort(Long::compareTo );
                            apiConnector.getTermsByFullText(idsOfFoundTerms);
                            renderTermTable();
                        }

                    }else{
                        fullTextLabel.setText("Szukana fraza nie może być pusta");
                    }
                }else{
                    fullTextLabel.setText("Wybierz jedną z opcji przeszukiwania.");
                }
            }
        });
        fullTextSearchButton.setToolTipText("Szukaj...");
        fullTextPanel.add(fullTextSearchButton);
        bottomBottomPanel.add(fullTextPanel,BorderLayout.EAST);
        // fullTextPanel
        // references
        referencesButton = new JButton("Zarządzaj odnośnikami");
        referencesButton.setToolTipText("Zarządzaj odnośnikami");
       //referencesButton.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
        bottomTopPanel.add(referencesButton);
        referencesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    referencesPanel = new ReferencesPanel(termId);
                    referencesPanel.setTitle(apiConnector.getTerm(termId).getTitle());
                    //referencesPanel.setPreferredSize(new Dimension(600,400));
                    referencesPanel.setMinimumSize(new Dimension(950,400));
                    referencesPanel.setVisible(true);
            }
        });
        //references

        textPanel.setLayout(new BorderLayout());
        htmlEditorPane= new HTMLEditorPane();
        //findInCurrentText.addActionListener(new TermListeners.RadioButtonsActionListener(findInCurrentText,findInOtherTerms,findInOtherTermsAllVersions));
        findInOtherTerms.addActionListener(new TermListeners.RadioButtonsActionListener(findInOtherTerms,findInOtherTermsAllVersions));
        findInOtherTermsAllVersions.addActionListener(new TermListeners.RadioButtonsActionListener(findInOtherTermsAllVersions,findInOtherTerms));
        textPanel.add(htmlEditorPane,BorderLayout.CENTER);
        jFrame.setMinimumSize(new Dimension(1000, 600));
        jFrame.setVisible(true);

    }

    private void renderTermTable() {
        if (ApplicationFrameController.frameWithTerms !=null){
            ApplicationFrameController.frameWithTerms.dispose();
        }
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
                List<String> imageStrings = new ArrayList<>();

                String tagString = "";
                if (!tagList.isEmpty()) {
                    for (Tag tag : tagList) {

                        String[] rgbFromDatabase = tag.getIconName().split("-");
                        rgbFromDatabase[2] = rgbFromDatabase[2].substring(0, rgbFromDatabase[2].indexOf("."));
                        Color color = new Color(Integer.valueOf(rgbFromDatabase[0]), Integer.valueOf(rgbFromDatabase[1]), Integer.valueOf(rgbFromDatabase[2]));
                        String tagColorName = tag.getIconName().substring(0, tag.getIconName().indexOf("."));
                        //imageIcons.add(new ImageIcon("src/main/resources/img/tags/"+tag.getIconName()));
                        String iconPath = ApplicationFrameController.getColorTagIcon(color, tagColorName);
                        imageIcons.add(new ImageIcon(iconPath));
                        imageStrings.add(tag.getName());

                    }

                }


                TagIcon tagIcon = new TagIcon(imageIcons,imageStrings);


                TermTable termTable = new TermTable();


                termTable.setLp(LpLong++);
                termTable.setId(t.getId());

                termTable.setTitle(t.getTitle());

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
        PaginationDataProvider<TermTable> dataProvider = new PaginationDataProvider<TermTable>() {
            @Override
            public int getTotalRowCount() {
                return list.size();
            }

            @Override
            public List<TermTable> getRows(int startIndex, int endIndex) {
                return list.subList(startIndex, endIndex);
            }

        };
        PaginatedTableDecorator<TermTable> paginatedDecorator = PaginatedTableDecorator.decorate(termsTable,
                dataProvider, new int[]{5, 10, 20, 50, 75, 100}, 50);
        ApplicationFrameController.frameWithTerms = createFrame();
        paginatedDecorator.getContentPanel();
        JPanel jPanel = new JPanel(new BorderLayout());
        JLabel jLabel = new JLabel(String.format("Znaleziono %s haseł.",apiConnector.getResponseList().size()));
        jPanel.add(jLabel,BorderLayout.EAST);
        frame.add(jPanel,BorderLayout.NORTH);
        ApplicationFrameController.frameWithTerms.add(paginatedDecorator.getContentPanel(),BorderLayout.CENTER);
        ApplicationFrameController.frameWithTerms.setLocationRelativeTo(null);

        ApplicationFrameController.frameWithTerms.setVisible(true);
    }

    private JFrame createFrame() {
        frame = new JFrame("Hasła");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 600));
        frame.setLayout(new BorderLayout());


        return frame;
    }




    private String findInTextAndMarkIt( String text) {
        String content = htmlEditorPane.getText();
        //content = NCRConverter.html2text(content);

        //content = "&#260; &#261; &#262; &#263; &#280; &#281; &#321; &#322; &#323; &#324; &#211; &#243; &#346; &#347; &#377; &#378; &#379; &#380;";
        content = NCRConverter.convertNcrToText(content);

        //content = content.replaceAll("(text)(?![^<]*>|[^<>]*</)","<style=\"color: #bfff00\">"+text+"</style>");
         //content = content.replaceAll("("+text+")(?![^<]*>)","<font= size=\"7\" face=\"Times New Roman,serif\" color=\"#ed2fc4\">"+text+"</font>");
         content = content.replaceAll("("+text+")(?![^<]*>)","<t>"+text+"</t>");

        return content;
    }

    public static void packFormDataToEntity(TermHistory termHistory, Term term,JList statusesList,JList tagsList,JTable authorsTable) {
        ArrayList<String> statuses = new ArrayList<>();
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<String> authors = new ArrayList<>();
        if (statusesList.getModel().getSize() > 0)
            termHistory.setStatus(statusesList.getModel().getElementAt(0).toString());
        for (int i = 0; i < statusesList.getModel().getSize(); i++) {
            statuses.add(statusesList.getModel().getElementAt(i).toString());
            CheckListItem checkListItem = (CheckListItem) statusesList.getModel().getElementAt(i);
            if (checkListItem.isSelected())
                termHistory.setStatus(checkListItem.toString());
        }
        for (int i = 0; i < tagsList.getModel().getSize(); i++) {
            CheckListItem checkListItem = (CheckListItem) tagsList.getModel().getElementAt(i);
            if (checkListItem.isSelected())
                tags.add(checkListItem.toString());
        }
        for (int i = 0; i < authorsTable.getRowCount(); i++) {
            if ((boolean) authorsTable.getValueAt(i, 1)) {

                Integer numberOfVerses = 0;
                if (!(authorsTable.getValueAt(i, 3) == null)) {
                    Object numbersOfVersesLong = authorsTable.getValueAt(i, 3);
                    numberOfVerses = Integer.valueOf(numbersOfVersesLong.toString());
                }
                authors.add(authorsTable.getValueAt(i, 0) + "|" + numberOfVerses);
            }
        }
        ArrayList<TermHistory> termHistories = new ArrayList<>();
        termHistories.add(termHistory);

        term.setTermHistories(termHistories);
        term.setAuthors(authors);
        term.setTagsString(tags);
        term.setStatuses(statuses);
    }


    public JLabel getDetailsLabel() {
        return detailsLabel;
    }

    public void setDetailsLabel(JLabel detailsLabel) {
        this.detailsLabel = detailsLabel;
    }

    public JPanel getTopDetailsPanel() {
        return topDetailsPanel;
    }

    public void setTopDetailsPanel(JPanel topDetailsPanel) {
        this.topDetailsPanel = topDetailsPanel;
    }

    public HTMLEditorPane getHtmlEditorPane() {
        return htmlEditorPane;
    }

    public void setHtmlEditorPane(HTMLEditorPane htmlEditorPane) {
        this.htmlEditorPane = htmlEditorPane;
    }

    public JLabel getAuthorsTableLabel() {
        return authorsTableLabel;
    }

    public void setAuthorsTableLabel(JLabel authorsTableLabel) {
        this.authorsTableLabel = authorsTableLabel;
    }

    public JTable getAuthorsTable() {
        return authorsTable;
    }

    public void setAuthorsTable(JTable authorsTable) {
        this.authorsTable = authorsTable;
    }

    public JLabel getTagsLabel() {
        return tagsLabel;
    }

    public void setTagsLabel(JLabel tagsLabel) {
        this.tagsLabel = tagsLabel;
    }

    public JLabel getStatusesLabel() {
        return statusesLabel;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public void setStatusesLabel(JLabel statusesLabel) {
        this.statusesLabel = statusesLabel;
    }

    public JList getTagsList() {
        return tagsList;
    }

    public void setTagsList(JList tagsList) {
        this.tagsList = tagsList;
    }

    public JPanel getBottomBottomPanel() {
        return bottomBottomPanel;
    }

    public void setBottomBottomPanel(JPanel bottomBottomPanel) {
        this.bottomBottomPanel = bottomBottomPanel;
    }

    public JPanel getTopMainPanel() {
        return topMainPanel;
    }

    public void setTopMainPanel(JPanel topMainPanel) {
        this.topMainPanel = topMainPanel;
    }

    public JList getStatusesList() {
        return statusesList;
    }

    public void setStatusesList(JList statusesList) {
        this.statusesList = statusesList;
    }

    public JScrollPane getAuthorsScrollPane() {
        return authorsScrollPane;
    }

    public void setAuthorsScrollPane(JScrollPane authorsScrollPane) {
        this.authorsScrollPane = authorsScrollPane;
    }

    public JScrollPane getTagsScrollPane() {
        return tagsScrollPane;
    }

    public void setTagsScrollPane(JScrollPane tagsScrollPane) {
        this.tagsScrollPane = tagsScrollPane;
    }

    public JScrollPane getStatusesScrollPane() {
        return statusesScrollPane;
    }

    public void setStatusesScrollPane(JScrollPane statusesScrollPane) {
        this.statusesScrollPane = statusesScrollPane;
    }

    public JPanel getTopLeftPanel() {
        return topLeftPanel;
    }

    public void setTopLeftPanel(JPanel topLeftPanel) {
        this.topLeftPanel = topLeftPanel;
    }

    public JPanel getTopCenterPanel() {
        return topCenterPanel;
    }

    public void setTopCenterPanel(JPanel topCenterPanel) {
        this.topCenterPanel = topCenterPanel;
    }

    public JPanel getTopRightPanel() {
        return topRightPanel;
    }

    public void setTopRightPanel(JPanel topRightPanel) {
        this.topRightPanel = topRightPanel;
    }

    public List<TermTable> getList() {
        return list;
    }

    public void setList(List<TermTable> list) {
        this.list = list;
    }


    public JFrame getjFrame() {
        return jFrame;
    }

    public void setjFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public JLabel getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(JLabel idLabel) {
        this.idLabel = idLabel;
    }

    public JTextField getTitleTextField() {
        return titleTextField;
    }

    public void setTitleTextField(JTextField titleTextField) {
        this.titleTextField = titleTextField;
    }

    public JComboBox getCategoryComboBox() {
        return categoryComboBox;
    }

    public void setCategoryComboBox(JComboBox categoryComboBox) {
        this.categoryComboBox = categoryComboBox;
    }

    public JComboBox getSubCategoryComboBox() {
        return subCategoryComboBox;
    }

    public void setSubCategoryComboBox(JComboBox subCategoryComboBox) {
        this.subCategoryComboBox = subCategoryComboBox;
    }

    public JCheckBox getIsSignedCheckBox() {
        return isSignedCheckBox;
    }

    public void setIsSignedCheckBox(JCheckBox isSignedCheckBox) {
        this.isSignedCheckBox = isSignedCheckBox;
    }

    public JLabel getActualVersionLabel() {
        return actualVersionLabel;
    }

    public void setActualVersionLabel(JLabel actualVersionLabel) {
        this.actualVersionLabel = actualVersionLabel;
    }

    public JLabel getEditedVersionLabel() {
        return editedVersionLabel;
    }

    public void setEditedVersionLabel(JLabel editedVersionLabel) {
        this.editedVersionLabel = editedVersionLabel;
    }

    public ApiConnector getApiConnector() {
        return apiConnector;
    }

    public void setApiConnector(ApiConnector apiConnector) {
        this.apiConnector = apiConnector;
    }

    public JButton getUpdateTermButton() {
        return updateTermButton;
    }

    public void setUpdateTermButton(JButton updateTermButton) {
        this.updateTermButton = updateTermButton;
    }

    public JFrame getFrame() {
        return jFrame;
    }

    public void setFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(JPanel topPanel) {
        this.topPanel = topPanel;
    }

    public JPanel getTextPanel() {
        return textPanel;
    }

    public JPanel getBottomMainPanel() {
        return bottomMainPanel;
    }

    public void setBottomMainPanel(JPanel bottomMainPanel) {
        this.bottomMainPanel = bottomMainPanel;
    }

    public JPanel getBottomTopPanel() {
        return bottomTopPanel;
    }

    public void setBottomTopPanel(JPanel bottomTopPanel) {
        this.bottomTopPanel = bottomTopPanel;
    }

    public void setTextPanel(JPanel textPanel) {
        this.textPanel = textPanel;
    }
}

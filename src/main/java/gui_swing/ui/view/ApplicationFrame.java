package gui_swing.ui.view;



import gui_swing.ui.model.AuthorTermTableModel;
import gui_swing.ui.model.TermTableModel;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;


public class ApplicationFrame extends JFrame {

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
    private JList TermsList;
    private JTable termsTable;

    private JPanel bottomDetailsBlankPanel;
    private JScrollPane termsTableScrollPane;
    private JPanel bottomDetailsTermsPanel;
    private JPanel bottomDetailsTermPanel;
    private JPanel bottomDetailsTermTopPanel;
    private JPanel bottomDetailsTermBottomPanel;
    private JTable authorsTable;
    private JTextArea textArea1;
    private JPanel topDetailsPanelBlank;
    private JPanel topDetailsPanelFilters;
    private JPanel GroupJPanel;
    private JList categoryJList;
    private  JList subcategoryJList;
    private  JList tagsJList;
    private  JList statusesJList;

    private JPanel SubgroupsJPanel;
    private ArrayList<String> tagsStrings;
    private JButton searchButton;
    private JPanel TagsJPanel;
    private JPanel StatusJPanel;
    private JList subCategoriesJList;
    private JPanel SubGroupJPanel;
    private JLabel termsErrorLabel;
    private JTextField textField1;
    private JCheckBox isSignedCheckBox;
    private JButton boldButton;
    private JButton italicsButton;
    private JButton underlineButton;
    private JButton markReferenceButton;
    private JComboBox categoryComboBox;
    private JComboBox subcategoryComboBox;
    private JButton setAsActualVersionButton;
    private JLabel termIdLabel;
    private JLabel actualTermHostoryVersionLabel;
    private JEditorPane editorPane1;
    private JPanel topTermDetailsPanel;
    private JList authorsTermJList;
    private JList tagsTermJList;
    private JList statusesTermJList;
    private JCheckBox editCheckBox;
    private JButton addTermButton;
    private JButton getStatusesForSubcategoryButton;
    private JLabel versesTermLabel;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JScrollPane editorPane;
    private JPanel TermErrorLabelPanel;


    private JRadioButton radioButton1;

    protected String[] columnToolTips = {null,
            "Id hasła, pod tym numerem jest ono dostępne w bazie danych.",
            "Tytuł hasła.",
            "Ile dabe hasło ma różnych wersji.",
            "Aktualna wersja hasła.",
            "Autorzy danego hasła.",
            "Czy dane hasło jest sygnowane",
            "Tagi hasła (najedź aby wyświetlić nazwy)"
    };

    public JList getTagsTermJList() {
        return tagsTermJList;
    }

    public void setTagsTermJList(JList tagsTermJList) {
        this.tagsTermJList = tagsTermJList;
    }

    public JList getStatusesTermJList() {
        return statusesTermJList;
    }

    public JLabel getVersesTermLabel() {
        return versesTermLabel;
    }

    public void setVersesTermLabel(JLabel versesTermLabel) {
        this.versesTermLabel = versesTermLabel;
    }

    public void setStatusesTermJList(JList statusesTermJList) {
        this.statusesTermJList = statusesTermJList;
    }

    public JCheckBox getEditCheckBox() {
        return editCheckBox;
    }

    public void setEditCheckBox(JCheckBox editCheckBox) {
        this.editCheckBox = editCheckBox;
    }

    public JButton getAddTermButton() {
        return addTermButton;
    }

    public void setAddTermButton(JButton addTermButton) {
        this.addTermButton = addTermButton;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JCheckBox getIsSignedCheckBox() {
        return isSignedCheckBox;
    }

    public void setIsSignedCheckBox(JCheckBox isSignedCheckBox) {
        this.isSignedCheckBox = isSignedCheckBox;
    }

    public JButton getGetStatusesForSubcategoryButton() {
        return getStatusesForSubcategoryButton;
    }

    public void setGetStatusesForSubcategoryButton(JButton getStatusesForSubcategoryButton) {
        this.getStatusesForSubcategoryButton = getStatusesForSubcategoryButton;
    }

    public JButton getBoldButton() {
        return boldButton;
    }

    public void setBoldButton(JButton boldButton) {
        this.boldButton = boldButton;
    }

    public JButton getItalicsButton() {
        return italicsButton;
    }

    public void setItalicsButton(JButton italicsButton) {
        this.italicsButton = italicsButton;
    }

    public JButton getUnderlineButton() {
        return underlineButton;
    }

    public void setUnderlineButton(JButton underlineButton) {
        this.underlineButton = underlineButton;
    }

    public JButton getMarkReferenceButton() {
        return markReferenceButton;
    }

    public void setMarkReferenceButton(JButton markReferenceButton) {
        this.markReferenceButton = markReferenceButton;
    }

    public JComboBox getCategoryComboBox() {
        return categoryComboBox;
    }

    public void setCategoryComboBox(JComboBox categoryComboBox) {
        this.categoryComboBox = categoryComboBox;
    }

    public JComboBox getSubcategoryComboBox() {
        return subcategoryComboBox;
    }

    public void setSubcategoryComboBox(JComboBox subcategoryComboBox) {
        this.subcategoryComboBox = subcategoryComboBox;
    }

    public JButton getSetAsActualVersionButton() {
        return setAsActualVersionButton;
    }

    public void setSetAsActualVersionButton(JButton setAsActualVersionButton) {
        this.setAsActualVersionButton = setAsActualVersionButton;
    }

    public JLabel getTermIdLabel() {
        return termIdLabel;
    }

    public void setTermIdLabel(JLabel termIdLabel) {
        this.termIdLabel = termIdLabel;
    }

    public JLabel getActualTermHostoryVersionLabel() {
        return actualTermHostoryVersionLabel;
    }

    public void setActualTermHostoryVersionLabel(JLabel actualTermHostoryVersionLabel) {
        this.actualTermHostoryVersionLabel = actualTermHostoryVersionLabel;
    }

    public JEditorPane getEditorPane1() {
        return editorPane1;
    }

    public void setEditorPane1(JEditorPane editorPane1) {
        this.editorPane1 = editorPane1;
    }

    public JPanel getTopTermDetailsPanel() {
        return topTermDetailsPanel;
    }

    public void setTopTermDetailsPanel(JPanel topTermDetailsPanel) {
        this.topTermDetailsPanel = topTermDetailsPanel;
    }

    public JList getAuthorsTermJList() {
        return authorsTermJList;
    }

    public void setAuthorsTermJList(JList authorsTermJList) {
        this.authorsTermJList = authorsTermJList;
    }

    public JPanel getTermErrorLabelPanel() {
        return TermErrorLabelPanel;
    }

    public void setTermErrorLabelPanel(JPanel termErrorLabelPanel) {
        TermErrorLabelPanel = termErrorLabelPanel;
    }

    public JPanel getTagsJPanel() {
        return TagsJPanel;
    }

    public JLabel getTermsErrorLabel() {
        return termsErrorLabel;
    }

    public void setTermsErrorLabel(JLabel termsErrorLabel) {
        this.termsErrorLabel = termsErrorLabel;
    }

    public void setTagsJPanel(JPanel tagsJPanel) {
        TagsJPanel = tagsJPanel;
    }

    public JPanel getStatusJPanel() {
        return StatusJPanel;
    }

    public void setStatusJPanel(JPanel statusJPanel) {
        StatusJPanel = statusJPanel;
    }

    public JList getSubCategoriesJList() {
        return subCategoriesJList;
    }

    public void setSubCategoriesJList(JList subCategoriesJList) {
        this.subCategoriesJList = subCategoriesJList;
    }

    public JPanel getSubGroupJPanel() {
        return SubGroupJPanel;
    }

    public void setSubGroupJPanel(JPanel subGroupJPanel) {
        SubGroupJPanel = subGroupJPanel;
    }

    public JList getSubcategoryJList() {
        return subcategoryJList;
    }

    public void setSubcategoryJList(JList subcategoryJList) {
        this.subcategoryJList = subcategoryJList;
    }

    public JList getTagsJList() {
        return tagsJList;
    }

    public void setTagsJList(JList tagsJList) {
        this.tagsJList = tagsJList;
    }

    public JList getStatusesJList() {
        return statusesJList;
    }

    public void setStatusesJList(JList statusesJList) {
        this.statusesJList = statusesJList;
    }

    public JPanel getBottomDetailsTermTopPanel() {
        return bottomDetailsTermTopPanel;
    }

    public void setBottomDetailsTermTopPanel(JPanel bottomDetailsTermTopPanel) {
        this.bottomDetailsTermTopPanel = bottomDetailsTermTopPanel;
    }

    public JPanel getBottomDetailsTermBottomPanel() {
        return bottomDetailsTermBottomPanel;
    }

    public void setBottomDetailsTermBottomPanel(JPanel bottomDetailsTermBottomPanel) {
        this.bottomDetailsTermBottomPanel = bottomDetailsTermBottomPanel;
    }

    public JTable getAuthorsTable() {
        return authorsTable;
    }

    public void setAuthorsTable(JTable authorsTable) {
        this.authorsTable = authorsTable;
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public void setTextArea1(JTextArea textArea1) {
        this.textArea1 = textArea1;
    }

    public JPanel getTopDetailsPanelBlank() {
        return topDetailsPanelBlank;
    }

    public void setTopDetailsPanelBlank(JPanel topDetailsPanelBlank) {
        this.topDetailsPanelBlank = topDetailsPanelBlank;
    }

    public JPanel getTopDetailsPanelFilters() {
        return topDetailsPanelFilters;
    }

    public void setTopDetailsPanelFilters(JPanel topDetailsPanelFilters) {
        this.topDetailsPanelFilters = topDetailsPanelFilters;
    }

    public JList getCategoryJList() {
        return categoryJList;
    }

    public void setCategoryJList(JList categoryJList) {
        this.categoryJList = categoryJList;
    }

    public JPanel getGroupJPanel() {
        return GroupJPanel;
    }

    public void setGroupJPanel(JPanel groupJPanel) {
        GroupJPanel = groupJPanel;
    }



    public JPanel getSubgroupsJPanel() {
        return SubgroupsJPanel;
    }

    public void setSubgroupsJPanel(JPanel subgroupsJPanel) {
        SubgroupsJPanel = subgroupsJPanel;
    }

    public String[] getColumnToolTips() {
        return columnToolTips;
    }

    public void setColumnToolTips(String[] columnToolTips) {
        this.columnToolTips = columnToolTips;
    }

    public String[] getHeadersStr() {
        return headersStr;
    }

    public void setHeadersStr(String[] headersStr) {
        this.headersStr = headersStr;
    }

    public ArrayList<String> getTagsStrings() {
        return tagsStrings;
    }

    public void setTagsStrings(ArrayList<String> tagsStrings) {
        this.tagsStrings = tagsStrings;
    }

    public JPanel getBottomDetailsTermPanel() {
        return bottomDetailsTermPanel;
    }

    public void setBottomDetailsTermPanel(JPanel bottomDetailsTermPanel) {
        this.bottomDetailsTermPanel = bottomDetailsTermPanel;
    }

    String[] headersStr =  {"Lp","Id", "Tytuł", "Ilość wersji", "Aktualna wersja", "Autorzy", "Podpis","Tagi"};


    public JScrollPane getTermsTableScrollPane() {
        return termsTableScrollPane;
    }

    public void setTermsTableScrollPane(JScrollPane termsTableScrollPane) {
        this.termsTableScrollPane = termsTableScrollPane;
    }




    /*public TermTableModel getTermTableModel() {
        return termTableModel;
    }

    public void setTermTableModel(TermTableModel termTableModel) {
        this.termTableModel = termTableModel;
    }*/

    public JPanel getBottomDetailsTermsPanel() {
        return bottomDetailsTermsPanel;
    }

    public void setBottomDetailsTermsPanel(JPanel bottomDetailsTermsPanel) {
        this.bottomDetailsTermsPanel = bottomDetailsTermsPanel;
    }

    public JTable getTermsTable() {
        return termsTable;
    }

    public void setTermsTable(JTable termsTable) {
        this.termsTable = termsTable;
    }

    public JPanel getBottomDetailsBlankPanel() {
        return bottomDetailsBlankPanel;
    }

    public void setBottomDetailsBlankPanel(JPanel bottomDetailsBlankPanel) {
        this.bottomDetailsBlankPanel = bottomDetailsBlankPanel;
    }

    public JList getTermsList() {
        return TermsList;
    }

    public void setTermsList(JList termsList) {
        TermsList = termsList;
    }


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

    public JButton getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(JButton searchButton) {
        this.searchButton = searchButton;
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
        tagsStrings= new ArrayList<String>(20000);
        int rows = 0;
        Vector headers = new Vector();
        for (int i = 0; i < headersStr.length;i++) {
            headers.addElement(headersStr[i]);

        }
        Vector authorHeaders = new Vector();

        authorHeaders.addElement("Wybierz");
        authorHeaders.addElement("Imię i nazwisko");
        authorHeaders.addElement("Ilość wersetów");

        authorsTable = new JTable(new AuthorTermTableModel(authorHeaders,0)){};

        termsTable = new JTable(new TermTableModel(headers,0)){


            public Component prepareRenderer(
                    TableCellRenderer renderer, int row, int column)
            {
                Component c = super.prepareRenderer(renderer, row, column);
                JComponent jc = (JComponent)c;

                //  Alternate row color

                if (isRowSelected(row))
                    c.setBackground(Color.LIGHT_GRAY);
                else c.setBackground(this.getBackground());

                return c;
            }

            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);

                if (realColumnIndex == 7) {
                    Long rowToCast = (Long) getValueAt(rowIndex,colIndex-6);
                    int convertedValue = Math.toIntExact(rowToCast);
                    tip = "Tagi dla hasła '" + getValueAt(rowIndex, colIndex -5 )
                            + "' to : "
                            + tagsStrings.get(convertedValue-1);

                }


                return tip;
            }
            protected JTableHeader createDefaultTableHeader() {
                return new JTableHeader(columnModel) {
                    public String getToolTipText(MouseEvent e) {
                        String tip = null;
                        java.awt.Point p = e.getPoint();
                        int index = columnModel.getColumnIndexAtX(p.x);
                        int realIndex = columnModel.getColumn(index).getModelIndex();
                        return columnToolTips[realIndex];
                    }
                };
            }
        };
        TermTableModel defaultTableModel = (TermTableModel) termsTable.getModel();
        DefaultTableModel tm= (DefaultTableModel) termsTable.getModel();
        tm.setColumnIdentifiers(headersStr);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) termsTable.getModel());
        termsTable.setRowSorter(sorter);
        TableColumnModel tableModel= termsTable.getColumnModel();

        //Width of termsTable
       /* long sum = 0;
        for(int i =0 ; i<7 ; i ++){
          sum+=  tableModel.getColumn(i).getWidth();
        }
        System.out.println( sum);*/
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment( JLabel.LEFT );

       /* tableModel.getColumn(0).setPreferredWidth(15);
        tableModel.getColumn(1).setPreferredWidth(15);
        tableModel.getColumn(2).setPreferredWidth(230);
        tableModel.getColumn(3).setPreferredWidth(25);
        tableModel.getColumn(4).setPreferredWidth(40);
        tableModel.getColumn(5).setPreferredWidth(145);
        tableModel.getColumn(6).setPreferredWidth(25);*/
       // tableModel.getColumn(7).setPreferredWidth(30);
        tableModel.getColumn(0).setCellRenderer(centerRenderer);
        tableModel.getColumn(1).setCellRenderer(centerRenderer);
        tableModel.getColumn(3).setCellRenderer(centerRenderer);
        tableModel.getColumn(4).setCellRenderer(centerRenderer);
        //tableModel.getColumn(7).setCellRenderer(leftRenderer);
        /*List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);*/

         categoryJList = new JList();
         subcategoryJList = new JList();
         tagsJList = new JList();
         statusesJList = new JList();

          authorsTermJList= new JList();
          tagsTermJList= new JList();
          statusesTermJList= new JList();

          editorPane1 = new JEditorPane();
          scrollPane1 = new JScrollPane(editorPane1);
          scrollPane2 = new JScrollPane(statusesTermJList);
          scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);





    }


}



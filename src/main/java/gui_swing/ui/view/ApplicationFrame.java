package gui_swing.ui.view;



import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import gui_swing.ui.model.TermTable;
import gui_swing.ui.model.tableModels.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
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
    public List<TermTable> listOfTermTable = new ArrayList<>();

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
    private JLabel actualTermHistoryVersionLabel;
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
    private JLabel authorsIdLabel;
    private JLabel authorPanelErrorLabel;
    private JLabel termDetailsIdLabel;
    private JList settingsList;
    private JPanel bottomDetailsSettingsPanel;
    private JPanel left;
    private JButton addSubcategoryButton;
    private JTextField SubcategoryTextField;
    private JButton deleteSubcategoryButton;
    private JPanel leftCenter;
    private JPanel rightCenter;
    private JPanel right;
    private JList list2;
    private JList list3;
    private JList list4;
    private JButton upButton;
    private JButton downButton;
    private JButton leftButton;
    private JButton rightButton;
    private JLabel settingsErrorLabel;
    private JButton saveSubcategoryStatuses;
    private JPanel bottomDetailsTagsPanel;
    private JPanel topDetailsTagsPanel;
    private JTextField tagsNameField;
    private JButton addTagButton;
    private JButton deleteTagButton;
    private JList list5;
    private JTable termsTagsTable;
    private JButton choseTagColorButton;
    private JLabel tagIconLabel;
    private JLabel tagsSettingsErrorLabel;
    private JButton saveTermsToTagButton;
    private JButton updateTagButton;
    private JLabel termHistoryVersionLabel;
    private JScrollPane scrollAuthors;
    private JScrollPane tagTermScroll;
    private JPanel shefPanel;
    private JButton updateTermButton;
    private JButton sendTermToNextStepButton;
    private JButton manageTermStatusesButton;
    private JButton manageTermVersionsButton;
    private JButton redirectTermToIndicatedAuthorButton;
    private JButton manageTermReferenceButton;
    private JLabel editedTermHistoryVersionLabel;
    private JButton categoryMinusButton;
    private JButton categoryPlusButton;
    private JPanel bottomDetailsFiltersPanel;
    private JButton subcategoryMinusButton;
    private JButton subcategoryPlusButton;
    private JButton tagsPlusButton;
    private JButton tagsMinusButton;
    private JButton statusMinusButton;
    private JButton statusPlusButton;
    private JPanel fullTextSearchPanel;

    private JScrollPane scrollFK;
    private JScrollPane editorPane;
    private JPanel TermErrorLabelPanel;

    private JTextField fullTextSearchField;
    private JRadioButton headersRadioButton;
    private JRadioButton contentRadioButton;
    private JButton fullTextSearchButton;
    private JRadioButton allVersionsRadioButton;
    private JLabel fullTextSearchLabel;

    protected String[] columnToolTips = {
            "Id hasła, pod tym numerem jest ono dostępne w bazie danych.",
            "Tytuł hasła.",

            "Tagi hasła (najedź aby wyświetlić nazwy)"
    };

    public JList getTagsTermJList() {
        return tagsTermJList;
    }

    public JButton getUpdateTermButton() {
        return updateTermButton;
    }

    public JButton getManageTermReferenceButton() {
        return manageTermReferenceButton;
    }

    public void setManageTermReferenceButton(JButton manageTermReferenceButton) {
        this.manageTermReferenceButton = manageTermReferenceButton;
    }

    public JButton getSubcategoryMinusButton() {
        return subcategoryMinusButton;
    }

    public JLabel getFullTextSearchLabel() {
        return fullTextSearchLabel;
    }

    public void setFullTextSearchLabel(JLabel fullTextSearchLabel) {
        this.fullTextSearchLabel = fullTextSearchLabel;
    }

    public JPanel getFullTextSearchPanel() {
        return fullTextSearchPanel;
    }

    public void setFullTextSearchPanel(JPanel fullTextSearchPanel) {
        this.fullTextSearchPanel = fullTextSearchPanel;
    }

    public JTextField getFullTextSearchField() {
        return fullTextSearchField;
    }

    public void setFullTextSearchField(JTextField fullTextSearchField) {
        this.fullTextSearchField = fullTextSearchField;
    }

    public JRadioButton getContentRadioButton() {
        return contentRadioButton;
    }

    public void setContentRadioButton(JRadioButton contentRadioButton) {
        this.contentRadioButton = contentRadioButton;
    }

    public JButton getFullTextSearchButton() {
        return fullTextSearchButton;
    }

    public void setFullTextSearchButton(JButton fullTextSearchButton) {
        this.fullTextSearchButton = fullTextSearchButton;
    }

    public JRadioButton getAllVersionsRadioButton() {
        return allVersionsRadioButton;
    }

    public void setAllVersionsRadioButton(JRadioButton allVersionsRadioButton) {
        this.allVersionsRadioButton = allVersionsRadioButton;
    }

    public void setSubcategoryMinusButton(JButton subcategoryMinusButton) {
        this.subcategoryMinusButton = subcategoryMinusButton;
    }

    public JButton getSubcategoryPlusButton() {
        return subcategoryPlusButton;
    }

    public void setSubcategoryPlusButton(JButton subcategoryPlusButton) {
        this.subcategoryPlusButton = subcategoryPlusButton;
    }

    public JButton getTagsPlusButton() {
        return tagsPlusButton;
    }

    public void setTagsPlusButton(JButton tagsPlusButton) {
        this.tagsPlusButton = tagsPlusButton;
    }

    public JButton getTagsMinusButton() {
        return tagsMinusButton;
    }

    public void setTagsMinusButton(JButton tagsMinusButton) {
        this.tagsMinusButton = tagsMinusButton;
    }

    public JButton getStatusMinusButton() {
        return statusMinusButton;
    }

    public void setStatusMinusButton(JButton statusMinusButton) {
        this.statusMinusButton = statusMinusButton;
    }

    public JButton getStatusPlusButton() {
        return statusPlusButton;
    }

    public void setStatusPlusButton(JButton statusPlusButton) {
        this.statusPlusButton = statusPlusButton;
    }

    public JLabel getEditedTermHistoryVersionLabel() {
        return editedTermHistoryVersionLabel;
    }

    public void setEditedTermHistoryVersionLabel(JLabel editedTermHistoryVersionLabel) {
        this.editedTermHistoryVersionLabel = editedTermHistoryVersionLabel;
    }

    public void setUpdateTermButton(JButton updateTermButton) {
        this.updateTermButton = updateTermButton;
    }

    public JButton getSendTermToNextStepButton() {
        return sendTermToNextStepButton;
    }

    public void setSendTermToNextStepButton(JButton sendTermToNextStepButton) {
        this.sendTermToNextStepButton = sendTermToNextStepButton;
    }

    public JButton getCategoryMinusButton() {
        return categoryMinusButton;
    }

    public void setCategoryMinusButton(JButton categoryMinusButton) {
        this.categoryMinusButton = categoryMinusButton;
    }

    public JButton getCategoryPlusButton() {
        return categoryPlusButton;
    }

    public void setCategoryPlusButton(JButton categoryPlusButton) {
        this.categoryPlusButton = categoryPlusButton;
    }

    public JPanel getBottomDetailsFiltersPanel() {
        return bottomDetailsFiltersPanel;
    }

    public void setBottomDetailsFiltersPanel(JPanel bottomDetailsFiltersPanel) {
        this.bottomDetailsFiltersPanel = bottomDetailsFiltersPanel;
    }

    public JButton getManageTermStatusesButton() {
        return manageTermStatusesButton;
    }

    public void setManageTermStatusesButton(JButton manageTermStatusesButton) {
        this.manageTermStatusesButton = manageTermStatusesButton;
    }

    public JButton getManageTermVersionsButton() {
        return manageTermVersionsButton;
    }

    public List<TermTable> getListOfTermTable() {
        return listOfTermTable;
    }

    public void setListOfTermTable(List<TermTable> listOfTermTable) {
        this.listOfTermTable = listOfTermTable;
    }

    public void setManageTermVersionsButton(JButton manageTermVersionsButton) {
        this.manageTermVersionsButton = manageTermVersionsButton;
    }

    public JButton getRedirectTermToIndicatedAuthorButton() {
        return redirectTermToIndicatedAuthorButton;
    }

    public void setRedirectTermToIndicatedAuthorButton(JButton redirectTermToIndicatedAuthorButton) {
        this.redirectTermToIndicatedAuthorButton = redirectTermToIndicatedAuthorButton;
    }

    public void setTagsTermJList(JList tagsTermJList) {
        this.tagsTermJList = tagsTermJList;
    }

    public JLabel getTermDetailsIdLabel() {
        return termDetailsIdLabel;
    }

    public void setTermDetailsIdLabel(JLabel termDetailsIdLabel) {
        this.termDetailsIdLabel = termDetailsIdLabel;
    }

    public JScrollPane getScrollFK() {
        return scrollFK;
    }

    public void setScrollFK(JScrollPane scrollFK) {
        this.scrollFK = scrollFK;
    }

    public JList getSettingsList() {
        return settingsList;
    }

    public JButton getSaveSubcategoryStatuses() {
        return saveSubcategoryStatuses;
    }

    public void setSaveSubcategoryStatuses(JButton saveSubcategoryStatuses) {
        this.saveSubcategoryStatuses = saveSubcategoryStatuses;
    }

    public void setSettingsList(JList settingsList) {
        this.settingsList = settingsList;
    }

    public JPanel getBottomDetailsTagsPanel() {
        return bottomDetailsTagsPanel;
    }

    public void setBottomDetailsTagsPanel(JPanel bottomDetailsTagsPanel) {
        this.bottomDetailsTagsPanel = bottomDetailsTagsPanel;
    }

    public JList getStatusesTermJList() {
        return statusesTermJList;
    }

    public JPanel getBottomDetailsSettingsPanel() {
        return bottomDetailsSettingsPanel;
    }

    public void setBottomDetailsSettingsPanel(JPanel bottomDetailsSettingsPanel) {
        this.bottomDetailsSettingsPanel = bottomDetailsSettingsPanel;
    }

    public JPanel getLeft() {
        return left;
    }

    public void setLeft(JPanel left) {
        this.left = left;
    }

    public JPanel getTopDetailsTagsPanel() {
        return topDetailsTagsPanel;
    }

    public void setTopDetailsTagsPanel(JPanel topDetailsTagsPanel) {
        this.topDetailsTagsPanel = topDetailsTagsPanel;
    }

    public JTextField getTagsNameField() {
        return tagsNameField;
    }

    public void setTagsNameField(JTextField tagsNameField) {
        this.tagsNameField = tagsNameField;
    }

    public JButton getAddTagButton() {
        return addTagButton;
    }

    public void setAddTagButton(JButton addTagButton) {
        this.addTagButton = addTagButton;
    }

    public JButton getDeleteTagButton() {
        return deleteTagButton;
    }

    public void setDeleteTagButton(JButton deleteTagButton) {
        this.deleteTagButton = deleteTagButton;
    }

    public JList getList5() {
        return list5;
    }

    public void setList5(JList list5) {
        this.list5 = list5;
    }

    public JTable getTermsTagsTable() {
        return termsTagsTable;
    }

    public void setTermsTagsTable(JTable termsTagsTable) {
        this.termsTagsTable = termsTagsTable;
    }

    public JButton getChoseTagColorButton() {
        return choseTagColorButton;
    }

    public void setChoseTagColorButton(JButton choseTagColorButton) {
        this.choseTagColorButton = choseTagColorButton;
    }

    public JLabel getTagIconLabel() {
        return tagIconLabel;
    }

    public void setTagIconLabel(JLabel tagIconLabel) {
        this.tagIconLabel = tagIconLabel;
    }

    public JButton getAddSubcategoryButton() {
        return addSubcategoryButton;
    }

    public void setAddSubcategoryButton(JButton addSubcategoryButton) {
        this.addSubcategoryButton = addSubcategoryButton;
    }

    public JTextField getSubcategoryTextField() {
        return SubcategoryTextField;
    }

    public void setSubcategoryTextField(JTextField subcategoryTextField) {
        SubcategoryTextField = subcategoryTextField;
    }

    public JLabel getSettingsErrorLabel() {
        return settingsErrorLabel;
    }

    public void setSettingsErrorLabel(JLabel settingsErrorLabel) {
        this.settingsErrorLabel = settingsErrorLabel;
    }

    public JButton getDeleteSubcategoryButton() {
        return deleteSubcategoryButton;
    }

    public void setDeleteSubcategoryButton(JButton deleteSubcategoryButton) {
        this.deleteSubcategoryButton = deleteSubcategoryButton;
    }

    public JPanel getLeftCenter() {
        return leftCenter;
    }

    public void setLeftCenter(JPanel leftCenter) {
        this.leftCenter = leftCenter;
    }

    public JPanel getRightCenter() {
        return rightCenter;
    }

    public void setRightCenter(JPanel rightCenter) {
        this.rightCenter = rightCenter;
    }

    public JPanel getRight() {
        return right;
    }

    public void setRight(JPanel right) {
        this.right = right;
    }

    public JList getList2() {
        return list2;
    }

    public void setList2(JList list2) {
        this.list2 = list2;
    }

    public JList getList3() {
        return list3;
    }

    public void setList3(JList list3) {
        this.list3 = list3;
    }

    public JList getList4() {
        return list4;
    }

    public void setList4(JList list4) {
        this.list4 = list4;
    }

    public JButton getUpButton() {
        return upButton;
    }

    public void setUpButton(JButton upButton) {
        this.upButton = upButton;
    }

    public JButton getDownButton() {
        return downButton;
    }

    public void setDownButton(JButton downButton) {
        this.downButton = downButton;
    }

    public JButton getLeftButton() {
        return leftButton;
    }

    public void setLeftButton(JButton leftButton) {
        this.leftButton = leftButton;
    }

    public JButton getRightButton() {
        return rightButton;
    }

    public void setRightButton(JButton rightButton) {
        this.rightButton = rightButton;
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

    public JScrollPane getScrollPane1() {
        return scrollPane1;
    }

    public void setScrollPane1(JScrollPane scrollPane1) {
        this.scrollPane1 = scrollPane1;
    }

    public JLabel getAuthorsIdLabel() {
        return authorsIdLabel;
    }

    public JLabel getAuthorPanelErrorLabel() {
        return authorPanelErrorLabel;
    }

    public void setAuthorPanelErrorLabel(JLabel authorPanelErrorLabel) {
        this.authorPanelErrorLabel = authorPanelErrorLabel;
    }

    public void setAuthorsIdLabel(JLabel authorsIdLabel) {
        this.authorsIdLabel = authorsIdLabel;
    }

    public JScrollPane getScrollPane2() {
        return scrollPane2;
    }

    public void setScrollPane2(JScrollPane scrollPane2) {
        this.scrollPane2 = scrollPane2;
    }

    public JList getAuthorsList() {
        return authorsList;
    }

    public void setAuthorsList(JList authorsList) {
        this.authorsList = authorsList;
    }

    public JPanel getBottomDetailsAuthorsPanel() {
        return bottomDetailsAuthorsPanel;
    }

    public void setBottomDetailsAuthorsPanel(JPanel bottomDetailsAuthorsPanel) {
        this.bottomDetailsAuthorsPanel = bottomDetailsAuthorsPanel;
    }

    public JTable getAuthorsMenageTable() {
        return authorsMenageTable;
    }

    public void setAuthorsMenageTable(JTable authorsMenageTable) {
        this.authorsMenageTable = authorsMenageTable;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(JTextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public JTextField getSurnameTextField() {
        return surnameTextField;
    }

    public void setSurnameTextField(JTextField surnameTextField) {
        this.surnameTextField = surnameTextField;
    }

    public JTextField getSignTextField() {
        return signTextField;
    }

    public void setSignTextField(JTextField signTextField) {
        this.signTextField = signTextField;
    }

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public void setEmailTextField(JTextField emailTextField) {
        this.emailTextField = emailTextField;
    }

    public JButton getAddAuthorButton() {
        return addAuthorButton;
    }

    public void setAddAuthorButton(JButton addAuthorButton) {
        this.addAuthorButton = addAuthorButton;
    }

    public JButton getUpdateAuthorButton() {
        return updateAuthorButton;
    }

    public void setUpdateAuthorButton(JButton updateAuthorButton) {
        this.updateAuthorButton = updateAuthorButton;
    }

    public JButton getDeleteAuthorButton() {
        return deleteAuthorButton;
    }

    public JButton getSaveTermsToTagButton() {
        return saveTermsToTagButton;
    }

    public void setSaveTermsToTagButton(JButton saveTermsToTagButton) {
        this.saveTermsToTagButton = saveTermsToTagButton;
    }

    public JButton getUpdateTagButton() {
        return updateTagButton;
    }

    public void setUpdateTagButton(JButton updateTagButton) {
        this.updateTagButton = updateTagButton;
    }

    public void setDeleteAuthorButton(JButton deleteAuthorButton) {
        this.deleteAuthorButton = deleteAuthorButton;
    }

    public JScrollPane getEditorPane() {
        return editorPane;
    }

    public void setEditorPane(JScrollPane editorPane) {
        this.editorPane = editorPane;
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

    public JPanel getShefPanel() {
        return shefPanel;
    }

    public void setShefPanel(JPanel shefPanel) {
        this.shefPanel = shefPanel;
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

    public JLabel getActualTermHistoryVersionLabel() {
        return actualTermHistoryVersionLabel;
    }

    public void setActualTermHistoryVersionLabel(JLabel actualTermHistoryVersionLabel) {
        this.actualTermHistoryVersionLabel = actualTermHistoryVersionLabel;
    }

    public JEditorPane getEditorPane1() {
        return editorPane1;
    }

    public void setEditorPane1(JEditorPane editorPane1) {
        this.editorPane1 = editorPane1;
    }

    public JScrollPane getScrollAuthors() {
        return scrollAuthors;
    }

    public void setScrollAuthors(JScrollPane scrollAuthors) {
        this.scrollAuthors = scrollAuthors;
    }

    public JScrollPane getTagTermScroll() {
        return tagTermScroll;
    }

    public void setTagTermScroll(JScrollPane tagTermScroll) {
        this.tagTermScroll = tagTermScroll;
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

    public JLabel getTermHistoryVersionLabel() {
        return termHistoryVersionLabel;
    }

    public void setTermHistoryVersionLabel(JLabel termHistoryVersionLabel) {
        this.termHistoryVersionLabel = termHistoryVersionLabel;
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

    public JLabel getTagsSettingsErrorLabel() {
        return tagsSettingsErrorLabel;
    }

    public void setTagsSettingsErrorLabel(JLabel tagsSettingsErrorLabel) {
        this.tagsSettingsErrorLabel = tagsSettingsErrorLabel;
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

    public JRadioButton getHeadersRadioButton() {
        return headersRadioButton;
    }

    public void setHeadersRadioButton(JRadioButton headersRadioButton) {
        this.headersRadioButton = headersRadioButton;
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


        $$$setupUI$$$();
        //setSize(WIDTH, HEIGHT);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setResizable(true);
        setMinimumSize(new Dimension(1320,850));


    }


    private void createUIComponents() {

        tagsStrings= new ArrayList<String>(20000);
        int rows = 0;
        Vector headers = new Vector();
        for (int i = 0; i < headersStr.length;i++) {
            headers.addElement(headersStr[i]);

        }
        Vector authorHeaders = new Vector();

        authorHeaders.addElement("Id");
        authorHeaders.addElement("Wybierz");
        authorHeaders.addElement("Imię i nazwisko");
        authorHeaders.addElement("Ilość wersetów");

        authorsTable = new JTable(new AuthorTermTableModel(authorHeaders,0)){};
        Vector authorHeaders1 = new Vector();
        authorHeaders1.addElement("Id");
        authorHeaders1.addElement("Imię");
        authorHeaders1.addElement("Nazwisko");
        authorHeaders1.addElement("Telefon");
        authorHeaders1.addElement("Email");
        authorHeaders1.addElement("Ilość przypisanych haseł");


        Vector tagsTableHeaders = new Vector();
        tagsTableHeaders.addElement("Wybierz");
        tagsTableHeaders.addElement("Id");
        tagsTableHeaders.addElement("Tytuł hasła");
        termsTagsTable = new JTable(new TagTermTableModel(tagsTableHeaders,0)){};
        TableColumnModel tableColumnModelTags = termsTagsTable.getColumnModel();
        tableColumnModelTags.getColumn(0).setPreferredWidth(70);
        tableColumnModelTags.getColumn(1).setPreferredWidth(70);
        tableColumnModelTags.getColumn(2).setPreferredWidth(250);

        authorsMenageTable = new JTable(new AuthorTableModel(authorHeaders1,0)){};
        termsTable = new JTable(new ObjectTableModel<TermTable>() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex){
                    case 0:
                        // case 1:

                        // case 3:
                        // case 4:
                        return Long.class;
                    // case 2:
                    case 1:


                        return String.class;
                    // case 6:
                    // return Boolean.class;
                    case 2:
                        return Icon.class;//getValueAt(0,columnIndex).getClass();
                    default:
                        return JLabel.class;
                }
            }

            @Override
            public Object getValueAt(TermTable termTable, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return termTable.getId();
                    case 1:
                        return termTable.getTitle();
                    case 2:
                        return termTable.getTags();
                   /* case 3:
                        return termTable.getVersionCount();
                    case 4:
                        return termTable.getActualVersion();
                    case 5:
                        return termTable.getAuthors();
                    case 6:
                        return termTable.getSigned();
                    case 7:*/


                }
                return null;
            }

            @Override
            public int getColumnCount() {
                return 3;
            }
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Id";
               /* case 1:
                return "Id";*/
                    case 1:
                        return "Tytuł";
           /* case 3:
                return "Ilość wersji";
            case 4:
                return "Aktualna wersja";
                case 5:
                return "Autorzy";
                case 6:
                return "Podpis";*/
                    case 2:
                        return "Tagi hasła";

                }
                return null;
            }

        })
        {
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                Point p = e.getPoint();
                Integer rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);

                if (realColumnIndex == 2) {
                   // Long rowToCastl =  (Long)getValueAt(rowIndex,0);
                    /*mainPanel
                    List<TermTable> termTableList = ApplicationFrameController.list.stream()
                            .filter(item->item.getId().equals((Long)getValueAt(rowIndex,0)))
                            .collect(Collectors.toList());*/
                    TermTable thisTermTable = new TermTable();
                    for (TermTable termTable: listOfTermTable
                         ) {
                        if(termTable.getId().equals(((Long)getValueAt(rowIndex,0)))){
                            thisTermTable=termTable;
                        }
                    }
                   //TermTable tt = (TermTable)termTableList.getItem(0);
                    Long rowToCastl =  thisTermTable.getLp();
                    int rowToCast =  rowToCastl.intValue();


                    //int convertedValue = Math.toIntExact(rowToCast);
                    tip = "Tagi dla hasła '" + getValueAt(rowIndex, colIndex -1 )
                            + "' to : "
                            + tagsStrings.get(rowToCast-1);

                }

                return tip;
            }
            protected JTableHeader createDefaultTableHeader() {
                return new JTableHeader(columnModel) {
                    public String getToolTipText(MouseEvent e) {
                        String tip = null;
                        Point p = e.getPoint();
                        int index = columnModel.getColumnIndexAtX(p.x);
                        int realIndex = columnModel.getColumn(index).getModelIndex();
                        return columnToolTips[realIndex];
                    }
                };
            }
        };




       /* termsTable = new JTable(new TermTableModel(headers,0)){


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
                Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);

                if (realColumnIndex == 7) {
                    int rowToCast =  (int)getValueAt(rowIndex,0);


                   //int convertedValue = Math.toIntExact(rowToCast);
                    tip = "Tagi dla hasła '" + getValueAt(rowIndex, colIndex -5 )
                            + "' to : "
                            + tagsStrings.get(rowToCast-1);

                }

                return tip;
            }
            protected JTableHeader createDefaultTableHeader() {
                return new JTableHeader(columnModel) {
                    public String getToolTipText(MouseEvent e) {
                        String tip = null;
                        Point p = e.getPoint();
                        int index = columnModel.getColumnIndexAtX(p.x);
                        int realIndex = columnModel.getColumn(index).getModelIndex();
                        return columnToolTips[realIndex];
                    }
                };
            }
        };*/



        //DefaultTableModel tm= (DefaultTableModel) termsTable.getModel();
        //tm.setColumnIdentifiers(headersStr);
       // TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) termsTable.getModel());
        //termsTable.setRowSorter(sorter);
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
       // tableModel.getColumn(0).setCellRenderer(centerRenderer);
        //tableModel.getColumn(1).setCellRenderer(centerRenderer);
        //tableModel.getColumn(3).setCellRenderer(centerRenderer);
       // tableModel.getColumn(4).setCellRenderer(centerRenderer);
        //tableModel.getColumn(7).setCellRenderer(leftRenderer);
        /*List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);*/

         categoryJList = new JList();
         subcategoryJList = new JList(new DefaultListModel());
         tagsJList = new JList();
         statusesJList = new JList();

          authorsTermJList= new JList();
          tagsTermJList= new JList();
          statusesTermJList= new JList();

          //HTMLEditorPane htmlEditorPane = new HTMLEditorPane();

       /* editorPane1.setLineWrap(true); //Makes the text wrap to the next line
        editorPane1.setWrapStyleWord(true); //Makes the text wrap full words, not just letters*/

          scrollPane1 = new JScrollPane(editorPane1);
          scrollPane2 = new JScrollPane(statusesTermJList);
          scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
           list2 = new JList(new DefaultListModel());
           list3 = new JList(new DefaultListModel());
           list4 = new JList(new DefaultListModel());
           list5 = new JList(new DefaultListModel());



           scrollFK = new JScrollPane(list2);
           scrollAuthors = new JScrollPane(authorsTable);
           tagTermScroll = new JScrollPane(tagsTermJList);
           //shefPanel.add(htmlEditorPane);
        //mainPanel = new JPanel();

    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setBackground(new Color(-3947581));
        mainPanel.setMaximumSize(new Dimension(-1, -1));
        mainPanel.setOpaque(false);
        mainPanel.setPreferredSize(new Dimension(1280, 800));
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        topPanel.setBackground(new Color(-14802385));
        topPanel.setMaximumSize(new Dimension(1280, 30));
        topPanel.setMinimumSize(new Dimension(1280, 30));
        topPanel.setOpaque(true);
        topPanel.setPreferredSize(new Dimension(1280, 30));
        mainPanel.add(topPanel, BorderLayout.NORTH);
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 5, 0, 0), 0, 0));
        leftPanel.setBackground(new Color(-14802385));
        leftPanel.setMaximumSize(new Dimension(130, 2147483647));
        leftPanel.setMinimumSize(new Dimension(130, 770));
        leftPanel.setOpaque(true);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        leftTopPanel = new JPanel();
        leftTopPanel.setLayout(new GridLayoutManager(1, 1, new Insets(20, 5, 0, 5), -1, -1));
        leftTopPanel.setOpaque(false);
        leftPanel.add(leftTopPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 200), null, null, 0, false));
        iconLabel = new JLabel();
        iconLabel.setHorizontalAlignment(0);
        iconLabel.setIcon(new ImageIcon(getClass().getResource("/img/logo-bk min.png")));
        iconLabel.setText("");
        leftTopPanel.add(iconLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        leftMiddlePanel = new JPanel();
        leftMiddlePanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        leftMiddlePanel.setOpaque(false);
        leftPanel.add(leftMiddlePanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 500), new Dimension(-1, 500), new Dimension(-1, 500), 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 14, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-2829100));
        label1.setText("Lista modułów:");
        leftMiddlePanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(130, -1), null, null, 0, false));
        list1 = new JList();
        list1.setBackground(new Color(-14802385));
        Font list1Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 12, list1.getFont());
        if (list1Font != null) list1.setFont(list1Font);
        list1.setForeground(new Color(-3947581));
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        defaultListModel1.addElement("Hasła");
        defaultListModel1.addElement("Autorzy");
        defaultListModel1.addElement("Ustawienia");
        list1.setModel(defaultListModel1);
        list1.setOpaque(false);
        list1.setSelectionMode(0);
        list1.setVisibleRowCount(5);
        list1.putClientProperty("List.isFileList", Boolean.FALSE);
        leftMiddlePanel.add(list1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(130, -1), new Dimension(80, 50), new Dimension(80, -1), 0, false));
        final Spacer spacer1 = new Spacer();
        leftMiddlePanel.add(spacer1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 400), new Dimension(-1, 400), new Dimension(-1, 400), 0, false));
        leftBottomPanel = new JPanel();
        leftBottomPanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 25, 15, 0), -1, -1));
        leftBottomPanel.setOpaque(false);
        leftPanel.add(leftBottomPanel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 70), new Dimension(-1, 70), null, 0, false));
        final Spacer spacer2 = new Spacer();
        leftBottomPanel.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 15), null, null, 0, false));
        exitLabel = new JLabel();
        exitLabel.setBackground(new Color(-1247233));
        Font exitLabelFont = this.$$$getFont$$$("Arial Black", Font.BOLD, 12, exitLabel.getFont());
        if (exitLabelFont != null) exitLabel.setFont(exitLabelFont);
        exitLabel.setForeground(new Color(-3947581));
        exitLabel.setText("Zamknij ");
        leftBottomPanel.add(exitLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        logoutLabel = new JLabel();
        logoutLabel.setBackground(new Color(-1247233));
        Font logoutLabelFont = this.$$$getFont$$$("Arial Black", Font.BOLD, 12, logoutLabel.getFont());
        if (logoutLabelFont != null) logoutLabel.setFont(logoutLabelFont);
        logoutLabel.setForeground(new Color(-3947581));
        logoutLabel.setText("Wyloguj");
        leftBottomPanel.add(logoutLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_SOUTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        leftBottomPanel.add(spacer3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), null, new Dimension(-1, 10), 0, false));
        containerContentPanel = new JPanel();
        containerContentPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));
        containerContentPanel.setMaximumSize(new Dimension(1150, 770));
        containerContentPanel.setMinimumSize(new Dimension(1150, 770));
        containerContentPanel.setOpaque(false);
        containerContentPanel.setPreferredSize(new Dimension(1150, 770));
        mainPanel.add(containerContentPanel, BorderLayout.CENTER);
        topContentPanel = new JPanel();
        topContentPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        topContentPanel.setBackground(new Color(-12748334));
        topContentPanel.setOpaque(true);
        containerContentPanel.add(topContentPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 70), null, null, 0, false));
        fullTextSearchPanel = new JPanel();
        fullTextSearchPanel.setLayout(new GridLayoutManager(6, 5, new Insets(0, 0, 0, 0), -1, -1));
        fullTextSearchPanel.setOpaque(false);
        topContentPanel.add(fullTextSearchPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        fullTextSearchField = new JTextField();
        fullTextSearchPanel.add(fullTextSearchField, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(250, -1), new Dimension(250, -1), new Dimension(250, -1), 0, false));
        final Spacer spacer4 = new Spacer();
        fullTextSearchPanel.add(spacer4, new GridConstraints(1, 4, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        headersRadioButton = new JRadioButton();
        headersRadioButton.setOpaque(false);
        headersRadioButton.setSelected(true);
        headersRadioButton.setText("Główki haseł");
        fullTextSearchPanel.add(headersRadioButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        contentRadioButton = new JRadioButton();
        contentRadioButton.setOpaque(false);
        contentRadioButton.setSelected(true);
        contentRadioButton.setText("Treść haseł");
        fullTextSearchPanel.add(contentRadioButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fullTextSearchButton = new JButton();
        fullTextSearchButton.setIcon(new ImageIcon(getClass().getResource("/img/arrows/search.png")));
        fullTextSearchButton.setLabel("");
        fullTextSearchButton.setOpaque(false);
        fullTextSearchButton.setText("");
        fullTextSearchPanel.add(fullTextSearchButton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(30, 30), new Dimension(30, 30), new Dimension(30, 30), 0, false));
        final Spacer spacer5 = new Spacer();
        fullTextSearchPanel.add(spacer5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        fullTextSearchPanel.add(spacer6, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        allVersionsRadioButton = new JRadioButton();
        allVersionsRadioButton.setOpaque(false);
        allVersionsRadioButton.setText("Wszystkie wersje");
        fullTextSearchPanel.add(allVersionsRadioButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        fullTextSearchPanel.add(spacer7, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(30, -1), new Dimension(30, -1), null, 0, false));
        fullTextSearchLabel = new JLabel();
        fullTextSearchLabel.setForeground(new Color(-4506768));
        fullTextSearchLabel.setRequestFocusEnabled(true);
        fullTextSearchLabel.setText("");
        fullTextSearchPanel.add(fullTextSearchLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bottomContainerContentPanel = new JPanel();
        bottomContainerContentPanel.setLayout(new GridLayoutManager(9, 2, new Insets(0, 0, 0, 0), 0, 0));
        bottomContainerContentPanel.setOpaque(true);
        containerContentPanel.add(bottomContainerContentPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        leftContainerPropertiesPanel = new JPanel();
        leftContainerPropertiesPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        leftContainerPropertiesPanel.setOpaque(true);
        bottomContainerContentPanel.add(leftContainerPropertiesPanel, new GridConstraints(0, 0, 9, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(250, 700), null, new Dimension(500, -1), 0, false));
        topPropertiesPanel = new JPanel();
        topPropertiesPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 10, 0, 0), -1, -1));
        topPropertiesPanel.setBackground(new Color(-9589762));
        topPropertiesPanel.setOpaque(true);
        leftContainerPropertiesPanel.add(topPropertiesPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(250, 200), null, null, 0, false));
        loggedUserLabel = new JLabel();
        loggedUserLabel.setText("");
        topPropertiesPanel.add(loggedUserLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bottomPropertiesPanel = new JPanel();
        bottomPropertiesPanel.setLayout(new CardLayout(0, 0));
        bottomPropertiesPanel.setBackground(new Color(-12748334));
        bottomPropertiesPanel.setOpaque(true);
        leftContainerPropertiesPanel.add(bottomPropertiesPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(250, 500), null, null, 0, false));
        bottomPropertiesTermsPanel = new JPanel();
        bottomPropertiesTermsPanel.setLayout(new GridLayoutManager(13, 1, new Insets(15, 5, 225, 5), 5, 5));
        bottomPropertiesTermsPanel.setBackground(new Color(-12748334));
        bottomPropertiesTermsPanel.setName("bottomPropertiesTermsPanel");
        bottomPropertiesTermsPanel.setOpaque(true);
        bottomPropertiesPanel.add(bottomPropertiesTermsPanel, "bottomPropertiesTermsPanel");
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 11, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("Hasła:");
        bottomPropertiesTermsPanel.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TermsList = new JList();
        TermsList.setBackground(new Color(-12748334));
        final DefaultListModel defaultListModel2 = new DefaultListModel();
        defaultListModel2.addElement("Wszystkie");
        defaultListModel2.addElement("Filtruj");
        defaultListModel2.addElement("Dodaj hasło");
        TermsList.setModel(defaultListModel2);
        TermsList.setOpaque(false);
        TermsList.setSelectionMode(0);
        bottomPropertiesTermsPanel.add(TermsList, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 50), null, 0, false));
        searchButton = new JButton();
        searchButton.setOpaque(true);
        searchButton.setText("Szukaj");
        searchButton.setVisible(false);
        bottomPropertiesTermsPanel.add(searchButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        bottomPropertiesTermsPanel.add(spacer8, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        editCheckBox = new JCheckBox();
        editCheckBox.setOpaque(false);
        editCheckBox.setText("Edytować");
        editCheckBox.setVisible(false);
        bottomPropertiesTermsPanel.add(editCheckBox, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addTermButton = new JButton();
        addTermButton.setText("Dodaj hasło");
        addTermButton.setVisible(false);
        bottomPropertiesTermsPanel.add(addTermButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        getStatusesForSubcategoryButton = new JButton();
        getStatusesForSubcategoryButton.setActionCommand("");
        getStatusesForSubcategoryButton.setLabel("Pobierz statusy dla podkategorii");
        getStatusesForSubcategoryButton.setText("Pobierz statusy dla podkategorii");
        getStatusesForSubcategoryButton.setVisible(false);
        bottomPropertiesTermsPanel.add(getStatusesForSubcategoryButton, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        updateTermButton = new JButton();
        updateTermButton.setText("Aktualizuj hasło");
        updateTermButton.setVisible(false);
        bottomPropertiesTermsPanel.add(updateTermButton, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sendTermToNextStepButton = new JButton();
        sendTermToNextStepButton.setText("Prześlij hasło na następny krok");
        sendTermToNextStepButton.setVisible(false);
        bottomPropertiesTermsPanel.add(sendTermToNextStepButton, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        manageTermStatusesButton = new JButton();
        manageTermStatusesButton.setText("Zarządzaj statusami hasła");
        manageTermStatusesButton.setVisible(false);
        bottomPropertiesTermsPanel.add(manageTermStatusesButton, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        manageTermVersionsButton = new JButton();
        manageTermVersionsButton.setText("Zarządzaj wersjami hasła");
        manageTermVersionsButton.setVisible(false);
        bottomPropertiesTermsPanel.add(manageTermVersionsButton, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        redirectTermToIndicatedAuthorButton = new JButton();
        redirectTermToIndicatedAuthorButton.setText("Przekaż hasło do wksazanego autora");
        redirectTermToIndicatedAuthorButton.setVisible(false);
        bottomPropertiesTermsPanel.add(redirectTermToIndicatedAuthorButton, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        manageTermReferenceButton = new JButton();
        manageTermReferenceButton.setText("Przekaż hasło do wksazanego autora");
        manageTermReferenceButton.setVisible(false);
        bottomPropertiesTermsPanel.add(manageTermReferenceButton, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        bottomPropertiesAuthorsPanel = new JPanel();
        bottomPropertiesAuthorsPanel.setLayout(new GridLayoutManager(6, 1, new Insets(15, 5, 225, 5), -1, -1));
        bottomPropertiesAuthorsPanel.setBackground(new Color(-15680741));
        bottomPropertiesAuthorsPanel.setName("bottomPropertiesAuthorsPanel");
        bottomPropertiesAuthorsPanel.setOpaque(false);
        bottomPropertiesPanel.add(bottomPropertiesAuthorsPanel, "bottomPropertiesAuthorsPanel");
        authorsList = new JList();
        authorsList.setBackground(new Color(-12748334));
        authorsList.setDoubleBuffered(true);
        final DefaultListModel defaultListModel3 = new DefaultListModel();
        defaultListModel3.addElement("Zarządzanie autorami");
        defaultListModel3.addElement("-");
        authorsList.setModel(defaultListModel3);
        authorsList.setOpaque(false);
        bottomPropertiesAuthorsPanel.add(authorsList, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final Spacer spacer9 = new Spacer();
        bottomPropertiesAuthorsPanel.add(spacer9, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        addAuthorButton = new JButton();
        addAuthorButton.setText("Dodaj autora");
        addAuthorButton.setVisible(false);
        bottomPropertiesAuthorsPanel.add(addAuthorButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        updateAuthorButton = new JButton();
        updateAuthorButton.setText("Aktualizuj autora");
        updateAuthorButton.setVisible(false);
        bottomPropertiesAuthorsPanel.add(updateAuthorButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteAuthorButton = new JButton();
        deleteAuthorButton.setText("Usuń autora");
        deleteAuthorButton.setVisible(false);
        bottomPropertiesAuthorsPanel.add(deleteAuthorButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 11, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("Autorzy:");
        bottomPropertiesAuthorsPanel.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bottomPropertiesBlankPanel = new JPanel();
        bottomPropertiesBlankPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        bottomPropertiesBlankPanel.setName("bottomPropertiesBlankPanel");
        bottomPropertiesBlankPanel.setOpaque(false);
        bottomPropertiesPanel.add(bottomPropertiesBlankPanel, "bottomPropertiesBlankPanel");
        bottomPropertiesAgreementsPanel = new JPanel();
        bottomPropertiesAgreementsPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        bottomPropertiesAgreementsPanel.setBackground(new Color(-4513357));
        bottomPropertiesAgreementsPanel.setName("bottomPropertiesAgreementsPanel");
        bottomPropertiesAgreementsPanel.setOpaque(true);
        bottomPropertiesPanel.add(bottomPropertiesAgreementsPanel, "bottomPropertiesAgreementsPanel");
        bottomPropertiesIllustrationsPanel = new JPanel();
        bottomPropertiesIllustrationsPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        bottomPropertiesIllustrationsPanel.setBackground(new Color(-4539636));
        bottomPropertiesIllustrationsPanel.setName("bottomPropertiesIllustrationsPanel");
        bottomPropertiesPanel.add(bottomPropertiesIllustrationsPanel, "bottomPropertiesIllustrationsPanel");
        bottomPropertiesSettingsPanel = new JPanel();
        bottomPropertiesSettingsPanel.setLayout(new GridLayoutManager(3, 1, new Insets(15, 5, 225, 5), -1, -1));
        bottomPropertiesSettingsPanel.setBackground(new Color(-4494843));
        bottomPropertiesSettingsPanel.setName("bottomPropertiesSettingsPanel");
        bottomPropertiesSettingsPanel.setOpaque(false);
        bottomPropertiesPanel.add(bottomPropertiesSettingsPanel, "bottomPropertiesSettingsPanel");
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 11, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setText("Ustawienia:");
        bottomPropertiesSettingsPanel.add(label4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        bottomPropertiesSettingsPanel.add(spacer10, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        settingsList = new JList();
        settingsList.setBackground(new Color(-12748334));
        final DefaultListModel defaultListModel4 = new DefaultListModel();
        defaultListModel4.addElement("Podkategorie");
        defaultListModel4.addElement("Tagi");
        defaultListModel4.addElement("Użytkownicy");
        settingsList.setModel(defaultListModel4);
        settingsList.setOpaque(false);
        settingsList.setSelectionMode(0);
        bottomPropertiesSettingsPanel.add(settingsList, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 50), null, 1, false));
        rightContainerDetailsPanel = new JPanel();
        rightContainerDetailsPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        rightContainerDetailsPanel.setOpaque(true);
        bottomContainerContentPanel.add(rightContainerDetailsPanel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(900, 700), new Dimension(900, 700), new Dimension(900, 700), 0, false));
        topDetailsPanel = new JPanel();
        topDetailsPanel.setLayout(new CardLayout(0, 0));
        topDetailsPanel.setBackground(new Color(-2829100));
        topDetailsPanel.setName("topDetailsPanel");
        topDetailsPanel.setOpaque(false);
        rightContainerDetailsPanel.add(topDetailsPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(900, 200), new Dimension(900, -1), new Dimension(900, -1), 0, false));
        topDetailsPanelBlank = new JPanel();
        topDetailsPanelBlank.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        topDetailsPanelBlank.setName("topDetailsPanelBlank");
        topDetailsPanelBlank.setOpaque(false);
        topDetailsPanel.add(topDetailsPanelBlank, "topDetailsPanelBlank");
        final Spacer spacer11 = new Spacer();
        topDetailsPanelBlank.add(spacer11, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        topTermDetailsPanel = new JPanel();
        topTermDetailsPanel.setLayout(new GridLayoutManager(1, 3, new Insets(5, 5, 5, 5), -1, -1));
        topTermDetailsPanel.setMaximumSize(new Dimension(900, 200));
        topTermDetailsPanel.setMinimumSize(new Dimension(900, 200));
        topTermDetailsPanel.setName("topTermDetailsPanel");
        topTermDetailsPanel.setOpaque(false);
        topTermDetailsPanel.setPreferredSize(new Dimension(900, 200));
        topDetailsPanel.add(topTermDetailsPanel, "topTermDetailsPanel");
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(5, 5, 5, 5), -1, -1));
        panel1.setOpaque(false);
        topTermDetailsPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scrollAuthors.putClientProperty("html.disable", Boolean.FALSE);
        panel1.add(scrollAuthors, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(400, 150), new Dimension(400, 150), new Dimension(400, 150), 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Autorzy");
        panel1.add(label5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 2, new Insets(5, 5, 5, 5), -1, -1));
        panel2.setOpaque(false);
        topTermDetailsPanel.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Tagi");
        panel2.add(label6, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel2.add(tagTermScroll, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, 1, 1, new Dimension(150, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(2, 1, new Insets(5, 5, 5, 5), -1, -1));
        panel3.setOpaque(false);
        topTermDetailsPanel.add(panel3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Scieżka statusów hasła (zaznaczony aktualny etap)");
        panel3.add(label7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel3.add(scrollPane2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane2.setViewportView(statusesTermJList);
        topDetailsTagsPanel = new JPanel();
        topDetailsTagsPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        topDetailsTagsPanel.setName("topDetailsTagsPanel");
        topDetailsPanel.add(topDetailsTagsPanel, "topDetailsTagsPanel");
        bottomDetailsPanel = new JPanel();
        bottomDetailsPanel.setLayout(new CardLayout(0, 0));
        bottomDetailsPanel.setBackground(new Color(-3947581));
        bottomDetailsPanel.setOpaque(false);
        rightContainerDetailsPanel.add(bottomDetailsPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(500, 500), null, null, 0, false));
        bottomDetailsTermsPanel = new JPanel();
        bottomDetailsTermsPanel.setLayout(new GridLayoutManager(2, 1, new Insets(25, 25, 0, 25), -1, -1));
        bottomDetailsTermsPanel.setBackground(new Color(-12511981));
        bottomDetailsTermsPanel.setName("bottomDetailsTermsPanel");
        bottomDetailsTermsPanel.setOpaque(false);
        bottomDetailsTermsPanel.setVisible(false);
        bottomDetailsPanel.add(bottomDetailsTermsPanel, "bottomDetailsTermsPanel");
        termsTableScrollPane = new JScrollPane();
        termsTableScrollPane.setAutoscrolls(true);
        termsTableScrollPane.setOpaque(false);
        termsTableScrollPane.setVerticalScrollBarPolicy(20);
        termsTableScrollPane.setVisible(true);
        bottomDetailsTermsPanel.add(termsTableScrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        termsTable.setBackground(new Color(-257));
        termsTable.setCellSelectionEnabled(false);
        termsTable.setOpaque(false);
        termsTableScrollPane.setViewportView(termsTable);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setOpaque(false);
        bottomDetailsTermsPanel.add(panel4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 25), new Dimension(-1, 25), new Dimension(-1, 25), 0, false));
        termsErrorLabel = new JLabel();
        termsErrorLabel.setText("");
        panel4.add(termsErrorLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bottomDetailsBlankPanel = new JPanel();
        bottomDetailsBlankPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        bottomDetailsBlankPanel.setBackground(new Color(-12631789));
        bottomDetailsBlankPanel.setName("bottomDetailsBlankPanel");
        bottomDetailsBlankPanel.setOpaque(false);
        bottomDetailsBlankPanel.setVisible(false);
        bottomDetailsPanel.add(bottomDetailsBlankPanel, "bottomDetailsBlankPanel");
        final Spacer spacer12 = new Spacer();
        bottomDetailsBlankPanel.add(spacer12, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        bottomDetailsAuthorsPanel = new JPanel();
        bottomDetailsAuthorsPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        bottomDetailsAuthorsPanel.setName("bottomDetailsAuthorsPanel");
        bottomDetailsAuthorsPanel.setOpaque(false);
        bottomDetailsPanel.add(bottomDetailsAuthorsPanel, "bottomDetailsAuthorsPanel");
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(3, 8, new Insets(10, 10, 10, 10), -1, -1));
        panel5.setOpaque(false);
        bottomDetailsAuthorsPanel.add(panel5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Imię: ");
        panel5.add(label8, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        nameTextField = new JTextField();
        panel5.add(nameTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(150, -1), null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Nazwisko: ");
        panel5.add(label9, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        surnameTextField = new JTextField();
        panel5.add(surnameTextField, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(150, -1), null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Telefon:");
        panel5.add(label10, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 1, false));
        signTextField = new JTextField();
        panel5.add(signTextField, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(150, -1), null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setText("Email: ");
        panel5.add(label11, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        emailTextField = new JTextField();
        panel5.add(emailTextField, new GridConstraints(1, 7, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(150, -1), null, 0, false));
        authorsIdLabel = new JLabel();
        authorsIdLabel.setText("ID:");
        panel5.add(authorsIdLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel6.setOpaque(false);
        panel5.add(panel6, new GridConstraints(2, 0, 1, 8, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        authorPanelErrorLabel = new JLabel();
        authorPanelErrorLabel.setText("");
        panel6.add(authorPanelErrorLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_SOUTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 1, new Insets(10, 10, 60, 20), -1, -1));
        panel7.setOpaque(false);
        bottomDetailsAuthorsPanel.add(panel7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 400), new Dimension(-1, 400), new Dimension(-1, 400), 0, false));
        final JScrollPane scrollPane3 = new JScrollPane();
        panel7.add(scrollPane3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        authorsMenageTable.setOpaque(false);
        authorsMenageTable.setPreferredScrollableViewportSize(new Dimension(450, 400));
        scrollPane3.setViewportView(authorsMenageTable);
        bottomDetailsTagsPanel = new JPanel();
        bottomDetailsTagsPanel.setLayout(new GridLayoutManager(1, 2, new Insets(25, 25, 25, 25), -1, -1));
        bottomDetailsTagsPanel.setName("bottomDetailsTagsPanel");
        bottomDetailsTagsPanel.setOpaque(false);
        bottomDetailsPanel.add(bottomDetailsTagsPanel, "bottomDetailsTagsPanel");
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(9, 2, new Insets(5, 5, 5, 5), -1, -1));
        panel8.setOpaque(false);
        bottomDetailsTagsPanel.add(panel8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(410, -1), new Dimension(410, -1), new Dimension(410, -1), 1, false));
        addTagButton = new JButton();
        addTagButton.setText("Dodaj tag");
        panel8.add(addTagButton, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteTagButton = new JButton();
        deleteTagButton.setText("Usuń tag");
        panel8.add(deleteTagButton, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel8.add(panel9, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        choseTagColorButton = new JButton();
        choseTagColorButton.setText("Wybierz kolor");
        panel9.add(choseTagColorButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tagIconLabel = new JLabel();
        tagIconLabel.setText("");
        panel9.add(tagIconLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tagsNameField = new JTextField();
        tagsNameField.setText("");
        panel8.add(tagsNameField, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JScrollPane scrollPane4 = new JScrollPane();
        panel8.add(scrollPane4, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final DefaultListModel defaultListModel5 = new DefaultListModel();
        list5.setModel(defaultListModel5);
        list5.setOpaque(true);
        scrollPane4.setViewportView(list5);
        final JLabel label12 = new JLabel();
        label12.setText("Tagi:");
        panel8.add(label12, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer13 = new Spacer();
        panel8.add(spacer13, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        updateTagButton = new JButton();
        updateTagButton.setAlignmentY(0.5f);
        updateTagButton.setText("Aktualizuj tag");
        panel8.add(updateTagButton, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tagsSettingsErrorLabel = new JLabel();
        tagsSettingsErrorLabel.setText("");
        panel8.add(tagsSettingsErrorLabel, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new GridLayoutManager(3, 1, new Insets(5, 5, 5, 5), -1, -1));
        panel10.setOpaque(false);
        bottomDetailsTagsPanel.add(panel10, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(410, -1), new Dimension(410, -1), new Dimension(410, -1), 0, false));
        final JScrollPane scrollPane5 = new JScrollPane();
        panel10.add(scrollPane5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        termsTagsTable.setOpaque(false);
        scrollPane5.setViewportView(termsTagsTable);
        final JLabel label13 = new JLabel();
        label13.setText("Hasła oznaczone tagiem:");
        panel10.add(label13, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveTermsToTagButton = new JButton();
        saveTermsToTagButton.setText("Zapisz hasła do tagów");
        panel10.add(saveTermsToTagButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bottomDetailsSettingsPanel = new JPanel();
        bottomDetailsSettingsPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        bottomDetailsSettingsPanel.setMaximumSize(new Dimension(900, 525));
        bottomDetailsSettingsPanel.setMinimumSize(new Dimension(900, 525));
        bottomDetailsSettingsPanel.setName("bottomDetailsSettingsPanel");
        bottomDetailsSettingsPanel.setOpaque(false);
        bottomDetailsSettingsPanel.setPreferredSize(new Dimension(900, 525));
        bottomDetailsPanel.add(bottomDetailsSettingsPanel, "bottomDetailsSettingsPanel");
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel11.setOpaque(false);
        bottomDetailsSettingsPanel.add(panel11, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(900, 400), new Dimension(900, 400), new Dimension(900, 400), 0, false));
        right = new JPanel();
        right.setLayout(new GridLayoutManager(3, 1, new Insets(5, 5, 5, 10), 5, 5));
        right.setOpaque(false);
        panel11.add(right, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(250, 500), new Dimension(250, 500), new Dimension(250, 500), 0, true));
        final JLabel label14 = new JLabel();
        label14.setText("Wybrane statusy:");
        right.add(label14, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveSubcategoryStatuses = new JButton();
        saveSubcategoryStatuses.setText("Zapisz statusy dla kategorii");
        right.add(saveSubcategoryStatuses, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane6 = new JScrollPane();
        right.add(scrollPane6, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list4.setMaximumSize(new Dimension(-1, -1));
        list4.setMinimumSize(new Dimension(-1, -1));
        final DefaultListModel defaultListModel6 = new DefaultListModel();
        list4.setModel(defaultListModel6);
        list4.setOpaque(true);
        list4.setPreferredSize(new Dimension(-1, -1));
        scrollPane6.setViewportView(list4);
        left = new JPanel();
        left.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), 3, 3));
        left.setOpaque(false);
        panel11.add(left, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(370, 500), new Dimension(370, 500), new Dimension(370, 500), 0, true));
        final JLabel label15 = new JLabel();
        label15.setName("");
        label15.setText("Podkategoria ");
        left.add(label15, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addSubcategoryButton = new JButton();
        addSubcategoryButton.setText("Dodaj");
        left.add(addSubcategoryButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(290, -1), new Dimension(290, -1), new Dimension(290, -1), 0, false));
        SubcategoryTextField = new JTextField();
        SubcategoryTextField.setText("");
        left.add(SubcategoryTextField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(290, -1), new Dimension(290, -1), new Dimension(290, -1), 0, false));
        final Spacer spacer14 = new Spacer();
        left.add(spacer14, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, new Dimension(-1, 100), 0, false));
        deleteSubcategoryButton = new JButton();
        deleteSubcategoryButton.setText("Usuń");
        left.add(deleteSubcategoryButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(290, -1), new Dimension(290, -1), new Dimension(290, -1), 0, false));
        final JScrollPane scrollPane7 = new JScrollPane();
        left.add(scrollPane7, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        scrollPane7.setViewportView(list2);
        leftCenter = new JPanel();
        leftCenter.setLayout(new GridLayoutManager(2, 1, new Insets(5, 5, 5, 5), 5, 5));
        leftCenter.setOpaque(false);
        panel11.add(leftCenter, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(250, 500), new Dimension(250, 500), new Dimension(370, 500), 0, true));
        final JLabel label16 = new JLabel();
        label16.setText("Dostępne statusy:");
        leftCenter.add(label16, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane8 = new JScrollPane();
        leftCenter.add(scrollPane8, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list3.setMaximumSize(new Dimension(-1, -1));
        list3.setMinimumSize(new Dimension(-1, -1));
        final DefaultListModel defaultListModel7 = new DefaultListModel();
        list3.setModel(defaultListModel7);
        list3.setOpaque(true);
        list3.setPreferredSize(new Dimension(-1, -1));
        scrollPane8.setViewportView(list3);
        rightCenter = new JPanel();
        rightCenter.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        rightCenter.setOpaque(false);
        panel11.add(rightCenter, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(30, -1), new Dimension(30, -1), new Dimension(30, -1), 0, false));
        upButton = new JButton();
        upButton.setActionCommand("");
        upButton.setIcon(new ImageIcon(getClass().getResource("/img/arrows/up.png")));
        upButton.setText("");
        rightCenter.add(upButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(30, 30), new Dimension(30, 30), new Dimension(30, 30), 0, false));
        downButton = new JButton();
        downButton.setIcon(new ImageIcon(getClass().getResource("/img/arrows/down.png")));
        downButton.setLabel("");
        downButton.setText("");
        rightCenter.add(downButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(30, 30), new Dimension(30, 30), new Dimension(30, 30), 0, false));
        leftButton = new JButton();
        leftButton.setIcon(new ImageIcon(getClass().getResource("/img/arrows/left.png")));
        leftButton.setLabel("");
        leftButton.setText("");
        rightCenter.add(leftButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(30, 30), new Dimension(30, 30), new Dimension(30, 30), 0, false));
        rightButton = new JButton();
        rightButton.setIcon(new ImageIcon(getClass().getResource("/img/arrows/right.png")));
        rightButton.setInheritsPopupMenu(false);
        rightButton.setText("");
        rightCenter.add(rightButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(30, 30), new Dimension(30, 30), new Dimension(30, 30), 0, false));
        final Spacer spacer15 = new Spacer();
        rightCenter.add(spacer15, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer16 = new Spacer();
        rightCenter.add(spacer16, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 35), -1, -1));
        panel12.setOpaque(false);
        bottomDetailsSettingsPanel.add(panel12, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(900, 25), new Dimension(900, 25), new Dimension(900, 25), 0, false));
        settingsErrorLabel = new JLabel();
        settingsErrorLabel.setAutoscrolls(true);
        settingsErrorLabel.setText("");
        panel12.add(settingsErrorLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bottomDetailsTermPanel = new JPanel();
        bottomDetailsTermPanel.setLayout(new GridBagLayout());
        bottomDetailsTermPanel.setBackground(new Color(-15582399));
        bottomDetailsTermPanel.setForeground(new Color(-4501441));
        bottomDetailsTermPanel.setMaximumSize(new Dimension(-1, -1));
        bottomDetailsTermPanel.setMinimumSize(new Dimension(-1, -1));
        bottomDetailsTermPanel.setName("bottomDetailsTermPanel");
        bottomDetailsTermPanel.setOpaque(false);
        bottomDetailsTermPanel.setPreferredSize(new Dimension(-1, -1));
        bottomDetailsTermPanel.setRequestFocusEnabled(true);
        bottomDetailsPanel.add(bottomDetailsTermPanel, "bottomDetailsTermPanel");
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new GridLayoutManager(4, 1, new Insets(10, 10, 30, 10), -1, -1));
        panel13.setOpaque(false);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 14;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        bottomDetailsTermPanel.add(panel13, gbc);
        final JPanel panel14 = new JPanel();
        panel14.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel14.setMaximumSize(new Dimension(-1, -1));
        panel14.setMinimumSize(new Dimension(-1, -1));
        panel14.setOpaque(false);
        panel14.setPreferredSize(new Dimension(-1, -1));
        panel14.setVisible(false);
        panel13.add(panel14, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 50), new Dimension(-1, 50), new Dimension(-1, 50), 0, false));
        termHistoryVersionLabel = new JLabel();
        termHistoryVersionLabel.setText("Numer wersji:");
        panel14.add(termHistoryVersionLabel);
        setAsActualVersionButton = new JButton();
        setAsActualVersionButton.setText("Ustaw jako aktualna");
        panel14.add(setAsActualVersionButton);
        boldButton = new JButton();
        boldButton.setHideActionText(true);
        boldButton.setIcon(new ImageIcon(getClass().getResource("/img/arrows/bold.png")));
        boldButton.setMaximumSize(new Dimension(19, 19));
        boldButton.setMinimumSize(new Dimension(19, 19));
        boldButton.setPreferredSize(new Dimension(19, 19));
        boldButton.setText("");
        boldButton.setVisible(false);
        panel14.add(boldButton);
        italicsButton = new JButton();
        italicsButton.setIcon(new ImageIcon(getClass().getResource("/img/arrows/italic.png")));
        italicsButton.setLabel("");
        italicsButton.setMaximumSize(new Dimension(19, 19));
        italicsButton.setMinimumSize(new Dimension(19, 19));
        italicsButton.setPreferredSize(new Dimension(19, 19));
        italicsButton.setText("");
        italicsButton.setVisible(false);
        panel14.add(italicsButton);
        underlineButton = new JButton();
        underlineButton.setIcon(new ImageIcon(getClass().getResource("/img/arrows/underLine.png")));
        underlineButton.setLabel("");
        underlineButton.setMaximumSize(new Dimension(19, 19));
        underlineButton.setMinimumSize(new Dimension(19, 19));
        underlineButton.setPreferredSize(new Dimension(19, 19));
        underlineButton.setText("");
        underlineButton.setVisible(false);
        panel14.add(underlineButton);
        shefPanel = new JPanel();
        shefPanel.setLayout(new BorderLayout(0, 0));
        shefPanel.setMaximumSize(new Dimension(-1, -1));
        shefPanel.setMinimumSize(new Dimension(-1, -1));
        shefPanel.setOpaque(false);
        shefPanel.setPreferredSize(new Dimension(-1, -1));
        panel13.add(shefPanel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, new Dimension(-1, 350), new Dimension(-1, 350), new Dimension(-1, 350), 0, false));
        final JPanel panel15 = new JPanel();
        panel15.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel15.setOpaque(false);
        panel13.add(panel15, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        versesTermLabel = new JLabel();
        versesTermLabel.setOpaque(false);
        versesTermLabel.setText("Uzupełnij pola");
        panel13.add(versesTermLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 2, false));
        isSignedCheckBox = new JCheckBox();
        isSignedCheckBox.setOpaque(false);
        isSignedCheckBox.setText("Czy hasło ma być podpisane?");
        gbc = new GridBagConstraints();
        gbc.gridx = 13;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        bottomDetailsTermPanel.add(isSignedCheckBox, gbc);
        termDetailsIdLabel = new JLabel();
        termDetailsIdLabel.setText("ID: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        bottomDetailsTermPanel.add(termDetailsIdLabel, gbc);
        final JLabel label17 = new JLabel();
        label17.setText("Kategoria: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        bottomDetailsTermPanel.add(label17, gbc);
        subcategoryComboBox = new JComboBox();
        subcategoryComboBox.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 11;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        bottomDetailsTermPanel.add(subcategoryComboBox, gbc);
        categoryComboBox = new JComboBox();
        categoryComboBox.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 11;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        bottomDetailsTermPanel.add(categoryComboBox, gbc);
        final JLabel label18 = new JLabel();
        label18.setHorizontalAlignment(4);
        label18.setText("Wersja tego hasła oznaczona jako aktualna: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        bottomDetailsTermPanel.add(label18, gbc);
        final JLabel label19 = new JLabel();
        label19.setText("Podkategoria: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        bottomDetailsTermPanel.add(label19, gbc);
        final JLabel label20 = new JLabel();
        label20.setOpaque(false);
        label20.setText("Nagłówek:  ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        bottomDetailsTermPanel.add(label20, gbc);
        textField1 = new JTextField();
        textField1.setEditable(true);
        textField1.setEnabled(true);
        textField1.setFocusCycleRoot(true);
        Font textField1Font = this.$$$getFont$$$(null, Font.BOLD, 11, textField1.getFont());
        if (textField1Font != null) textField1.setFont(textField1Font);
        textField1.setOpaque(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 12;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 220);
        bottomDetailsTermPanel.add(textField1, gbc);
        actualTermHistoryVersionLabel = new JLabel();
        actualTermHistoryVersionLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 11;
        gbc.fill = GridBagConstraints.BOTH;
        bottomDetailsTermPanel.add(actualTermHistoryVersionLabel, gbc);
        termIdLabel = new JLabel();
        termIdLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 13;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        bottomDetailsTermPanel.add(termIdLabel, gbc);
        final JLabel label21 = new JLabel();
        label21.setText("Wersja hasła aktualnie edytowana: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        bottomDetailsTermPanel.add(label21, gbc);
        editedTermHistoryVersionLabel = new JLabel();
        editedTermHistoryVersionLabel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 11;
        gbc.fill = GridBagConstraints.BOTH;
        bottomDetailsTermPanel.add(editedTermHistoryVersionLabel, gbc);
        bottomDetailsFiltersPanel = new JPanel();
        bottomDetailsFiltersPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        bottomDetailsFiltersPanel.setName("bottomDetailsFiltersPanel");
        bottomDetailsPanel.add(bottomDetailsFiltersPanel, "bottomDetailsFiltersPanel");
        topDetailsPanelFilters = new JPanel();
        topDetailsPanelFilters.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        topDetailsPanelFilters.setMaximumSize(new Dimension(900, 2147483647));
        topDetailsPanelFilters.setMinimumSize(new Dimension(900, 58));
        topDetailsPanelFilters.setName("topDetailsPanelFilters");
        topDetailsPanelFilters.setOpaque(false);
        topDetailsPanelFilters.setPreferredSize(new Dimension(900, 156));
        bottomDetailsFiltersPanel.add(topDetailsPanelFilters, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        GroupJPanel = new JPanel();
        GroupJPanel.setLayout(new GridLayoutManager(3, 1, new Insets(5, 5, 35, 5), -1, -1));
        GroupJPanel.setName("GroupJPanel");
        GroupJPanel.setOpaque(true);
        topDetailsPanelFilters.add(GroupJPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label22 = new JLabel();
        label22.setText("Kategorie");
        GroupJPanel.add(label22, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane9 = new JScrollPane();
        scrollPane9.setOpaque(true);
        GroupJPanel.add(scrollPane9, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final DefaultListModel defaultListModel8 = new DefaultListModel();
        categoryJList.setModel(defaultListModel8);
        categoryJList.setOpaque(true);
        scrollPane9.setViewportView(categoryJList);
        final JPanel panel16 = new JPanel();
        panel16.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        GroupJPanel.add(panel16, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        categoryMinusButton = new JButton();
        categoryMinusButton.setIcon(new ImageIcon(getClass().getResource("/img/arrows/minus.png")));
        categoryMinusButton.setText("");
        panel16.add(categoryMinusButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(30, 30), new Dimension(30, 30), new Dimension(30, 30), 0, false));
        categoryPlusButton = new JButton();
        categoryPlusButton.setIcon(new ImageIcon(getClass().getResource("/img/arrows/plus.png")));
        categoryPlusButton.setText("");
        panel16.add(categoryPlusButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(30, 30), new Dimension(30, 30), new Dimension(30, 30), 0, false));
        SubGroupJPanel = new JPanel();
        SubGroupJPanel.setLayout(new GridLayoutManager(3, 1, new Insets(5, 5, 35, 5), -1, -1));
        topDetailsPanelFilters.add(SubGroupJPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label23 = new JLabel();
        label23.setText("Podkategorie");
        SubGroupJPanel.add(label23, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane10 = new JScrollPane();
        SubGroupJPanel.add(scrollPane10, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane10.setViewportView(subcategoryJList);
        final JPanel panel17 = new JPanel();
        panel17.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        SubGroupJPanel.add(panel17, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        subcategoryMinusButton = new JButton();
        subcategoryMinusButton.setIcon(new ImageIcon(getClass().getResource("/img/arrows/minus.png")));
        subcategoryMinusButton.setLabel("");
        subcategoryMinusButton.setText("");
        panel17.add(subcategoryMinusButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(30, 30), new Dimension(30, 30), new Dimension(30, 30), 0, false));
        subcategoryPlusButton = new JButton();
        subcategoryPlusButton.setIcon(new ImageIcon(getClass().getResource("/img/arrows/plus.png")));
        subcategoryPlusButton.setLabel("");
        subcategoryPlusButton.setText("");
        panel17.add(subcategoryPlusButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(30, 30), new Dimension(30, 30), new Dimension(30, 30), 0, false));
        TagsJPanel = new JPanel();
        TagsJPanel.setLayout(new GridLayoutManager(3, 1, new Insets(5, 5, 35, 5), -1, -1));
        topDetailsPanelFilters.add(TagsJPanel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label24 = new JLabel();
        label24.setText("Tagi");
        TagsJPanel.add(label24, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane11 = new JScrollPane();
        TagsJPanel.add(scrollPane11, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane11.setViewportView(tagsJList);
        final JPanel panel18 = new JPanel();
        panel18.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        TagsJPanel.add(panel18, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        tagsMinusButton = new JButton();
        tagsMinusButton.setIcon(new ImageIcon(getClass().getResource("/img/arrows/minus.png")));
        tagsMinusButton.setLabel("");
        tagsMinusButton.setText("");
        panel18.add(tagsMinusButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(30, 30), new Dimension(30, 30), new Dimension(30, 30), 0, false));
        tagsPlusButton = new JButton();
        tagsPlusButton.setIcon(new ImageIcon(getClass().getResource("/img/arrows/plus.png")));
        tagsPlusButton.setLabel("");
        tagsPlusButton.setText("");
        panel18.add(tagsPlusButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(30, 30), new Dimension(30, 30), new Dimension(30, 30), 0, false));
        StatusJPanel = new JPanel();
        StatusJPanel.setLayout(new GridLayoutManager(3, 1, new Insets(5, 5, 35, 5), -1, -1));
        topDetailsPanelFilters.add(StatusJPanel, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label25 = new JLabel();
        label25.setText("Status");
        StatusJPanel.add(label25, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane12 = new JScrollPane();
        StatusJPanel.add(scrollPane12, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane12.setViewportView(statusesJList);
        final JPanel panel19 = new JPanel();
        panel19.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        StatusJPanel.add(panel19, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        statusMinusButton = new JButton();
        statusMinusButton.setIcon(new ImageIcon(getClass().getResource("/img/arrows/minus.png")));
        statusMinusButton.setLabel("");
        statusMinusButton.setText("");
        panel19.add(statusMinusButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(30, 30), new Dimension(30, 30), new Dimension(30, 30), 0, false));
        statusPlusButton = new JButton();
        statusPlusButton.setIcon(new ImageIcon(getClass().getResource("/img/arrows/plus.png")));
        statusPlusButton.setLabel("");
        statusPlusButton.setText("");
        panel19.add(statusPlusButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(30, 30), new Dimension(30, 30), new Dimension(30, 30), 0, false));
        label8.setLabelFor(nameTextField);
        label9.setLabelFor(surnameTextField);
        label10.setLabelFor(signTextField);
        label11.setLabelFor(emailTextField);
        label17.setLabelFor(categoryComboBox);
        label19.setLabelFor(subcategoryComboBox);
        label20.setLabelFor(textField1);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}



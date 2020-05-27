package gui_swing.ui.model;

import gui_swing.ui.model.Components.ReferencesPanel;
import gui_swing.ui.model.Listeners.TermListeners;
import gui_swing.ui.model.tableModels.ReferencesTermTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReferencesSearchPanel extends JFrame {
    private JPanel mainPanel;
    private JPanel content;
    private JPanel optionsPanel;
    private JScrollPane scrollPane;
    private JTable table;
    private  Integer option;
    private Integer termId;
    private ApiConnector apiConnector;
    private JButton addButton;
    ReferencesPanel referencesParentPanel;

    public ReferencesSearchPanel(Integer option, Integer termId, ReferencesPanel referencesPanel,String... text){
        super();
        this.setOption(option);
        this.setTermId(termId);
        apiConnector = new ApiConnector();
        mainPanel = new JPanel();
        content =new JPanel();
        optionsPanel = new JPanel();
        table = new JTable(new ReferencesTermTableModel());
        scrollPane = new JScrollPane(table);
        content.setLayout(new BorderLayout());
        optionsPanel.setLayout(new FlowLayout());
        content.add(scrollPane);
        mainPanel.add(content,BorderLayout.CENTER);
        addButton = new JButton("Dodaj referencje");
        optionsPanel.add(addButton);
        mainPanel.add(optionsPanel,BorderLayout.SOUTH);
        getContentPane().add(mainPanel);
        this.referencesParentPanel = referencesPanel ;
        switch(getOption()){
            case 3:
            case 1:
                this.setTitle("Propozycje odnośników w haśle: '"+ apiConnector.getTerm(getTermId()).getTitle()+" '");
               if(getOption()==1)
                    ReferencesPanel.fillTableWithTerms(apiConnector.getTermReferencesProposal(getTermId()),table);
               else{
                   apiConnector.getTermsByFullText((ArrayList<Long>) apiConnector.findAllTermsWitchHeaderContains(text[0]));
                   if(apiConnector.getResponseList().size()>0)
                        referencesPanel.fillTableWithTerms((ArrayList<Term>) apiConnector.responseList,table);

               }
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(table.getSelectedRow()>-1){
                           Integer referenceId = (Integer) table.getValueAt(table.getSelectedRow(),0);
                           Boolean result = apiConnector.addReferenceToTerm(termId,referenceId);
                           referencesParentPanel.fillLeftTable(termId);
                           dispose();
                        }
                    }
                });
                break;
            default:
                this.setTitle("Hasło: '"+ apiConnector.getTerm(getTermId()).getTitle()+" ', może występować w poniższych propozycjach.");
                if(getOption()==2)
                    ReferencesPanel.fillTableWithTerms(apiConnector.getTermForWhomThisTermIsReferencedProposal(getTermId()),table);
                else{
                    apiConnector.getTermsByFullText((ArrayList<Long>) apiConnector.findAllTermsWitchHeaderContains(text[0]));
                    if(apiConnector.getResponseList().size()>0)
                        referencesPanel.fillTableWithTerms((ArrayList<Term>) apiConnector.responseList,table);
                }
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(table.getSelectedRow()>-1){
                            Integer referenceId = (Integer) table.getValueAt(table.getSelectedRow(),0);
                            Boolean result = apiConnector.addReferencedToTerm(termId,referenceId);
                            referencesParentPanel.fillRightTable(termId);
                            dispose();
                        }
                    }
                });
        }
        table.addMouseListener(new TermListeners.ReferencesTablesActionListener());

    }

    public Integer getOption() {
        return option;
    }

    public void setOption(Integer option) {
        this.option = option;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }
}

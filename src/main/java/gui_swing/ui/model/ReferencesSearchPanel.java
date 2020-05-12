package gui_swing.ui.model;

import gui_swing.ui.model.tableModels.ReferencesTermTableModel;
import org.glassfish.jersey.internal.jsr166.Flow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public ReferencesSearchPanel(Integer option, Integer termId){
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

        switch(getOption()){
            case 1:
                this.setTitle("Propozycje odnośników w haśle: '"+ apiConnector.getTerm(getTermId()).getTitle()+" '");
                ReferencesPanel.fillTableWithTerms(apiConnector.getTermReferencesProposal(getTermId()),table);
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(table.getSelectedRow()>-1){
                           Integer referenceId = (Integer) table.getValueAt(table.getSelectedRow(),0);
                           Boolean result = apiConnector.addReferenceToTerm(termId,referenceId);
                           dispose();
                        }
                    }
                });
                break;
            default:
                this.setTitle("Hasło: '"+ apiConnector.getTerm(getTermId()).getTitle()+" ', może występować w poniższych propozycjach.");
                ReferencesPanel.fillTableWithTerms(apiConnector.getTermForWhomThisTermIsReferencedProposal(getTermId()),table);

        }

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

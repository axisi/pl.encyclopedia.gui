package gui_swing.ui.model;

import gui_swing.ui.model.Listeners.TermListeners;
import gui_swing.ui.model.tableModels.ReferencesTermTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReferencesPanel extends JFrame {
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel leftLabel;
    private JLabel rightLabel;
    private JTable termContainingReferencesToTable;
    private JScrollPane termContainingReferencesToScrollPanel;
    private JPanel termContainingReferencesToOptionsPanel;

    private JTable termsContainingReferencesToThisTermTable;
    private JScrollPane termsContainingReferencesToThisTermScrollPanel;
    private JPanel termsContainingReferencesToThisTermOptionsPanel;
    private ApiConnector apiConnector;
    // options panels

    private JPanel lTopPanel;
    private JPanel lBotPanel;
    private JButton lProposalButton;
    private JTextField lTextSearchField;
    private JButton lSearchButton;
    private JButton lDeleteButton;
    private JLabel lErrorLabel;

    private JPanel rTopPanel;
    private JPanel rBotPanel;
    private JButton rProposalButton;
    private JTextField rTextSearchField;
    private JButton rSearchButton;
    private JButton rDeleteButton;
    private JLabel rErrorLabel;

    // options panels



    private Integer termId;

    public ReferencesPanel(Integer termId){
        super();
        this.setTermId(termId);
        apiConnector = new ApiConnector();
        mainPanel= new JPanel();
        leftPanel= new JPanel();
        rightPanel= new JPanel();
        //leftPanel.setBackground(Color.RED);
        //rightPanel.setBackground(Color.GREEN);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(leftPanel,BorderLayout.CENTER);
        mainPanel.add(rightPanel,BorderLayout.EAST);
        getContentPane().add(mainPanel);

        leftLabel= new JLabel("Odnośniki w tym haśle:");

        /*Vector<String> strings = new Vector<>();
        strings.addElement("Id");
        strings.addElement("Tytuł");*/
        termContainingReferencesToTable = new JTable(new ReferencesTermTableModel());
        termContainingReferencesToScrollPanel = new JScrollPane(termContainingReferencesToTable);
        fillTableWithTerms(apiConnector.getTermReferences(termId),termContainingReferencesToTable);



       //ArrayList<Term> termsList = apiConnector.getTermReferences(termId);
       // ReferencesTermTableModel referencesTermTableModel= (ReferencesTermTableModel) termContainingReferencesToTable.getModel();
       /* for (Term t: termsList
        ) {
            referencesTermTableModel.addRow(new Object[]{t.getId().intValue(),t.getTitle()});
        }*/

        termContainingReferencesToTable.getTableHeader().setReorderingAllowed(false);

        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(leftLabel,BorderLayout.NORTH);
        leftPanel.add(termContainingReferencesToScrollPanel,BorderLayout.CENTER);
        termContainingReferencesToOptionsPanel = new JPanel();
        leftPanel.add(termContainingReferencesToOptionsPanel,BorderLayout.SOUTH);

        rightLabel = new JLabel("To hasło wystepuje jako odnośnik w:");
        termsContainingReferencesToThisTermTable = new JTable(new ReferencesTermTableModel());
        termsContainingReferencesToThisTermScrollPanel = new JScrollPane(termsContainingReferencesToThisTermTable);
        fillTableWithTerms(apiConnector.getTermForWhomThisTermIsReferenced(termId),termsContainingReferencesToThisTermTable);
       /* termsList= apiConnector.getTermForWhomThisTermIsReferenced(termId);
        ReferencesTermTableModel referencedTermTableModel= (ReferencesTermTableModel) termsContainingReferencesToThisTermTable.getModel();
        for (Term t: termsList
        ) {
            referencedTermTableModel.addRow(new Object[]{t.getId().intValue(),t.getTitle()});
        }*/
        termsContainingReferencesToThisTermTable.getTableHeader().setReorderingAllowed(false);
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(rightLabel,BorderLayout.NORTH);
        rightPanel.add(termsContainingReferencesToThisTermScrollPanel,BorderLayout.CENTER);
        termsContainingReferencesToThisTermOptionsPanel = new JPanel();
        rightPanel.add(termsContainingReferencesToThisTermOptionsPanel, BorderLayout.SOUTH);


        termsContainingReferencesToThisTermTable.addMouseListener(new TermListeners.ReferencesTablesActionListener());
        termContainingReferencesToTable.addMouseListener(new TermListeners.ReferencesTablesActionListener());

        // options panels
        prepareOptionPanel(termContainingReferencesToOptionsPanel,lTopPanel,lBotPanel,lProposalButton,lTextSearchField, lSearchButton, lDeleteButton,lErrorLabel,apiConnector , 1);
        prepareOptionPanel(termsContainingReferencesToThisTermOptionsPanel,rTopPanel,rBotPanel,rProposalButton,rTextSearchField, rSearchButton, rDeleteButton,rErrorLabel,apiConnector,2);


        // options panels
    }

    private void prepareOptionPanel(JPanel rootPanel, JPanel topPanel,
                                    JPanel botPanel, JButton proposalButton,
                                    JTextField textField,JButton searchButton, JButton deleteButton,
                                    JLabel errorLabel, ApiConnector apiConnector,Integer option) {
        rootPanel.setLayout(new BorderLayout());
        topPanel = new JPanel(new FlowLayout());
        botPanel = new JPanel();
        proposalButton= new JButton("Generuj propozycje");
        proposalButton.setToolTipText("Generuj propozycje");
        textField = new JTextField();
        textField.setMinimumSize(new Dimension(150,30));
        textField.setMaximumSize(new Dimension(150,30));
        textField.setPreferredSize(new Dimension(150,30));

        searchButton = new JButton(new ImageIcon("src/main/resources/img/arrows/search.png"));
        searchButton.setPreferredSize(new Dimension(30,30));
        deleteButton = new JButton("Usuń wiersz");
        deleteButton.setToolTipText("Usuń wiersz");
        errorLabel= new JLabel("Test");

        topPanel.add(proposalButton);
        topPanel.add(textField);
        topPanel.add(searchButton);
        topPanel.add(deleteButton);

        botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.Y_AXIS));
        botPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        botPanel.add(errorLabel);

        rootPanel.add(topPanel,BorderLayout.NORTH);
        rootPanel.add(botPanel,BorderLayout.SOUTH);

        switch (option){
            case 1:
                errorLabel.setText("W haśle znaleziono "+howManyReferencesInTerm()+" potencjalnych referencji.");
                proposalButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ReferencesSearchPanel referencesSearchPanel = new ReferencesSearchPanel(option,getTermId());
                        referencesSearchPanel.setMinimumSize(new Dimension(600,500));
                        referencesSearchPanel.setVisible(true);
                    }
                });
            break;
            default:


        }



    }

    private  Integer howManyReferencesInTerm() {
        String content= apiConnector.getActualTermHistoryOfTerm(getTermId()).getContent();
        content =  NCRConverter.html2text(content);
        Integer counter =0;
        for (char c: content.toCharArray()
        ) {
            if(c=='®')
                counter++;
        }
        return counter;
    }

    public static void fillTableWithTerms(ArrayList<Term> termReferences, JTable table) {
        ReferencesTermTableModel referencesTermTableModel= (ReferencesTermTableModel) table.getModel();

        if(referencesTermTableModel.getRowCount()!=0){
            for (int i = referencesTermTableModel.getRowCount()-1; i >-1 ; i--) {
                referencesTermTableModel.removeRow(i);
            }
        }
        for (Term t: termReferences
        ) {
            referencesTermTableModel.addRow(new Object[]{t.getId().intValue(),t.getTitle()});
        }

    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }


}

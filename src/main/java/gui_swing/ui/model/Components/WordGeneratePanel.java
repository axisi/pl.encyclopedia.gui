package gui_swing.ui.model.Components;

import gui_swing.ui.controller.ApplicationFrameController;
import gui_swing.ui.model.ApiConnector;
import gui_swing.ui.model.CreateWord;
import gui_swing.ui.model.Term;
import gui_swing.ui.model.TermHistory;
import gui_swing.ui.model.tableModels.GradientButton;
import gui_swing.ui.model.tableModels.WordGenerateTableModel;
import gui_swing.ui.view.ApplicationFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

public class WordGeneratePanel extends JFrame {

    private ApiConnector apiConnector;
    private JPanel mainJPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JScrollPane scrollPane;
    private JTable jTable;
    private ArrayList<Long> termsArray;
    private ArrayList<Long> termsArrayConst;
    private Dimension dim;
    private Dimension widthDim;


    private JPanel bottomTopPanel;
    private JPanel bottomMainPanel;
    private JButton addButton;
    private JButton minusButton;
    private JButton startGenerateButton;


    public WordGeneratePanel(ArrayList<Long>ids){
        super();
        termsArray= (ArrayList<Long>) ids.clone();
        termsArrayConst= (ArrayList<Long>) ids.clone();
        initComponents();
        insertData();
        showForm();

    }

    private void showForm() {
        this.setMinimumSize(new Dimension((int) (dim.width*0.6-20), (int) (dim.height * 0.8)));

        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.pack();
        this.setVisible(true);
        this.setTitle("Generowanie plików Word...");
    }

    private void insertData() {
        ArrayList<Term> terms = apiConnector.getTermsByIds(termsArray);
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();

        for (Term t: terms
             ) {
            ArrayList<TermHistory> termHistories= apiConnector.getAllTermHistoriesOfTerm(t.getId());
            Vector<Long> longs = new Vector<>();
            //ArrayList<Long> longs = new ArrayList<>();
            for (TermHistory t1: termHistories
                 ) {
                longs.add(t1.getVersion());
            }
            tableModel.addRow(new Object[]{true,t.getId(),t.getTitle(),longs.size(),t.getActualVersion()});
        }
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if(column==4){
                    if(( Long.valueOf(tableModel.getValueAt(row,column).toString())> Long.valueOf(tableModel.getValueAt(row,column-1).toString()))
                        ||( Long.valueOf(tableModel.getValueAt(row,column).toString())< 1l)){
                        tableModel.setValueAt(tableModel.getValueAt(row,column-1),row,column);

                    }


                }
            }
        });
    }

    private void initComponents() {
        apiConnector = new ApiConnector();
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        widthDim = new Dimension((int) (dim.width*0.6-40), (int) (dim.height * 0.6));
        mainJPanel=new JPanel(new BorderLayout());
        getContentPane().add(mainJPanel);

        topPanel= new JPanel();

        mainJPanel.add(topPanel,BorderLayout.NORTH);
        jTable = new JTable(new WordGenerateTableModel());
        scrollPane = new JScrollPane(jTable);
        topPanel.add(scrollPane);
        scrollPane.setPreferredSize(widthDim);
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.setAutoCreateRowSorter(true);
        jTable.getColumnModel().getColumn(0).setPreferredWidth((int)(widthDim.width * 0.1));
        jTable.getColumnModel().getColumn(1).setPreferredWidth((int)(widthDim.width * 0.1));
        jTable.getColumnModel().getColumn(2).setPreferredWidth((int)(widthDim.width * 0.6));
        jTable.getColumnModel().getColumn(3).setPreferredWidth((int)(widthDim.width * 0.1));
        jTable.getColumnModel().getColumn(4).setPreferredWidth((int)(widthDim.width * 0.1));

        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    Long termId = Long.valueOf(jTable.getValueAt(jTable.getSelectedRow(),1).toString());
                    ArrayList<TermHistory> termHistoryList = apiConnector.getAllTermHistoriesOfTerm(termId);
                    Long termHistoryId= -1l;
                    for (TermHistory t:termHistoryList
                         ) {
                        if(t.getVersion()== Long.valueOf(jTable.getValueAt(jTable.getSelectedRow(),4).toString())){
                            termHistoryId= t.getId();
                        }
                    }
                    if(termHistoryId>-1l)
                        ApplicationFrameController.termWindows.add(new TermWindow(termId.intValue(),termHistoryId.intValue()));

                }
            }
        });

        initButtonsPanel();

    }

    private void initButtonsPanel() {
        bottomPanel= new JPanel(new BorderLayout());
        mainJPanel.add(bottomPanel,BorderLayout.SOUTH);
        bottomTopPanel = new JPanel();
        bottomPanel.add(bottomTopPanel,BorderLayout.WEST);
        addButton = new JButton(new ImageIcon(getClass().getResource("/img/arrows/plus.png")));
        addButton.setPreferredSize(new Dimension(30,30));
        minusButton = new JButton(new ImageIcon(getClass().getResource("/img/arrows/minus.png")));
        minusButton.setPreferredSize(new Dimension(30,30));
        addButton.setBorder(new EmptyBorder(15,15,15,15));
        minusButton.setBorder(new EmptyBorder(15,15,15,15));
        addButton.setToolTipText("Zaznacz wszystkie...");
        minusButton.setToolTipText("Odznacz wszystkie...");
        bottomTopPanel.add(addButton);
        bottomTopPanel.add(minusButton);
        bottomMainPanel = new JPanel();
        bottomPanel.add(bottomMainPanel,BorderLayout.CENTER);
        startGenerateButton = new GradientButton("Generuj", Color.GREEN.darker());
        bottomMainPanel.add(startGenerateButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < jTable.getRowCount(); i++) {
                    jTable.getModel().setValueAt(true,i,0);
                }
            }
        });
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < jTable.getRowCount(); i++) {
                    jTable.getModel().setValueAt(false,i,0);
                }
            }
        });

        startGenerateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateWord(jTable);
            }
        });
    }
}

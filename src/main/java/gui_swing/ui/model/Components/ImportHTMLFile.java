package gui_swing.ui.model.Components;

import com.fasterxml.jackson.core.JsonProcessingException;
import gui_swing.ui.controller.ApplicationFrameController;
import gui_swing.ui.model.ApiConnector;

import gui_swing.ui.model.pojo.Term;
import gui_swing.ui.model.pojo.TermHistory;
import gui_swing.ui.model.tableModels.GradientButton;
import gui_swing.ui.model.tableModels.ImportedTermsTableModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class ImportHTMLFile extends JFrame {
   private File input;
   private Document doc;
    private ArrayList<Term> terms;
    private ApiConnector apiConnector;
    private Dimension dim;
    private Dimension widthDim;

    private JPanel mainJPanel;
    private JPanel topJPanel;
    private JPanel centerJPanel;
    private JPanel leftJPanel;
    private JPanel rightJPanel;
    private JPanel bottomJPanel;

    private boolean errorOccurred = false;

    private JButton updateTermsButton;

    private JTable table;
    private JScrollPane scrollPane;

    public ImportHTMLFile(String importedFileHtml) {

        openDocument(importedFileHtml);
        parseDocument();
        initComponents();
        insertData();
        showForm();
    }

    private void insertData() {
        ImportedTermsTableModel importedTermsTableModel = (ImportedTermsTableModel) table.getModel();
        JComboBox comboBox = new JComboBox();
        for (String s: apiConnector.getAllStatusesString()
        ) {
            comboBox.addItem(s);
        }
        //comboBox.setSelectedItem("W redakcji");
        TableColumn comboColumn  =  table.getColumnModel().getColumn(6);
        comboColumn.setCellEditor(new DefaultCellEditor(comboBox));
        for ( Term t: terms
             ) {


            ArrayList<TermHistory> termHistories= apiConnector.getAllTermHistoriesOfTerm(t.getId());
            Vector<Long> longs = new Vector<>();
            Term term = apiConnector.getTerm(t.getId().intValue());
            //ArrayList<Long> longs = new ArrayList<>();
            for (TermHistory t1: termHistories
            ) {
                longs.add(t1.getVersion());
            }

            importedTermsTableModel.addRow(new Object[]{true,t.getId(),t.getTitle(),longs.size(),term.getActualVersion(),"Sprawdź wersje","W redakcji"});
        }
        importedTermsTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if(column==4){
                    if(( Long.valueOf(importedTermsTableModel.getValueAt(row,column).toString())> Long.valueOf(importedTermsTableModel.getValueAt(row,column-1).toString()))
                            ||( Long.valueOf(importedTermsTableModel.getValueAt(row,column).toString())< 1l)){
                        importedTermsTableModel.setValueAt(importedTermsTableModel.getValueAt(row,column-1),row,column);

                    }


                }
            }
        });

    }

    private void initComponents() {
        apiConnector= new ApiConnector();
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        widthDim = new Dimension((int) (dim.width*0.6), (int) (dim.height * 0.6));
        mainJPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainJPanel);
        centerJPanel = new JPanel();
        mainJPanel.add(centerJPanel,BorderLayout.CENTER);

        table = new JTable(new ImportedTermsTableModel() );
        scrollPane= new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension((int)(widthDim.width*0.9),(int)(widthDim.height*0.6)));
        centerJPanel.add(scrollPane);
        openTermVersionInDetailsPanelActionListener(table, apiConnector,terms);

        bottomJPanel = new JPanel();
        mainJPanel.add(bottomJPanel,BorderLayout.SOUTH);
        updateTermsButton = new GradientButton("Aktualizuj zaznaczone",Color.GREEN.brighter());
        bottomJPanel. add(updateTermsButton);

        updateTermsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImportedTermsTableModel tableModel = (ImportedTermsTableModel) table.getModel();
                try {
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        if ((boolean) tableModel.getValueAt(i, 0)) {
                            Long id = (Long) tableModel.getValueAt(i, 1);
                            String status = tableModel.getValueAt(i, 6).toString().trim();

                            Term contentTerm = terms.stream().filter(term1 -> id.equals(term1.getId())).findAny().orElse(null);
                            String content = contentTerm.getTermHistories().get(0).getContent();
                            Term term = apiConnector.getTerm(id.intValue());
                            TermHistory termHistory = new TermHistory();
                            termHistory.setContent(content);
                            termHistory.setStatus1(status);
                            termHistory.setVersion(term.getActualVersion() + 1);
                            term.setActualVersion(termHistory.getVersion());
                            term.getTermHistories().add(termHistory);
                            try {
                                apiConnector.termImport(term);

                            } catch (JsonProcessingException ex) {
                                errorOccurred = true;
                                ex.printStackTrace();

                            }

                        }
                    }
                } catch (Exception e1) {
                    errorOccurred = true;
                    e1.printStackTrace();
                }
                dispose();
                if(!errorOccurred){

                    JOptionPane.showMessageDialog(null,"Wersje zaznaczonych haseł zostały poprawnie zaimportowane","Sukces",JOptionPane.PLAIN_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"Podczas impoortu wystąpił błąd","Błąd",JOptionPane.ERROR_MESSAGE);

                }
            }

        });

    }

    static void openTermVersionInDetailsPanelActionListener(JTable table, ApiConnector apiConnector,ArrayList<Term>terms) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    Long termId = Long.valueOf(table.getValueAt(table.getSelectedRow(),1).toString());
                    ArrayList<TermHistory> termHistoryList = apiConnector.getAllTermHistoriesOfTerm(termId);
                    Long termHistoryId= -1l;
                    for (TermHistory t:termHistoryList
                    ) {
                        if(t.getVersion()== Long.valueOf(table.getValueAt(table.getSelectedRow(),4).toString())){
                            termHistoryId= t.getId();
                        }
                    }
                    Point  point    = e.getPoint();
                    int column = table.columnAtPoint(point);
                    if(termHistoryId>-1l&& column !=5)
                        ApplicationFrameController.termWindows.add(new TermWindow(termId.intValue(),termHistoryId.intValue()));
                    else{
                        if(column ==5){
                            Term term = terms.stream().filter(term1 ->termId.equals(term1.getId())).findAny().orElse(null);
                            new CompareTwoVersionsPanel(termId,termHistoryId,term);
                        }
                    }
                }
            }
        });
    }

    private void showForm() {
        this.setMinimumSize(widthDim);

        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.pack();
        this.setVisible(true);
        this.setTitle("Importuj znalezione pozycje.");
    }

    private void parseDocument() {
        terms = new ArrayList<>();
        Element div = doc.getElementsByClass("document").first();
        Elements tables = div.getElementsByTag("table");
        float j =0;
        for (Element table: tables
             ) {
            j++;
            System.out.println(j/tables.size()*100 +"%");
            Elements cells = table.getElementsByTag("td");
            if (cells.size()==9){
                Term term = new Term();
                TermHistory termHistory = new TermHistory();
                for (int i = 0; i < 9 ; i++) {
                    Element cell = cells.get(i);
                    switch (i){

                        case 1:
                            term.setId(Long.valueOf(cell.text()));
                            break;
                        case 5:
                            term.setTitle(cell.text());
                            break;
                        case 8:
                            termHistory.setContent(cell.html());
                            break;
                        default:
                            break;
                    }
                }
               term.getTermHistories().add(termHistory);
                terms.add(term);
            }else{
                System.out.println("Coś tu nie działa!");
            }
        }

    }

    private void openDocument(String importedFileHtml) {
        try {

            input = new File(importedFileHtml);
            doc = Jsoup.parse(input,"UTF-8");
           // System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

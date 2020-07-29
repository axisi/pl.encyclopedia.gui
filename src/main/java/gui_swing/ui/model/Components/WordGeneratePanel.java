package gui_swing.ui.model.Components;

import gui_swing.ui.controller.ApplicationFrameController;
import gui_swing.ui.model.*;
import gui_swing.ui.model.tableModels.GradientButton;
import gui_swing.ui.model.tableModels.WordGenerateTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


    private JPanel bottomLeftPanel;
    private JPanel bottomRightPanel;
    private JPanel bottomTopPanel;
    private JPanel chooserDialogPanel;
    private JButton addButton;
    private JButton minusButton;
    private JButton startGenerateButton;
    private JButton sendMailButton;
    private JButton foregroundColor;
    private JButton backgroundColor;

    private JLabel nameLabel;
    private JTextField nameField;
    private JFileChooser fileChooser;
    private  JCheckBox openAfterGeneration;

    private WordGeneratePanel wordGeneratePanel;
    private ArrayList<Long> sendTermsIdList;

    private String filePath;


    public WordGeneratePanel(ArrayList<Long>ids){
        super();
        wordGeneratePanel = this;
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
        widthDim = new Dimension((int) (dim.width*0.6-40), (int) (dim.height * 0.4));
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

        ImportHTMLFile.openTermVersionInDetailsPanelActionListener(jTable, apiConnector,null);

        initButtonsPanel();

    }

    private void initButtonsPanel() {
        bottomPanel= new JPanel(new BorderLayout());
        mainJPanel.add(bottomPanel,BorderLayout.SOUTH);
        bottomLeftPanel = new JPanel();
        bottomPanel.add(bottomLeftPanel,BorderLayout.WEST);
        addButton = new JButton(new ImageIcon(getClass().getResource("/img/arrows/plus.png")));
        addButton.setPreferredSize(new Dimension(30,30));
        minusButton = new JButton(new ImageIcon(getClass().getResource("/img/arrows/minus.png")));
        minusButton.setPreferredSize(new Dimension(30,30));
        addButton.setBorder(new EmptyBorder(15,15,15,15));
        minusButton.setBorder(new EmptyBorder(15,15,15,15));
        addButton.setToolTipText("Zaznacz wszystkie...");
        minusButton.setToolTipText("Odznacz wszystkie...");
        bottomLeftPanel.add(addButton);
        bottomLeftPanel.add(minusButton);

        bottomRightPanel = new JPanel(new GridLayout(0,1));
        bottomPanel.add(bottomRightPanel,BorderLayout.EAST);
        chooserDialogPanel = new JPanel();
        bottomPanel.add(chooserDialogPanel,BorderLayout.CENTER);
        nameLabel = new JLabel("Nazwa: ");
        bottomTopPanel =new JPanel();
        bottomPanel.add(bottomTopPanel,BorderLayout.NORTH);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomTopPanel.add(nameLabel);
        openAfterGeneration = new JCheckBox("Czy otworzyć plik?");
        openAfterGeneration.setSelected(true);


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        String date =(dtf.format(now));
        nameField = new JTextField(date);
        nameField.setPreferredSize(new Dimension(200,25));
        bottomTopPanel.add(nameField);
        bottomTopPanel.add(openAfterGeneration);

        fileChooser = new JFileChooser(ConfigManager.getTempFolder());
        fileChooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setControlButtonsAreShown(false);
        chooserDialogPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        chooserDialogPanel.add(fileChooser);




        startGenerateButton = new GradientButton("Generuj", Color.GREEN.darker());
        sendMailButton = new GradientButton("Wyślij hasła mailem", Color.blue.darker());
        foregroundColor = new JButton("Kolor nagłówków");
        backgroundColor = new JButton("Kolor tła nagłówków");
        foregroundColor.setToolTipText("Kolor nagłówków jakie będą miały wygenerowane hasła w pliku Word");
        backgroundColor.setToolTipText("Kolor tła nagłówków jakie będą miały wygenerowane hasła w pliku Word");
        foregroundColor.setBackground(Color.BLACK);
        backgroundColor.setBackground(Color.WHITE);
        bottomRightPanel.add(foregroundColor);
        bottomRightPanel.add(backgroundColor);
        bottomRightPanel.add(startGenerateButton);
        bottomRightPanel.add(sendMailButton);
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
            public void actionPerformed(ActionEvent evt) {

                SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {

                        // mimic some long-running process here...
                        String path = fileChooser.getCurrentDirectory().getAbsolutePath();
                        String name = nameField.getText();
                        File path1 = new File(path);
                        if(Files.exists(path1.toPath())&& name.length()>0){
                          CreateWord createWord =   new CreateWord(jTable,foregroundColor.getBackground(),backgroundColor.getBackground(),path,name,wordGeneratePanel,openAfterGeneration.isSelected());
                            filePath = createWord.getFullPath();
                            for (int i = 0; i < jTable.getRowCount(); i++) {
                                if ((Boolean) jTable.getValueAt(i, 0)) {
                                    sendTermsIdList.add(Long.valueOf(jTable.getValueAt(i, 1).toString()));
                                }
                            }
                        }
                        // mimic some long-running process here...
                        return null;
                    }
                };

                Window win = SwingUtilities.getWindowAncestor((AbstractButton) evt.getSource());
                final JDialog dialog = new JDialog(win, "Generowanie listy", Dialog.ModalityType.APPLICATION_MODAL);

                mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {

                    @Override
                    public void propertyChange(PropertyChangeEvent e) {
                        if (e.getPropertyName().equals("state")) {
                            if (e.getNewValue() == SwingWorker.StateValue.DONE) {
                                dialog.dispose();

                            }
                        }
                    }
                });

                mySwingWorker.execute();


                JProgressBar progressBar = new JProgressBar();
                progressBar.setIndeterminate(true);
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(progressBar, BorderLayout.CENTER);
                panel.add(new JLabel("Tworzenie pliku..."), BorderLayout.PAGE_START);
                dialog.add(panel);
                dialog.pack();
                dialog.setLocationRelativeTo(win);
                dialog.setVisible(true);
            }


        });
        sendMailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAfterGeneration.setSelected(false);
                startGenerateButton.doClick();
                new SendMailPanel(filePath,sendTermsIdList);
            }
        });
        foregroundColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                foregroundColor.setBackground(JColorChooser.showDialog(mainJPanel,"Kolor tekstu nagłówków",Color.red.darker()));
            }
        });

        backgroundColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundColor.setBackground(JColorChooser.showDialog(mainJPanel,"Kolor tła nagłówków",new Color(0, 255, 255).darker()));
            }
        });
    }


}

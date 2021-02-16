package gui_swing.ui.model.Components;

import gui_swing.ui.model.*;
import gui_swing.ui.model.pojo.Term;
import gui_swing.ui.model.pojo.TermHistory;
import gui_swing.ui.model.tableModels.GradientButton;
import org.jsoup.Jsoup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CompareTwoVersionsPanel extends JFrame {

    private JPanel mainJPanel;
    private JPanel topJPanel;
    private JPanel centerJPanel;
    private JPanel leftJPanel;
    private JPanel rightJPanel;
    private JPanel bottomJPanel;

    private JLabel leftLabel;
    private JLabel rightLabel;

    private JScrollPane leftScroll;
    private JScrollPane rightScroll;

    private JEditorPane  leftTextArea;
    private JEditorPane  rightTextArea;

    private JButton compareInWordButton;

    private ApiConnector apiConnector = new ApiConnector();

    private Term oldTerm;
    private TermHistory oldTermHistory;
    private Term  newTerm;

    private Dimension dim;
    private Dimension widthDim;

    private String oldContent;
    private String oldContentNoHTLM;
    private String newContent;
    private String newContentNoHTLM;
     private FileDiff fileDiff;

     private JComboBox leftComboBox;
     private JComboBox rightCombobox;

     private Long leftIndex;
     private Long rightIndex;

  public CompareTwoVersionsPanel(Long termId){
      oldTerm = apiConnector.getTerm(termId.intValue());
      initComponents();
      fillDataCompare();

      showForm();
  }

    private void fillDataCompare() {
        leftLabel.setText("Hasło: '"+oldTerm.getTitle()+"'. Wersja: '");
        rightLabel.setText("Hasło: '"+oldTerm.getTitle()+"'. Wersja: '");
        ArrayList<TermHistory> termHistories = apiConnector.getAllTermHistoriesOfTerm(oldTerm.getId());
        for (TermHistory t:   termHistories
        ) {
            leftComboBox.addItem(t.getVersion().intValue());
            rightCombobox.addItem(t.getVersion().intValue());

        }
        rightCombobox.setSelectedIndex(rightCombobox.getItemCount()-1);
        leftComboBox.setSelectedIndex(leftComboBox.getItemCount()-2);
        initCompare(termHistories);
        leftComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initCompare(termHistories);
            }
        });
        rightCombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initCompare(termHistories);
            }
        });

        compareInWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
                LocalDateTime now = LocalDateTime.now();
                String date =(dtf.format(now));
                String word1 = new CreateWord(oldTerm.getId().intValue(),Long.valueOf(leftComboBox.getSelectedItem().toString()),Color.black,Color.WHITE,ConfigManager.getTempFolder(),date+"old",false,false).getFullPath();
                String word2 = new CreateWord(oldTerm.getId().intValue(),Long.valueOf(rightCombobox.getSelectedItem().toString()),Color.black,Color.WHITE,ConfigManager.getTempFolder(),date,false,false).getFullPath();
               // String word2 = new CreateWord(newTerm,Color.black,Color.WHITE,ConfigManager.getTempFolder(),date).getFullPath();
                // word1.replaceAll("/","\\");
                //word2.replaceAll("/","\\");
                // ConfigManager.setCompareURI(word1,word2);
                createFilesToCompare(word1, word2);
            }
        });



    }

    private void createFilesToCompare(String word1, String word2) {
        File file = new File(ConfigManager.getScriptsFolder()+"files.txt");
        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(word1);
            myWriter.write("\r\n");
            myWriter.write(word2);
            myWriter.close();
        }catch (Exception e1){

        }
        file = new File(ConfigManager.getScriptsFolder()+"script.docm");

        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) {
            try {
                desktop.open(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void initCompare(ArrayList<TermHistory> termHistories) {
        leftIndex = Long.valueOf(leftComboBox.getSelectedItem().toString());
        rightIndex = Long.valueOf(rightCombobox.getSelectedItem().toString());
        prepareTextToPopulateImport(termHistories.stream().filter(termHistory -> termHistory.getVersion().equals(leftIndex)).findAny().orElse(null).getContent()
                , termHistories.stream().filter(termHistory -> termHistory.getVersion().equals(rightIndex)).findAny().orElse(null).getContent());
        leftTextArea.setText(fileDiff.getNewText1());
        rightTextArea.setText(fileDiff.getNewText2());
    }

    public CompareTwoVersionsPanel(Long termId, Long termHistoryId, Term term) {


        oldTerm = apiConnector.getTerm(termId.intValue());
        oldTermHistory = apiConnector.getTermHistory(termHistoryId);
        newTerm = term;


        initComponents();

        fillDataImport();

        showForm();
    }

    private void prepareTextToPopulateImport( String leftString,String rightString) {

        oldContent = leftString;
        newContent = rightString;
        oldContentNoHTLM = Jsoup.parse(oldContent).text();
        newContentNoHTLM = Jsoup.parse(newContent).text();
        fileDiff = new FileDiff(oldContentNoHTLM,newContentNoHTLM);
        //fileDiff = new FileDiff(oldContent,newContent);


    }

    private void fillDataImport() {
        prepareTextToPopulateImport(oldTermHistory.getContent(),newTerm.getTermHistories().get(0).getContent());
        leftLabel.setText("Hasło: '"+oldTerm.getTitle()+"'. Wersja: '");
        rightLabel.setText("Importowane hasło: '"+newTerm.getTitle()+"'.");
        leftTextArea.setText(fileDiff.getNewText1());
        rightTextArea.setText(fileDiff.getNewText2());
       ArrayList<TermHistory> termHistories = apiConnector.getAllTermHistoriesOfTerm(oldTerm.getId());
        for (TermHistory t:   termHistories
             ) {
            leftComboBox.addItem(t.getVersion().intValue());
        }
        leftComboBox.setSelectedItem(oldTermHistory.getVersion().intValue());
        rightCombobox.addItem("Importowane hasło");
        leftComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareTextToPopulateImport(oldTerm.getTermHistories().stream().filter(termHistory1 -> termHistory1.getVersion().equals(Long.valueOf(leftComboBox. getSelectedItem().toString()))).findAny().orElse(null).getContent(),
                        newTerm.getTermHistories().get(0).getContent());
                leftTextArea.setText(fileDiff.getNewText1());
                rightTextArea.setText(fileDiff.getNewText2());

            }
        });
        compareInWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
                LocalDateTime now = LocalDateTime.now();
                String date =(dtf.format(now));
                String word1 = new CreateWord(oldTerm.getId().intValue(),Long.valueOf(leftComboBox.getSelectedItem().toString()),Color.black,Color.WHITE,ConfigManager.getTempFolder(),date+"old",false,false).getFullPath();
                String word2 = new CreateWord(newTerm,Color.black,Color.WHITE,ConfigManager.getTempFolder(),date).getFullPath();
                // word1.replaceAll("/","\\");
                //word2.replaceAll("/","\\");
                // ConfigManager.setCompareURI(word1,word2);
                createFilesToCompare(word1, word2);
            }
        });
    }

    private void showForm() {
        this.setMinimumSize(widthDim);

        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.pack();
        this.setVisible(true);
        this.setTitle("Porównaj wersje - ID : '"+oldTerm.getId()+"'");
    }

    private void initComponents() {



        dim = Toolkit.getDefaultToolkit().getScreenSize();
        widthDim = new Dimension((int) (dim.width*0.8), (int) (dim.height * 0.8));
        mainJPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainJPanel);
        leftJPanel = new JPanel(new BorderLayout());
        mainJPanel.add(leftJPanel,BorderLayout.WEST);
        rightJPanel = new JPanel(new BorderLayout());
        mainJPanel.add(rightJPanel,BorderLayout.EAST);
        leftTextArea = new JEditorPane ();
        rightTextArea = new JEditorPane ();
        leftScroll = new JScrollPane(leftTextArea);
        rightScroll = new JScrollPane(rightTextArea);
        leftScroll.setPreferredSize(new Dimension(widthDim.width/2,(int)(widthDim.height*0.8)));
        rightScroll.setPreferredSize(new Dimension(widthDim.width/2,(int)(widthDim.height*0.8)));
        leftLabel = new JLabel("left");
        rightLabel = new JLabel("right");

        JPanel topLeftPanel = new JPanel();
        JPanel topRightPanel  = new JPanel();
        leftJPanel.add(topLeftPanel,BorderLayout.NORTH);
        topLeftPanel.add(leftLabel);

        rightJPanel.add(topRightPanel,BorderLayout.NORTH);

        topRightPanel.add(rightLabel);
        leftComboBox = new JComboBox();
        rightCombobox = new JComboBox();

        topLeftPanel.add(leftComboBox);
        topRightPanel.add(rightCombobox);



        leftJPanel.add(leftScroll,BorderLayout.CENTER);
        rightJPanel.add(rightScroll,BorderLayout.CENTER);
        leftTextArea.setEditable(false);
        rightTextArea.setEditable(false);
        leftTextArea.setContentType("text/html");
        rightTextArea.setContentType("text/html");
        bottomJPanel = new JPanel();
        mainJPanel.add(bottomJPanel,BorderLayout.SOUTH);
        compareInWordButton = new GradientButton("Porównaj w MS Word",Color.GRAY.darker());

        bottomJPanel.add(compareInWordButton);


    }
}

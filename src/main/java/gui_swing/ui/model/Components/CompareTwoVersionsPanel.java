package gui_swing.ui.model.Components;

import gui_swing.ui.model.*;
import gui_swing.ui.model.tableModels.GradientButton;
import org.jsoup.Jsoup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    private ApiConnector apiConnector;

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

    public CompareTwoVersionsPanel(Long termId, Long termHistoryId, Term term) {
        apiConnector = new ApiConnector();
        oldTerm = apiConnector.getTerm(termId.intValue());
        oldTermHistory = apiConnector.getTermHistory(termHistoryId);
        newTerm = term;
        prepareTextToPopulate();

        initComponents(termId,termHistoryId,term);

        fillData();

        showForm();
    }

    private void prepareTextToPopulate() {

        oldContent=oldTermHistory.getContent();
        newContent = newTerm.getTermHistories().get(0).getContent();
        oldContentNoHTLM = Jsoup.parse(oldContent).text();
        newContentNoHTLM = Jsoup.parse(newContent).text();
        fileDiff = new FileDiff(oldContentNoHTLM,newContentNoHTLM);
        //fileDiff = new FileDiff(oldContent,newContent);
    }

    private void fillData() {
        leftLabel.setText("Hasło: '"+oldTerm.getTitle()+"'. Wersja: '"+oldTermHistory.getVersion()+"'.");
        rightLabel.setText("Importowane hasło: '"+newTerm.getTitle()+"'.");
        leftTextArea.setText(fileDiff.getNewText1());
        rightTextArea.setText(fileDiff.getNewText2());
    }

    private void showForm() {
        this.setMinimumSize(widthDim);

        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.pack();
        this.setVisible(true);
        this.setTitle("Porównaj wersje - ID : '"+newTerm.getId()+"'");
    }

    private void initComponents(Long termId, Long termHistoryId, Term term) {
        apiConnector = new ApiConnector();


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

        leftJPanel.add(leftLabel,BorderLayout.NORTH);
        rightJPanel.add(rightLabel,BorderLayout.NORTH);
        leftJPanel.add(leftScroll,BorderLayout.CENTER);
        rightJPanel.add(rightScroll,BorderLayout.CENTER);
        leftTextArea.setEditable(false);
        rightTextArea.setEditable(false);
        leftTextArea.setContentType("text/html");
        rightTextArea.setContentType("text/html");
        bottomJPanel = new JPanel();
        mainJPanel.add(bottomJPanel,BorderLayout.SOUTH);
        compareInWordButton = new GradientButton("Porównaj w MS Word",Color.GRAY.darker());
        compareInWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
                LocalDateTime now = LocalDateTime.now();
                String date =(dtf.format(now));
                String word1 = new CreateWord(oldTerm.getId().intValue(),oldTermHistory.getVersion(),Color.black,Color.WHITE,ConfigManager.getTempFolder(),date+"old",false,false).getFullPath();
                String word2 = new CreateWord(newTerm,Color.black,Color.WHITE,ConfigManager.getTempFolder(),date).getFullPath();
               // word1.replaceAll("/","\\");
                //word2.replaceAll("/","\\");
                ConfigManager.setCompareURI(word1,word2);
            }
        });
        bottomJPanel.add(compareInWordButton);


    }
}

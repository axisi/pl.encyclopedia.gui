package gui_swing.ui.model.Components;

import gui_swing.ui.controller.ApplicationFrameController;
import gui_swing.ui.model.*;
import gui_swing.ui.model.pojo.Term;
import gui_swing.ui.model.pojo.TermHistory;
import gui_swing.ui.model.tableModels.GradientButton;
import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class ChangesPanel extends JFrame {


    private ArrayList<Long> termsArray;
    private ArrayList<Long> termsArrayConst;
    private Integer index;
    private String searchedString;
    private String replacedString;

    private Integer counter;

    private String newContent;
    private Boolean isFound = false;

    private JPanel mainPanel;
    private JSplitPane splitPane;
    private JPanel topPane;
    private JPanel topTopPane;
    private JPanel topCenterPane;
    private JPanel topBottomPane;
    private JPanel leftPane;
    private JPanel rightPane;
    private JPanel bottomPane;

    private JLabel leftIdLabel;
    private JLabel rightIdLabel;
    private JLabel leftTermLabel;
    private JLabel rightTermLabel;
    private JLabel errorLabel;

    private JTextField searchedText;
    private JTextField replacedText;

    private JButton startButton;
    private GradientButton acceptButton;
    private GradientButton skipButton;
    private GradientButton cancelButton;

    private JCheckBox caseSensitiveCheckBox;

    private JScrollPane leftScrollPane;
    private JScrollPane rightScrollPane;

    private JEditorPane leftEditorPane;
    private JEditorPane rightEditorPane;
    private ApiConnector apiConnector;

    private Boolean hasAccess = false;


    public ChangesPanel(ArrayList<Long> list){
        super();
        this.termsArray = new ArrayList<>();

        this.termsArrayConst = new ArrayList<>();
        if(list!=null){
            this.termsArray = list;
            this.termsArrayConst = (ArrayList<Long>) list.clone();

        }


        apiConnector = new ApiConnector();
        if(Objects.isNull(termsArrayConst))
            termsArray = new ArrayList<>();

        mainPanel= new JPanel();
        mainPanel.setLayout(new BorderLayout());
        topPane = new JPanel();
        // topPane.setBackground(Color.RED);
        leftPane = new JPanel();
        // leftPane.setBackground(Color.BLUE);
        rightPane = new JPanel();
        //rightPane.setBackground(Color.GREEN);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftPane,rightPane);
        splitPane.setOneTouchExpandable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        splitPane.setDividerLocation((int)(dim.width*0.8/2));
        //splitPane.setDividerLocation(0.5);
        //this.setLocation(dim.width/2-this.getSize().width/2,dim.height/2-this.getSize().height/2);
        Dimension minimumSize = new Dimension(380, 250);
        leftPane.setMinimumSize(minimumSize);
        rightPane.setMinimumSize(minimumSize);
        topTopPane = new JPanel();
        topPane.setLayout(new BorderLayout());
        topPane.add(topTopPane, BorderLayout.NORTH);
        topBottomPane= new JPanel();
        topPane.add(topBottomPane,BorderLayout.SOUTH);
        topCenterPane = new JPanel();
        topPane.add(topCenterPane,BorderLayout.CENTER);
        bottomPane= new JPanel();
        mainPanel.add(topPane,BorderLayout.NORTH);
        mainPanel.add(splitPane,BorderLayout.CENTER);
        mainPanel.add(bottomPane,BorderLayout.SOUTH);
        errorLabel = new JLabel("",JLabel.RIGHT);
        bottomPane.add(errorLabel);

        searchedText = new JTextField();
        searchedText.setPreferredSize(new Dimension(150,30));

        searchedText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                if(Objects.equals(source.getText(),"Szukany text...")){
                    source.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                if(Objects.equals(source.getText(),"")){
                    source.setText("Szukany text...");
                }
            }
        });
        replacedText = new JTextField("Zamień na...");
        replacedText.setPreferredSize(new Dimension(150,30));

        replacedText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                if(Objects.equals(source.getText(),"Zamień na...")){
                    source.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                if(Objects.equals(source.getText(),"")){
                    source.setText("Zamień na...");
                }
            }
        });

        startButton = new JButton("Rozpocznij zamianę");
        startButton.setToolTipText("Rozpocznij zamianę");
        topTopPane.add(searchedText);
        topTopPane.add(replacedText);
        caseSensitiveCheckBox = new JCheckBox("Uwzględniaj wielkość liter",null,true);
        topTopPane.add(caseSensitiveCheckBox);
        topTopPane.add(startButton);

        leftIdLabel = new JLabel("ID:");
        rightIdLabel = new JLabel();
        leftTermLabel = new JLabel("Hasło:");
        rightTermLabel = new JLabel();

        topCenterPane.add(leftIdLabel);
        topCenterPane.add(rightIdLabel);
        topCenterPane.add(leftTermLabel);
        topCenterPane.add(rightTermLabel);

        leftEditorPane = new JEditorPane();
        leftScrollPane = new JScrollPane(leftEditorPane);
        leftPane.add(leftScrollPane);
        leftEditorPane.setEditable(false);
        leftEditorPane.setContentType("text/html");

        rightEditorPane = new JEditorPane();
        rightEditorPane.setEditable(false);
        rightScrollPane = new JScrollPane(rightEditorPane);
        rightPane.add(rightScrollPane);
        //rightScrollPane.setMinimumSize(new Dimension(450,450));




        rightEditorPane.setContentType("text/html");

        leftScrollPane.setMinimumSize(minimumSize);
        rightScrollPane.setMinimumSize(minimumSize);
        leftScrollPane.setPreferredSize(minimumSize);
        rightScrollPane.setPreferredSize(minimumSize);

       //leftEditorPane.setPreferredSize(new Dimension(500,600));
       // rightEditorPane.setPreferredSize(new Dimension(500,600));
       // rightScrollPane.setMaximumSize(new Dimension(800,800));
        //rightScrollPane.setPreferredSize(new Dimension(600,650));
        //leftScrollPane.setMinimumSize(new Dimension(450,450));
       // leftScrollPane.setPreferredSize(new Dimension(600,650));
        //leftScrollPane.setMaximumSize(new Dimension(800,800));
        topCenterPane.setVisible(false);
        acceptButton = new GradientButton("Zatwierdź",Color.GREEN.darker());
        skipButton = new GradientButton("Odrzuć",Color.YELLOW.darker());
        cancelButton = new GradientButton("Anuluj",Color.pink.darker());


        topBottomPane.add(acceptButton);
        topBottomPane.add(skipButton);
        topBottomPane.add(cancelButton);
        caseSensitiveCheckBox.setVisible(false);
        topBottomPane.setVisible(false);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter = 0;
                index = 0;
                searchedString = searchedText.getText();
                replacedString = replacedText.getText();
                if(termsArray.size()==0){
                   if(termsArrayConst.size()==0){
                       termsArray = (ArrayList<Long>) apiConnector.findTermsWitchActualVersionContentContains(searchedString);
                   }else
                       termsArray= (ArrayList<Long>) termsArrayConst.clone();

                }
                topCenterPane.setVisible(true);
                replacedText.setEditable(false);
                searchedText.setEditable(false);
                caseSensitiveCheckBox.setEnabled(false);
                topBottomPane.setVisible(true);
                startButton.setEnabled(false);
                getNextOccurrence();
            }
        });
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TermHistory termHistory= new TermHistory();
                termHistory.setContent(newContent);
                apiConnector.updateTermHistory(termsArray.get(0),termHistory);
                index =index + searchedString.length();
                getNextOccurrence();
            }
        });
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index =index + searchedString.length();
                getNextOccurrence();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endOfReplacing();
            }
        });
        rightIdLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ApplicationFrameController.termWindows.add( new TermWindow(Integer.valueOf(rightIdLabel.getText())));
                TermWindow termWindow = ApplicationFrameController.termWindows.get(ApplicationFrameController.termWindows.size()-1);
                termWindow.getFrame().setTitle(apiConnector.getTerm(Integer.valueOf(rightIdLabel.getText())).getTitle());
            }
        });


        this.add(mainPanel);
        this.setTitle("Zamień wiele...");
        //this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //this.setPreferredSize(dim);
        this.setPreferredSize(new Dimension((int)(dim.width*0.8) , (int) (dim.height*0.8)));
        this.setMinimumSize(new Dimension((int)(dim.width*0.8) , (int) (dim.height*0.8)));
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMaximumSize(dim);
        leftScrollPane.setPreferredSize(new Dimension((int) (dim.width*0.8/2-20), (int) (dim.height * 0.7)));
        rightScrollPane.setPreferredSize(new Dimension((int) (dim.width*0.8/2-20), (int) (dim.height * 0.7)));
       // Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int)(dim.width*0.8/2-this.getSize().width/2),(int)(dim.height*0.8/2-this.getSize().height/2));
        this.pack();
        this.setVisible(true);
    }

    public ChangesPanel() throws HeadlessException {
        this(null);
    }

    private void getNextOccurrence() {
        while(!isFound) {
            if(termsArray.size()>0){
                index =  getNextId(index);
            }else{
                break;
            }
        }
        if(isFound){
            newContent = replaceString(
                    apiConnector.getActualTermHistoryOfTerm
                            (apiConnector.getTerm(termsArray.get(0).intValue()).getId().intValue()).getContent()
                    ,replacedString
                    ,index,
                    searchedString);
            leftEditorPane.setText(replaceString(
                    apiConnector.getActualTermHistoryOfTerm
                            (apiConnector.getTerm(termsArray.get(0).intValue()).getId().intValue()).getContent()
                    ,"<span style=\"background-color:#d04fac;\">" +searchedString + "</span>"
                    ,index,
                    searchedString)
            );
            rightEditorPane.setText(replaceString(
                    apiConnector.getActualTermHistoryOfTerm
                            (apiConnector.getTerm(termsArray.get(0).intValue()).getId().intValue()).getContent()
                    ,"<span style=\"background-color:#00ff80;\">" +replacedString + "</span>"
                    ,index
                    ,searchedString)
            );
            isFound=false;
            rightIdLabel.setText(apiConnector.getTerm(termsArray.get(0).intValue()).getId().toString());
            rightTermLabel.setText(apiConnector.getTerm(termsArray.get(0).intValue()).getTitle());
            counter++;
            errorLabel.setText("Wystąpienie "+counter+".");

        }else{

            endOfReplacing();
        }
    }

    private void endOfReplacing() {
        termsArray.clear();
        errorLabel.setText("Nie znaleziono więcej wystąpień. Możesz rozpocząć nową zamianę.");
        topBottomPane.setVisible(false);
        topCenterPane.setVisible(false);
        replacedText.setEditable(true);
        searchedText.setEditable(true);
        caseSensitiveCheckBox.setEnabled(true);

        startButton.setEnabled(true);
        leftEditorPane.setText("");
        rightEditorPane.setText("");
    }

    public String replaceString(String str, String string, int index, String searchedString) {
        return str.substring(0, index) + string + str.substring(index+ searchedString.length());
    }
    public Integer getNextId(Integer index){

        Term term = apiConnector.getTerm(termsArray.get(0).intValue());
        hasAccess= TermWindow.checkAccess(term.getId().intValue());
        if(!hasAccess){
            termsArray.remove(0);
            return 0;
        }
        TermHistory  termHistory = apiConnector.getActualTermHistoryOfTerm(term.getId().intValue());
        String tempContent;
        if(index>0)
            tempContent = termHistory.getContent().substring(index);
        else
            tempContent = termHistory.getContent();
        int countInText = StringUtils.countMatches(NCRConverter.html2text(tempContent),searchedString);
        int countInHTML = StringUtils.countMatches(tempContent,searchedString);
        if (countInHTML== countInText){
            if(countInText ==0){
                termsArray.remove(0);
                return 0;
            }else{
                isFound = true;
                return index+ tempContent.indexOf(searchedString);
            }
        }else{
            Integer tempIndex = tempContent.indexOf(searchedString);
            Integer decision = -1;
            String tempTempContent = tempContent.substring(tempIndex);
            Integer tempTempIndex= 0;
            for (char ch: tempTempContent.toCharArray()
                 ) {
                switch(ch){
                    case '<':
                    decision = 1;
                    break;
                    case '>':
                    decision = 2;
                    break;
                    default:
                    break;
                }
                if(decision>0){
                    break;
                }else{
                    tempTempIndex++;
                }
            }
            if(decision==1){
                isFound = true;
                return index+ tempContent.indexOf(searchedString);
            }else{
                return index + tempTempIndex-1;
            }
        }
    }


}

package gui_swing.ui.model.Components;

import gui_swing.ui.controller.ApplicationFrameController;
import gui_swing.ui.model.ApiConnector;

import gui_swing.ui.model.pojo.TermHistory;
import gui_swing.ui.model.pojo.TermHistoryComment;
import gui_swing.ui.model.tableModels.GradientButton;
import gui_swing.ui.view.ApplicationFrame;
import net.atlanticbb.tantlinger.ui.text.DefaultWysiwygEditor;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class EditCommentPanel<pirivate> extends JFrame {
    private JPanel mainJPanel;
    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;

    private TermHistory termHistory;
    private ApiConnector apiConnector;

    private Integer commentLp;

    private JTextArea jTextArea;
    private JCheckBox jCheckbox;
    private JLabel lpLabel;
    private JLabel deadLineLabel;
    private JLabel startLabel;
    private JLabel endLabel;
    private JTextField startText;
    private JTextField endText;
    private JButton getSelectedTextButton;
    private Dimension dim ;
    private Integer startGlobal;
    private Integer endGlobal;
    private Boolean editing;
    private TermWindow parentFrame;
    private JButton saveButton;
    private TermHistoryComment termHistoryComment;
    private JDatePickerImpl datePicker;


    public EditCommentPanel(Long id, Long termHistoryId, int start, int end, Boolean editing, TermWindow termWindow, Integer commentLp ) {
        apiConnector = new ApiConnector();
        this.termHistory = apiConnector.getTermHistory(termHistoryId);
        this.editing = editing;
        this.startGlobal = start;
        this.endGlobal = end;
        this.parentFrame = termWindow;
        if(!editing)
            this.commentLp = apiConnector.getNextCommentLp(id);
        else{
            this.commentLp = commentLp;
            termHistoryComment = apiConnector.getTermHistoryComment(id,commentLp);
        }

        buildForm();
        fillForm();
        showForm();



    }

    private void fillForm() {
        if(!editing){
            if(startGlobal != endGlobal){
                startText.setText(startGlobal.toString());
                endText.setText(endGlobal.toString());
            }
        }else{
            if(termHistoryComment.getDeadlineDate()!=null){
                Calendar ca = GregorianCalendar.getInstance();
                ca.setTimeInMillis(termHistoryComment.getDeadlineDate().getTime());
                datePicker.getModel().setDate(ca.get(Calendar.YEAR),ca.get(Calendar.MONTH),ca.get(Calendar.DATE));
            }
            jCheckbox.setSelected(termHistoryComment.getVisibleInWholeTerm());
            jTextArea.setText(termHistoryComment.getContent());
        }
        lpLabel.setText("Komentarz numer: "+commentLp);

    }

    private void showForm() {
        this.setMinimumSize(new Dimension((int) (dim.width*0.2), (int) (dim.height * 0.2)));
        this.setMaximumSize(new Dimension((int) (dim.width*0.4), (int) (dim.height * 0.4)));

        this.setLocation(dim.width-this.getSize().width/2-this.getWidth(), dim.height/2-this.getSize().height/2);
        this.setVisible(true);
        this.setTitle("Komentarz");

    }

    private void buildForm() {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        mainJPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainJPanel);
        topPanel = new JPanel(new GridLayout(0,1));
        JPanel topTopPanel = new   JPanel();
        lpLabel = new JLabel("");
        jCheckbox = new JCheckBox("Widoczny dla wszystkikch wersji",true);
        mainJPanel.add(topPanel,BorderLayout.NORTH);
        topTopPanel.add(lpLabel);
        topTopPanel.add(jCheckbox);
        topPanel.add(topTopPanel);
        JPanel midTopPanel = new JPanel();
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
        midTopPanel.add(datePicker);
        deadLineLabel = new JLabel("Deadline: ");
        topPanel.add(deadLineLabel);
        topPanel.add(midTopPanel);
        JPanel botTopPanel = new JPanel(new FlowLayout());
        startLabel = new JLabel("Od: ");
        endLabel = new JLabel("Do: ");
        startText = new JTextField();
        endText = new JTextField();
        startText.setEnabled(false);
        endText.setEnabled(false);
        startText.setPreferredSize(new Dimension(50,30));
        endText.setPreferredSize(new Dimension(50,30));
        getSelectedTextButton = new GradientButton("Pobierz zaznaczony text",Color.blue.brighter());
        botTopPanel.add(startLabel);
        botTopPanel.add(startText);
        botTopPanel.add(endLabel);
        botTopPanel.add(endText);
        botTopPanel.add(getSelectedTextButton);
        JButton deleteButton = new GradientButton("Usuń oznaczenie",Color.red.darker());
        botTopPanel.add(deleteButton);
        if(editing){
            getSelectedTextButton.setEnabled(false);
        }
        topPanel.add(botTopPanel);
        jTextArea = new JTextArea();
        jTextArea.setLineWrap(true);
        jTextArea.setPreferredSize(new Dimension( this.getWidth(),300));
        mainJPanel.add(jTextArea,BorderLayout.CENTER);
        this.setAlwaysOnTop(true);
        JPanel topBottom2 = new JPanel();
        topPanel.add(topBottom2);
        saveButton = new GradientButton("Zapisz",Color.green.darker());
        topBottom2.add(saveButton);

        pack();
        getSelectedTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultWysiwygEditor defaultWysiwygEditor=    (DefaultWysiwygEditor)    parentFrame.getHtmlEditorPane().getWysEditor();
                Integer start = defaultWysiwygEditor.getTextArea().getSelectionStart();
                Integer end = defaultWysiwygEditor.getTextArea().getSelectionEnd();
                if(!start.equals(end)){
                    startText.setText(start.toString());
                    endText.setText(end. toString());
                    startGlobal = start;
                    endGlobal  = end;

                }else{
                    startText.setText("");
                    endText.setText("");
                    startGlobal = -1;
                    endGlobal=-1;
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(parentFrame.isTemporaryIsSelected1()){
                    parentFrame.clearSelection();

                }
                parentFrame.getCommentsPanel().dispose();
                if(editing){
                    if(!(startGlobal==endGlobal||startGlobal==-1)){

                        updateTermContent();
                    }
                    TermHistoryComment termHistoryComment = getTermHistoryComment();
                    parentFrame.clearSelection();
                    apiConnector.updateTermHistoryComment(termHistory.getId(),termHistoryComment);
                }else{
                    if(!(startGlobal==endGlobal||startGlobal==-1)) {

                        updateTermContent();
                    }
                    TermHistoryComment termHistoryComment = getTermHistoryComment();
                    parentFrame.clearSelection();
                    apiConnector.createTermHistoryComment(termHistory.getId(), termHistoryComment);
                }
                dispose();

                //parentFrame.setTemporaryIsSelected1(false);
                //parentFrame.setContentInComments("");
               // parentFrame.getCommentsPanel().dispose();
                parentFrame.setCommentsPanel(new CommentPanel(termHistory.getId().intValue(),parentFrame));
                parentFrame.getCommentsPanel().setAlwaysOnTop(true);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startText.setText("");
                endText.setText("");
                startGlobal = -1;
                endGlobal=-1;
                if(editing){
                    deleteTags();
                    deleteButton.setEnabled(false);
                    getSelectedTextButton.setEnabled(true);
                }
            }
        });


    }

    private TermHistoryComment getTermHistoryComment() {
        java.sql.Timestamp sqlDate= null;
        java.util.Date utilDate = (java.util.Date) datePicker.getModel().getValue();

        if(utilDate !=null){

            sqlDate = new java.sql.Timestamp(utilDate.getTime());
            //sqlDate.setTime( sqlDate.getTime() -sqlDate.getTime()%(24*60*60*1000));

        }
        TermHistoryComment termHistoryComment;
        if(null!=sqlDate)
           termHistoryComment = new TermHistoryComment(commentLp, new java.sql.Timestamp(sqlDate.getTime()), jTextArea.getText(), true /*jCheckbox.isSelected()*/);
        else
            termHistoryComment = new TermHistoryComment(commentLp, null, jTextArea.getText(), true /*jCheckbox.isSelected()*/);
        return termHistoryComment;
    }

    public void deleteTags() {
        parentFrame.getHtmlEditorPane().getWysEditor().setText(parentFrame.getHtmlEditorPane().getWysEditor().getText().replaceAll("[*]" + commentLp + "[*]", ""));
    }

    private void updateTermContent() {

        String marble = "*" + commentLp + "*";

            parentFrame.getHtmlEditorPane().getWysEditor().insertText(marble, startGlobal);
            parentFrame.getHtmlEditorPane().getWysEditor().insertText(marble, endGlobal + marble.length());




        //text = CommentInstance.insertString(text,startGlobal,marble);
        //text = CommentInstance.insertString(text,endGlobal+marble.length(),marble);
        //parentFrame.getHtmlEditorPane().getWysEditor().getTextArea().setText(text);

        parentFrame.getHtmlEditorPane().getWysEditor().setText(parentFrame.getHtmlEditorPane().getWysEditor().getText().replaceAll("[*]" + commentLp + "[*]", "<sup>*" + commentLp + "*</sup>"));
        termHistory.setContent(parentFrame.getHtmlEditorPane().getWysEditor().getText());
        parentFrame.clearSelection();

        parentFrame.getUpdateTermButton().doClick();
        parentFrame = ApplicationFrameController.termWindows.get(ApplicationFrameController.termWindows.size()-1);
        parentFrame.setContentInComments(parentFrame.getHtmlEditorPane().getWysEditor().getText());
    }

    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "dd-MM-yyyy";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }
}



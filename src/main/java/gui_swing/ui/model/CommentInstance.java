package gui_swing.ui.model;

import gui_swing.ui.model.Components.TermWindow;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class CommentInstance extends JPanel {

private ApiConnector apiConnector = new ApiConnector();
private Boolean isSelected = false;
private Boolean isPrimary ;
private Color primaryColor = Color.YELLOW.brighter().brighter().darker();
private Color secondaryColor = Color.gray;
private JPanel parentCommentsPanel;
private TermWindow parentFrame;
private Integer lp;
private static Boolean lockOnColor = false;

    public ApiConnector getApiConnector() {
        return apiConnector;
    }

    public void setApiConnector(ApiConnector apiConnector) {
        this.apiConnector = apiConnector;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public Color getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public JPanel getParentCommentsPanel() {
        return parentCommentsPanel;
    }

    public void setParentCommentsPanel(JPanel parentCommentsPanel) {
        this.parentCommentsPanel = parentCommentsPanel;
    }

    public TermWindow getParentFrame() {
        return parentFrame;
    }

    public void setParentFrame(TermWindow parentFrame) {
        this.parentFrame = parentFrame;
    }

    public static void setLockOnColor(Boolean lockOnColor) {
        CommentInstance.lockOnColor = lockOnColor;
    }

    public static Boolean getLockOnColor() {
        return lockOnColor;
    }

    public Integer getLp() {
        return lp;
    }

    public void setLp(Integer lp) {
        this.lp = lp;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
    public CommentInstance(TermHistoryComment t, Integer width, Integer height, Boolean primary, JPanel commentsPanel, TermWindow parentFrame) {
        super();
        this.isPrimary = primary;
        parentCommentsPanel = commentsPanel;
        this.parentFrame = parentFrame;
        this.lp= t.getLp();
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));

        this.setPreferredSize(new Dimension(width-15,height/6));
        this.setMaximumSize(new Dimension(width-15,height/6));
        if(isPrimary)
            this.setBackground(primaryColor);
        else
            this.setBackground(secondaryColor);

        JLabel lpLabel= new JLabel("Komentarz numer: "+ lp);
        lpLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel creationLabel= new JLabel("Utworzony: "+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format( t.getCreationDate()) +" przez: " +apiConnector.getCommentCreatedBy(t.getId()));
        Font font = creationLabel.getFont();
        Font font1 = new Font(font.getName(),font.getStyle(),font.getSize()-2);
        creationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        creationLabel.setFont(font1);
        JLabel modifyLabel= new JLabel("Zmieniony: "+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(t.getModifiedDate() ) +" przez: " +apiConnector.getCommentModifiedBy(t.getId()));
        modifyLabel.setFont(font1);
        modifyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel deadlineLabel = new JLabel();
        if(null!=t.getDeadlineDate())
            deadlineLabel= new JLabel( "Ustawiono deadline: "+ new SimpleDateFormat("dd/MM/yyyy").format( t.getDeadlineDate()));
        deadlineLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        deadlineLabel.setFont(font1);
        JTextArea jTextArea = new JTextArea(t.getContent());
        jTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        jTextArea.setEnabled(false);
        jTextArea.setDisabledTextColor(Color.BLACK);
        jTextArea.setLineWrap(true);

        if(primary)
            jTextArea.setBackground(Color.YELLOW.brighter().brighter().darker());
        else
            jTextArea.setBackground(Color.gray);

        this.add(lpLabel);
        this.add(creationLabel);
        if(!(creationLabel.getText().equals(modifyLabel.getText())))
            this.add(modifyLabel);

        if(t.getDeadlineDate()!=null){
            this.add(deadlineLabel);
            deadlineLabel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        }else{
            if(!(creationLabel.getText().equals(modifyLabel.getText()))){
                modifyLabel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
            }else{
                creationLabel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
            }
        }


        this.add(jTextArea);
        jTextArea.setOpaque(false);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==1){

                    // parentFrame.clearSelection();
                    JPanel panel = (JPanel) e.getSource();
                    //unsetCommentColor();
                    if(isSelected){
                        isSelected = false;
                        if(isPrimary)
                            panel.setBackground(primaryColor);
                        else
                            panel.setBackground(secondaryColor);
                    }else{
                        for (Component component: parentCommentsPanel.getComponents()
                        ) {
                            deselectComment((JPanel) component);
                        }
                        panel.setBackground(panel.getBackground().darker());
                        isSelected = true;
                        setCommentColor(lp);

                    }
                }

            }
        });
    }

    private void deselectComment(JPanel component) {
        JPanel jpanel = component;
        CommentInstance commentInstance = (CommentInstance) jpanel.getComponent(0);
        if(commentInstance.isSelected){

            commentInstance.isSelected = false;
            if(commentInstance.isPrimary)
                commentInstance.setBackground(primaryColor);
            else
                commentInstance.setBackground(secondaryColor);
        }
    }

    public void unsetCommentColor(){

        //if(null!=parentFrame.getContentInComments()){
        if(parentFrame.isTemporaryIsSelected1()){
            parentFrame.getHtmlEditorPane().setText(parentFrame.getContentInComments());
            parentFrame.setTemporaryIsSelected1(false);
        }

    }
    public void deselectAll(){
        for (Component c :parentFrame.getCommentsPanel().getComponents()
             ) {
            deselectComment((JPanel) c);
        }
    }
    public void setCommentColor(Integer lp){

        unsetCommentColor();

       String text =  parentFrame.getHtmlEditorPane().getText();

        parentFrame.setContentInComments(text);
        text=NCRConverter.html2text(text);
        text = NCRConverter.convertNcrToText(text);
        String text1 = text;
        int start= text.indexOf("*"+lp+"*");
        if(start>-1){
            text = text.substring(start+1);
            int lengthOfColor = text.indexOf("*"+lp+"*");
            text =  text1;


           //String marble = "<span style=\"background-color:#00ff80;\">";
           String marble1 = "<span style=\"background-color:#00ff80;\">";
           text = insertString(text, start , marble1);
            String marble2 = "</span>";
           text =  insertString(text, lengthOfColor+marble1.length()+start+3+lp.toString().length() , marble2);

           parentFrame.getHtmlEditorPane().setText(text);

            parentFrame.setTemporaryIsSelected1(true);

       }

    }

    public static String insertString(String text, int start, String marble) {
        String bagBegin = text.substring(0,start);
        String bagEnd = text.substring(start);
        return bagBegin+marble + bagEnd;
    }
}


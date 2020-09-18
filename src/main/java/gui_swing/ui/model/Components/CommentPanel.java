package gui_swing.ui.model.Components;

import gui_swing.ui.controller.ApplicationFrameController;
import gui_swing.ui.model.*;
import gui_swing.ui.model.pojo.Term;
import gui_swing.ui.model.pojo.TermHistory;
import gui_swing.ui.model.pojo.TermHistoryComment;
import gui_swing.ui.model.tableModels.GradientButton;
import net.atlanticbb.tantlinger.ui.text.DefaultSourceEditor;
import net.atlanticbb.tantlinger.ui.text.DefaultWysiwygEditor;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

public class CommentPanel extends JFrame {

    private JPanel mainJPanel;
    private JPanel topJPanel;
    private JPanel centerJPanel;
    private JPanel leftJPanel;
    private JPanel rightJPanel;
    private JPanel bottomJPanel;

    private TermHistory termHistory;
    private Term term;
    private ApiConnector apiConnector;
    private Dimension dim;
    private TermWindow parentFrame;

    private JButton addNewComment;
    private JButton deleteComment;
    private JButton updateComment;

    private JScrollPane scrollPane;
    private  JPanel commentsPanel;


    public CommentPanel(Integer termHistoryId, TermWindow termFrame) {
        apiConnector = new ApiConnector();
        this.termHistory = apiConnector.getTermHistory(termHistoryId.longValue());
        this.term = apiConnector.getTermOfTermHistory(termHistoryId);
        this.parentFrame = termFrame;
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        prepareForm();
        fillData();
        showForm();



    }

    private void showForm() {
        this.setMinimumSize(new Dimension((int) (dim.width*0.2), (int) (dim.height*0.8)));
        this.setLocation(dim.width-this.getSize().width,dim.height - this.getSize().height);

        this.setVisible(true);
        this.setTitle("Komentarze");
    }

    public void fillData() {
        for (Component c: commentsPanel.getComponents()
             ) {
            remove(c);
        }
        /*for (int i = 0; i <100; i++) {
            JPanel jPanel = new JPanel();
            if(i%2==0)
                jPanel.setBackground(Color.red);
            else
                jPanel.setBackground(Color.green);
            jPanel.setPreferredSize(new Dimension(commentsPanel.getWidth()-15,commentsPanel.getHeight()/6));
            commentsPanel.add(jPanel);
        }*/
        ArrayList<TermHistoryComment>termHistoryCommentsPrimary = apiConnector.getAllCommentsOfTerm(term.getId());
        ArrayList<TermHistoryComment>termHistoryCommentsSecondary = apiConnector.getAllCommentsOfTermHistory(termHistory.getId());
        ArrayList<TermHistoryComment>termHistoryCommentsTemporary = new ArrayList<>();

        for (TermHistoryComment t: termHistoryCommentsPrimary
             ) {
            if(t.getVisibleInWholeTerm()) {
                termHistoryCommentsTemporary.add(t);
                JPanel panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(Color.black));
                panel .setPreferredSize(new Dimension(commentsPanel.getWidth()-15,commentsPanel.getHeight()/6));
                commentsPanel.add(panel);
                panel.add(new CommentInstance(t, commentsPanel.getWidth(), commentsPanel.getHeight(), true,commentsPanel,parentFrame));
            }
        }
        for (TermHistoryComment t: termHistoryCommentsSecondary
             ) {
            if(!termHistoryCommentsTemporary.stream().filter(w -> w.getId().equals(t.getId())).findFirst().isPresent()){
                JPanel panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(Color.black));
                panel .setPreferredSize(new Dimension(commentsPanel.getWidth()-15,commentsPanel.getHeight()/6));
                commentsPanel.add(panel);
                panel.add(new   CommentInstance(t,commentsPanel.getWidth(),commentsPanel.getHeight(),false,commentsPanel,parentFrame));
            }
        }

    }

    private void prepareForm() {
        mainJPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainJPanel);
        centerJPanel=new JPanel();
        mainJPanel.add(centerJPanel,BorderLayout.CENTER);
        topJPanel = new JPanel();
        mainJPanel.add(topJPanel,BorderLayout.NORTH);
        addNewComment = new GradientButton("Dodaj",Color.GREEN.darker());
        updateComment = new GradientButton("Edytuj",Color.YELLOW.darker());
        deleteComment = new GradientButton("Usuń",Color.RED.darker());
        topJPanel.add(addNewComment);
        topJPanel.add(updateComment);
        topJPanel.add(deleteComment);
        commentsPanel= new JPanel(new GridLayout(0,1)){@Override
        public Dimension getMaximumSize() {
            return getPreferredSize();}
        };
        scrollPane = new JScrollPane(commentsPanel);
        scrollPane.setPreferredSize(new Dimension((int) (dim.width*0.2), (int) (dim.height*0.7)));
        centerJPanel.add(scrollPane);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);


        this.pack();

        addListeners();


    }

    private void addListeners() {
        addNewComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultWysiwygEditor defaultWysiwygEditor=    (DefaultWysiwygEditor)    parentFrame.getHtmlEditorPane().getWysEditor();
                 int start = defaultWysiwygEditor.getTextArea().getSelectionStart();
                 int end = defaultWysiwygEditor.getTextArea().getSelectionEnd();
                new EditCommentPanel(term.getId(),termHistory.getId(),start,end,false,parentFrame,null);
            }
        });

        updateComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer commentLp=-1;
                for (Component c: commentsPanel.getComponents()
                     ) {
                    JPanel jPanel = (JPanel) c;
                    CommentInstance commentInstance = (CommentInstance)jPanel.getComponent(0);
                    if(commentInstance.getSelected()){
                        commentLp=commentInstance.getLp();
                        break;
                    }
                }

                if(commentLp>0)
                    new EditCommentPanel(term.getId(),termHistory.getId(),-1,-1,true,parentFrame,commentLp);

            }
        });
        deleteComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer commentLp=-1;
                for (Component c: commentsPanel.getComponents()
                ) {
                    JPanel jPanel = (JPanel) c;
                    CommentInstance commentInstance = (CommentInstance)jPanel.getComponent(0);
                    if(commentInstance.getSelected()){
                        commentLp=commentInstance.getLp();
                        break;
                    }
                }
                if(commentLp>0){
                    parentFrame.clearSelection();
                    parentFrame.getHtmlEditorPane().getWysEditor().setText(parentFrame.getHtmlEditorPane().getWysEditor().getText().replaceAll("[<sup>*]" + commentLp + "[*</sup>]", ""));
                    apiConnector.deleteComment(term.getId(),commentLp);
                    parentFrame.getCommentsPanel().dispose();
                    parentFrame.getUpdateTermButton().doClick();
                    parentFrame = ApplicationFrameController.termWindows.get(ApplicationFrameController.termWindows.size()-1);


                    parentFrame.setCommentsPanel(new CommentPanel(termHistory.getId().intValue(),parentFrame));

                }
            }
        });
       /* this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                parentFrame.clearSelection();
            }
        });*/
    }

}

package gui_swing;
//import org.apache.log4j.BasicConfigurator;
//import org.docx4j.org.xhtmlrenderer.util.XRLog;
import gui_swing.ui.controller.MainController;
import net.atlanticbb.tantlinger.shef.HTMLEditorPane;
import net.atlanticbb.tantlinger.ui.text.AbstractEditor;
import net.atlanticbb.tantlinger.ui.text.AbstractToolBar;
import net.atlanticbb.tantlinger.ui.text.CompoundUndoManager;
import net.atlanticbb.tantlinger.ui.text.DefaultWysiwygToolBar;
import net.atlanticbb.tantlinger.ui.text.actions.PasteFormattedAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Runner {
    public static void main(String[] args) {

        //BasicConfigurator.configure();

        MainController mainController = new MainController();


        /* JFrame frame = new JFrame();
        HTMLEditorPane html = new HTMLEditorPane();
        frame.setLayout(new BorderLayout());
        JButton btn = new JButton("nwewwe");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println( html.getSelectedEditor());
                AbstractEditor editor = html.getSelectedEditor();
                AbstractToolBar abstractToolBar = editor.getToolBar();
                DefaultWysiwygToolBar  defaultWysiwygToolBar= (DefaultWysiwygToolBar) abstractToolBar;
                System.out.println(abstractToolBar);
                System.out.println( abstractToolBar.getActionMap());
                defaultWysiwygToolBar.getActionList().add(CompoundUndoManager.UNDO);
                defaultWysiwygToolBar.getActionList().add(CompoundUndoManager.REDO);


                for (Object a: defaultWysiwygToolBar.getActionList()
                     ) {
                    Action aa = (Action) a;
                    *//*if(aa!=null && aa.getValue("Name").equals("Pogrubienie")){
                        aa.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
                        System.out.println("aa");
                    }*//*
                    if(aa!=null)
                        System.out.println(aa.toString());
                }



            }
        });
        frame.add(btn,BorderLayout.NORTH);
        frame.add(html,BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setSize(new Dimension(600,600));*/


    }
}

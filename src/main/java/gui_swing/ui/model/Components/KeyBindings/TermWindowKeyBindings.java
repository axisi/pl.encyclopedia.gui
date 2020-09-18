package gui_swing.ui.model.Components.KeyBindings;

import net.atlanticbb.tantlinger.shef.HTMLEditorPane;
import net.atlanticbb.tantlinger.ui.text.AbstractEditor;
import net.atlanticbb.tantlinger.ui.text.AbstractToolBar;
import net.atlanticbb.tantlinger.ui.text.CompoundUndoManager;
import net.atlanticbb.tantlinger.ui.text.DefaultWysiwygToolBar;
import net.atlanticbb.tantlinger.ui.text.actions.PasteFormattedAction;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.awt.event.ActionEvent;

public class TermWindowKeyBindings {

    public static Action keyBindingBold(HTMLEditorPane htmlEditorPane){
        Action boldAction =new AbstractAction("bold") {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractEditor editor =htmlEditorPane.getSelectedEditor();
                AbstractToolBar abstractToolBar = editor.getToolBar();
                DefaultWysiwygToolBar defaultWysiwygToolBar= (DefaultWysiwygToolBar) abstractToolBar;

                for (Object a: defaultWysiwygToolBar.getActionList()
                ) {
                    Action aa = (Action) a;
                    if(aa!=null && aa.getValue("Name").equals("Pogrubienie")){
                        aa.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
                    }
                }
            }
        };
        return boldAction;
    }

    public static Action keyBindingItalic(HTMLEditorPane htmlEditorPane) {
        Action italicAction =new AbstractAction("italic") {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractEditor editor =htmlEditorPane.getSelectedEditor();
                AbstractToolBar abstractToolBar = editor.getToolBar();
                DefaultWysiwygToolBar defaultWysiwygToolBar= (DefaultWysiwygToolBar) abstractToolBar;

                for (Object a: defaultWysiwygToolBar.getActionList()
                ) {
                    Action aa = (Action) a;
                    if(aa!=null && aa.getValue("Name").equals("Kursywa")){
                        aa.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
                    }
                }
            }
        };
        return italicAction;
    }

    public static Action keyBindingUnderline(HTMLEditorPane htmlEditorPane) {
        Action underlineAction =new AbstractAction("underline") {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractEditor editor =htmlEditorPane.getSelectedEditor();
                AbstractToolBar abstractToolBar = editor.getToolBar();
                DefaultWysiwygToolBar defaultWysiwygToolBar= (DefaultWysiwygToolBar) abstractToolBar;

                for (Object a: defaultWysiwygToolBar.getActionList()
                ) {
                    Action aa = (Action) a;
                    if(aa!=null && aa.getValue("Name").equals("Underline")){
                        aa.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));

                    }
                }
            }
        };
        return underlineAction;
    }

    public static Action keyBindingPasteFormatted(HTMLEditorPane htmlEditorPane) {
        Action pasteFormattedAction =new AbstractAction("Paste formatted") {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractEditor editor =htmlEditorPane.getSelectedEditor();
                AbstractToolBar abstractToolBar = editor.getToolBar();
                DefaultWysiwygToolBar defaultWysiwygToolBar= (DefaultWysiwygToolBar) abstractToolBar;
                defaultWysiwygToolBar.getActionList().add(new PasteFormattedAction());

                for (Object a: defaultWysiwygToolBar.getActionList()
                ) {
                    Action aa = (Action) a;
                    if(aa!=null && aa.getValue("Name").equals("Paste formatted")){
                        try{
                            aa.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
                        }catch (NullPointerException e1){

                        }
                        break;
                    }
                }
            }
        };
        return pasteFormattedAction;
    }

    public static Action keyBindingUndo(HTMLEditorPane htmlEditorPane) {
        Action undoAction =new AbstractAction("undo") {
            @Override
            public void actionPerformed(ActionEvent e) {

                    Action a = CompoundUndoManager.UNDO;

                        a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

            }
        };
        return undoAction;

    }

    public static Action keyBindingRedo(HTMLEditorPane htmlEditorPane) {
        Action redoAction =new AbstractAction("redo") {
            @Override
            public void actionPerformed(ActionEvent e) {
               Action a = CompoundUndoManager.REDO;
               try{
                   a.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
               }catch (CannotRedoException e1){

               }
        }
        };
        return redoAction;
    }

    public static Action keyBindingSave(HTMLEditorPane htmlEditorPane, JLabel actualVersionLabel, JLabel editedVersionLabel, JButton action) {
        Action saveAction =new  AbstractAction("save") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("csd");
                if (actualVersionLabel.getText().equals(editedVersionLabel.getText())) {
                    action.doClick();

                } else {

                    int n = JOptionPane.showConfirmDialog(
                            null,
                            "Edytowana wersja nie jest aktualną wersją hasła. Czy napewno chcesz ją nadpisac?",
                            "Czy napewno aktualizować",
                            JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        action.doClick();
                    }
                }

            }
        };
        return  saveAction;
    }

    public static Action keyBindingPrint(JButton printTermButton) {
        Action printAction =new  AbstractAction("print") {
            @Override
            public void actionPerformed(ActionEvent e) {

                printTermButton.doClick();

            }
        };
        return  printAction;
    }
}

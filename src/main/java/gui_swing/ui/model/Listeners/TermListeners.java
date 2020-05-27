package gui_swing.ui.model.Listeners;

import gui_swing.ui.model.Components.TermWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TermListeners {
    public static class RadioButtonsActionListener implements ActionListener{

        JRadioButton myRadioButton;
        JRadioButton secondRadioButton;
        JRadioButton thirdRadioButton;

       // HTMLEditorPane htmlEditorPane;

        @Override
        public void actionPerformed(ActionEvent e) {
            if(myRadioButton.isSelected()){

                secondRadioButton.setSelected(false);
                thirdRadioButton.setSelected(false);

                //htmlEditorPane.setText(TermWindow.removeSearchTags());

            }
        }

        public RadioButtonsActionListener(JRadioButton myRadioButton, JRadioButton secondRadioButton, JRadioButton thirdRadioButton) {
            this.myRadioButton = myRadioButton;
            this.secondRadioButton = secondRadioButton;
            this.thirdRadioButton = thirdRadioButton;
           // this.htmlEditorPane = htmlEditorPane;
        }
    }

    public static class ReferencesTablesActionListener extends MouseAdapter {
       @Override
       public void mousePressed(MouseEvent mouseEvent){
           JTable table = (JTable) mouseEvent.getSource();
           if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1){
              TermWindow termWindow = new TermWindow((Integer) table.getValueAt(table.getSelectedRow(),0));
              termWindow.getFrame().setTitle((String) table.getValueAt(table.getSelectedRow(),1));
           }
       }
    }

}

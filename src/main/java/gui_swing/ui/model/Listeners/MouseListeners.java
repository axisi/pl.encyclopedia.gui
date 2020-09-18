package gui_swing.ui.model.Listeners;

import gui_swing.ui.model.CheckListItem;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListeners {
    public static class CheckListItemMouseListenerStatuses extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent event) {
            JList list = (JList) event.getSource();
            if(list.getModel().getSize()>0){
                int index = list.locationToIndex(event.getPoint());// Get index of item
                // clicked
                for (int i = 0; i < list.getModel().getSize(); i++) {
                    CheckListItem item = (CheckListItem) list.getModel()
                            .getElementAt(i);
                    item.setSelected(false);
                    list.repaint(list.getCellBounds(i, i));
                    //System.out.println(item.isSelected());

                }
                CheckListItem item = (CheckListItem) list.getModel()
                        .getElementAt(index);
                item.setSelected(true); // Toggle selected state
                list.repaint(list.getCellBounds(index, index));// Repaint cell
            }

        }
    }
    public static class CheckListItemMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent event) {
            JList list = (JList) event.getSource();
            int index = list.locationToIndex(event.getPoint());// Get index of item
            // clicked
            if (index > -1) {


            CheckListItem item = (CheckListItem) list.getModel()
                    .getElementAt(index);
            item.setSelected(!item.isSelected()); // Toggle selected state
            list.repaint(list.getCellBounds(index, index));// Repaint cell
            }
        }


    }
}

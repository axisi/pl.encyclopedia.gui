

package gui_swing.ui.model;


import gui_swing.ui.model.Listeners.MouseListeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class RenderTermsFilters{
    private Boolean isTagsTableRendered=false;

    public class CheckListRenderer extends JCheckBox implements ListCellRenderer {
        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean hasFocus) {
            setEnabled(list.isEnabled());
            setSelected(((CheckListItem) value).isSelected());
            setFont(list.getFont());
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            setText(value.toString());
            return this;
        }
    }
    public RenderTermsFilters(JList[]jLists, int option) {


        switch (option) {
            case 1:
            //jLists[0].setListData(new JLabel[]{new JLabel("aaaa")});
            jLists[0].setListData(new CheckListItem[]{
                    new CheckListItem("Życie polityczne"),
                    new CheckListItem("Życie gospodarcze"),
                    new CheckListItem("Mniejszości i stowarzyszenia"),
                    new CheckListItem("Sztuka"),
                    new CheckListItem("Literatura, teatr, muzyka"),
                    new CheckListItem("Nauka i biografistyka"),
                    new CheckListItem("Historia i kalendarium"),
                    new CheckListItem("Cracovia sacra"),
                    new CheckListItem("Geografia i środowisko przyrodnicze"),
                    new CheckListItem("Architektura i urbanizacja"),
                    new CheckListItem("Odsyłaczowe")

            });
           /* jLists[1].setListData(new CheckListItem[]{
                    new CheckListItem("Hasło w całości przenoszone"),
                    new CheckListItem("Hasło wymagające poprawy"),
                    new CheckListItem("Hasło wymagające dużej poprawy"),
                    new CheckListItem("Hasło nowe"),
                    new CheckListItem("Propozycja nowego hasła")
            });*/

            jLists[2].setListData(new CheckListItem[]{
                    new CheckListItem("Nowy"),
                    new CheckListItem("W redakcji"),
                    new CheckListItem("U autora"),
                    new CheckListItem("U recenzenta"),
                    new CheckListItem("U redaktora merytorycznego działu"),
                    new CheckListItem("U redaktora naukowego"),
                    new CheckListItem("W pierwszej korekcie"),
                    new CheckListItem("W drugiej korekcie"),
                    new CheckListItem("Gotowe do składu"),
                    new CheckListItem("Usunięte")



            });
            break;

        }
        for (JList myList: jLists
        ) {
            setJListCheckBoxFeatures(myList,1);
        }
    }

    public void setJListCheckBoxFeatures(JList myList , int option) {
        myList.setCellRenderer(new CheckListRenderer());
        myList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if(option==1) {
            myList.addMouseListener(new MouseListeners.CheckListItemMouseListener());



                    /*new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    JList list = (JList) event.getSource();
                    int index = list.locationToIndex(event.getPoint());// Get index of item
                    // clicked
                    CheckListItem item = (CheckListItem) list.getModel()
                            .getElementAt(index);
                    item.setSelected(!item.isSelected()); // Toggle selected state
                    list.repaint(list.getCellBounds(index, index));// Repaint cell
                }
            });*/
        }
    }


    public void setTagsJList(JList tagsJList, ArrayList<String> tagsName) {
        int i=0;
        CheckListItem[] tagsCheckListItem= new CheckListItem[tagsName.size()];
        for (String t: tagsName
             ) {
            tagsCheckListItem[i++] = new CheckListItem(t);
        }
        tagsJList.setListData(tagsCheckListItem);
        if(!isTagsTableRendered) {

            //setJListCheckBoxFeatures(tagsJList,1);
            isTagsTableRendered=true;
        }

    }
}
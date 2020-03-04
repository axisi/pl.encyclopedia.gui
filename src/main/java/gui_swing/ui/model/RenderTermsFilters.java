

package gui_swing.ui.model;


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
                    new CheckListItem("Architektura i urbanizacja")

            });
            jLists[1].setListData(new CheckListItem[]{
                    new CheckListItem("Hasło w całości przenoszone"),
                    new CheckListItem("Hasło wymagające poprawy"),
                    new CheckListItem("Hasło wymagające dużej poprawy"),
                    new CheckListItem("Hasło nowe"),
                    new CheckListItem("Propozycja nowego hasła")
            });

            jLists[2].setListData(new CheckListItem[]{
                    new CheckListItem("Nowy"),
                    new CheckListItem("U autora aktualizacji"),
                    new CheckListItem("Zwrócone przez autora aktualizacji"),
                    new CheckListItem("U autora"),
                    new CheckListItem("Zwrócone przez autora"),
                    new CheckListItem("U kierownika działu"),
                    new CheckListItem("Zwrócone przez kierownika działu"),
                    new CheckListItem("U recenzenta"),
                    new CheckListItem("Zwrócone przez recenzenta"),
                    new CheckListItem("U redaktora naukowego"),
                    new CheckListItem("Zwrócone przez redaktora naukowego"),
                    new CheckListItem("Gotowe do redakcji"),
                    new CheckListItem("Gotowe do druku"),
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
            myList.addMouseListener(new MouseAdapter() {
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
            });
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
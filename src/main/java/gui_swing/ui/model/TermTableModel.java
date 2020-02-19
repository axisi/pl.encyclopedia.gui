package gui_swing.ui.model;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;
import java.util.Vector;

import static java.awt.desktop.UserSessionEvent.Reason.LOCK;

public class TermTableModel extends DefaultTableModel {

    public TermTableModel(Vector<?> columnNames, int rowCount) {
        super(columnNames, rowCount);
    }
    //String[] terms = {"Id", "Tytuł", "Ilość wersji", "Aktualna wersja", "Autorzy", "Podpis"};
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
            case 1:

            case 3:
            case 4:
                return Long.class;
            case 2:
            case 5:


                return String.class;
            case 6:
                return Boolean.class;
            case 7:
                return Icon.class;//getValueAt(0,columnIndex).getClass();
            default:
                return JLabel.class;
        }
    }
    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }


/* int i =0;
            for (Object o : columnNames) {
            terms[i]=o.toString();
            i++;
        }*/
   /* */

   /* @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }



    @Override
        public int getColumnCount() {
            return columnNames.length;
        }



    @Override
    public int getRowCount()
    {
        int result = 0;
        synchronized(LOCK) {
            if(listOfTerms != null) {
                result = listOfTerms.size();
            } // if
        } // synchronized
        return result;
    }
*/


}

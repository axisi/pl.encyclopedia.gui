package gui_swing.ui.model.tableModels;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;
import java.util.Vector;



public class TermTableModel extends DefaultTableModel {

    public TermTableModel(Vector<?> columnNames, int rowCount) {
        super(columnNames, rowCount);
    }
    //String[] terms = {"Id", "Tytuł", "Ilość wersji", "Aktualna wersja", "Autorzy", "Podpis"};
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
           // case 1:

           // case 3:
           // case 4:
                return Long.class;
           // case 2:
            case 1:


                return String.class;
           // case 6:
               // return Boolean.class;
            case 2:
                return Icon.class;//getValueAt(0,columnIndex).getClass();
            default:
                return JLabel.class;
        }
    }
    @Override
    public int getColumnCount() {
        return 3;
    }
    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Lp";
               /* case 1:
                return "Id";*/
            case 1:
                return "Tytuł";
           /* case 3:
                return "Ilość wersji";
            case 4:
                return "Aktualna wersja";
                case 5:
                return "Autorzy";
                case 6:
                return "Podpis";*/
                case 3:
                return "Tagi hasła";

        }
        return null;
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

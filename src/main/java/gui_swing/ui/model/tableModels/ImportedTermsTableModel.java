package gui_swing.ui.model.tableModels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ImportedTermsTableModel extends DefaultTableModel {
    public ImportedTermsTableModel() {
    }


    @Override
    public int getColumnCount() {
        return 7;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        //return getValueAt(0, columnIndex).getClass();
        switch (columnIndex){
            case 0:
                return Boolean.class;

            case 2:
            case 5:
                return String.class;
            case 6:
                return JComboBox.class;
            default:
                return Integer.class;


        }
    }

    @Override
    public boolean isCellEditable(int row, int column){

        switch (column) {

            case 0:
            case 4:
            case 6:
                return true;
            default:
                return false;
        }

    }
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Czy aktualizować";
            case 1:
                return "Id";
            case 2:
                return "Hasło";
            case 3:
                return "Ilość wersji";
            case 4:
                return "Wybrana wersja";
            case 5:
                return "Porównaj wersje";
            default:
                return "Status";
        }
    }
}

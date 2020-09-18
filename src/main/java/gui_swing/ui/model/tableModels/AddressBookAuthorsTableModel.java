package gui_swing.ui.model.tableModels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AddressBookAuthorsTableModel extends DefaultTableModel {
    public AddressBookAuthorsTableModel() {
    }
    @Override
    public int getColumnCount() {
        return 3;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        //return getValueAt(0, columnIndex).getClass();

            return String.class;

    }
    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Imię";
            case 1:
                return "Nazwisko";
            default:
                return "e-mail";
        }
    }
}

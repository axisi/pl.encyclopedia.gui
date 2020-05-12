package gui_swing.ui.model.tableModels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class ReferencesTermTableModel extends DefaultTableModel {
    public ReferencesTermTableModel(Vector<?> columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public ReferencesTermTableModel() {
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return Integer.class;
            default:
                return String.class;
        }
    }
    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            default:
                return "Tytuł";
        }
    }


}

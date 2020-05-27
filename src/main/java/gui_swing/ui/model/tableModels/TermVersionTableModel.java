package gui_swing.ui.model.tableModels;

import javax.swing.table.DefaultTableModel;
import java.sql.Timestamp;
import java.util.Vector;

public class TermVersionTableModel extends DefaultTableModel {
    public TermVersionTableModel(Vector<?> columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public TermVersionTableModel() {
    }
    @Override
    public int getColumnCount() {
        return 3;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return Integer.class;
            case 1:
                return Timestamp.class;
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
                return "Numer wersji";
            case 1:
                return "Data stworzenia";
            default:
                return "Utworzona przez";
        }
    }
}

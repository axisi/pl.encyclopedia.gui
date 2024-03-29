package gui_swing.ui.model.tableModels;

import javax.swing.table.DefaultTableModel;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;

public class TermVersionTableModel extends DefaultTableModel {
    public TermVersionTableModel(Vector<?> columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public TermVersionTableModel() {
    }
    @Override
    public int getColumnCount() {
        return 5;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return Integer.class;
            case 1:
                return DateTimeFormatter.class;
            case 2:
                return Boolean.class;
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
            case 2:
                return "Czy aktualna";
            case 3:
                return "Status";
            default:
                return "Utworzona przez";
        }
    }
}

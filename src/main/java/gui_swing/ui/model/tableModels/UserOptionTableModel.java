package gui_swing.ui.model.tableModels;

import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class UserOptionTableModel extends DefaultTableModel {

    public UserOptionTableModel(Vector<?> columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public UserOptionTableModel() {
    }
    @Override
    public int getColumnCount() {
        return 4;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return Integer.class;
            default:
                return  String.class;

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
            case 1:
                return "Login";
            case 2:
                return "Email";
            default:
                return "Rola";
        }
    }
}

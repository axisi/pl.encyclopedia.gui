package gui_swing.ui.model.tableModels;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class AuthorTableModel extends DefaultTableModel {

    public AuthorTableModel(Vector<?> columnNames, int rowCount) {
        super(columnNames, rowCount);
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){


            case 1:
                case 2:
                case 3:
                case 4:
                return String.class;
            default:
                return Long.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column){

        return  false;

    }
}

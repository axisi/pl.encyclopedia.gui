package gui_swing.ui.model.tableModels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class WordGenerateTableModel extends DefaultTableModel {
    public WordGenerateTableModel(Vector<?> columnNames, int rowCount) {
        super(columnNames, rowCount);
    }


    public WordGenerateTableModel() {
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        //return getValueAt(0, columnIndex).getClass();
        switch (columnIndex){
            case 0:
                return Boolean.class;

            case 2:
                return String.class;

                default:
                    return Integer.class;


        }
    }
    @Override
    public boolean isCellEditable(int row, int column){

        switch (column) {

            case 0:
            case 4:
                return true;
            default:
                return false;
        }

    }
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Czy generować";
            case 1:
                return "Id";
            case 2:
                return "Hasło";
            case 3:
                return "Ilość wersji";
            default:
                return "Wybrana wersja";
        }
    }
}

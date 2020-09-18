package gui_swing.ui.model.tableModels;

import gui_swing.ui.controller.MainController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class AuthorTermTableModel extends DefaultTableModel {

    public AuthorTermTableModel(Vector<?> columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 1:
                return Boolean.class;
            case 2:
            case 3:
                return String.class;
            default:
                return Integer.class;
        }
    }
    @Override
    public boolean isCellEditable(int row, int column){

        switch (column) {
            case 0:
            case 2:
            case 3:
                return false;
            default:
                return true;
        }


    }
}

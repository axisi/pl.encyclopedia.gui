package gui_swing.ui.model;

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
            case 0:
                return Boolean.class;
            case 1:
                return String.class;
            default:
                return Long.class;
        }
    }
    @Override
    public boolean isCellEditable(int row, int column){

        switch (column) {
            case 1:
                return false;
            default:
                return true;
        }


    }
}

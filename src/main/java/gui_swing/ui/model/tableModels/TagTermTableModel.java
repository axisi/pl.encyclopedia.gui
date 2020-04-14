package gui_swing.ui.model.tableModels;


import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class TagTermTableModel extends DefaultTableModel {

        public TagTermTableModel(Vector<?> columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex){
                case 0:
                    return Boolean.class;
                case 1:
                    return Integer.class;
                default:
                    return String.class;
            }
        }
        @Override
        public boolean isCellEditable(int row, int column){

            switch (column) {
                case 0:
                    return true;
                default:
                    return false;
            }


        }
    }



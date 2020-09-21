package gui_swing.ui.model.tableModels;

import javax.swing.table.DefaultTableModel;

public class EditorsCategoriesTableModel  extends DefaultTableModel {
    public EditorsCategoriesTableModel() {
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        //return getValueAt(0, columnIndex).getClass();
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
            case 1:
                return "Imię";
            case 2:
                return "Nazwisko";
            default:
                return "e-mail";
        }
    }
}

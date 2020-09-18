package gui_swing.ui.model.tableModels;

import javax.swing.table.DefaultTableModel;

public class AddressBookUsersTableModel extends DefaultTableModel {

    public AddressBookUsersTableModel() {
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        //return getValueAt(0, columnIndex).getClass();

        return String.class;

    }
    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "login";

            default:
                return "e-mail";
        }
    }
}

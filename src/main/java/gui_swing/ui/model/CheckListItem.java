package gui_swing.ui.model;

public class CheckListItem {

    private String label;
    private boolean isSelected = false;

    public CheckListItem(String label) {
        this.label = label;
    }
    public CheckListItem(String label,Boolean isSelected) {
        this.label = label;
        this.setSelected(isSelected);
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public String toString() {
        return label;
    }
}

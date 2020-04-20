package gui_swing.ui.model;

import java.util.List;

public interface PaginationDataProvider<T> {
    int getTotalRowCount();

    List<T> getRows(int startIndex, int endIndex);
}

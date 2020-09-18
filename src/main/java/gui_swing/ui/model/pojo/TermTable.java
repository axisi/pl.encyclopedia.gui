package gui_swing.ui.model.pojo;

import javax.swing.*;

public class TermTable {
    public Long getLp() {
        return lp;
    }

    private Long lp;

    private Long id;
    private String title;
    /*private Long versionCount;
    private Long actualVersion;
    private String authors;
    private Boolean isSigned;*/
    private Icon tags;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setLp(Long lp) {
        this.lp = lp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Icon getTags() {
        return tags;
    }

    public void setTags(Icon tags) {
        this.tags = tags;
    }
}

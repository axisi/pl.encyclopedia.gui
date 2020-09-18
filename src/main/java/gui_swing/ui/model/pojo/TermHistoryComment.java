package gui_swing.ui.model.pojo;

import java.sql.Timestamp;

public class TermHistoryComment {
    private Long id;
    private Timestamp creationDate;
    private Timestamp modifiedDate;
    private String content;
    private Boolean visibleInWholeTerm;
    private Integer lp;
    private Timestamp deadlineDate;
    private Long startIndex;
    private Long length;
    private  User modifiedBy;


    public TermHistoryComment() {

    }

    public TermHistoryComment(Integer commentLp, Timestamp value, String text, boolean selected) {
        this.lp= commentLp;
        this.deadlineDate = value;
        this.content = text;
        this.visibleInWholeTerm = selected;

    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Long startIndex) {
        this.startIndex = startIndex;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getVisibleInWholeTerm() {
        return visibleInWholeTerm;
    }

    public void setVisibleInWholeTerm(Boolean visibleInWholeTerm) {
        this.visibleInWholeTerm = visibleInWholeTerm;
    }

    public Integer getLp() {
        return lp;
    }

    public void setLp(Integer lp) {
        this.lp = lp;
    }

    public Timestamp getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Timestamp deadlineDate) {
        this.deadlineDate = deadlineDate;
    }
}

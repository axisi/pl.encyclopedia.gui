package gui_swing.ui.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TermHistory {
    private Long id;
    private Timestamp creationDate;
    private Timestamp modifiedDate;
    private String content;
    private Long version;
    private String status;
    private String status1;

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }



    public String getContent() {
        return content;
    }

    public Long getVersion() {
        return version;
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

    public void setContent(String content) {
        this.content = content;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}

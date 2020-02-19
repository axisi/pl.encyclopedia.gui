package gui_swing.ui.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TermHistory {
    private float id;
    private float creationDate;
    private float modifiedDate;
    private String content;
    private float version;


    // Getter Methods

    public float getId() {
        return id;
    }

    public float getCreationDate() {
        return creationDate;
    }

    public float getModifiedDate() {
        return modifiedDate;
    }

    public String getContent() {
        return content;
    }

    public float getVersion() {
        return version;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setCreationDate(float creationDate) {
        this.creationDate = creationDate;
    }

    public void setModifiedDate(float modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setVersion(float version) {
        this.version = version;
    }

}

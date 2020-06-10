package gui_swing.ui.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRole {
    private Long id;
    private Timestamp creationDate;
    private Timestamp modifiedDate;
    private String name;

    public UserRole(String name) {
        this.name = name;
    }

    public UserRole() {
    }
    // Getter Methods

    public Long getId() {
        return id;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public String getName() {
        return name;
    }

    // Setter Methods

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

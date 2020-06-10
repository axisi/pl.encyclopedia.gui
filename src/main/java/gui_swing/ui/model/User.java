package gui_swing.ui.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;


@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private Long id;
    private Timestamp creationDate;
    private Timestamp modifiedDate;
    private String login;
    private UserRole userRole;
    private Boolean hidden;
    private String email;


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

    public String getLogin() {
        return login;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public boolean getHidden() {
        return hidden;
    }

    public String getEmail() {
        return email;
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

    public void setLogin(String login) {
        this.login = login;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package gui_swing.ui.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {
    private float id;
    private String creationDate = null;
    private String modifiedDate = null;
    private String name;
    private String surname;
    private String email;
   // private String sign;
    private String telephone;

    ArrayList< Object > listCategoryManager = new ArrayList < Object > ();
    ArrayList < Object > termHistoriesAuthors = new ArrayList < Object > ();


    // Getter Methods

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public float getId() {
        return id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    /*public String getSign() {
        return sign;
    }*/

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public void setSign(String sign) {
        this.sign = sign;
    }*/
}

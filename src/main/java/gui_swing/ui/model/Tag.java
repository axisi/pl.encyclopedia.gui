package gui_swing.ui.model;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.List;

public class Tag {
    private float id;
    private Timestamp creationDate = null;
    private Timestamp modifiedDate = null;
    private String name;
    private String iconName;
    //private ImageIcon icon;

    // Getter Methods

    public float getId() {
        return id;
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

    public String getName() {
        return name;
    }

    public String getIconName() {
        return iconName;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }




    public void setName(String name) {
        this.name = name;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    /*public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }*/
}

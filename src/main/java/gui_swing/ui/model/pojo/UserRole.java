package gui_swing.ui.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.swing.*;
import java.sql.Timestamp;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRole {
    private Long id;
    private Timestamp creationDate;
    private Timestamp modifiedDate;
    private String name;

    private Boolean usersVisible;
    private Boolean usersEditable;
    private Boolean rolesModification;

    private Boolean authorsManagement;
    private Boolean subCategoriesManagement;
    private Boolean tagsManagement;

    private Boolean editCategory1;
    private Boolean editCategory2;
    private Boolean editCategory3;
    private Boolean editCategory4;
    private Boolean editCategory5;
    private Boolean editCategory6;
    private Boolean editCategory7;
    private Boolean editCategory8;
    private Boolean editCategory9;
    private Boolean editCategory10;
    private Boolean editCategory11;


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

    public Boolean getUsersVisible() {
        return usersVisible;
    }

    public void setUsersVisible(Boolean usersVisible) {
        this.usersVisible = usersVisible;
    }

    public Boolean getUsersEditable() {
        return usersEditable;
    }

    public void setUsersEditable(Boolean usersEditable) {
        this.usersEditable = usersEditable;
    }

    public Boolean getRolesModification() {
        return rolesModification;
    }

    public void setRolesModification(Boolean rolesModification) {
        this.rolesModification = rolesModification;
    }

    public Boolean getAuthorsManagement() {
        return authorsManagement;
    }

    public void setAuthorsManagement(Boolean authorsManagement) {
        this.authorsManagement = authorsManagement;
    }

    public Boolean getSubCategoriesManagement() {
        return subCategoriesManagement;
    }

    public void setSubCategoriesManagement(Boolean subCategoriesManagement) {
        this.subCategoriesManagement = subCategoriesManagement;
    }

    public Boolean getTagsManagement() {
        return tagsManagement;
    }

    public void setTagsManagement(Boolean tagsManagement) {
        this.tagsManagement = tagsManagement;
    }

    public Boolean getEditCategory1() {
        return editCategory1;
    }

    public void setEditCategory1(Boolean editCategory1) {
        this.editCategory1 = editCategory1;
    }

    public Boolean getEditCategory2() {
        return editCategory2;
    }

    public void setEditCategory2(Boolean editCategory2) {
        this.editCategory2 = editCategory2;
    }

    public Boolean getEditCategory3() {
        return editCategory3;
    }

    public void setEditCategory3(Boolean editCategory3) {
        this.editCategory3 = editCategory3;
    }

    public Boolean getEditCategory4() {
        return editCategory4;
    }

    public void setEditCategory4(Boolean editCategory4) {
        this.editCategory4 = editCategory4;
    }

    public Boolean getEditCategory5() {
        return editCategory5;
    }

    public void setEditCategory5(Boolean editCategory5) {
        this.editCategory5 = editCategory5;
    }

    public Boolean getEditCategory6() {
        return editCategory6;
    }

    public void setEditCategory6(Boolean editCategory6) {
        this.editCategory6 = editCategory6;
    }

    public Boolean getEditCategory7() {
        return editCategory7;
    }

    public void setEditCategory7(Boolean editCategory7) {
        this.editCategory7 = editCategory7;
    }

    public Boolean getEditCategory8() {
        return editCategory8;
    }

    public void setEditCategory8(Boolean editCategory8) {
        this.editCategory8 = editCategory8;
    }

    public Boolean getEditCategory9() {
        return editCategory9;
    }

    public void setEditCategory9(Boolean editCategory9) {
        this.editCategory9 = editCategory9;
    }

    public Boolean getEditCategory10() {
        return editCategory10;
    }

    public void setEditCategory10(Boolean editCategory10) {
        this.editCategory10 = editCategory10;
    }

    public Boolean getEditCategory11() {
        return editCategory11;
    }

    public void setEditCategory11(Boolean editCategory11) {
        this.editCategory11 = editCategory11;
    }

    @Override
    public String toString() {
        return name;
    }
}

package gui_swing.ui.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Term {

    private Long id;
    private Timestamp creationDate;
    private Timestamp modifiedDate;
    private Long actualVersion;
    private Boolean isSigned;
    private String title;
    private String category;
    private String subcategory;
    private ArrayList<TermHistory> termHistories = new ArrayList<TermHistory>();
    private ArrayList<String> authors = new ArrayList<String>();


    private ArrayList<Tag> tags = new ArrayList<Tag>();
    private ArrayList<String> statuses = new ArrayList<String>();
    private ArrayList<String> tagsString = new ArrayList<String>();


    public Boolean getSigned() {
        return isSigned;
    }

    public void setSigned(Boolean signed) {
        isSigned = signed;
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

    public Long getActualVersion() {
        return actualVersion;
    }

    public void setActualVersion(Long actualVersion) {
        this.actualVersion = actualVersion;
    }

    public boolean isSigned() {
        return isSigned;
    }

    public void setSigned(boolean signed) {
        isSigned = signed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public ArrayList<TermHistory> getTermHistories() {
        return termHistories;
    }

    public void setTermHistories(ArrayList<TermHistory> termHistories) {
        this.termHistories = termHistories;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(ArrayList<String> statuses) {
        this.statuses = statuses;
    }

    public ArrayList<String> getTagsString() {
        return tagsString;
    }

    public void setTagsString(ArrayList<String> tagsString) {
        this.tagsString = tagsString;
    }
}



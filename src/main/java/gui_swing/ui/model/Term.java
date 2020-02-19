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
        private boolean isSigned;
        private String title;
        ArrayList< TermHistory > termHistories = new ArrayList <TermHistory> () ;

    public ArrayList<TermHistory> getTermHistories() {
        return termHistories;
    }

    public void setTermHistories(ArrayList<TermHistory> termHistories) {
        this.termHistories = termHistories;
    }


        ArrayList < Tag > tags = new ArrayList < Tag > ();


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

        public float getActualVersion() {
            return actualVersion;
        }

        public boolean getIsSigned() {
            return isSigned;
        }

        public String getTitle() {
            return title;
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

        public void setActualVersion(Long actualVersion) {
            this.actualVersion = actualVersion;
        }

        public void setIsSigned(boolean isSigned) {
            this.isSigned = isSigned;
        }

        public void setTitle(String title) {
            this.title = title;
        }


    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
}


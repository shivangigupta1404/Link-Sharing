package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class DocumentResource extends Resource {
    String filepath;

    public DocumentResource() {
    }

    public DocumentResource(String filepath) {
        this.filepath = filepath;
    }

    public DocumentResource(String description, User createdBy, Topic topic, Date dateCreated, Date lastUpdated, String filepath) {
        super(description, createdBy, topic, dateCreated, lastUpdated);
        this.filepath = filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilepath() {
        return filepath;
    }

    @Override
    public String toString() {
        return "DocumentResource{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", filepath='" + filepath + '\'' +
                ", createdBy=" + createdBy +
                ", topic=" + topic +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}

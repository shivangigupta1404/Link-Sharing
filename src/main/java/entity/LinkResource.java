package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class LinkResource extends Resource {
    String url;

    public LinkResource() {
    }

    public LinkResource(String url) {
        this.url = url;
    }

    public LinkResource(String description, User createdBy, Topic topic, Date dateCreated, Date lastUpdated, String url) {
        super(description, createdBy, topic, dateCreated, lastUpdated);
        this.url = url;
    }

    //SETTER

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "LinkResource{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", createdBy=" + createdBy +
                ", topic=" + topic +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}

package entity;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    @ManyToOne
    User createdBy;
    Date dateCreated;
    Date lastUpdated;
    Visibility visibility;

    public Topic(){}

    public Topic(String name, User createdBy, Visibility visibility) {
        this.name = name;
        this.createdBy = createdBy;
        this.visibility = visibility;
    }

    public Topic(String name, User createdBy, Date dateCreated, Date lastUpdated, Visibility visibility) {
        this.name = name;
        this.createdBy = createdBy;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.visibility = visibility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdBy=" + createdBy +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                ", visibility=" + visibility +
                '}';
    }
}

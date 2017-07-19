package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    Topic topic;
    @ManyToOne
    User user;
    Seriousness seriousness;
    Date dateCreated;

    public Subscription(){}

    public Subscription(Topic topic, User user, Seriousness seriousness, Date dateCreated) {
        this.topic = topic;
        this.user = user;
        this.seriousness = seriousness;
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seriousness getSeriousness() {
        return seriousness;
    }

    public void setSeriousness(Seriousness seriousness) {
        this.seriousness = seriousness;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}

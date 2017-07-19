package entity;

import javax.persistence.*;

@Entity
public class ReadingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    Resource resource;
    @ManyToOne
    User user;
    Boolean isRead;

    public ReadingItem() {
    }

    public ReadingItem(Resource resource, User user, Boolean isRead) {
        this.resource = resource;
        this.user = user;
        this.isRead = isRead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }
}

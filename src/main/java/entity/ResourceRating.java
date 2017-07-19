package entity;

import javax.persistence.*;

@Entity
public class ResourceRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    Resource resource;
    @ManyToOne
    User user;
    Integer score;

    public ResourceRating() {
    }

    public ResourceRating(Resource resource, User user, Integer score) {
        this.resource = resource;
        this.user = user;
        this.score = score;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}

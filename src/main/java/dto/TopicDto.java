package dto;

import entity.User;
import entity.Visibility;

import java.util.Date;

public class TopicDto {
    int id;
    String name;
    User createdBy;
    Date dateCreated;
    Date lastUpdated;
    Visibility visibility;
    int subscriptionCount;
    int postCount;

    public TopicDto() {}

    public TopicDto(int id, String name, User createdBy, Date dateCreated, Date lastUpdated, Visibility visibility, int subscriptionCount, int postCount) {
        this.id = id;
        this.name = name;
        this.createdBy = createdBy;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.visibility = visibility;
        this.subscriptionCount = subscriptionCount;
        this.postCount = postCount;
    }

    //GETTER AND SETTER
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

    public int getSubscriptionCount() {
        return subscriptionCount;
    }

    public void setSubscriptionCount(int subscriptionCount) {
        this.subscriptionCount = subscriptionCount;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }
}

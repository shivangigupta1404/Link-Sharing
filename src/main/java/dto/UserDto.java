package dto;

import java.util.Date;

public class UserDto {
    int id;
    String email;
    String username;
    String password;
    String firstname;
    String lastname;
    byte[] photo;
    Boolean admin;
    Boolean active;
    Date datecreated;
    Date lastupdated;

    int subscriptionCount;
    int topicCount;

    public UserDto() {}

    public UserDto(int id, String email, String username, String password, String firstname, String lastname, byte[] photo, Boolean admin, Boolean active, Date datecreated, Date lastupdated) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.photo = photo;
        this.admin = admin;
        this.active = active;
        this.datecreated = datecreated;
        this.lastupdated = lastupdated;
    }

    //GETTER AND SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Date getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Date lastupdated) {
        this.lastupdated = lastupdated;
    }

    public int getSubscriptionCount() {
        return subscriptionCount;
    }

    public void setSubscriptionCount(int subscriptionCount) {
        this.subscriptionCount = subscriptionCount;
    }

    public int getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(int topicCount) {
        this.topicCount = topicCount;
    }
}

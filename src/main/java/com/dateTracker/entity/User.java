package com.dateTracker.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The type User.  test
 */
@Entity(name = "User")
@Table(name = "user")

public class User {

    /**
     * Instance variables
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "user_name")
    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Event> events = new HashSet<>();

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param userName the user name
     */
    public User(String userName) {
        this.userName = userName;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets events.
     *
     * @return the events
     */
    public Set<Event> getEvents() {
        return events;
    }

    /**
     * Sets events.
     *
     * @param events the events
     */
    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    /**
     * Add event.
     *
     * @param event the event
     */
    public void addEvent(Event event) {
        events.add(event);
        event.setUser(this);
    }


    /**
     * Remove event.
     *
     * @param event the event
     */
    public void removeEvent(Event event) {
        events.remove(event);
        event.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (!getUserName().equals(user.getUserName())) return false;
        return getEvents().equals(user.getEvents());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getUserName().hashCode();
        result = 31 * result + getEvents().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", events=" + events +
                '}';
    }
}

package toni.aguilera.spaceStation.model;

import org.hibernate.annotations.Type;
import toni.aguilera.spaceStation.util.RoleEnum;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "user", schema = "spacestation")
public class User {

    @Id
    @Type(type = "uuid-char")
    private UUID id;

    private Timestamp createdOn;

    private Timestamp logIn;

    private Timestamp logOut;

    private Boolean active;

    private String name;

    private String surname;

    private String username;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;


    @ManyToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn(name = "room_id")
    @Type(type = "uuid-char")
    private Room room;

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getLogIn() {
        return logIn;
    }

    public void setLogIn(Timestamp logIn) {
        this.logIn = logIn;
    }

    public Timestamp getLogOut() {
        return logOut;
    }

    public void setLogOut(Timestamp logOut) {
        this.logOut = logOut;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

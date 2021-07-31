package toni.aguilera.spaceStation.model;

import toni.aguilera.spaceStation.util.RoleEnum;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "room")
public class Room {

    @Id
    private UUID id;
    private String name;
    private RoleEnum role;
    private Integer people;


    public Room() {
    }

    public Room(UUID id, String name, RoleEnum role, Integer people) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.people = people;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }
}

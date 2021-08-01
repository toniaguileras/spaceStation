package toni.aguilera.spaceStation.model;

import org.hibernate.annotations.Type;
import toni.aguilera.spaceStation.util.RoleEnum;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @Type(type = "uuid-char")
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;


    public Room() {
    }

    public Room(UUID id, String name, RoleEnum role) {
        this.id = id;
        this.name = name;
        this.role = role;
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

}

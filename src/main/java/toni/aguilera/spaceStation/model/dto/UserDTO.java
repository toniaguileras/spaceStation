package toni.aguilera.spaceStation.model.dto;

import toni.aguilera.spaceStation.util.RoleEnum;

import java.sql.Timestamp;

public class UserDTO {

    private String username;
    private Timestamp logIn;
    private Boolean active;
    private RoleEnum role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getLogIn() {
        return logIn;
    }

    public void setLogIn(Timestamp logIn) {
        this.logIn = logIn;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}

package toni.aguilera.spaceStation.model.dto;

import java.util.List;
import java.util.UUID;

public class UserRoomDTO {

    private UUID roomId;
    private String roomName;
    private List<UserDTO> users;

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}

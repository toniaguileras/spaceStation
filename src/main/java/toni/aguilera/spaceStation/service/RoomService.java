package toni.aguilera.spaceStation.service;

import toni.aguilera.spaceStation.model.dto.UserDTO;
import toni.aguilera.spaceStation.model.dto.UserRoomDTO;

import java.util.List;

public interface RoomService {
    List<UserRoomDTO> getUsersInRooms();
}

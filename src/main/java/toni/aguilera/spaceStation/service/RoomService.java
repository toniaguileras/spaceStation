package toni.aguilera.spaceStation.service;

import toni.aguilera.spaceStation.model.dto.UserDTO;
import toni.aguilera.spaceStation.model.dto.UserRoomDTO;

import java.util.List;
import java.util.UUID;

public interface RoomService {
    List<UserRoomDTO> getUsersInRooms();

    UserRoomDTO goToRoom(UUID roomId, UUID userId);
}

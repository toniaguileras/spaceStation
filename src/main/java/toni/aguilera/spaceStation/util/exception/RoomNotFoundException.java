package toni.aguilera.spaceStation.util.exception;

import org.springframework.http.HttpStatus;

public class RoomNotFoundException extends SpaceStationException {
  public RoomNotFoundException() {
    super("room.not.found.exception", "Room not found", HttpStatus.BAD_REQUEST);
  }
}

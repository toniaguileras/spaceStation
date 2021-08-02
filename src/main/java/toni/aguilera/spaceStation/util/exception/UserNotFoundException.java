package toni.aguilera.spaceStation.util.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends SpaceStationException {
  public UserNotFoundException() {
    super("user.not.found.exception", "User not found", HttpStatus.BAD_REQUEST);
  }
}

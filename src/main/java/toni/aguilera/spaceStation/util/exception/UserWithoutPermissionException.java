package toni.aguilera.spaceStation.util.exception;

import org.springframework.http.HttpStatus;

public class UserWithoutPermissionException extends SpaceStationException {
    public UserWithoutPermissionException() {
        super("user.without.permissions", "User without permission", HttpStatus.BAD_REQUEST);
    }
}

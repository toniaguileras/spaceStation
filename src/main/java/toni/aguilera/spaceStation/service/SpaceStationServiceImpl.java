package toni.aguilera.spaceStation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceStationServiceImpl implements SpaceStationService {

    private final UserService userService;
    private final RoomService roomService;

    @Autowired
    public SpaceStationServiceImpl(UserService userService, RoomService roomService) {
        this.userService = userService;
        this.roomService = roomService;
    }

    @Override
    public String launch() {
        return null;
    }
}

package toni.aguilera.spaceStation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toni.aguilera.spaceStation.model.Room;
import toni.aguilera.spaceStation.model.User;
import toni.aguilera.spaceStation.repository.RoomRepository;
import toni.aguilera.spaceStation.repository.UserRepository;
import toni.aguilera.spaceStation.util.RoleEnum;
import toni.aguilera.spaceStation.util.exception.RoomNotFoundException;

import java.util.List;

@Service
public class SpaceStationServiceImpl implements SpaceStationService {

    public static final String SPACE_SHIP = "Space ship";
    public static final String CONTROL_ROOM = "Control room";
    public static final String ROCKET_LAUNCHED = "Launch in 3..2..1.. Rocket Launched!!!";
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public SpaceStationServiceImpl(UserRepository userRepository, RoomRepository roomRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }


    /**
     * Se busca agrupar a las personas en la habitacion de control independientemente del rol, excepto
     * los astronautas que tendrán que estar en la nave para el lanzamiento
     *
     * @return cuenta atrás para el lanzamiento
     */
    @Override
    public String launch() {
        List<User> userList = userRepository.findAll();
        userList.forEach(this::sendUserToCorrectRoom);
        return ROCKET_LAUNCHED;
    }

    private void sendUserToCorrectRoom(User user) {
        if (user.getRole().equals(RoleEnum.ASTRONAUT)) {
            sendToRoom(user, SPACE_SHIP);
        } else {
            sendToRoom(user, CONTROL_ROOM);
        }
    }

    private void sendToRoom(User user, String roomName) {
        Room room = findRoomByNameIfExist(roomName);
        user.setRoom(room);
        userRepository.save(user);
    }


    private Room findRoomByNameIfExist(String name) {
        Room room = roomRepository.findByName(name);
        if (room == null) {
            throw new RoomNotFoundException();
        }
        return room;
    }
}

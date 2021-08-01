package toni.aguilera.spaceStation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toni.aguilera.spaceStation.model.Room;
import toni.aguilera.spaceStation.model.User;
import toni.aguilera.spaceStation.model.dto.UserDTO;
import toni.aguilera.spaceStation.model.dto.UserRoomDTO;
import toni.aguilera.spaceStation.repository.RoomRepository;
import toni.aguilera.spaceStation.repository.UserRepository;
import toni.aguilera.spaceStation.util.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;


    @Autowired
    public RoomServiceImpl(UserRepository userRepository, RoomRepository roomRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;

    }

    @Override
    public List<UserRoomDTO> getUsersInRooms() {
        List<UserRoomDTO> resultList = new ArrayList<>();
        List<Room> roomList = roomRepository.findAll();
        roomList.forEach(room -> resultList.add(groupUsersByRoom(room)));
        return resultList;
    }

    private UserRoomDTO groupUsersByRoom(Room room) {
        UserRoomDTO userRoomDTO = new UserRoomDTO();
        userRoomDTO.setRoomId(room.getId());
        userRoomDTO.setRoomName(room.getName());
        userRoomDTO.setUsers(UserMapper.map(userRepository.findByRoomId(room.getId())));
        return userRoomDTO;
    }
}

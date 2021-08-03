package toni.aguilera.spaceStation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toni.aguilera.spaceStation.model.Room;
import toni.aguilera.spaceStation.model.User;
import toni.aguilera.spaceStation.model.dto.UserDTO;
import toni.aguilera.spaceStation.model.dto.UserRoomDTO;
import toni.aguilera.spaceStation.repository.RoomRepository;
import toni.aguilera.spaceStation.repository.UserRepository;
import toni.aguilera.spaceStation.util.RoleEnum;
import toni.aguilera.spaceStation.util.exception.RoomNotFoundException;
import toni.aguilera.spaceStation.util.exception.UserNotFoundException;
import toni.aguilera.spaceStation.util.exception.UserWithoutPermissionException;
import toni.aguilera.spaceStation.util.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomServiceImpl implements RoomService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(UserRepository userRepository, RoomRepository roomRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    /**
     * Search and group users in each room
     *
     * @return list of users in each room
     */
    @Override
    public List<UserRoomDTO> getUsersInRooms() {
        List<UserRoomDTO> resultList = new ArrayList<>();
        List<Room> roomList = roomRepository.findAll();
        roomList.forEach(room -> resultList.add(groupUsersByRoom(room)));
        return resultList;
    }

    /**
     * Changes the room where user is at this moment
     *
     * @param roomId room where it's mean to go
     * @param userId user meant to go
     * @return UserRoomDTO with the actual room of the user
     */
    @Override
    public UserRoomDTO goToRoom(UUID roomId, UUID userId) {
        Room room = getRoomIfExist(roomId);
        User user = getUserIfExist(userId);
        if (room.getRole() == RoleEnum.ANY || room.getRole() == user.getRole()) {
            user.setRoom(room);
            userRepository.save(user);
            return createUserRoomDTO(user, room);
        }
        throw new UserWithoutPermissionException();

    }

    /**
     * Creates the dto from user and room entity
     *
     * @param user to return
     * @param room where's user
     * @return dto with user and room
     */
    private UserRoomDTO createUserRoomDTO(User user, Room room) {
        UserRoomDTO userRoomDTO = new UserRoomDTO();
        userRoomDTO.setRoomId(room.getId());
        userRoomDTO.setRoomName(room.getName());
        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(UserMapper.map(user));
        userRoomDTO.setUsers(userDTOList);
        return userRoomDTO;
    }

    /**
     * Searches user in database
     *
     * @param userId to search
     * @return user if exists, exception if not
     */
    private User getUserIfExist(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }
        return user.get();
    }

    /**
     * Searches room by id
     *
     * @param roomId to search
     * @return room if exist
     */
    private Room getRoomIfExist(UUID roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        if (!room.isPresent()) {
            throw new RoomNotFoundException();
        }
        return room.get();
    }

    /**
     * Returns user in a room
     *
     * @param room meant to search users in
     * @return dto with room and users in
     */
    private UserRoomDTO groupUsersByRoom(Room room) {
        UserRoomDTO userRoomDTO = new UserRoomDTO();
        userRoomDTO.setRoomId(room.getId());
        userRoomDTO.setRoomName(room.getName());
        userRoomDTO.setUsers(UserMapper.map(userRepository.findByRoomId(room.getId())));
        return userRoomDTO;
    }
}

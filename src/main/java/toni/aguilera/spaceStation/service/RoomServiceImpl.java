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
     * Devuelve los usuarios que hay en cada habitación
     *
     * @return listado de usuarios por habitación
     */
    @Override
    public List<UserRoomDTO> getUsersInRooms() {
        List<UserRoomDTO> resultList = new ArrayList<>();
        List<Room> roomList = roomRepository.findAll();
        roomList.forEach(room -> resultList.add(groupUsersByRoom(room)));
        return resultList;
    }

    /**
     * Hace que el usuario que se pasa, esté en la habitación indicada
     *
     * @param roomId id de la habitacion a la que va a ir
     * @param userId id del usuario que va a ir a la habitacion
     * @return UserRoomDTO con la habitacion en la que está el usuario
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
     * Crea el dto que se devuelve en el metodo goToRoom
     *
     * @param user usuario que se devuelve
     * @param room habitacion a la que va el usuario
     * @return el dto con el usuario y la habitacion a la que va
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
     * Busca el usuario por id
     *
     * @param userId usuario a buscar
     * @return el usuario si existe, exception si no existe
     */
    private User getUserIfExist(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }
        return user.get();
    }

    /**
     * Busca la habitacion por id
     *
     * @param roomId id de la habitacion
     * @return la habitacion, exception si no existe
     */
    private Room getRoomIfExist(UUID roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        if (!room.isPresent()) {
            throw new RoomNotFoundException();
        }
        return room.get();
    }

    /**
     * Devuelve los usuarios que hay en la habitacion
     *
     * @param room habitacion en la que se buscan los usuarios
     * @return el dto con los usuarios por habitacion
     */
    private UserRoomDTO groupUsersByRoom(Room room) {
        UserRoomDTO userRoomDTO = new UserRoomDTO();
        userRoomDTO.setRoomId(room.getId());
        userRoomDTO.setRoomName(room.getName());
        userRoomDTO.setUsers(UserMapper.map(userRepository.findByRoomId(room.getId())));
        return userRoomDTO;
    }
}

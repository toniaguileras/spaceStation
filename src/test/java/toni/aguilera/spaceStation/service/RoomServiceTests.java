package toni.aguilera.spaceStation.service;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import toni.aguilera.spaceStation.model.Room;
import toni.aguilera.spaceStation.model.User;
import toni.aguilera.spaceStation.model.dto.UserDTO;
import toni.aguilera.spaceStation.model.dto.UserRoomDTO;
import toni.aguilera.spaceStation.repository.RoomRepository;
import toni.aguilera.spaceStation.repository.UserRepository;
import toni.aguilera.spaceStation.util.RoleEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoomServiceTests {

    private RoomService roomService;
    private RoomRepository roomRepository;
    private UserRepository userRepository;

    @BeforeEach()
    public void setUp() {
        roomRepository = mock(RoomRepository.class);
        userRepository = mock(UserRepository.class);
        roomService = new RoomServiceImpl(userRepository, roomRepository);
    }

    @Test
    public void getUsersInRoomsTest() {
        List<UserRoomDTO> resultList = new ArrayList<>();
        UserRoomDTO userRoomDTO = new UserRoomDTO();
        userRoomDTO.setRoomName("Space station");
        userRoomDTO.setRoomId(UUID.randomUUID());
        userRoomDTO.setUsers(createUserDTOList());
        resultList.add(userRoomDTO);

        List<Room> roomList = new ArrayList<>();
        Room room = new Room();
        room.setId(UUID.randomUUID());
        room.setRole(RoleEnum.ANY);
        room.setName("Space");
        roomList.add(room);
        when(roomRepository.findAll()).thenReturn(roomList);
        when(roomService.getUsersInRooms()).thenReturn(resultList);
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(new User()));
        when(roomRepository.findById(any(UUID.class))).thenReturn(Optional.of(new Room()));
        when(userRepository.findByRoomId(any(UUID.class))).thenReturn(new ArrayList<>());
        List<UserRoomDTO> userRoomDTOList = roomService.getUsersInRooms();
        assertNotNull(userRoomDTOList);
    }

    @Test
    public void goToRoomTest() {
        when(roomRepository.findById(any(UUID.class))).thenReturn(Optional.of(new Room()));
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(new User()));


        UserRoomDTO userRoomDTO = new UserRoomDTO();
        List<UserDTO> userDTOList = new ArrayList<>();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(UUID.randomUUID());
        userDTO.setUsername("angelguardian");
        userDTO.setActive(true);
        userDTO.setRole(RoleEnum.ASTRONAUT);
        userDTOList.add(userDTO);

        userRoomDTO.setUsers(userDTOList);
        userRoomDTO.setRoomId(UUID.randomUUID());
        userRoomDTO.setRoomName("Game Room");

        when(roomService.goToRoom(UUID.randomUUID(), UUID.randomUUID())).thenReturn(userRoomDTO);
        when(userRepository.save(any(User.class))).thenReturn(new User());
        assertNotNull(roomService.goToRoom(UUID.randomUUID(), UUID.randomUUID()));
    }


    private List<UserDTO> createUserDTOList() {
        List<UserDTO> userDTOList = new ArrayList<>();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(UUID.randomUUID());
        userDTO.setUsername("angelguardian");
        userDTO.setActive(true);
        userDTO.setRole(RoleEnum.ASTRONAUT);

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setId(UUID.randomUUID());
        userDTO1.setUsername("pacofontanero");
        userDTO1.setActive(true);
        userDTO1.setRole(RoleEnum.CLEANER);

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setId(UUID.randomUUID());
        userDTO2.setUsername("davidanalista");
        userDTO2.setActive(true);
        userDTO2.setRole(RoleEnum.ANALYST);

        userDTOList.add(userDTO);
        userDTOList.add(userDTO1);
        userDTOList.add(userDTO2);
        return userDTOList;
    }
}

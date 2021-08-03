package toni.aguilera.spaceStation.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import toni.aguilera.spaceStation.model.Room;
import toni.aguilera.spaceStation.model.User;
import toni.aguilera.spaceStation.repository.RoomRepository;
import toni.aguilera.spaceStation.repository.UserRepository;
import toni.aguilera.spaceStation.util.RoleEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class SpaceStationServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    RoomRepository roomRepository;

    @InjectMocks
    SpaceStationServiceImpl spaceStationService;

    @Test
    public void launchTest() {
        when(userRepository.findAll()).thenReturn(createUserList());
        Room room = new Room();
        room.setId(UUID.randomUUID());
        room.setRole(RoleEnum.ANY);
        room.setName("Infinite Space");
        when(roomRepository.findByName(any(String.class))).thenReturn(room);
        String response = spaceStationService.launch();
        assertEquals(response, "Launch in 3..2..1.. Rocket Launched!!!");
    }

    private List<User> createUserList() {
        User user = new User();
        user.setRoom(new Room());
        user.setActive(true);
        user.setUsername("user1");
        user.setRole(RoleEnum.ANY);
        user.setName("user");
        user.setSurname("1");
        User user1 = new User();
        user1.setRoom(new Room());
        user1.setActive(true);
        user1.setUsername("user2");
        user1.setRole(RoleEnum.ANALYST);
        user1.setName("user");
        user1.setSurname("2");
        User user2 = new User();
        user2.setRoom(new Room());
        user2.setActive(true);
        user2.setUsername("user3");
        user2.setRole(RoleEnum.ASTRONAUT);
        user2.setName("user");
        user2.setSurname("3");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);
        userList.add(user2);
        return userList;
    }
}

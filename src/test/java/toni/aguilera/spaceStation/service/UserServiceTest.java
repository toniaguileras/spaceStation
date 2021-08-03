package toni.aguilera.spaceStation.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import toni.aguilera.spaceStation.model.Room;
import toni.aguilera.spaceStation.model.User;
import toni.aguilera.spaceStation.model.dto.UserDTO;
import toni.aguilera.spaceStation.repository.UserRepository;
import toni.aguilera.spaceStation.util.RoleEnum;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void getUsersTest() {
        List<UserDTO> userDTOList = createUserDTOList();
        when(userRepository.findAll()).thenReturn(createUserList());
        when(userService.getUsers()).thenReturn(userDTOList);
        Assert.assertEquals(userDTOList.get(0).getUsername(), "user1");
    }

    @Test
    public void getUsersByRoleTest() {
        List<User> userList = new ArrayList<>();
        userList.add(createAstronaut());
        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(createAstronautDTO());
        when(userRepository.findByRole(any(RoleEnum.class))).thenReturn(userList);
        List<UserDTO> resultList = userService.getUsersByRole(RoleEnum.ASTRONAUT);
        Assert.assertEquals(resultList.get(0).getRole(), RoleEnum.ASTRONAUT);

    }

    private UserDTO createAstronautDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setActive(true);
        userDTO.setUsername("user2");
        userDTO.setRole(RoleEnum.ASTRONAUT);
        return userDTO;
    }

    private User createAstronaut() {
        User user = new User();
        user.setActive(true);
        user.setUsername("user2");
        user.setRole(RoleEnum.ASTRONAUT);
        return user;
    }

    private List<UserDTO> createUserDTOList() {
        UserDTO user = new UserDTO();
        user.setActive(true);
        user.setUsername("user1");
        user.setRole(RoleEnum.ANY);
        UserDTO user1 = new UserDTO();
        user1.setActive(true);
        user1.setUsername("user2");
        user1.setRole(RoleEnum.ANALYST);
        UserDTO user2 = new UserDTO();
        user2.setActive(true);
        user2.setUsername("user3");
        user2.setRole(RoleEnum.ASTRONAUT);
        List<UserDTO> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);
        userList.add(user2);
        return userList;
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

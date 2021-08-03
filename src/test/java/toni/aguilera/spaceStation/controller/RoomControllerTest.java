package toni.aguilera.spaceStation.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import toni.aguilera.spaceStation.model.Room;
import toni.aguilera.spaceStation.model.dto.UserDTO;
import toni.aguilera.spaceStation.model.dto.UserRoomDTO;
import toni.aguilera.spaceStation.service.RoomService;
import toni.aguilera.spaceStation.util.RoleEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RoomController.class)
public class RoomControllerTest {
    @MockBean
    RoomService roomService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUsersInRoomsTest() throws Exception {
        List<UserRoomDTO> resultList = new ArrayList<>();
        UserRoomDTO userRoomDTO = new UserRoomDTO();
        userRoomDTO.setRoomName("Space station");
        userRoomDTO.setRoomId(UUID.randomUUID());
        userRoomDTO.setUsers(createUserDTOList());
        resultList.add(userRoomDTO);
        when(roomService.getUsersInRooms()).thenReturn(resultList);

        mockMvc.perform(get("http://localhost:8080/api/room").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$[0].roomName").value("Space station"));

    }

    @Test
    public void goToRoomTest() throws Exception {
        UUID roomId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        UserRoomDTO userRoomDTO = new UserRoomDTO();
        userRoomDTO.setRoomName("Space station");
        userRoomDTO.setRoomId(UUID.randomUUID());
        userRoomDTO.setUsers(createUserDTOList());

        when(roomService.goToRoom(any(UUID.class), any(UUID.class))).thenReturn(userRoomDTO);
        mockMvc.perform(post("http://localhost:8080/api/room").param("roomId", String.valueOf(roomId)).param("userId", String.valueOf(userId)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

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

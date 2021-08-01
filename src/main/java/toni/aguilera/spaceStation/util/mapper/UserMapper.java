package toni.aguilera.spaceStation.util.mapper;

import toni.aguilera.spaceStation.model.User;
import toni.aguilera.spaceStation.model.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static List<UserDTO> map(List<User> userList) {
        List<UserDTO> resultList = new ArrayList<>();
        userList.forEach(p -> resultList.add(map(p)));
        return resultList;
    }

    public static UserDTO map(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setActive(user.getActive());
        userDTO.setLogIn(user.getLogIn());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}

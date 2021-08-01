package toni.aguilera.spaceStation.service;

import org.springframework.stereotype.Service;
import toni.aguilera.spaceStation.model.dto.UserDTO;
import toni.aguilera.spaceStation.util.RoleEnum;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();

    List<UserDTO> getUsersByRole(RoleEnum roleEnum);
}

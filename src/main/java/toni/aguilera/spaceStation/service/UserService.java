package toni.aguilera.spaceStation.service;

import org.springframework.stereotype.Service;
import toni.aguilera.spaceStation.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();
}

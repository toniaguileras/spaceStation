package toni.aguilera.spaceStation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toni.aguilera.spaceStation.model.dto.UserDTO;
import toni.aguilera.spaceStation.repository.UserRepository;
import toni.aguilera.spaceStation.util.mapper.UserMapper;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getUsers() {
        return UserMapper.map(userRepository.findAll());
    }
}

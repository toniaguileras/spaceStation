package toni.aguilera.spaceStation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toni.aguilera.spaceStation.model.User;
import toni.aguilera.spaceStation.repository.RoomRepository;
import toni.aguilera.spaceStation.repository.UserRepository;
import toni.aguilera.spaceStation.util.RoleEnum;

import java.util.List;

@Service
public class SpaceStationServiceImpl implements SpaceStationService {

  private final UserRepository userRepository;
  private final RoomRepository roomRepository;

  @Autowired
  public SpaceStationServiceImpl(UserRepository userRepository, RoomRepository roomRepository) {
    this.userRepository = userRepository;
    this.roomRepository = roomRepository;
  }



  /**
   * Se busca agrupar a las personas en la habitacion de control independientemente del rol, excepto
   * los astronautas que tendrán que estar en la nave para el lanzamiento
   *
   * @return cuenta atrás para el lanzamiento
   */
  @Override
  public String launch() {
    List<User> userList = userRepository.findAll();
    userList.forEach(user -> sendUserToCorrectRoom(user));
    return "Launch in 3..2..1.. Go!!!";
  }

  private void sendUserToCorrectRoom(User user) {
    if(user.getRole().equals(RoleEnum.ASTRONAUT)){
      sendToSpaceShip(user);
    }
    else{
      sendToControlRoom(user);
    }
  }

  private void sendToControlRoom(User user) {

  }

  private void sendToSpaceShip(User user) {
  }
}

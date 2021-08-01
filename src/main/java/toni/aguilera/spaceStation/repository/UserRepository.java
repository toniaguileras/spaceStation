package toni.aguilera.spaceStation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import toni.aguilera.spaceStation.model.User;
import toni.aguilera.spaceStation.util.RoleEnum;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findByRole(RoleEnum roleEnum);

    List<User> findByRoomId(UUID roomId);
}

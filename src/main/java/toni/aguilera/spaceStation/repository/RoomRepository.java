package toni.aguilera.spaceStation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toni.aguilera.spaceStation.model.Room;

import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {

}

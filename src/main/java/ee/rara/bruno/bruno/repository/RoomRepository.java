package ee.rara.bruno.bruno.repository;

import ee.rara.bruno.bruno.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}

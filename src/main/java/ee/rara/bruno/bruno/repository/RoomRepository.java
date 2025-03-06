package ee.rara.bruno.bruno.repository;

import ee.rara.bruno.bruno.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE r.id NOT IN " +
            "(SELECT b.room.id FROM Booking b WHERE " +
            "((b.startTime <= :endDateTime) AND (b.endTime >= :startDateTime))) " +
            "AND (:minCapacity IS NULL OR r.capacity >= :minCapacity) " +
            "AND (:floor IS NULL OR r.floor = :floor) " +
            "AND (:keyword IS NULL OR LOWER(r.keywords) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Room> findAvailableRoomsWithFilters(
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("minCapacity") Integer minCapacity,
            @Param("floor") Integer floor,
            @Param("keyword") String keyword);
}

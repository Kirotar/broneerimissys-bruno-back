package ee.rara.bruno.bruno.repository;

import ee.rara.bruno.bruno.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query("SELECT b FROM Booking b WHERE b.user = :userId")
    List<Booking> findAllByUserId(int userId);

    @Query("SELECT b FROM Booking b WHERE b.room = :roomId " +
    "AND b.startTime = :startTime " +
    "AND b.endTime = :endTime")
    List<Booking> findIfAvailableByRoomId(
            @Param("roomId") int roomId,
            @Param("startDateTime") Instant startTime,
            @Param("endDateTime") Instant endTime);


}

package ee.rara.bruno.bruno.repository;

import ee.rara.bruno.bruno.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query("SELECT b FROM Booking b WHERE b.user = :userId")
    List<Booking> findAllByUserId(int userId);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Booking b WHERE b.room = :roomId " +
            "AND ((:startTime BETWEEN b.startTime AND b.endTime) " +
            "OR (:endTime BETWEEN b.startTime AND b.endTime) " +
            "OR (b.startTime BETWEEN :startTime AND :endTime))")
    boolean existsBookingForRoom(
            @Param("roomId") int roomId,
            @Param("startTime") Instant startTime,
            @Param("endTime") Instant endTime);


}

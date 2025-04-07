package ee.rara.bruno.bruno.repository;

import ee.rara.bruno.bruno.model.Booking;
import ee.rara.bruno.bruno.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query("SELECT b FROM Booking b WHERE b.user = :user")
    List<Booking> findAllByUser(@Param("user") User user);


    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Booking b WHERE b.room.id = :roomId " +
            "AND NOT (:endTime <= b.startTime OR :startTime >= b.endTime)")

    boolean existsBookingForRoom(
            @Param("roomId") int roomId,
            @Param("startTime") Instant startTime,
            @Param("endTime") Instant endTime);

    @Query("SELECT b FROM Booking b WHERE b.isPaid = false")
    List<Booking> findUnpaidBookings();

    @Query("SELECT b FROM Booking b WHERE b.user = :user AND b.endTime > :now")
    List<Booking> findFutureBookingsByUser(@Param("user") User user, @Param("now") Instant now);
}

package ee.rara.bruno.bruno.repository;

import ee.rara.bruno.bruno.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}

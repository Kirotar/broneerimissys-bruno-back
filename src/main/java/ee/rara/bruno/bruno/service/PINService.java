package ee.rara.bruno.bruno.service;

import ee.rara.bruno.bruno.model.Booking;
import ee.rara.bruno.bruno.repository.BookingRepository;
import ee.rara.bruno.bruno.util.RandomGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PINService {

    private final BookingRepository bookingRepository;

    public PINService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void savePIN(List<Booking> bookings) {
        int PIN = RandomGenerator.generateRandomPIN();
        for (Booking booking  : bookings) {
            booking.setPIN(PIN);
            bookingRepository.save(booking);
        }
    }

    public ResponseEntity<Integer> fetchPIN(String transactionRef) {
        List<Integer> pin = bookingRepository.findBookingPIN(transactionRef);
        if (pin == null || pin.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pin.getFirst());
    }
}

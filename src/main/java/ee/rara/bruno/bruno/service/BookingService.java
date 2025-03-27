package ee.rara.bruno.bruno.service;

import ee.rara.bruno.bruno.dto.BookingRequest;
import ee.rara.bruno.bruno.model.Booking;
import ee.rara.bruno.bruno.model.Room;
import ee.rara.bruno.bruno.model.User;
import ee.rara.bruno.bruno.repository.BookingRepository;
import ee.rara.bruno.bruno.repository.RoomRepository;
import ee.rara.bruno.bruno.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class BookingService {

    private final int maxNrOfBookingsPerUser = 10;

    private PINService pinService;

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final PaymentService paymentService;

    public BookingService(RoomRepository roomRepository, BookingRepository bookingRepository, UserRepository userRepository, PaymentService paymentService, PINService pinService) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.paymentService = paymentService;
        this.pinService = pinService;
    }

    public void temporaryBooking(BookingRequest booking) {
        addBooking(booking);
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> {
            if (!booking.getIsPaid()) {
                deleteBookingById(booking.getBookingId());
            }
            executor.shutdown();
        }, 10, TimeUnit.MINUTES);
    }

    public void addBooking(BookingRequest request) {

        if (request.getIsAvailable() && isNrOfBookingsLessThanMax(request.getBookingId())) {
            Room room = roomRepository.findById(request.getRoomId())
                    .orElseThrow(() -> new EntityNotFoundException("Room not found"));

            User user = userRepository.findById(request.getRoomId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));

            Booking booking = new Booking();
            booking.setRoom(room);
            booking.setUser(user);
            booking.setStartTime(request.getStartTime());
            booking.setEndTime(request.getEndTime());
            bookingRepository.save(booking);

            pinService.createBookingPinByBookingId(request.getBookingId());
        }
    }

    public void addRepeatedBooking(BookingRequest bookings) {
        int numberOfWeeksToRepeat = bookings.getNumberOfWeeksToRepeat();
        addBooking(bookings);
        for(int i = 0; i < numberOfWeeksToRepeat; i++) {
            bookings.setStartTime(bookings.getStartTime().plus(7, ChronoUnit.DAYS));
            bookings.setEndTime(bookings.getStartTime().plus(7, ChronoUnit.DAYS));
            addBooking(bookings);
        }
    }

    public void deleteBookingById(int id) {
        bookingRepository.deleteById(id);
        //save to deleted records? Mark deleted but don't remove from table?
    }

    public List<Booking> getUserBookingsByUserId(int userId) {
        return bookingRepository.findAllByUserId(userId);
    }

    //Is this needed? Will probably never be used. Maybe by date or room id instead.
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public boolean isRoomAvailable(BookingRequest query) {
        return !bookingRepository.existsBookingForRoom(query.getRoomId(), query.getStartTime(), query.getEndTime());
    }



    //kuidagi peaks siin kasutaja saama juba enne teada, et tal on max täis kui tal nt on 6 broni
    // ja tahab veel 6 teha, et ei broneeriks esimest nelja ja teisi mitte.
    public boolean isNrOfBookingsLessThanMax(int id) {
        List<Booking> userBookings = bookingRepository.findAllByUserId(id);
        return userBookings.size() <= maxNrOfBookingsPerUser;
    }
}

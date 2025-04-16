package ee.rara.bruno.bruno.service;

import ee.rara.bruno.bruno.dto.BookingRequest;
import ee.rara.bruno.bruno.model.Booking;
import ee.rara.bruno.bruno.model.Room;
import ee.rara.bruno.bruno.model.User;
import ee.rara.bruno.bruno.repository.BookingRepository;
import ee.rara.bruno.bruno.repository.RoomRepository;
import ee.rara.bruno.bruno.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
     private final UserService userService;

    public BookingService(RoomRepository roomRepository, BookingRepository bookingRepository,
                            UserService userService, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
         this.userService = userService;
         this.userRepository = userRepository;
    }

    public void temporaryBooking(String token, List<BookingRequest> booking) {
        for (BookingRequest request  : booking) {
            addBooking(token, request);
        }

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> {
            List<Booking> bookings = bookingRepository.findUnpaidBookings();
            for (Booking tempBooking : bookings) {
                if (!tempBooking.getIsPaid()) {
                    deleteBookingById(tempBooking.getId());
                }
            }
            executor.shutdown();
        }, 10, TimeUnit.MINUTES);
    }

    public void addBooking(String token, BookingRequest request) {
        User user = userService.getUserFromToken(token);

        if (isNrOfBookingsLessThanMax(user)){
            Room room = roomRepository.findById(request.getRoomId())
                    .orElseThrow(() -> new EntityNotFoundException("Room not found"));

            Booking booking = new Booking();
            booking.setRoom(room);
            booking.setUser(user);
            booking.setStartTime(request.getStartTime());
            booking.setEndTime(request.getEndTime());
            bookingRepository.save(booking);

            bookingPin(request.getBookingId());
        } else {
            System.out.println("Booking request failed due to availability or limit.");
        }
    }

    public void addRepeatedBooking(String token, BookingRequest bookings) {
        int numberOfWeeksToRepeat = bookings.getNumberOfWeeksToRepeat();
        addBooking(token, bookings);
        for (int i = 0; i < numberOfWeeksToRepeat; i++) {
            bookings.setStartTime(bookings.getStartTime().plus(7, ChronoUnit.DAYS));
            bookings.setEndTime(bookings.getStartTime().plus(7, ChronoUnit.DAYS));
            addBooking(token, bookings);
        }
    }

    public void deleteBookingById(int id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
        }
        //save to deleted records? Mark deleted but don't remove from table?
    }


    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public boolean isRoomAvailable(BookingRequest query) {
        return !bookingRepository.existsBookingForRoom(query.getRoomId(), query.getStartTime(), query.getEndTime());
    }

    public String bookingPin(int id) {
        //send to doorsystem with roomid, date-time of booking
        //send an email/sms

        return "Booking Pin: 123";
    }

    public boolean isNrOfBookingsLessThanMax(User user) {
        Instant now = Instant.now();
        List<Booking> futureBookings = bookingRepository.findFutureBookingsByUser(user, now);
        return futureBookings.size() < maxNrOfBookingsPerUser;
    }
}

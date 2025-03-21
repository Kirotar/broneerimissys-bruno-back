package ee.rara.bruno.bruno.controller;

import ee.rara.bruno.bruno.dto.BookingRequest;
import ee.rara.bruno.bruno.dto.RoomSearch;
import ee.rara.bruno.bruno.model.Booking;
import ee.rara.bruno.bruno.service.BookingService;
import ee.rara.bruno.bruno.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final PaymentService paymentService;

    public BookingController(BookingService bookingService, PaymentService paymentService) {
        this.bookingService = bookingService;
        this.paymentService = paymentService;
    }

    @PostMapping("/temp-booking")
    @Operation(summary = "Teeb ajutiselt broneeringu.")
    public void addTemporaryBooking(@RequestBody BookingRequest booking) {
        bookingService.temporaryBooking(booking);
    }

    @PostMapping("/add-booking")
    @Operation(summary = "Lisab broneeringu andmebaasi.")
    public void addBooking(@RequestBody BookingRequest booking) {
        bookingService.addBooking(booking);
    }

    @PostMapping("/add-repeat-booking")
    @Operation(summary = "Lisab andmebaasi intervalli tagant korduva broneeringu.")
    public void addRepeatedBooking(@RequestBody BookingRequest booking) {
        bookingService.addRepeatedBooking(booking);
    }

    @DeleteMapping("/delete-booking")
    @Operation(summary = "Kustutab broneeringu.")
    public void deleteBooking(@RequestBody BookingRequest booking) {
        bookingService.deleteBooking(booking);
    }

    @GetMapping("/get-bookings/{id}")
    @Operation(summary = "Kasutaja broneeringud ID järgi.")
    public List<Booking> getUserBookingsByUserId(@PathVariable ("id") int id) {
        return bookingService.getUserBookingsByUserId(id);
    }
    @GetMapping("/get-room-availability")
    @Operation(summary = "Kontrollib ruumi saadavust.")
    public boolean getRoomAvailability(@ModelAttribute BookingRequest query) {
        return bookingService.isRoomAvailable(query);
    }

    //admin
    @GetMapping("/all-bookings")
    @Operation(summary = "Kõik broneeringud.")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/pin/{id}")
    public String bookingPin(@PathVariable("id") int id) {
        return bookingService.bookingPin(id);
    }

    //make an enquiry room or eduevent

}

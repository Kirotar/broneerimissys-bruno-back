package ee.rara.bruno.bruno.controller;

import ee.rara.bruno.bruno.dto.BookingRequest;
import ee.rara.bruno.bruno.model.Booking;
import ee.rara.bruno.bruno.repository.BookingRepository;
import ee.rara.bruno.bruno.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/temp-booking")
    public void addTemporaryBooking(@RequestBody BookingRequest booking) {
        bookingService.temporaryBooking(booking);
    }

    @PostMapping("/add-booking")
    public void addBooking(@RequestBody BookingRequest booking) {
        bookingService.addBooking(booking);
    }

    @PostMapping("/add-repeat-booking")
    public void addRepeatedBooking(@RequestBody BookingRequest booking) {
        bookingService.addRepeatedBooking(booking);
    }

    @DeleteMapping("/delete-booking}")
    public void deleteBooking(@RequestBody BookingRequest booking) {
        bookingService.deleteBooking(booking);
    }

    @GetMapping("/get-bookings/{id}")
    public List<Booking> getUserBookingsById(@PathVariable ("id") int id) {
        return bookingService.getUserBookingsById(id);
    }

    //admin
    @GetMapping("/all-bookings")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/payment")
    public String bookingPaymentStatus(boolean status) {
        return bookingService.bookingPaymentStatus(status);
    }

    @GetMapping("/pin/{id}")
    public String bookingPin(@PathVariable("id") int id) {
        return bookingService.bookingPin(id);
    }

    //make an enquiry room or eduevent

}

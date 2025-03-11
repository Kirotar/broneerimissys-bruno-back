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
    private final BookingRepository bookingRepository;

    public BookingController(BookingService bookingService, BookingRepository bookingRepository) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
    }

    @PostMapping("/temp-booking")
    public void addTemporaryBooking(@RequestBody BookingRequest booking) {
        bookingService.temporaryBooking(booking);
    }

    @PostMapping("/add-booking")
    public List<Booking> addBooking(@RequestBody BookingRequest booking) {
        return bookingService.addBooking(booking);
    }

    @DeleteMapping("/delete-booking/{id}")
    public void deleteBookingById(@PathVariable ("id") int id) {
        bookingService.deleteBooking(id);
    }

    @GetMapping("/get-bookings/{id}")
    public List<Booking> getUserBookingsById(@PathVariable ("id") int id) {
        return bookingService.getUserBookingsById(id);
    }

    //temporarily make booking

    //send pin

    //payment

    //see user bookings

    //admin see all bookings

    //make an enquiry room or eduevent

}

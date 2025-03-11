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
    public List<Booking> addBooking(@RequestBody BookingRequest booking) {
        return bookingService.addBooking(booking);
    }

    @PostMapping("/add-repeat-booking")
    public List<Booking> addRepeatedBooking(@RequestBody BookingRequest booking) {
        return bookingService.addRepeatedBooking(booking);
    }


    @DeleteMapping("/delete-booking/{id}")
    public void deleteBookingById(@PathVariable ("id") int id) {
        //If less than 7d to booking
        //Return money
        //Else
        //No refund
        bookingService.deleteBooking(id);
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
    public String bookingPaymentStatus(String status) {
        return bookingService.bookingPaymentStatus(status);
    }

    @GetMapping("/pin/{id}")
    public String bookingPin(@PathVariable("id") int id) {
        return bookingService.bookingPin(id);
    }

    //make an enquiry room or eduevent

}

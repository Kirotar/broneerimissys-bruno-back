package ee.rara.bruno.bruno.service;

import ee.rara.bruno.bruno.dto.BookingRequest;
import ee.rara.bruno.bruno.model.Booking;
import ee.rara.bruno.bruno.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository roomBookingRepository;
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository roomBookingRepository, BookingRepository bookingRepository) {
        this.roomBookingRepository = roomBookingRepository;
        this.bookingRepository = bookingRepository;
    }

    public void temporaryBooking(BookingRequest booking) {
        //Reserve for 10min
        //If payment
        //addBooking
        //Else
        //Delete
    }

    public List<Booking> addBooking(BookingRequest booking) {
        //isavailable
        //is less than 10
        //is less than 3h
    }

    public List<Booking> addRepeatedBooking(List<BookingRequest> bookings) {
        //if available
        //loop (nr of weeks), +7 /up to 10x/is it possible to book 10weeks ahead?
        // Save all

    }

    public void deleteBooking(int id) {
        bookingRepository.deleteById(id);
        //Keep record somewhere?
    }

    public List<Booking> getUserBookingsById(int userId) {
        return bookingRepository.findAllByUserId(userId);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public String bookingPaymentStatus(String status) {
        return "Booking payment status: Success";
    }

    public String bookingPin(int id){
        //send to doorsystem with roomid, date-time of booking
        //send an email/sms
        return "Booking Pin: 123";
    }

}

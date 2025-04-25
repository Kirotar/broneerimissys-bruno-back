package ee.rara.bruno.bruno.service;

import ee.rara.bruno.bruno.dto.BookingRequest;
import ee.rara.bruno.bruno.model.Booking;
import ee.rara.bruno.bruno.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PaymentService {

    private final BookingRepository bookingRepository;
    private final PINService pinService;

    public PaymentService(BookingRepository bookingRepository, PINService pinService) {
        this.bookingRepository = bookingRepository;
        this.pinService = pinService;
    }

    public boolean bookingPaymentStatus(String transactionReference) {
        boolean paymentStatus = Math.random() < 0.75;
        List<Booking> bookings = bookingRepository.findByTransactionReference(transactionReference);

        for (Booking booking  : bookings) {
            booking.setIsPaid(paymentStatus);
            bookingRepository.save(booking);
        }
        if (paymentStatus) {
            pinService.savePIN(bookings);
        }

        if(!paymentStatus) {
            bookingRepository.deleteAll(bookings);
        }
        return paymentStatus;
    }

    public void canBookingGetARefund(BookingRequest booking) {
        Instant now = Instant.now();
        Instant sevenDaysFromNow = now.plus(7, ChronoUnit.DAYS);
        if(booking.getStartTime().isAfter(sevenDaysFromNow)){
            System.out.println("Raha tagastatakse 3 tööpäeva jooksul");
        } else {
            System.out.println("Raha enam ei saa tagastada");
        }
    }
}

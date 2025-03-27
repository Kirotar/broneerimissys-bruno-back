package ee.rara.bruno.bruno.service;

import ee.rara.bruno.bruno.dto.BookingRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class PaymentService {

    public void bookingPaymentStatus(BookingRequest request) {
        boolean paymentStatus = Math.random() < 0.5;
        request.setIsPaid(paymentStatus);
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

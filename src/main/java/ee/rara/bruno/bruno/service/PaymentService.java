package ee.rara.bruno.bruno.service;

import ee.rara.bruno.bruno.dto.BookingRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public void bookingPaymentStatus(BookingRequest request) {
        boolean paymentStatus = Math.random() < 0.5;
        request.setIsPaid(paymentStatus);
    }
}

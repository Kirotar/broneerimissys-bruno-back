package ee.rara.bruno.bruno.controller;

import ee.rara.bruno.bruno.dto.BookingRequest;
import ee.rara.bruno.bruno.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/status")
    public boolean bookingPaymentStatus(@RequestBody String transactionReference ) {
        return paymentService.bookingPaymentStatus(transactionReference);
    }
}

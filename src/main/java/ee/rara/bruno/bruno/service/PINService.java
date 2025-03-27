package ee.rara.bruno.bruno.service;

import org.springframework.stereotype.Service;

@Service
public class PINService {

    public String createBookingPinByBookingId(int id) {
        //send to doorsystem with roomid, date-time of booking
        //send an email/sms

        return "Booking Pin: 123";
    }
}

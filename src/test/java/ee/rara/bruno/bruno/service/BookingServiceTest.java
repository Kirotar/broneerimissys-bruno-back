package ee.rara.bruno.bruno.service;

import ee.rara.bruno.bruno.model.Booking;
import ee.rara.bruno.bruno.repository.BookingRepository;
import ee.rara.bruno.bruno.repository.RoomRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    public static final String STATUS1 = new String("see on vale ja peab olemafail");
    public static final Booking BOOKING1 = new Booking();
    {
        BOOKING1.setId(123);
    }

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    BookingService bookingService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void deleteBooking() {
    }

    @Test
    void getUserBookingsById() {
        when(bookingRepository.findAllByUserId(anyInt())).thenReturn(List.of(BOOKING1, BOOKING1));
        when(bookingRepository.findAllByUserId(eq(123))).thenReturn(List.of(BOOKING1));

        List<Booking> userBookingsByUserId = bookingService.getUserBookingsByUserId(123);
        assertEquals( 1, userBookingsByUserId.size());
        Booking booking = userBookingsByUserId.get(0);
        assertEquals(BOOKING1, booking);

        userBookingsByUserId = bookingService.getUserBookingsByUserId(345);
        assertEquals( 2, userBookingsByUserId.size());
    }


    @Test
    void getAllBookings() {
    }

    @ParameterizedTest
    @ValueSource(strings = {"see on vale ja peab olemafail", "see on vale ja peab olemafail"})
    void bookingPaymentStatus(String status) {
       /* assertEquals(status, bookingService.bookingPaymentStatus(status));

        assertEquals("Booking payment status: Success", bookingService.bookingPaymentStatus("Booking payment status: Success"));
        assertNotEquals("Booking payment status: Success", bookingService.bookingPaymentStatus("see on vale ja peab olemafail"));*/
    }

    @Test
    void bookingPin() {
    }
}
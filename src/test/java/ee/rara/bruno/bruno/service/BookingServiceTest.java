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
import static org.mockito.Mockito.*;

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
    void temporaryBooking_whenBookingIsSent_shouldAddBooking() {}

    @Test
    void temporaryBooking_whenBookingIsInvalid_shouldThrowException() {}

    @Test
    void temporaryBooking_whenBookingWasNotPaid_shouldDeleteBooking() {}

    @Test
    void addBooking_whenBookingIsValid_shouldAddBooking() {}

    @Test
    void addBooking_whenBookingIsInvalid_shouldThrowException() {}

    @Test
    void addBooking_whenTimeIsNotAvailable_shouldThrowException() {}

    @Test
    void addBooking_whenNumberOfBookingsIsOverMaximum_shouldThrowException() {}

    @Test
    void addRepeatedBookings_whenBookingisValid_shouldAddRepeatedBooking(){
    }

    @Test
    void addRepeatedBookings_whenNumberOfRepetitions_shouldRepeatEverySevenDays(){
    }

    @Test
    void deleteBookingById_whenHasBookingId_thenDeleteBookingWithId() {
        when(bookingRepository.existsById(890)).thenReturn(true);
        bookingService.deleteBookingById(890);
        verify(bookingRepository, times(1)).existsById(890);
        verify(bookingRepository, times(1)).deleteById(890);
    }

    @Test
    void deleteBookingById_whenBookingDoesNotExist_thenDoNothing() {
        when(bookingRepository.existsById(890)).thenReturn(false);
        bookingService.deleteBookingById(890);
        verify(bookingRepository, never()).deleteById(anyInt());
    }

    @Test
    void getUserBookingsById_whenUserHasOneBooking_thenReturnOneBooking() {
        when(bookingRepository.findAllByUserId(eq(123))).thenReturn(List.of(BOOKING1));

        List<Booking> userBookingsByUserId = bookingService.getUserBookingsByUserId(123);
        assertEquals( 1, userBookingsByUserId.size());

        Booking booking = userBookingsByUserId.get(0);
        assertEquals(BOOKING1, booking);
    }

    @Test
    void getUserBookingsById_whenUserHasTwoBooking_thenReturnTwoBookings() {
        when(bookingRepository.findAllByUserId(anyInt())).thenReturn(List.of(BOOKING1, BOOKING1));

        List<Booking> userBookingsByUserId = bookingService.getUserBookingsByUserId(345);

        assertEquals( 2, userBookingsByUserId.size());
    }

    @Test
    void getUserBookingsById_whenUserHasNoBooking_thenReturnEmptyList() {
        when(bookingRepository.findAllByUserId(anyInt())).thenReturn(List.of());
        List <Booking> userBookings =  bookingService.getUserBookingsByUserId(345);
        assertTrue(userBookings.isEmpty(), "User bookings should be empty, but got " + userBookings);
    }

    @Test
    void getUserBookingsById_whenRepositoryThrowsException_ShouldThrowServiceError() {
        when(bookingRepository.findAllByUserId(anyInt())).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () -> bookingService.getUserBookingsByUserId(345));

        assertEquals("Database error", exception.getMessage());
    }

    @Test
    void getAllBookings_whenBookingsExist_thenGetAllBookings() {

    }

    @Test
    void getAllBookings_whenBookingsIsEmpty_thenReturnEmptyList() {

    }

    @Test
    void isRoomAvailable_whenRoomIsAvailable_thenReturnTrue() {}

    @Test
    void isRoomAvailable_whenRoomIsNotAvailable_thenReturnFalse() {}


    @Test
    void isNrOfBookingsLessThanMax_whenNrOfBookingsIsLessOrEqualThanMax_thenReturnTrue() {}

    @Test
    void isNrOfBookingsLessThanMax_whenNrOfBookingsIsMoreThanMax_thenReturnFalse() {}

    @Test
    void isNrOfBookingsLessThanMax_whenNoBookingsAreFound_thenReturnFalse() {}


    @ParameterizedTest
    @ValueSource(strings = {"see on vale ja peab olemafail", "see on vale ja peab olemafail"})
    void bookingPaymentStatus(String status) {
       /* assertEquals(status, bookingService.bookingPaymentStatus(status));

        assertEquals("Booking payment status: Success", bookingService.bookingPaymentStatus("Booking payment status: Success"));
        assertNotEquals("Booking payment status: Success", bookingService.bookingPaymentStatus("see on vale ja peab olemafail"));*/
    }


}
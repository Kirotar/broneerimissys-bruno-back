package ee.rara.bruno.bruno.service;

import ee.rara.bruno.bruno.dto.BookingDto;
import ee.rara.bruno.bruno.dto.UserDto;
import ee.rara.bruno.bruno.model.Booking;
import ee.rara.bruno.bruno.model.User;
import ee.rara.bruno.bruno.repository.BookingRepository;
import ee.rara.bruno.bruno.repository.UserRepository;
import ee.rara.bruno.bruno.util.JwtUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final JwtUtil jwtUtil;


    public UserService(UserRepository userRepository, JwtUtil jwtUtil, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.bookingRepository = bookingRepository;
    }

    public User getUserFromToken(String token) {
        String jwt = token.substring(7);  // Remove "Bearer " prefix

        String email = jwtUtil.extractEmail(jwt);

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    public UserDto getUserInfoByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }

    public List<BookingDto> getUserBookingsByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<Booking> bookings = bookingRepository.findAllByUserId(user.getId());

        return bookings.stream()
                .map(booking -> {
                    BookingDto dto = new BookingDto();
                    dto.setBookingId(booking.getId());
                    dto.setRoomName(booking.getRoom().getRoomName());
                    dto.setRoomId(booking.getRoom().getId());
                    dto.setRoomFloor(booking.getRoom().getFloor());
                    dto.setStartTime(booking.getStartTime());
                    dto.setEndTime(booking.getEndTime());
                    return dto;
                })
                .toList();

    }
}

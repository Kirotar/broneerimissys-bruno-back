package ee.rara.bruno.bruno.controller;

import ee.rara.bruno.bruno.dto.BookingDto;
import ee.rara.bruno.bruno.dto.UserDto;
import ee.rara.bruno.bruno.model.Booking;
import ee.rara.bruno.bruno.repository.UserRepository;
import ee.rara.bruno.bruno.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }


    @GetMapping("/info")
    @Operation(summary = "Praeguse kasutaja info.")
    public UserDto getUserInfo(Authentication authentication) {
        String email = authentication.getName();
        return userService.getUserInfoByEmail(email);
    }

    @GetMapping("/my-bookings")
    @Operation(summary = "Praeguse kasutaja broneeringud.")
    public List<BookingDto> getUserBookings(Authentication authentication) {
        String email = authentication.getName();
        return userService.getUserBookingsByEmail(email);
    }

}

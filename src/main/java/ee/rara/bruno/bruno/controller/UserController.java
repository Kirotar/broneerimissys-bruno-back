package ee.rara.bruno.bruno.controller;

import ee.rara.bruno.bruno.dto.UserDto;
import ee.rara.bruno.bruno.repository.UserRepository;
import ee.rara.bruno.bruno.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/info")
    public UserDto getUserInfo(@RequestHeader("Authorization") String token) {
        return userService.getUserInfo(token);
    }


}

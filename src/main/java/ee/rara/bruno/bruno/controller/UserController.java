package ee.rara.bruno.bruno.controller;

import ee.rara.bruno.bruno.dto.RegisterRequest;
import ee.rara.bruno.bruno.model.User;
import ee.rara.bruno.bruno.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //user
    //register
    //login
    //logout
    //change user
    //roles for admin: see users, add roles somehow?

}

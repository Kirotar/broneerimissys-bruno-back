package ee.rara.bruno.bruno.service;

import ee.rara.bruno.bruno.dto.UserDto;
import ee.rara.bruno.bruno.model.User;
import ee.rara.bruno.bruno.repository.UserRepository;
import ee.rara.bruno.bruno.util.JwtUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService {

private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
    this.userRepository = userRepository;
    this.jwtUtil = jwtUtil;
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
}

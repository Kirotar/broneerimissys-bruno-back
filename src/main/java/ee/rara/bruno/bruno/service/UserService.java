package ee.rara.bruno.bruno.service;

import ee.rara.bruno.bruno.model.User;
import ee.rara.bruno.bruno.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

private final UserRepository userRepository;

public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
}

public void addUser(User user) {
    userRepository.save(user);
}
}

package ee.rara.bruno.bruno.service;

import ee.rara.bruno.bruno.dto.LoginRequest;
import ee.rara.bruno.bruno.dto.RegisterRequest;
import ee.rara.bruno.bruno.model.Role;
import ee.rara.bruno.bruno.model.User;
import ee.rara.bruno.bruno.repository.RoleRepository;
import ee.rara.bruno.bruno.repository.UserRepository;
import ee.rara.bruno.bruno.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.roleRepository = roleRepository;
    }

    public String login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
        return jwtUtil.generateToken(user.getUsername());
    }

    public void registerUser(RegisterRequest request) {
        User user = new User();
        Role role = roleRepository.findById(request.getRoleId()).orElse(null);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        user.setCreatedAt(Instant.now());

        userRepository.save(user);
    }
}


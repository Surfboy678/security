package com.brodacki.security.service;


import com.brodacki.security.model.User;
import com.brodacki.security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static String ROLE_USER = "ROLE_USER";

    private static String ROLE_ADMIN = "ROLE_ADMIN";

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void addNewUser(User user) {
        user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(ROLE_USER);
        userRepository.save(user);
    }
}

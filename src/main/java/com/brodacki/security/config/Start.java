package com.brodacki.security.config;

import com.brodacki.security.model.User;
import com.brodacki.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Start {

  private String ROLE_USER = "ROLE_USER";

  private PasswordEncoder passwordEncoder;

  private UserRepository userRepository;

  @Autowired
  public Start(PasswordEncoder passwordEncoder, UserRepository userRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;

    User user = new User();
    user.setUsername("Janusz");
    user.setPassword(passwordEncoder.encode("Janusz123"));
    user.setRole(ROLE_USER);
    user.setEnabled(true);
    userRepository.save(user);
  }
}

package com.brodacki.security.service;

import com.brodacki.security.model.User;
import com.brodacki.security.model.VerificationToken;
import com.brodacki.security.repository.UserRepository;
import com.brodacki.security.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class UserService {

  @Value("${mail.admin}")
  private String adminMail;

  private static String ROLE_USER = "ROLE_USER";

  private static String ROLE_ADMIN = "ROLE_ADMIN";

  private PasswordEncoder passwordEncoder;

  private UserRepository userRepository;

  private VerificationTokenRepository verificationTokenRepository;

  private MailSenderService mailSenderService;

  @Autowired
  public UserService(
      PasswordEncoder passwordEncoder,
      UserRepository userRepository,
      VerificationTokenRepository verificationTokenRepository,
      MailSenderService mailSenderService) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.verificationTokenRepository = verificationTokenRepository;
    this.mailSenderService = mailSenderService;
  }

  public void addNewUser(User user, HttpServletRequest request) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole(user.getRole());
    userRepository.save(user);

    String token = UUID.randomUUID().toString();

    VerificationToken verificationToken = new VerificationToken(user, token);
    verificationTokenRepository.save(verificationToken);
    String getRoleByVerificationToken =
        verificationTokenRepository.findByValue(token).getUser().getRole();

    String url =
        "http://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/verify-token?token="
            + token;

    try {
      if (getRoleByVerificationToken.equals(ROLE_USER)) {
        mailSenderService.sendMail(user.getUsername(), "Verification Token", url, false);
      } else if (getRoleByVerificationToken.equals(ROLE_ADMIN)) {
        mailSenderService.sendMail(adminMail, "Verification Token", url, false);
      }
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }

  public void verifyToken(String token) {
    User user = verificationTokenRepository.findByValue(token).getUser();
    user.setEnabled(true);
    userRepository.save(user);
    Integer idVerificationToken = verificationTokenRepository.findByValue(token).getId();
    verificationTokenRepository.deleteById(idVerificationToken);
  }
}

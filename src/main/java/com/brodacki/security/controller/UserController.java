package com.brodacki.security.controller;

import com.brodacki.security.model.User;
import com.brodacki.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/forAll")
  public String forAll() {
    return "forAll";
  }

  @GetMapping("/forUser")
  public String forUser(Principal principal) {
    return "hello user: " + principal.getName();
  }

  @GetMapping("/forAdmin")
  public String forAdmin(Principal principal) {
    return "Hello admin: " + principal.getName();
  }

  @PostMapping("/login")
  public ResponseEntity<User> loginUser(@RequestBody User user) {
    return ResponseEntity.ok(user);
  }

  @PostMapping("/register")
  public void registerUser(@RequestBody User user, HttpServletRequest request) {
    userService.addNewUser(user, request);
  }

  @RequestMapping("/verify-token")
  public String confirmationByEmail(@RequestParam String token) {
    userService.verifyToken(token);
    return "Gratulacje.Twój adres mailowy został potwierdzony";
  }
}

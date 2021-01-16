package com.brodacki.security.controller;

import com.brodacki.security.model.User;
import com.brodacki.security.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    private UserRepository userRepository;

    @GetMapping("/forAll")
    public String forAll(){
        return "forAll";
    }
    @GetMapping("/forUser")
    public String forUser(Principal principal){
        return "hello user: " + principal.getName();
    }
    @GetMapping("/forAdmin")
    public String forAdmin(Principal principal){
        return "Hello admin: " + principal.getName();
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user){
        return ResponseEntity.ok(user);
    }


}

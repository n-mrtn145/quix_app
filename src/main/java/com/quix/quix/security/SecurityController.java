package com.quix.quix.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Controller
public class SecurityController {

    @Autowired
    SecurityService securityService;

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestBody UUID uuid) {
        User user = securityService.getUserById(uuid);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {

        return ResponseEntity.ok(user);
        }
    }

    @GetMapping("/user/exists")
    public ResponseEntity<Boolean> existsUser(@RequestBody UUID uuid) {
        return ResponseEntity.ok(securityService.userExists(uuid));
    }

    @PostMapping("/user/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto user) {
        return ResponseEntity.ok(securityService.registerUser(user));
    }
}

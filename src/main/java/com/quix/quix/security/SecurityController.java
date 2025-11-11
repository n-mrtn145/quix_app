package com.quix.quix.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Controller
public class SecurityController {

    @Autowired
    SecurityService securityService;

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestBody UUID uuid) {
        return ResponseEntity.ok(securityService.getUserById(uuid));
    }

    @GetMapping("/user/exists")
    public ResponseEntity<Boolean> existsUser(@RequestBody UUID uuid) {
        return ResponseEntity.ok(securityService.userExists(uuid));
    }
}

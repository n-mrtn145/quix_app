package com.quix.quix.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SecurityController {

    @Autowired
    SecurityService securityService;

    @PostMapping("/securitiy/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        try {
            UserEntity userEntity = securityService.registerUser(userDto.getUsername(), userDto.getPassword());
            UserDto userDtoReturn = new UserDto(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword());
            return ResponseEntity.ok(userDtoReturn);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

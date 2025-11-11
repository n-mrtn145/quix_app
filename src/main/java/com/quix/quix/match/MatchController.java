package com.quix.quix.match;

import com.quix.quix.security.SecurityService;
import com.quix.quix.security.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Controller
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private SecurityService securityService;

    @PostMapping("/match/start")
    private ResponseEntity<MatchEntity> startMatch(@RequestBody List<UUID> userIds) {
        List<UserEntity> entities = new java.util.ArrayList<>(List.of());
        for(UUID userId : userIds) {
            if(!securityService.userExists(userId)) {
                throw new RuntimeException("User with Id: " + userId.toString() + " does not exist");
            }
            entities.add(securityService.getUserById(userId));
        }
        MatchEntity matchEntity = matchService.startMatch(entities);
        return ResponseEntity.ok(matchEntity);
    }
}

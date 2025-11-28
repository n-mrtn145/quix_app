package com.quix.quix.match;

import com.quix.quix.security.SecurityService;
import com.quix.quix.security.User;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private EntryService entryService;

    @Autowired
    private SecurityService securityService;

    @PostMapping("/api/match/active/tick")
    public ResponseEntity<EntryEntity> tick(@RequestBody TickDto tick) {
        Claims claims = (Claims) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String userId = claims.getSubject();
        Colors color = tick.color();
        return ResponseEntity.ok(null);
    }

    @PostMapping("/api/match/start")
    private ResponseEntity<MatchEntity> startMatch(@RequestBody List<UUID> userIds) {
        Claims claims = (Claims) SecurityContextHolder.getContext().getAuthentication().getDetails();
        userIds.add(UUID.fromString(claims.getSubject()));
        List<User> users = new java.util.ArrayList<>(List.of());
        for(UUID userId : userIds) {
            if(!securityService.userExists(userId)) {
                return ResponseEntity.notFound().build();
            }
            users.add(securityService.getUserById(userId));
        }
        MatchEntity matchEntity = matchService.startMatch(users);
        return ResponseEntity.ok(matchEntity);
    }
}

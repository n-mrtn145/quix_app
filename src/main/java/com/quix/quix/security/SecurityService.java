package com.quix.quix.security;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecurityService {
    @Autowired
    private SecurityRepository repository;

    @Transactional
    public UserEntity registerUser(String username, String password) {
        if (repository.existsByUsername(username)) {
            throw new RuntimeException("Username exists");
        }
        UUID uuid = UUID.randomUUID();
        while (repository.existsById(uuid)) {
            uuid = UUID.randomUUID();
        }
        UserEntity user = new UserEntity();
        user.setId(uuid);
        user.setUsername(username);
        user.setPassword(password); // optional Hashen

        return repository.save(user); // save() erledigt INSERT automatisch
    }

    public boolean userExists(String username) {
        return repository.existsByUsername(username);
    }
    public boolean userExists(UUID uuid) {
        return repository.existsById(uuid);
    }

    public UserEntity getUserById(UUID uuid) {
        if (!repository.existsById(uuid)) {
            return null;
        } else {
            return repository.findById(uuid).get();
        }
    }

    private void addFriend(UUID userId, UUID friendId) {
        UserEntity user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Freund hinzufügen, falls noch nicht drin
        if (!user.getFriends().contains(friendId)) {
            user.getFriends().add(friendId);
        }

        repository.save(user); // speichert die Änderungen
    }

    public void addFriends(UUID friend1, UUID friend2) {
        addFriend(friend1, friend2);
        addFriend(friend2, friend1);
    }
}

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
}

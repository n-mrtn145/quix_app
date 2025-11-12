package com.quix.quix.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecurityService {
    @Autowired
    private UserRepository repository;

    public boolean userExists(UUID uuid) {
        return repository.existsById(uuid);
    }

    public User getUserById(UUID uuid) {
        if (!userExists(uuid)) {
            return null;
        } else {
            return repository.getUserInfoById(uuid);
        }
    }

}

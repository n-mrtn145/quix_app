package com.quix.quix.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SecurityRepository extends JpaRepository<UserEntity, UUID> {

    @Modifying
    @Query(value = """
        INSERT INTO users
        VALUES(:id, :username, :password)
""", nativeQuery = true)
    public void registerUser(UUID id, String username, String password);

    boolean existsByUsername(String username);
}

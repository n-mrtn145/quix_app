package com.quix.quix.security;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private UUID id;

    private String username;

    private String password;

    @ElementCollection
    @CollectionTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "friend_id")
    private List<UUID> friends = new ArrayList<>();
}

package com.quix.quix.match;

import com.quix.quix.security.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class MatchEntity {
    @Id
    private UUID id;
    @OneToMany()
    private List<UserEntity> players;
    @OneToMany
    private List<CardEntity> cards;
    private MatchStatus status;
    private LocalDateTime timestamp;
}

package com.quix.quix.match;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.quix.quix.security.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // 🔹 Match hat mehrere Spieler
    @ManyToMany
    @JoinTable(
            name = "match_players",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            foreignKey = @ForeignKey(name = "fk_match_player_match"),
            inverseForeignKey = @ForeignKey(name = "fk_match_player_user")
    )
    private List<UserEntity> players = new ArrayList<>();

    // 🔹 Match hat mehrere Karten
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CardEntity> cards = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private MatchStatus status;

    private LocalDateTime timestamp;

    @Version
    private Long version;
}

package com.quix.quix.match;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "matches")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // 🔹 Match hat mehrere Spieler
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "match_id") // FK in MatchPlayer
    private List<MatchPlayer> players = new ArrayList<>();

    // 🔹 Match hat mehrere Karten (bleibt wie gehabt)
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CardEntity> cards = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private MatchStatus status;

    private LocalDateTime timestamp;

    @Version
    private Long version = 0L;
}

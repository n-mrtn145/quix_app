package com.quix.quix.match;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@IdClass(MatchPlayerId.class)
@Table(name = "match_user",
        uniqueConstraints = @UniqueConstraint(columnNames = {"match_id", "user_id"}))
public class MatchPlayer {
    @Id
    @Column(name = "match_id", nullable = false)
    private UUID matchId;

    @Id
    @Column(name = "user_id", nullable = false)
    private UUID userId;
}


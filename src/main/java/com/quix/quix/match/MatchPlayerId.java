package com.quix.quix.match;

import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchPlayerId implements Serializable {
    private UUID matchId;
    private UUID userId;
}

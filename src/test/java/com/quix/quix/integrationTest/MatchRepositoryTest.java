package com.quix.quix.integrationTest;

import com.quix.quix.match.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
class MatchRepositoryTest {

    @Autowired
    MatchRepository matchRepository;
    private static final UUID MATCH_ID = UUID.randomUUID();
    private static final UUID MATCH_ID2 = UUID.randomUUID();
    private static final UUID USER_ID = UUID.randomUUID();
    private static final UUID USER_ID2 = UUID.randomUUID();

    @Test
    void getAcitveEntryEntitybyUserId() {
        MatchPlayer match1Player1 = new MatchPlayer(MATCH_ID, USER_ID);
        MatchPlayer match1Player2 = new MatchPlayer(MATCH_ID, USER_ID2);
        MatchPlayer match2Player1 = new MatchPlayer(MATCH_ID2, USER_ID);
        EntryEntity entryMatch1Player2 = new EntryEntity(null, "", "", "", "", 1, MATCH_ID, USER_ID2);
        EntryEntity entry = new EntryEntity(null,"1,2", "1", "4", "", 0, MATCH_ID, USER_ID);
        EntryEntity entry2 = new EntryEntity(null, "", "", "", "", 1, MATCH_ID, USER_ID);
        MatchEntity match = new MatchEntity(MATCH_ID, List.of(match1Player1, match1Player2), List.of(entry, entryMatch1Player2), MatchStatus.A, LocalDateTime.now());
        MatchEntity match2 = new MatchEntity(MATCH_ID2, List.of(match2Player1), List.of(entry2), MatchStatus.F, LocalDateTime.now());
        matchRepository.save(match);
        matchRepository.save(match2);
        assertThat(matchRepository.getAcitveEntryEntitybyUserId(USER_ID)).extracting(EntryEntity::getRed).isEqualTo("1,2");

    }
}
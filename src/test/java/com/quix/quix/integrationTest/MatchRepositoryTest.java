package com.quix.quix.integrationTest;

import com.quix.quix.match.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest(properties = "spring.profiles.active=test")
@Testcontainers
@Transactional
class MatchRepositoryTest {
    @Container
    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:17")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "validate"); // Hibernate validiert Schema
    }


    @Autowired
    MatchRepository matchRepository;
    @Autowired
    MatchPlayerRepository matchPlayerRepository;
    private static final UUID MATCH_ID = UUID.randomUUID();
    private static final UUID MATCH_ID2 = UUID.randomUUID();
    private static final UUID USER_ID = UUID.randomUUID();
    private static final UUID ENTRY_ID = UUID.randomUUID();
    private static final UUID ENTRY_ID2 = UUID.randomUUID();

    @Test
    void getAcitveEntryEntitybyUserId() {
        MatchPlayer matchPlayer = new MatchPlayer(MATCH_ID, USER_ID);
        MatchPlayer matchPlayer2 = new MatchPlayer(MATCH_ID2, USER_ID);
        EntryEntity entry = new EntryEntity(null,"1,2", "1", "4", "", 0, MATCH_ID, USER_ID);
        EntryEntity entry2 = new EntryEntity(null, "", "", "", "", 1, MATCH_ID, USER_ID);
        MatchEntity match = new MatchEntity(MATCH_ID, List.of(matchPlayer), List.of(entry), MatchStatus.A, LocalDateTime.now());
        MatchEntity match2 = new MatchEntity(MATCH_ID2, List.of(matchPlayer2), List.of(entry2), MatchStatus.F, LocalDateTime.now());
        matchRepository.save(match);
        matchRepository.save(match2);
        assertThat(matchRepository.getAcitveEntryEntitybyUserId(USER_ID)).isEqualTo(entry);

    }
}
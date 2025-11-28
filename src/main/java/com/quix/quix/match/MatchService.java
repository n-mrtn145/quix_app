package com.quix.quix.match;

import com.quix.quix.security.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private CardRepository cardRepository;

    @Transactional
    public MatchEntity startMatch(List<User> players) {
        MatchEntity match = new MatchEntity();
        UUID matchId = UUID.randomUUID();
        match.setId(matchId);

        List<MatchPlayer>  matchPlayers = new ArrayList<>();
        MatchPlayer matchPlayer;

        match.setStatus(MatchStatus.A);
        match.setTimestamp(LocalDateTime.now());

        List<EntryEntity> entries = new ArrayList<>();

        for (User player : players) {
            EntryEntity entry = new EntryEntity();
            entry.setRed("");
            entry.setYellow("");
            entry.setGreen("");
            entry.setBlue("");
            entry.setWrongThrow(0);
            entry.setUserId(player.getId());
            entry.setMatchId(matchId);
            entries.add(entry);

            matchPlayer = new MatchPlayer();
            matchPlayer.setMatchId(matchId);
            matchPlayer.setUserId(player.getId());
            matchPlayers.add(matchPlayer);
        }
        match.setPlayers(matchPlayers);
        match.setEntries(entries);

        return matchRepository.save(match);
    }

    public MatchEntity getActiveMatchOfPlayer(UUID playerId) {
return null;
    }


}

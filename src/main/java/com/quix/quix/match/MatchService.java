package com.quix.quix.match;

import com.quix.quix.security.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private CardRepository cardRepository;

    @Transactional
    public MatchEntity startMatch(List<UserEntity> players) {
        MatchEntity match = new MatchEntity();
        match.setPlayers(players);
        match.setStatus(MatchStatus.A);
        match.setTimestamp(LocalDateTime.now());

        List<CardEntity> cards = new ArrayList<>();

        for (UserEntity player : players) {
            EntryEntity entry = new EntryEntity();
            entry.setRed(List.of());
            entry.setYellow(List.of());
            entry.setGreen(List.of());
            entry.setBlue(List.of());
            entry.setWrongTrow(0);

            CardEntity card = new CardEntity();
            card.setUser(player);
            card.setEntry(entry);
            card.setMatch(match);

            cards.add(card);
        }

        match.setCards(cards);

        return matchRepository.save(match); // ✅ jetzt geht’s sauber durch
    }


}

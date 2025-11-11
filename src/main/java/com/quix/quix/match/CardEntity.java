package com.quix.quix.match;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // 🔹 Eine Karte gehört zu einem Match
    @ManyToOne(optional = false)
    @JoinColumn(name = "match_id", foreignKey = @ForeignKey(name = "fk_card_match"))
    @JsonBackReference
    private MatchEntity match;

    private UUID userId;

    // 🔹 Eine Karte hat genau ein Entry (1:1 Beziehung)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "entry_id", foreignKey = @ForeignKey(name = "fk_card_entry"))
    private EntryEntity entry;

    @Version
    private Long version = 0L;
}

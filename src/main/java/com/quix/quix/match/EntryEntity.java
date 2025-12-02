package com.quix.quix.match;

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
public class EntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String red;


    private String yellow;


    private String green;


    private String blue;

    private Integer wrongThrow;

    @Column(name = "match_id")
    private UUID matchId;

    private UUID userId;
}


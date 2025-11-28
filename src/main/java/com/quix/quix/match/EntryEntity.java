package com.quix.quix.match;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    private String red;


    private String yellow;


    private String green;


    private String blue;

    private Integer wrongThrow;

    private UUID matchId;

    private UUID userId;
}


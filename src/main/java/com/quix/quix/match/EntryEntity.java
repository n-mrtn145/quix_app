package com.quix.quix.match;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class EntryEntity {
    @Id
    private UUID id;
    private List<Integer> red;
    private List<Integer> yellow;
    private List<Integer> green;
    private List<Integer> blue;
}

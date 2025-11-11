package com.quix.quix.match;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ElementCollection
    @CollectionTable(name = "entry_red", joinColumns = @JoinColumn(name = "entry_id"))
    @Column(name = "value")
    private List<Integer> red;

    @ElementCollection
    @CollectionTable(name = "entry_yellow", joinColumns = @JoinColumn(name = "entry_id"))
    @Column(name = "value")
    private List<Integer> yellow;

    @ElementCollection
    @CollectionTable(name = "entry_green", joinColumns = @JoinColumn(name = "entry_id"))
    @Column(name = "value")
    private List<Integer> green;

    @ElementCollection
    @CollectionTable(name = "entry_blue", joinColumns = @JoinColumn(name = "entry_id"))
    @Column(name = "value")
    private List<Integer> blue;

    private Integer wrongThrow;

    @Version
    private Long version = 0L;
}


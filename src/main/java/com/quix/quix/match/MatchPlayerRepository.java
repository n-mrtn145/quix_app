package com.quix.quix.match;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MatchPlayerRepository extends CrudRepository<MatchPlayer, UUID> {
}

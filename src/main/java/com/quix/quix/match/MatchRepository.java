package com.quix.quix.match;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MatchRepository extends CrudRepository<MatchEntity, UUID> {
}
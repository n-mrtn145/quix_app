package com.quix.quix.match;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MatchRepository extends CrudRepository<MatchEntity, UUID> {
    @Query(value = """
                    select e.id as id, e.wrong_throw as wrongThrow, e.red, e.yellow, e.blue, e.green, e.match_id as matchId, e.user_id as userId
                    from entry_entity e 
                    join match_user mu on mu.user_id = e.user_id
                    join match_entity m on e.match_id = m.id
                    WHERE e.user_id = :userId 
                    AND m.status = 'A'
            """, nativeQuery = true)
    EntryEntity getAcitveEntryEntitybyUserId(UUID userId);
}
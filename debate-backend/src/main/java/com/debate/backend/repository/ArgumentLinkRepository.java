package com.debate.backend.repository;

import com.debate.backend.entity.ArgumentLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ArgumentLinkRepository
        extends JpaRepository<ArgumentLink, UUID> {

    @Query("""
            SELECT link
            FROM ArgumentLink link
            WHERE link.fromArgument.debate.id = :debateId
            """)
    List<ArgumentLink> findLinksByDebateId(UUID debateId);
}
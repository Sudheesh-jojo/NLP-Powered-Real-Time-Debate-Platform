package com.debate.backend.repository;

import com.debate.backend.entity.Argument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;

public interface ArgumentRepository
        extends JpaRepository<Argument, UUID> {

    List<Argument> findByDebateId(UUID debateId);
}
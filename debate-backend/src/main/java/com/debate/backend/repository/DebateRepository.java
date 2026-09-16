package com.debate.backend.repository;

import com.debate.backend.entity.Debate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DebateRepository extends JpaRepository<Debate, UUID> {
}

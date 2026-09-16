package com.debate.backend.repository;

import com.debate.backend.entity.ArgumentStrength;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArgumentStrengthRepository extends JpaRepository<ArgumentStrength, UUID> {
}
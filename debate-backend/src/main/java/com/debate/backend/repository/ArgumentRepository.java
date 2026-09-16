package com.debate.backend.repository;

import com.debate.backend.entity.Argument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArgumentRepository extends JpaRepository<Argument, UUID> {
}

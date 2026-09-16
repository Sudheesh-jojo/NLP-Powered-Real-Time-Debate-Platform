package com.debate.backend.repository;

import com.debate.backend.entity.ArgumentLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArgumentLinkRepository extends JpaRepository<ArgumentLink, UUID> {
}
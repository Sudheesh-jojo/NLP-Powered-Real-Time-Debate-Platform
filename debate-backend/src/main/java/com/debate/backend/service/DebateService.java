package com.debate.backend.service;

import com.debate.backend.dto.CreateDebateRequest;
import com.debate.backend.dto.DebateDto;
import com.debate.backend.entity.Debate;
import com.debate.backend.entity.User;
import com.debate.backend.repository.DebateRepository;
import com.debate.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DebateService {

    private final DebateRepository debateRepository;
    private final UserRepository userRepository;

    public DebateService(
            DebateRepository debateRepository,
            UserRepository userRepository) {

        this.debateRepository = debateRepository;
        this.userRepository = userRepository;
    }

    public List<DebateDto> getAllDebates() {

        return debateRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public DebateDto getDebateById(UUID id) {

        Debate debate = debateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Debate not found"));

        return convertToDto(debate);
    }

    public DebateDto createDebate(CreateDebateRequest request) {

        User user = userRepository.findById(request.getCreatedBy())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Debate debate = new Debate();

        debate.setTitle(request.getTitle());
        debate.setDescription(request.getDescription());
        debate.setCreatedBy(user);

        Debate savedDebate = debateRepository.save(debate);

        return convertToDto(savedDebate);
    }

    private DebateDto convertToDto(Debate debate) {

        DebateDto dto = new DebateDto();

        dto.setId(debate.getId());
        dto.setTitle(debate.getTitle());
        dto.setDescription(debate.getDescription());

        if (debate.getCreatedBy() != null) {
            dto.setCreatedByUsername(
                    debate.getCreatedBy().getUsername()
            );
        }

        dto.setCreatedAt(debate.getCreatedAt());

        if (debate.getArguments() != null) {
            dto.setArgumentCount(
                    debate.getArguments().size()
            );
        } else {
            dto.setArgumentCount(0);
        }

        return dto;
    }
}
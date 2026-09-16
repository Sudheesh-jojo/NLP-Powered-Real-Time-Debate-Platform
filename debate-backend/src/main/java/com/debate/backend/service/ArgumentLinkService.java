package com.debate.backend.service;

import com.debate.backend.dto.ArgumentLinkDto;
import com.debate.backend.dto.CreateArgumentLinkRequest;
import com.debate.backend.entity.Argument;
import com.debate.backend.entity.ArgumentLink;
import com.debate.backend.repository.ArgumentLinkRepository;
import com.debate.backend.repository.ArgumentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ArgumentLinkService {

    private final ArgumentLinkRepository argumentLinkRepository;
    private final ArgumentRepository argumentRepository;

    public ArgumentLinkService(
            ArgumentLinkRepository argumentLinkRepository,
            ArgumentRepository argumentRepository) {

        this.argumentLinkRepository = argumentLinkRepository;
        this.argumentRepository = argumentRepository;
    }

    public ArgumentLinkDto createLink(
            CreateArgumentLinkRequest request) {

        Argument fromArgument = argumentRepository
                .findById(request.getFromArgumentId())
                .orElseThrow(() ->
                        new RuntimeException("From argument not found"));

        Argument toArgument = argumentRepository
                .findById(request.getToArgumentId())
                .orElseThrow(() ->
                        new RuntimeException("To argument not found"));

        ArgumentLink link = new ArgumentLink();

        link.setFromArgument(fromArgument);
        link.setToArgument(toArgument);
        link.setLinkType(request.getLinkType());
        link.setSimilarityScore(request.getSimilarityScore());

        ArgumentLink savedLink =
                argumentLinkRepository.save(link);

        return convertToDto(savedLink);
    }

    private ArgumentLinkDto convertToDto(
            ArgumentLink link) {

        ArgumentLinkDto dto = new ArgumentLinkDto();

        dto.setId(link.getId());
        dto.setFromArgumentId(
                link.getFromArgument().getId());
        dto.setToArgumentId(
                link.getToArgument().getId());
        dto.setLinkType(link.getLinkType());
        dto.setSimilarityScore(link.getSimilarityScore());
        dto.setCreatedAt(link.getCreatedAt());

        return dto;
    }
}
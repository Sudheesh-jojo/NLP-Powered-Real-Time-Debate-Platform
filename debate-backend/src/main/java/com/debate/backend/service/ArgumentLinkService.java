package com.debate.backend.service;

import com.debate.backend.dto.ArgumentLinkDto;
import com.debate.backend.dto.CreateArgumentLinkRequest;
import com.debate.backend.entity.Argument;
import com.debate.backend.entity.ArgumentLink;
import com.debate.backend.repository.ArgumentLinkRepository;
import com.debate.backend.repository.ArgumentRepository;
import org.springframework.stereotype.Service;
import com.debate.backend.dto.NlpSimilarityResponse;
import java.util.List;

import java.util.UUID;

@Service
public class ArgumentLinkService {

    private final ArgumentLinkRepository argumentLinkRepository;
    private final ArgumentRepository argumentRepository;
    private final NlpService nlpService;

    public ArgumentLinkService(
            ArgumentLinkRepository argumentLinkRepository,
            ArgumentRepository argumentRepository,
            NlpService nlpService) {

        this.argumentLinkRepository = argumentLinkRepository;
        this.argumentRepository = argumentRepository;
        this.nlpService = nlpService;
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
    public List<ArgumentLinkDto> getLinksByDebate(UUID debateId) {

        return argumentLinkRepository
                .findLinksByDebateId(debateId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }
    public void findAndCreateLinks(Argument newArgument) {

        List<Argument> previousArguments =
                argumentRepository.findByDebateId(
                        newArgument.getDebate().getId()
                );

        for (Argument previousArgument : previousArguments) {

            if (previousArgument.getId()
                    .equals(newArgument.getId())) {
                continue;
            }

            NlpSimilarityResponse response =
                    nlpService.calculateSimilarity(
                            previousArgument.getMessageText(),
                            newArgument.getMessageText()
                    );

            double similarity = response.getSimilarity();

            if (similarity >= 0.60) {

                ArgumentLink link = new ArgumentLink();

                link.setFromArgument(previousArgument);
                link.setToArgument(newArgument);
                link.setLinkType("RELATED");
                link.setSimilarityScore(similarity);

                argumentLinkRepository.save(link);
            }
        }
    }
}
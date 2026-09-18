package com.debate.backend.service;

import com.debate.backend.dto.GraphEdgeDto;
import com.debate.backend.dto.GraphNodeDto;
import com.debate.backend.entity.Argument;
import com.debate.backend.entity.ArgumentLink;
import com.debate.backend.repository.ArgumentLinkRepository;
import com.debate.backend.repository.ArgumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GraphService {

    private final ArgumentRepository argumentRepository;
    private final ArgumentLinkRepository argumentLinkRepository;

    public GraphService(
            ArgumentRepository argumentRepository,
            ArgumentLinkRepository argumentLinkRepository) {

        this.argumentRepository = argumentRepository;
        this.argumentLinkRepository = argumentLinkRepository;
    }

    public List<GraphNodeDto> getNodes(UUID debateId) {

        List<Argument> arguments =
                argumentRepository.findByDebateId(debateId);

        return arguments.stream()
                .map(this::convertToNodeDto)
                .toList();
    }

    public List<GraphEdgeDto> getEdges(UUID debateId) {

        List<ArgumentLink> links =
                argumentLinkRepository.findLinksByDebateId(debateId);

        return links.stream()
                .map(this::convertToEdgeDto)
                .toList();
    }

    private GraphNodeDto convertToNodeDto(Argument argument) {

        GraphNodeDto dto = new GraphNodeDto();

        dto.setId(argument.getId());
        dto.setText(argument.getMessageText());
        dto.setArgumentType(argument.getArgumentType());

        return dto;
    }

    private GraphEdgeDto convertToEdgeDto(ArgumentLink link) {

        GraphEdgeDto dto = new GraphEdgeDto();

        dto.setSource(
                link.getFromArgument().getId());

        dto.setTarget(
                link.getToArgument().getId());

        dto.setLinkType(
                link.getLinkType());

        dto.setSimilarityScore(
                link.getSimilarityScore());

        return dto;
    }
}
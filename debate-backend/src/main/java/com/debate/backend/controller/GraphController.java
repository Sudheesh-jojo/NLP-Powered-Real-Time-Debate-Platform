package com.debate.backend.controller;

import com.debate.backend.dto.GraphEdgeDto;
import com.debate.backend.dto.GraphNodeDto;
import com.debate.backend.service.GraphService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/graph")
public class GraphController {

    private final GraphService graphService;

    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @GetMapping("/{debateId}/nodes")
    public List<GraphNodeDto> getNodes(
            @PathVariable UUID debateId) {

        return graphService.getNodes(debateId);
    }

    @GetMapping("/{debateId}/edges")
    public List<GraphEdgeDto> getEdges(
            @PathVariable UUID debateId) {

        return graphService.getEdges(debateId);
    }
}
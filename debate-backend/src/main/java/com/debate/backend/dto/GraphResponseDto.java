package com.debate.backend.dto;

import java.util.List;

public class GraphResponseDto {

    private List<GraphNodeDto> nodes;
    private List<GraphLinkDto> links;

    public List<GraphNodeDto> getNodes() {
        return nodes;
    }

    public void setNodes(List<GraphNodeDto> nodes) {
        this.nodes = nodes;
    }

    public List<GraphLinkDto> getLinks() {
        return links;
    }

    public void setLinks(List<GraphLinkDto> links) {
        this.links = links;
    }
}
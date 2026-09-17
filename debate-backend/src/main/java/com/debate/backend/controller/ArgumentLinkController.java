package com.debate.backend.controller;

import com.debate.backend.dto.ArgumentLinkDto;
import com.debate.backend.dto.CreateArgumentLinkRequest;
import com.debate.backend.service.ArgumentLinkService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/argument-links")
public class ArgumentLinkController {

    private final ArgumentLinkService argumentLinkService;

    public ArgumentLinkController(
            ArgumentLinkService argumentLinkService) {

        this.argumentLinkService = argumentLinkService;
    }

    @PostMapping
    public ArgumentLinkDto createLink(
            @RequestBody CreateArgumentLinkRequest request) {

        return argumentLinkService.createLink(request);
    }

    @GetMapping("/debate/{debateId}")
    public List<ArgumentLinkDto> getLinksByDebate(
            @PathVariable UUID debateId) {

        return argumentLinkService.getLinksByDebate(debateId);
    }
}
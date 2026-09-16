package com.debate.backend.controller;

import com.debate.backend.dto.ArgumentDto;
import com.debate.backend.dto.CreateArgumentRequest;
import com.debate.backend.service.ArgumentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/arguments")
public class ArgumentController {

    private final ArgumentService argumentService;

    public ArgumentController(ArgumentService argumentService) {
        this.argumentService = argumentService;
    }

    @PostMapping
    public ArgumentDto createArgument(
            @RequestParam UUID userId,
            @RequestBody CreateArgumentRequest request) {

        return argumentService.createArgument(request, userId);
    }
    @GetMapping("/debate/{debateId}")
    public List<ArgumentDto> getArgumentsByDebate(
            @PathVariable UUID debateId) {

        return argumentService.getArgumentsByDebate(debateId);
    }
    @GetMapping("/{argumentId}")
    public ArgumentDto getArgumentById(
            @PathVariable UUID argumentId) {

        return argumentService.getArgumentById(argumentId);
    }
}
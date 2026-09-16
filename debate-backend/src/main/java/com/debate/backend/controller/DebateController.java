package com.debate.backend.controller;

import com.debate.backend.dto.CreateDebateRequest;
import com.debate.backend.dto.DebateDto;
import com.debate.backend.service.DebateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/debates")
public class DebateController {

    private final DebateService debateService;

    public DebateController(DebateService debateService) {
        this.debateService = debateService;
    }

    @GetMapping
    public List<DebateDto> getAllDebates() {
        return debateService.getAllDebates();
    }

    @GetMapping("/{id}")
    public DebateDto getDebate(@PathVariable UUID id) {
        return debateService.getDebateById(id);
    }

    @PostMapping
    public DebateDto createDebate(
            @RequestBody CreateDebateRequest request) {

        return debateService.createDebate(request);
    }
}
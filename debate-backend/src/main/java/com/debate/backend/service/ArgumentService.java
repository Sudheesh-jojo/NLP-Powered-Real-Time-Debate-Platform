package com.debate.backend.service;

import com.debate.backend.dto.ArgumentDto;
import com.debate.backend.dto.CreateArgumentRequest;
import com.debate.backend.entity.Argument;
import com.debate.backend.entity.Debate;
import com.debate.backend.entity.User;
import com.debate.backend.repository.ArgumentRepository;
import com.debate.backend.repository.DebateRepository;
import com.debate.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.debate.backend.dto.NlpClassifyResponse;
import java.util.List;
import java.util.UUID;

@Service
public class ArgumentService {

    private final ArgumentRepository argumentRepository;
    private final DebateRepository debateRepository;
    private final UserRepository userRepository;
    private final NlpService nlpService;

    public ArgumentService(
            ArgumentRepository argumentRepository,
            DebateRepository debateRepository,
            UserRepository userRepository,
            NlpService nlpService) {

        this.argumentRepository = argumentRepository;
        this.debateRepository = debateRepository;
        this.userRepository = userRepository;
        this.nlpService = nlpService;
    }

    public ArgumentDto createArgument(
            CreateArgumentRequest request,
            UUID userId) {

        Debate debate = debateRepository.findById(request.getDebateId())
                .orElseThrow(() -> new RuntimeException("Debate not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Argument argument = new Argument();

        argument.setDebate(debate);
        argument.setUser(user);
        argument.setMessageText(request.getMessageText());

        Argument savedArgument = argumentRepository.save(argument);


        NlpClassifyResponse nlpResponse =
                nlpService.classifyArgument(argument.getMessageText());


        savedArgument.setArgumentType(nlpResponse.getArgument_type());
        savedArgument.setNlpConfidence(nlpResponse.getConfidence());
        
        savedArgument = argumentRepository.save(savedArgument);

        return convertToDto(savedArgument);
    }

    private ArgumentDto convertToDto(Argument argument) {

        ArgumentDto dto = new ArgumentDto();

        dto.setId(argument.getId());
        dto.setDebateId(argument.getDebate().getId());
        dto.setUserId(argument.getUser().getId());
        dto.setMessageText(argument.getMessageText());
        dto.setArgumentType(argument.getArgumentType());
        dto.setNlpConfidence(argument.getNlpConfidence());
        dto.setCreatedAt(argument.getCreatedAt());

        return dto;
    }
    public List<ArgumentDto> getArgumentsByDebate(UUID debateId) {

        return argumentRepository.findAll()
                .stream()
                .filter(argument -> argument.getDebate().getId().equals(debateId))
                .map(this::convertToDto)
                .toList();
    }
    public ArgumentDto getArgumentById(UUID argumentId) {

        Argument argument = argumentRepository.findById(argumentId)
                .orElseThrow(() -> new RuntimeException("Argument not found"));

        return convertToDto(argument);
    }
}
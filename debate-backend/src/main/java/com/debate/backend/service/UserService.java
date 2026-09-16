package com.debate.backend.service;

import com.debate.backend.dto.CreateUserRequest;
import com.debate.backend.dto.UserDto;
import com.debate.backend.entity.User;
import com.debate.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(CreateUserRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        // Temporary for now.
        // Proper password hashing will be added with JWT authentication.
        user.setPasswordHash(request.getPassword());

        User savedUser = userRepository.save(user);

        return convertToDto(savedUser);
    }

    private UserDto convertToDto(User user) {

        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setReputationScore(user.getReputationScore());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }
}
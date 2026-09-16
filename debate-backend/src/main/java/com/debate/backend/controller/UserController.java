package com.debate.backend.controller;

import com.debate.backend.dto.CreateUserRequest;
import com.debate.backend.dto.UserDto;
import com.debate.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto createUser(
            @RequestBody CreateUserRequest request) {

        return userService.createUser(request);
    }
}
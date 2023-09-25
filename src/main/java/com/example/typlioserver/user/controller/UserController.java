package com.example.typlioserver.user.controller;

import com.example.typlioserver.user.dto.MeDto;
import com.example.typlioserver.user.dto.UserDto;
import com.example.typlioserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public MeDto getMe() {
        return userService.getMe();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOne(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}

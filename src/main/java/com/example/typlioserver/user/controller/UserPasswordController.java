package com.example.typlioserver.user.controller;

import com.example.typlioserver.user.dto.PasswordResetDto;
import com.example.typlioserver.user.service.UserPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserPasswordController {

    private final UserPasswordService userPasswordService;

    @PostMapping("/password")
    @ResponseStatus(HttpStatus.CREATED)
    public void resetPassword(@RequestBody PasswordResetDto passwordResetDto) {
        userPasswordService.resetPassword(passwordResetDto.getEmail());
    }
}

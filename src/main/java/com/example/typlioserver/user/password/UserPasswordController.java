package com.example.typlioserver.user.password;

import com.example.typlioserver.user.password.dto.PasswordResetDto;
import com.example.typlioserver.user.password.dto.RequestResetPasswordDto;
import jakarta.validation.Valid;
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
    void sendResetPasswordLink(@RequestBody RequestResetPasswordDto passwordResetDto) {
        userPasswordService.sendResetPasswordLink(passwordResetDto.getEmail());
    }

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    void resetPassword(@RequestBody @Valid PasswordResetDto resetPasswordDto) {
        userPasswordService.resetPassword(resetPasswordDto);
    }
}

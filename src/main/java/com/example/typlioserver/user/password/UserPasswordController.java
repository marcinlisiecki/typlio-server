package com.example.typlioserver.user.password;

import com.example.typlioserver.user.password.dto.ChangePasswordDto;
import com.example.typlioserver.user.password.dto.PasswordResetDto;
import com.example.typlioserver.user.password.dto.RequestPasswordResetDto;
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
    void sendResetPasswordLink(@RequestBody RequestPasswordResetDto passwordResetDto) {
        userPasswordService.sendResetPasswordLink(passwordResetDto.getEmail());
    }

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    void resetPassword(@RequestBody @Valid PasswordResetDto resetPasswordDto) {
        userPasswordService.resetPassword(resetPasswordDto);
    }

    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.OK)
    void changePassword(@PathVariable Long userId, @RequestBody @Valid ChangePasswordDto passwordDto) {
        userPasswordService.changePassword(userId, passwordDto);
    }
}

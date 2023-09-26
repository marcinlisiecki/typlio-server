package com.example.typlioserver.user;

import com.example.typlioserver.user.dto.MeDto;
import com.example.typlioserver.user.dto.UpdateUsernameDto;
import com.example.typlioserver.user.dto.UpdateUsernameResponseDto;
import com.example.typlioserver.user.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    MeDto getMe() {
        return userService.getMe();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    UserDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteOne(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @PatchMapping("/{userId}/username")
    @ResponseStatus(HttpStatus.OK)
    UpdateUsernameResponseDto updateUsername(
            @PathVariable Long userId,
            @RequestBody @Valid UpdateUsernameDto updateUsernameDto) {

        return userService.updateUsername(userId, updateUsernameDto);
    }
}

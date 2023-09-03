package com.example.typlioserver.user;

import com.example.typlioserver.user.dto.UserDto;
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
    public UserDto getMe() {
        return userService.getMe();
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOne(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}

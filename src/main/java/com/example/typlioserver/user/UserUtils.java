package com.example.typlioserver.user;

import com.example.typlioserver.auth.utils.AuthUtils;
import com.example.typlioserver.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtils {

    private final UserRepository userRepository;

    public User getLoggedInUser() {
        return userRepository
                .findByUsername(AuthUtils.getLoggedInUser().getUsername())
                .orElseThrow(UserNotFoundException::new);
    }
}

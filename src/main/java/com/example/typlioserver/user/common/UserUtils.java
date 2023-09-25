package com.example.typlioserver.user.common;

import com.example.typlioserver.auth.utils.AuthUtils;
import com.example.typlioserver.user.User;
import com.example.typlioserver.user.UserRepository;
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

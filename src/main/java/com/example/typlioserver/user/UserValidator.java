package com.example.typlioserver.user;

import com.example.typlioserver.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void checkIfEmailUserExists(String email) {
        userRepository
                .findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    public void checkIfIdUserExists(Long id) {
        userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}

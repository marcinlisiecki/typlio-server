package com.example.typlioserver.user;

import com.example.typlioserver.common.exception.InsufficientPermissionsException;
import com.example.typlioserver.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;
    private final UserUtils userUtils;

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

    public void checkIfSameUser(Long userId) {
        User loggedUser = userUtils.getLoggedInUser();
        if (!Objects.equals(userId, loggedUser.getId())) {
            throw new InsufficientPermissionsException();
        }
    }
}

package com.example.typlioserver.user;

import com.example.typlioserver.auth.utils.AuthUtils;
import com.example.typlioserver.common.exception.InsufficientPermissionsException;
import com.example.typlioserver.user.exception.UserNotFoundException;
import com.example.typlioserver.user.dto.MeDto;
import com.example.typlioserver.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
class UserService {

    private final UserValidator userValidator;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserUtils userUtils;

    MeDto getMe() {
        UserDetails userDetails = AuthUtils.getLoggedInUserDetails();

        return userRepository
                .findByUsername(userDetails.getUsername())
                .map(userMapper::userToMeDto)
                .orElseThrow(UserNotFoundException::new);
    }

    UserDto getUser(Long userId) {
        return userRepository
                .findById(userId)
                .map(userMapper::userToUserDto)
                .orElseThrow(UserNotFoundException::new);
    }

    void deleteUser(Long userId) {
        userValidator.checkIfIdUserExists(userId);
        User loggedUser = userUtils.getLoggedInUser();

        if (!Objects.equals(loggedUser.getId(), userId)) {
            throw new InsufficientPermissionsException();
        }

        userRepository.deleteById(userId);
    }
}

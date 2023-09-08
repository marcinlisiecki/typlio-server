package com.example.typlioserver.user.service;

import com.example.typlioserver.auth.utils.AuthUtils;
import com.example.typlioserver.common.exception.InsufficientPermissionsException;
import com.example.typlioserver.user.User;
import com.example.typlioserver.user.UserMapper;
import com.example.typlioserver.user.UserRepository;
import com.example.typlioserver.user.dto.UserDto;
import com.example.typlioserver.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto getMe() {
        UserDetails userDetails = AuthUtils.getLoggedInUser();

        return userRepository
                .findByUsername(userDetails.getUsername())
                .map(userMapper::map)
                .orElseThrow(UserNotFoundException::new);
    }

    public void deleteUser(Long userId) {
        User loggedUser = userRepository
                .findByUsername(AuthUtils.getLoggedInUser().getUsername())
                .orElseThrow(UserNotFoundException::new);

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }

        if (!Objects.equals(loggedUser.getId(), userId)) {
            throw new InsufficientPermissionsException();
        }

        userRepository.deleteById(userId);
    }
}

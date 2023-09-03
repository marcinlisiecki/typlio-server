package com.example.typlioserver.user;

import com.example.typlioserver.auth.utils.AuthUtils;
import com.example.typlioserver.user.dto.UserDto;
import com.example.typlioserver.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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
}

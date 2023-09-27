package com.example.typlioserver.user;

import com.example.typlioserver.auth.exception.UsernameAlreadyExistsException;
import com.example.typlioserver.auth.jwt.JwtService;
import com.example.typlioserver.auth.utils.AuthUtils;
import com.example.typlioserver.user.dto.UpdateUsernameDto;
import com.example.typlioserver.user.dto.UpdateUsernameResponseDto;
import com.example.typlioserver.user.exception.UserNotFoundException;
import com.example.typlioserver.user.dto.MeDto;
import com.example.typlioserver.user.dto.UserDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserService {

    private final UserValidator userValidator;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserUtils userUtils;
    private final JwtService jwtService;

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
        userValidator.checkIfSameUser(userId);
        userRepository.deleteById(userId);
    }

    @Transactional
    public UpdateUsernameResponseDto updateUsername(Long userId, UpdateUsernameDto updateUsernameDto) {
        userValidator.checkIfIdUserExists(userId);
        userValidator.checkIfSameUser(userId);

        User loggedUser = userUtils.getLoggedInUser();

        if (userRepository.existsByUsername(updateUsernameDto.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        loggedUser.setUsername(updateUsernameDto.getUsername());
        String refreshToken = jwtService.generateRefreshToken(loggedUser);

        return UpdateUsernameResponseDto
                .builder()
                .refreshToken(refreshToken)
                .user(userMapper.userToMeDto(loggedUser))
                .build();
    }
}

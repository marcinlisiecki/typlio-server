package com.example.typlioserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserMapper {

    MeDto userToMeDto(User user) {
        return MeDto
                .builder()
                .email(user.getEmail())
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

    UserDto userToUserDto(User user) {
        return UserDto
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}

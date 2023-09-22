package com.example.typlioserver.user;

import com.example.typlioserver.user.dto.MeDto;
import com.example.typlioserver.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public MeDto userToMeDto(User user) {
        return MeDto
                .builder()
                .email(user.getEmail())
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

    public UserDto userToUserDto(User user) {
        return UserDto
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}

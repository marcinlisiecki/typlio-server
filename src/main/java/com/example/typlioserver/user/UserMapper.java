package com.example.typlioserver.user;

import com.example.typlioserver.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDto map(User user) {
        return UserDto
                .builder()
                .email(user.getEmail())
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}

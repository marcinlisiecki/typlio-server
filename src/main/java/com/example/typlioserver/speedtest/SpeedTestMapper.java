package com.example.typlioserver.speedtest;

import com.example.typlioserver.auth.exception.UserNotAuthenticatedException;
import com.example.typlioserver.speedtest.dto.NewSpeedTestDto;
import com.example.typlioserver.speedtest.dto.SpeedTestDto;
import com.example.typlioserver.user.User;
import com.example.typlioserver.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpeedTestMapper {

    private final UserRepository userRepository;

    public SpeedTest map(NewSpeedTestDto newSpeedTestDto, Long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(UserNotAuthenticatedException::new);

        return SpeedTest
                .builder()
                .mode(newSpeedTestDto.getMode())
                .time(newSpeedTestDto.getTime())
                .cpm(newSpeedTestDto.getCpm())
                .accuracy(newSpeedTestDto.getAccuracy())
                .mistakes(newSpeedTestDto.getMistakes())
                .wpmHistory(newSpeedTestDto.getWpmHistory())
                .user(user)
                .build();
    }

    public SpeedTestDto map(SpeedTest speedTest) {
        return SpeedTestDto
                .builder()
                .id(speedTest.getId())
                .mode(speedTest.getMode())
                .accuracy(speedTest.getAccuracy())
                .cpm(speedTest.getCpm())
                .mistakes(speedTest.getMistakes())
                .time(speedTest.getTime())
                .userId(speedTest.getUser().getId())
                .wpmHistory(speedTest.getWpmHistory())
                .createdAt(speedTest.getCreatedAt().toString())
                .build();
    }
}

package com.example.typlioserver.user.service;

import com.example.typlioserver.speedtest.SpeedTest;
import com.example.typlioserver.speedtest.SpeedTestMapper;
import com.example.typlioserver.speedtest.SpeedTestRepository;
import com.example.typlioserver.speedtest.dto.NewSpeedTestDto;
import com.example.typlioserver.speedtest.dto.SpeedTestDto;
import com.example.typlioserver.speedtest.exception.SpeedTestNotFoundException;
import com.example.typlioserver.user.User;
import com.example.typlioserver.user.UserRepository;
import com.example.typlioserver.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSpeedTestService {

    private final SpeedTestRepository speedTestRepository;
    private final SpeedTestMapper speedTestMapper;
    private final UserRepository userRepository;

    public SpeedTestDto createSpeedTest(NewSpeedTestDto newSpeedTestDto, Long userId) {
        SpeedTest speedTest = speedTestMapper.map(newSpeedTestDto, userId);
        return speedTestMapper.map(speedTestRepository.save(speedTest));
    }

    public SpeedTestDto findSpeedTest(Long speedTestId, Long userId) {
        userRepository
                .findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return speedTestRepository
                .findById(speedTestId)
                .map(speedTestMapper::map)
                .orElseThrow(SpeedTestNotFoundException::new);
    }

    public List<SpeedTestDto> findUserSpeedTests(Long userId) {
        return userRepository
                .findById(userId)
                .map(User::getSpeedTests)
                .orElseThrow(UserNotFoundException::new)
                .stream()
                .map(speedTestMapper::map)
                .toList();
    }
}

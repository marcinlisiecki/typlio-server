package com.example.typlioserver.speedtest;

import com.example.typlioserver.speedtest.dto.NewSpeedTestDto;
import com.example.typlioserver.speedtest.dto.SpeedTestDto;
import com.example.typlioserver.speedtest.exception.SpeedTestNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeedTestService {

    private final SpeedTestRepository speedTestRepository;
    private final SpeedTestMapper speedTestMapper;

    public SpeedTestDto createSpeedTest(NewSpeedTestDto newSpeedTestDto, Long userId) {
        SpeedTest speedTest = speedTestMapper.map(newSpeedTestDto, userId);
        return speedTestMapper.map(speedTestRepository.save(speedTest));
    }

    public SpeedTestDto findSpeedTest(Long speedTestId) {
        return speedTestRepository
                .findById(speedTestId)
                .map(speedTestMapper::map)
                .orElseThrow(SpeedTestNotFoundException::new);
    }

    public List<SpeedTestDto> findUserSpeedTests(Long userId) {
        return speedTestRepository
                .findAllByUserId(userId)
                .stream()
                .map(speedTestMapper::map)
                .toList();
    }
}

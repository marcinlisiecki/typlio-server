package com.example.typlioserver.speedtest;

import com.example.typlioserver.speedtest.dto.NewSpeedTestDto;
import com.example.typlioserver.speedtest.dto.SpeedTestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}")
public class SpeedTestController {

    private final SpeedTestService speedTestService;

    @PostMapping("/speed-tests")
    public SpeedTestDto createSpeedTest(@PathVariable Long userId, @RequestBody NewSpeedTestDto newSpeedTestDto) {
        return speedTestService.createSpeedTest(newSpeedTestDto, userId);
    }

    @GetMapping("/speed-tests")
    public List<SpeedTestDto> findUserSpeedTests(@PathVariable Long userId) {
        return speedTestService.findUserSpeedTests(userId);
    }

    @GetMapping("/speed-tests/{speedTestId}")
    public SpeedTestDto findUserSpeedTest(@PathVariable Long speedTestId, @PathVariable String userId) {
        return speedTestService.findSpeedTest(speedTestId);
    }
}

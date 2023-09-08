package com.example.typlioserver.user.controller;

import com.example.typlioserver.speedtest.dto.NewSpeedTestDto;
import com.example.typlioserver.speedtest.dto.SpeedTestDto;
import com.example.typlioserver.user.service.UserSpeedTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}/speed-tests")
public class UserSpeedTestController {

    private final UserSpeedTestService userSpeedTestService;

    @PostMapping
    public SpeedTestDto createSpeedTest(@PathVariable Long userId, @RequestBody NewSpeedTestDto newSpeedTestDto) {
        return userSpeedTestService.createSpeedTest(newSpeedTestDto, userId);
    }

    @GetMapping
    public List<SpeedTestDto> findUserSpeedTests(@PathVariable Long userId) {
        return userSpeedTestService.findUserSpeedTests(userId);
    }

    @GetMapping("/{speedTestId}")
    public SpeedTestDto findUserSpeedTest(@PathVariable Long speedTestId, @PathVariable Long userId) {
        return userSpeedTestService.findSpeedTest(speedTestId, userId);
    }
}

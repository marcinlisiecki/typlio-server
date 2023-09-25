package com.example.typlioserver.user.speedtest;

import com.example.typlioserver.common.dto.PagedResponse;
import com.example.typlioserver.speedtest.SpeedTestMode;
import com.example.typlioserver.speedtest.dto.NewSpeedTestDto;
import com.example.typlioserver.speedtest.dto.SpeedTestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}/speed-tests")
class UserSpeedTestController {

    private final UserSpeedTestService userSpeedTestService;

    @PostMapping
    SpeedTestDto createSpeedTest(@PathVariable Long userId, @RequestBody NewSpeedTestDto newSpeedTestDto) {
        return userSpeedTestService.createSpeedTest(newSpeedTestDto, userId);
    }

    @GetMapping
    PagedResponse<SpeedTestDto> findUserSpeedTests(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "-createdAt") String sortBy,
            @RequestParam(required = false) List<SpeedTestMode> modes) {

        return userSpeedTestService.findPagedUserSpeedTests(userId, page, size, sortBy, modes);
    }

    @GetMapping("/{speedTestId}")
    SpeedTestDto findUserSpeedTest(@PathVariable Long speedTestId, @PathVariable Long userId) {
        return userSpeedTestService.findSpeedTest(speedTestId, userId);
    }
}

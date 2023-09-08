package com.example.typlioserver.speedtest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/speed-tests")
public class SpeedTestController {

    private final SpeedTestService speedTestService;

    @GetMapping("/modes")
    public List<SpeedTestMode> getAvailableSpeedTestModes() {
        return speedTestService.getAvailableSpeedTestModes();
    }
}

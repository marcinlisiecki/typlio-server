package com.example.typlioserver.speedtest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeedTestService {

    public List<SpeedTestMode> getAvailableSpeedTestModes() {
        return Arrays.stream(SpeedTestMode.values()).toList();
    }
}

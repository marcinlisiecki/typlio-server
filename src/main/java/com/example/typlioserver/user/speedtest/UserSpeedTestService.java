package com.example.typlioserver.user.speedtest;

import com.example.typlioserver.common.dto.PagedResponse;
import com.example.typlioserver.speedtest.*;
import com.example.typlioserver.speedtest.dto.NewSpeedTestDto;
import com.example.typlioserver.speedtest.dto.SpeedTestDto;
import com.example.typlioserver.speedtest.exception.SpeedTestNotFoundException;
import com.example.typlioserver.user.UserRepository;
import com.example.typlioserver.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
class UserSpeedTestService {

    private final SpeedTestRepository speedTestRepository;
    private final SpeedTestMapper speedTestMapper;
    private final UserRepository userRepository;
    private final PagedSpeedTestMapper pagedSpeedTestMapper;

    SpeedTestDto createSpeedTest(NewSpeedTestDto newSpeedTestDto, Long userId) {
        SpeedTest speedTest = speedTestMapper.map(newSpeedTestDto, userId);
        return speedTestMapper.map(speedTestRepository.save(speedTest));
    }

    SpeedTestDto findSpeedTest(Long speedTestId, Long userId) {
        userRepository
                .findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return speedTestRepository
                .findById(speedTestId)
                .map(speedTestMapper::map)
                .orElseThrow(SpeedTestNotFoundException::new);
    }

    PagedResponse<SpeedTestDto> findPagedUserSpeedTests(
            Long userId,
            int page,
            int size,
            String sortBy,
            List<SpeedTestMode> modes) {

        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        if (modes == null) {
            modes = Arrays.asList(SpeedTestMode.values());
        }

        Sort.Direction sortDirection = Sort.Direction.ASC;
        if (sortBy != null && sortBy.startsWith("-")) {
            sortDirection = Sort.Direction.DESC;
            sortBy = sortBy.substring(1);
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        Page<SpeedTest> pagedSpeedTests = speedTestRepository.findByUserIdAndModeIn(userId, modes, pageable);

        return pagedSpeedTestMapper.map(pagedSpeedTests);
    }
}

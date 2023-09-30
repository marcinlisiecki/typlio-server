package com.example.typlioserver.speedtest;

import com.example.typlioserver.auth.exception.UserNotAuthenticatedException;
import com.example.typlioserver.common.dto.PagedResponse;
import com.example.typlioserver.speedtest.dto.NewSpeedTestDto;
import com.example.typlioserver.speedtest.dto.SpeedTestDto;
import com.example.typlioserver.user.User;
import com.example.typlioserver.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
                .keyErrorsCount(newSpeedTestDto.getKeyErrorsCount())
                .wpmHistory(newSpeedTestDto.getWpmHistory())
                .histogram(newSpeedTestDto.getHistogram())
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
                .keyErrorsCount(speedTest.getKeyErrorsCount())
                .time(speedTest.getTime())
                .userId(speedTest.getUser().getId())
                .wpmHistory(speedTest.getWpmHistory())
                .histogram(speedTest.getHistogram())
                .createdAt(speedTest.getCreatedAt().toString())
                .build();
    }

    public PagedResponse<SpeedTestDto> map(Page<SpeedTest> pagedSpeedTests) {
        PagedResponse<SpeedTestDto> pagedSpeedTestsDto = new PagedResponse<>();

        pagedSpeedTestsDto.setContent(pagedSpeedTests
                .getContent()
                .stream()
                .map(this::map)
                .toList());
        pagedSpeedTestsDto.setCurrentPage(pagedSpeedTests.getNumber());
        pagedSpeedTestsDto.setTotalPages(pagedSpeedTests.getTotalPages());
        pagedSpeedTestsDto.setTotalItems(pagedSpeedTests.getTotalElements());

        return pagedSpeedTestsDto;
    }
}

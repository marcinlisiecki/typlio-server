package com.example.typlioserver.speedtest;

import com.example.typlioserver.common.dto.PagedResponse;
import com.example.typlioserver.speedtest.dto.SpeedTestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PagedSpeedTestMapper {

    private final SpeedTestMapper speedTestMapper;

    public PagedResponse<SpeedTestDto> map(Page<SpeedTest> pagedSpeedTests) {
        PagedResponse<SpeedTestDto> pagedSpeedTestsDto = new PagedResponse<>();

        pagedSpeedTestsDto.setContent(pagedSpeedTests
                .getContent()
                .stream()
                .map(speedTestMapper::map)
                .toList());
        pagedSpeedTestsDto.setCurrentPage(pagedSpeedTests.getNumber());
        pagedSpeedTestsDto.setTotalPages(pagedSpeedTests.getTotalPages());
        pagedSpeedTestsDto.setTotalItems(pagedSpeedTests.getTotalElements());

        return pagedSpeedTestsDto;
    }
}

package com.example.typlioserver.speedtest.dto;

import com.example.typlioserver.speedtest.Histogram;
import com.example.typlioserver.speedtest.SpeedTestMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpeedTestDto {

    private Long id;
    private SpeedTestMode mode;
    private Integer time;
    private Integer cpm;
    private Integer keyErrorsCount;
    private Float accuracy;
    private Long userId;
    private List<Integer> wpmHistory;
    private List<Histogram> histogram;
    private String createdAt;
}

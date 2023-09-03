package com.example.typlioserver.speedtest.dto;

import com.example.typlioserver.speedtest.SpeedTestMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpeedTestDto {

    private Long id;
    private SpeedTestMode mode;
    private Integer time;
    private Integer cpm;
    private Integer mistakes;
    private Float accuracy;
    private Long userId;
}

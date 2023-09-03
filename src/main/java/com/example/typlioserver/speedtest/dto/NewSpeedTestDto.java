package com.example.typlioserver.speedtest.dto;

import com.example.typlioserver.speedtest.SpeedTestMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewSpeedTestDto {

    private SpeedTestMode mode;
    private Integer time;
    private Integer cpm;
    private Integer mistakes;
    private Float accuracy;
}

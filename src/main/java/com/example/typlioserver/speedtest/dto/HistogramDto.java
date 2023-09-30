package com.example.typlioserver.speedtest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistogramDto {

    private Long id;
    private Integer keyCode;
    private Integer hitCount;
    private Integer missCount;
}

package com.example.typlioserver.suggestion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SuggestionDto {

    private Long id;
    private String content;
}

package com.example.typlioserver.suggestion;

import com.example.typlioserver.suggestion.dto.NewSuggestionDto;
import com.example.typlioserver.suggestion.dto.SuggestionDto;
import org.springframework.stereotype.Component;

@Component
public class SuggestionMapper {

    Suggestion map(NewSuggestionDto newSuggestionDto) {
        return Suggestion
                .builder()
                .content(newSuggestionDto.getContent())
                .build();
    }

    SuggestionDto map(Suggestion suggestion) {
        return SuggestionDto
                .builder()
                .id(suggestion.getId())
                .content(suggestion.getContent())
                .build();
    }
}

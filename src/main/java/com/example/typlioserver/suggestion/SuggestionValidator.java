package com.example.typlioserver.suggestion;

import com.example.typlioserver.suggestion.exception.SuggestionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SuggestionValidator {

    private final SuggestionRepository suggestionRepository;

    void checkIfSuggestionExists(Long id) {
        suggestionRepository
                .findById(id)
                .orElseThrow(SuggestionNotFoundException::new);
    }
}

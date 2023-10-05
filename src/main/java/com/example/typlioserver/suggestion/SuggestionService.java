package com.example.typlioserver.suggestion;

import com.example.typlioserver.common.security.IsAdmin;
import com.example.typlioserver.suggestion.dto.NewSuggestionDto;
import com.example.typlioserver.suggestion.dto.SuggestionDto;
import com.example.typlioserver.suggestion.exception.SuggestionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuggestionService {

    private final SuggestionRepository suggestionRepository;
    private final SuggestionMapper suggestionMapper;
    private final SuggestionValidator suggestionValidator;

    SuggestionDto newSuggestion(NewSuggestionDto newSuggestionDto) {
        Suggestion suggestion = suggestionRepository.save(suggestionMapper.map(newSuggestionDto));
        return suggestionMapper.map(suggestion);
    }

    @IsAdmin
    SuggestionDto findSuggestion(Long id) {
        return suggestionRepository
                .findById(id)
                .map(suggestionMapper::map)
                .orElseThrow(SuggestionNotFoundException::new);
    }

    @IsAdmin
    List<SuggestionDto> findAllSuggestions() {
        return StreamUtils
                .createStreamFromIterator(suggestionRepository.findAll().iterator())
                .map(suggestionMapper::map)
                .toList();
    }

    @IsAdmin
    void deleteSuggestion(Long id) {
        suggestionValidator.checkIfSuggestionExists(id);
        suggestionRepository.deleteById(id);
    }
}

package com.example.typlioserver.suggestion;

import com.example.typlioserver.suggestion.dto.NewSuggestionDto;
import com.example.typlioserver.suggestion.dto.SuggestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/suggestions")
@RequiredArgsConstructor
public class SuggestionController {

    private final SuggestionService suggestionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SuggestionDto createSuggestion(@RequestBody NewSuggestionDto suggestion) {
        return suggestionService.newSuggestion(suggestion);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<SuggestionDto> findAllSuggestions() {
        return suggestionService.findAllSuggestions();
    }

    @GetMapping("/{suggestionId}")
    @ResponseStatus(HttpStatus.OK)
    SuggestionDto findSuggestion(@PathVariable Long suggestionId) {
        return suggestionService.findSuggestion(suggestionId);
    }
}

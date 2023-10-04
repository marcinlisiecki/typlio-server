package com.example.typlioserver.suggestion.exception;

import com.example.typlioserver.common.constants.ErrorMessages;

public class SuggestionNotFoundException extends RuntimeException {

    public SuggestionNotFoundException() {
        super(ErrorMessages.SUGGESTION_NOT_FOUND);
    }
}

package com.example.typlioserver.common.advice;

import com.example.typlioserver.common.dto.ErrorMessageDto;
import com.example.typlioserver.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({
            UserNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageDto handleResourceNotFoundException(Exception e) {
        return new ErrorMessageDto(e.getMessage());
    }
}

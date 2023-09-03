package com.example.typlioserver.auth;

import com.example.typlioserver.auth.exception.EmailAlreadyExistsException;
import com.example.typlioserver.auth.exception.UsernameAlreadyExistsException;
import com.example.typlioserver.common.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler({
            UsernameAlreadyExistsException.class,
            EmailAlreadyExistsException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageDto handleException(Exception e) {
        return new ErrorMessageDto(e.getMessage());
    }
}

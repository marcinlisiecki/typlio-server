package com.example.typlioserver.auth;

import com.example.typlioserver.auth.exception.EmailAlreadyExistsException;
import com.example.typlioserver.auth.exception.UserNotAuthenticatedException;
import com.example.typlioserver.auth.exception.UsernameAlreadyExistsException;
import com.example.typlioserver.common.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler({
            UsernameAlreadyExistsException.class,
            EmailAlreadyExistsException.class,
            BadCredentialsException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageDto handleBadRequestException(Exception e) {
        return new ErrorMessageDto(e.getMessage());
    }

    @ExceptionHandler({
            UserNotAuthenticatedException.class
    })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessageDto handleUnauthenticatedException(Exception e) {
        return new ErrorMessageDto(e.getMessage());
    }
}

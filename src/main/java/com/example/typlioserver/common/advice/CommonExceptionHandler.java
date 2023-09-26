package com.example.typlioserver.common.advice;

import com.example.typlioserver.common.dto.ErrorMessageDto;
import com.example.typlioserver.common.exception.InsufficientPermissionsException;
import com.example.typlioserver.speedtest.exception.SpeedTestNotFoundException;
import com.example.typlioserver.user.exception.UserNotFoundException;
import com.example.typlioserver.user.password.exception.IncorrectPasswordException;
import com.example.typlioserver.user.password.exception.PasswordResetTokenExpiredException;
import com.example.typlioserver.user.password.exception.PasswordResetTokenNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({
            UserNotFoundException.class,
            SpeedTestNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageDto handleResourceNotFoundException(Exception e) {
        return new ErrorMessageDto(e.getMessage());
    }

    @ExceptionHandler({
            InsufficientPermissionsException.class
    })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessageDto handleForbiddenException(Exception e) {
        return new ErrorMessageDto(e.getMessage());
    }

    @ExceptionHandler({
            PasswordResetTokenExpiredException.class,
            PasswordResetTokenNotFoundException.class,
            IncorrectPasswordException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageDto handleBadRequestException(Exception e) {
        return new ErrorMessageDto(e.getMessage());
    }
}

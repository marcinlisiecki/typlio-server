package com.example.typlioserver.user.common;

import com.example.typlioserver.common.constants.ErrorMessages;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super(ErrorMessages.USER_NOT_FOUND);
    }
}

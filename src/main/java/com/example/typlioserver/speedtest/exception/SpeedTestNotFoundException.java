package com.example.typlioserver.speedtest.exception;

import com.example.typlioserver.common.constants.ErrorMessages;

public class SpeedTestNotFoundException extends RuntimeException {

    public SpeedTestNotFoundException() {
        super(ErrorMessages.SPEED_TEST_NOT_FOUND);
    }
}

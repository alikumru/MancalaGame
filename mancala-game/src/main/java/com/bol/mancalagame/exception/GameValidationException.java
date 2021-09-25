package com.bol.mancalagame.exception;

import lombok.Getter;

@Getter
public class GameValidationException extends Exception {

    private String errorCode;

    public GameValidationException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}

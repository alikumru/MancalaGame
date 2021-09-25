package com.bol.mancalagame.response;

import lombok.Getter;

@Getter
public class ErrorHttpResponse {

    private String errorCode;
    private String errorMessage;

    public ErrorHttpResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}

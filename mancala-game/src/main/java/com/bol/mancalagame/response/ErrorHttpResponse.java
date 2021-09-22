package com.bol.mancalagame.response;

import lombok.Getter;

@Getter
public enum ErrorHttpResponse {

    ERROR101("Player Not Found"),
    ERROR102("It's not your turn"),
    ERROR103("Game is already over"),
    ERROR104("You can not play with empty pit"),
    ERROR105("Pit number is not correct");

    ErrorHttpResponse(String errorMessage) {
    }
}

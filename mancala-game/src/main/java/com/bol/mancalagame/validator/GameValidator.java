package com.bol.mancalagame.validator;

import com.bol.mancalagame.exception.GameValidationException;
import com.bol.mancalagame.model.Game;
import com.bol.mancalagame.response.ErrorHttpResponse;
import com.bol.mancalagame.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GameValidator {

    public boolean valitadateGame(GameService gameService, int playerId, int pitId) throws Exception {

        if (gameService == null) {
            throw new GameValidationException("Game is not created!", "1000");
        }

        Game game = gameService.getGame();
        // Be sure game is still active
        if (game.isOver()) {
            throw new GameValidationException("Game is already over!", "1001");
        }

        // Be sure if player's turn is correct
        if (game.getTurn() != playerId)
            throw new GameValidationException("Not your turn wait!", "1004");

        // Pit should be between 1-14
        if (pitId < 1 || pitId > 14)
            throw new GameValidationException("Unsupported number, pit number should be between 1-14 except 7 and 14!", "1002");

        // Pit number 7 and 14 are stores, not allowed to play
        if (pitId == 7 || pitId == 14)
            throw new GameValidationException("Stones can not be played", "1003");

        if (playerId == 1 && pitId > 7)
            throw new GameValidationException("Play your pit:)", "1005");

        if (playerId == 2 && pitId < 7)
            throw new GameValidationException("Play your pit:)", "1005");

        //If pit is empty return error
        if (game.getGameBoard().get(pitId) == 0)
            throw new GameValidationException("Pit is Emtpy", "1006");

        return true;
    }
}

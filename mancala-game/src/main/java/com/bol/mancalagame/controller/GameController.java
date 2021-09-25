package com.bol.mancalagame.controller;

import com.bol.mancalagame.exception.GameValidationException;
import com.bol.mancalagame.factory.GameBoardFactory;
import com.bol.mancalagame.factory.GameFactory;
import com.bol.mancalagame.factory.PlayerFactory;
import com.bol.mancalagame.model.Game;
import com.bol.mancalagame.publisher.GamePublisher;
import com.bol.mancalagame.response.ErrorHttpResponse;
import com.bol.mancalagame.service.GameService;
import com.bol.mancalagame.service.MultiPlayerGameService;
import com.bol.mancalagame.service.RegularGameService;
import com.bol.mancalagame.validator.GameValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(GameController.GAME_PATH)

public class GameController {

    public static final String GAME_PATH = "/api/game";
    public static Map<Integer, GameService> gamesMap = new HashMap<>();

    GamePublisher gamePublisher;
    GameValidator gameValidator;

    @Autowired
    public GameController(GamePublisher gamePublisher, GameValidator gameValidator) {
        this.gamePublisher = gamePublisher;
        this.gameValidator = gameValidator;
    }

    @GetMapping(path = "/regular-game-start", produces = MediaType.APPLICATION_JSON_VALUE)
    public int startRegularGame(@RequestParam("first-player-name") String firstPlayerName, @RequestParam("second-player-name") String secondPlayerName) throws Exception {
        RegularGameService regularGame = (RegularGameService) GameFactory.getGameService(2, "regular");
        int gameId = new Random().nextInt(100);

        //create a new game with gameData, gameId and double players
        Game game = new Game(gameId, PlayerFactory.createRegularPlayers(firstPlayerName, secondPlayerName));
        game.setTurn(1);
        // Get a new gameboard
        game.setGameBoard(GameBoardFactory.getNewGameBoard("regular"));
        // Put this board into the game
        regularGame.setGame(game);
        // Subscribe the game object to the game publisher
        gamePublisher.subscribe(regularGame);
        // Put this new game object into the game map to be able to get currect game and validate
        gamesMap.put(gameId, regularGame);
        return gameId;
    }

    @PutMapping(path = "/move-regular", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> moveRegularGame(@RequestParam("game-id") int gameId, @RequestParam("player") Integer playerId, @RequestParam("pit") Integer pitId) throws JsonProcessingException {
        // Get the game service from gamesMap
        RegularGameService regularGameService = (RegularGameService) gamesMap.get(gameId);

        // Be sure reqular game object is not null
        if (regularGameService == null) {
            return new ResponseEntity<>(new ErrorHttpResponse("1000", "Please start new game!"), HttpStatus.BAD_REQUEST);
        }
        // Get game object from game service
        Game game = regularGameService.getGame();

        try {
            gameValidator.valitadateGame(regularGameService, playerId, pitId);
            regularGameService.move(pitId, playerId, game);
        } catch (GameValidationException gameValidationException) {
            return new ResponseEntity<>(new ErrorHttpResponse(gameValidationException.getErrorCode(), gameValidationException.getMessage()),
                    HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorHttpResponse(e.getMessage(), "Error during playing game"),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Object>(game, HttpStatus.OK);
    }

    @GetMapping(path = "/multiplayer-game-start", produces = MediaType.APPLICATION_JSON_VALUE)
    public String startMultiplayerGame() {

        MultiPlayerGameService multiPlayerGameService = (MultiPlayerGameService) GameFactory.getGameService(2, "regular");
        gamePublisher.subscribe(multiPlayerGameService);
        return "Started Mancala Game";
    }

    @PutMapping(path = "/move-multiplayer", produces = MediaType.APPLICATION_JSON_VALUE)
    public String moveMultiplayerGame(@RequestParam("player") Integer playerId, @RequestParam("pit") Integer pitId) throws JsonProcessingException {

        MultiPlayerGameService multiPlayerGameService = (MultiPlayerGameService) gamesMap.get(0);
        String json = new ObjectMapper().writeValueAsString(multiPlayerGameService.move(playerId, pitId, null));
        return json;
    }

}

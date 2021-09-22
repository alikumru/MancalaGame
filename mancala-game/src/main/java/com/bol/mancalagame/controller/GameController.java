package com.bol.mancalagame.controller;

import com.bol.mancalagame.factory.GameBoardFactory;
import com.bol.mancalagame.factory.GameFactory;
import com.bol.mancalagame.factory.PlayerFactory;
import com.bol.mancalagame.model.Game;
import com.bol.mancalagame.publisher.GamePublisher;
import com.bol.mancalagame.response.ErrorHttpResponse;
import com.bol.mancalagame.service.GameService;
import com.bol.mancalagame.service.MultiPlayerGameService;
import com.bol.mancalagame.service.RegularGameService;
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

    @Autowired
    public GameController(GamePublisher gamePublisher) {
        this.gamePublisher = gamePublisher;
    }

    @GetMapping(path = "/regular-game-start", produces = MediaType.APPLICATION_JSON_VALUE)
    public String startRegularGame(String firstPlayerName, String secondPlayerName) throws Exception {
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
        return String.valueOf(gameId);
    }

    @PutMapping(path = "/move-regular", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> moveRegularGame(@RequestParam("game-id") int gameId, @RequestParam("player") Integer playerId, @RequestParam("pit") Integer pitId) throws JsonProcessingException {
        // Get the game service from gamesMap
        RegularGameService regularGameService = (RegularGameService) gamesMap.get(gameId);

        // Get game object from game service
        Game game = regularGameService.getGame();

        if (game.isOver())
            return new ResponseEntity<>(ErrorHttpResponse.ERROR103.toString(), HttpStatus.BAD_REQUEST);
        // Pit should be between 1-14
        if (pitId < 1 || pitId > 14)
            return new ResponseEntity<String>(ErrorHttpResponse.ERROR105.toString(), HttpStatus.BAD_REQUEST);
        // Pit number 7 and 14 are stores, not allowed to play
        if (pitId == 7 || pitId == 14)
            return new ResponseEntity<String>(ErrorHttpResponse.ERROR105.toString(), HttpStatus.BAD_REQUEST);

        // Be sure if player's turn is correct
        if (game.getTurn() != playerId)
            return new ResponseEntity<String>(ErrorHttpResponse.ERROR102.toString(), HttpStatus.BAD_REQUEST);


        //If pit is empty return error
        if (game.getGameBoard().get(pitId) == 0)
            return new ResponseEntity<String>(ErrorHttpResponse.ERROR104.toString(), HttpStatus.BAD_REQUEST);

        regularGameService.move(pitId, playerId, game);
//        String json = new ObjectMapper().writeValueAsString(regularGameService.move(playerId, pitId,game));
        return null;
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

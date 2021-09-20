package com.bol.mancalagame.controller;

import com.bol.mancalagame.factory.GameFactory;
import com.bol.mancalagame.model.GameData;
import com.bol.mancalagame.publisher.GamePublisher;
import com.bol.mancalagame.service.GameService;
import com.bol.mancalagame.service.MultiPlayerGameService;
import com.bol.mancalagame.service.RegularGameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(GameController.GAME_PATH)

public class GameController {

    public static final String GAME_PATH = "/api/game";
    public static Map<Integer, GameService> gamesMap = new HashMap<>();

    @Autowired
    GameFactory gameFactory;

    @Autowired
    GamePublisher gamePublisher;

    @GetMapping(path = "/regular-game-start", produces = MediaType.APPLICATION_JSON_VALUE)
    public String startRegularGame() {
        RegularGameService regularGame = (RegularGameService) GameFactory.getGameService(2,"regular");
        gamePublisher.subscribe(regularGame);

        return "Started Mancala Game";
    }

    @PutMapping(path = "/move-regular", produces = MediaType.APPLICATION_JSON_VALUE)
    public String moveRegularGame(@RequestParam("player") Integer playerId, @RequestParam("pit") Integer pitId) throws JsonProcessingException {

        RegularGameService regularGameService = (RegularGameService) gamesMap.get(0);
        String json = new ObjectMapper().writeValueAsString(regularGameService.move(playerId, pitId));
        return json;
    }

    @GetMapping(path = "/multiplayer-game-start", produces = MediaType.APPLICATION_JSON_VALUE)
    public String startMultiplayerGame() {

        MultiPlayerGameService multiPlayerGameService = (MultiPlayerGameService) GameFactory.getGameService(2,"regular");
        gamePublisher.subscribe(multiPlayerGameService);
        return "Started Mancala Game";
    }

    @PutMapping(path = "/move-multiplayer", produces = MediaType.APPLICATION_JSON_VALUE)
    public String moveMultiplayerGame(@RequestParam("player") Integer playerId, @RequestParam("pit") Integer pitId) throws JsonProcessingException {

        MultiPlayerGameService multiPlayerGameService = (MultiPlayerGameService) gamesMap.get(0);
        String json = new ObjectMapper().writeValueAsString(multiPlayerGameService.move(playerId, pitId));
        return json;
    }

}

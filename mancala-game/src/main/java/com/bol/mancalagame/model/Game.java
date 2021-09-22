package com.bol.mancalagame.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class Game {

    private int id;
    private List<Player> players;
    private boolean isOver;
    private int turn;
    private Map<Integer, Integer> gameBoard;
    private int winner = -1;
    private Map<Integer,Integer> score;

    public Game(int id, List<Player> players) {
        this.id = id;
        this.players = players;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public Map<Integer, Integer> getScore() {
        if(gameBoard.size()==0)
            return null;

        Map<Integer, Integer> scoreBoard = gameBoard.entrySet().stream()
                .filter(map -> (map.getKey()==7 || map.getKey() == 14))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
        return scoreBoard;
    }
}

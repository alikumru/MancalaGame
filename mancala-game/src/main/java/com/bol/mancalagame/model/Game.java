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
    private Map<Integer, Integer> score = new HashMap<>();

    public Game(int id, List<Player> players) {
        this.id = id;
        this.players = players;
        score.put(1, 0);
        score.put(2, 0);
    }

    public Map<Integer, Integer> getScore() {
        return score;
    }
}

package com.bol.mancalagame.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameData {

    private String data;
    private int id;
    private List<Player> players;
    private boolean isOver;
    private int turn;

    public GameData(String data, int id, List<Player> players) {
        this.data = data;
        this.id = id;
        this.players = players;
    }
}

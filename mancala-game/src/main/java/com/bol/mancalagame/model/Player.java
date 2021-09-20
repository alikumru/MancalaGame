package com.bol.mancalagame.model;

import lombok.Getter;
import lombok.Setter;
import java.util.concurrent.Flow;

@Getter
@Setter
public class Player {

    private String name;
    private int id;
    private int score;

    public Player(String name, int id, int score) {
        this.name = name;
        this.id = id;
        this.score = score;
    }
}

package com.bol.mancalagame.factory;

import com.bol.mancalagame.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {

    public static List<Player> createRegularPlayers(String firstPlayer,String secondPlayer){
        List<Player> playerList = new ArrayList<>();
        Player player1 = new Player(firstPlayer, 1, 0);
        Player player2 = new Player(secondPlayer, 2, 0);
        playerList.add(player1);
        playerList.add(player2);

        return playerList;
    }
}

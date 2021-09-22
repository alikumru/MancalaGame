package com.bol.mancalagame.service;

import com.bol.mancalagame.model.Game;
import com.bol.mancalagame.model.Player;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RegularGameService extends GameService {

    @Override
    public Map<Integer, Integer> move(int pit, int playerId, Game game) {
        System.out.print(playerId+"-"+pit+"-->\t");
        Map<Integer, Integer> gameBoard = game.getGameBoard();
        int stones = gameBoard.get(pit);
        int turn = game.getTurn();
        //Taking stones from the pit, pit will be empty
        gameBoard.put(pit, 0);

        while (stones > 0) {
            pit++;
            if (pit > 14) pit = 1;
            int stonesFromApposite = 0;
            if (playerId == 1 && pit == 14) {
                continue;
            }
            if (playerId == 2 && pit == 7) {
                continue;
            }

            //if last stone land player's and empty pit player gets stones from right apposite
            if (stones == 1 && gameBoard.get(pit) == 0) {
                stonesFromApposite = checkAppositePit(playerId,pit, gameBoard);
            }
            gameBoard.merge(pit, stonesFromApposite + 1, Integer::sum);
            stones--;
        }

        for(Map.Entry entry: gameBoard.entrySet()){
            System.out.print(entry.getValue()+"\t");
        }
        System.out.println();
        if (status(game)) {
            return game.getScore();
        }
        //If the player's last stone lands in his own big pit, he gets another turn.
        turn = checkTurn(playerId, pit, gameBoard);
        game.setTurn(turn);

        //If player's last stone goes to empty pit(but player's own pit) and other player has any stone d
        return gameBoard;
    }

    /*  Always when the last stone
           lands in an own empty pit, the player captures his own stone and all stones in the
           opposite pit (the other player’s pit) and puts them in his own (big or little?) pit.
    */
    private int checkAppositePit(int player,int pit, Map<Integer, Integer> gameBoard) {
        if (pit == 7 || pit == 14)
            return 0;
        if(player==1 && pit >7)
            return 0;
        if(player==2 && pit <7)
            return 0;
        return gameBoard.get(14 - pit);
    }

    @Override
    public boolean status(Game game) {
        Map<Integer, Integer> gameBoard = game.getGameBoard();

        Map<Integer, Integer> firstPlayerBoard = gameBoard.entrySet().stream()
                .filter(map -> (map.getKey() < 7 && 0 == map.getValue()))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
        List<Integer> secondPlayerBoard = gameBoard.entrySet().stream()
                .filter(map -> (map.getKey() > 7 && map.getKey() < 14 && 0 == map.getValue()))
                .map(map -> map.getValue())
                .collect(Collectors.toList());

        if (firstPlayerBoard.size() == 6 || secondPlayerBoard.size() == 6) {
            game.setOver(true);
        }
        return getGame().isOver();
    }

    @Override
    public List<Player> getPlayers() {
        return this.getGame().getPlayers();
    }

    @Override
    int checkTurn(int player, int lastPosition, Map<Integer, Integer> gameBoard) {

        if (lastPosition == 7 && player == 1) {
            return 1;
        } else if (lastPosition == 14 && player == 2) {
            return 2;
        }
        return (player == 1) ? 2 : 1;
    }

    @Override
    public void onNext(Game item) {
        System.out.println("Subscription request sended!");
        subscription.request(1);
    }
}

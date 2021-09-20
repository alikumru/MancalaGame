package com.bol.mancalagame.service;

import com.bol.mancalagame.model.GameData;
import com.bol.mancalagame.model.Player;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegularGameService extends GameService {

    //It stores key:id_of_pit and value:Number of stones in pit
    static Map<Integer, Integer> stonesMap = new HashMap<>();
    static Map<Integer, Integer> appositeMap = new HashMap<>();

    @PostConstruct
    public void createStones() {
        //keys: between 1-6 belongs to player 1,keys: between 8-13 belongs to player 2
        //key: number 7 is player 1's store
        //key: number 14 is player 2's store
        stonesMap.put(1, 4);
        stonesMap.put(2, 4);
        stonesMap.put(3, 4);
        stonesMap.put(4, 4);
        stonesMap.put(5, 4);
        stonesMap.put(6, 4);
        stonesMap.put(7, 0);
        stonesMap.put(8, 4);
        stonesMap.put(9, 4);
        stonesMap.put(10, 4);
        stonesMap.put(11, 4);
        stonesMap.put(12, 4);
        stonesMap.put(13, 4);
        stonesMap.put(14, 4);

        appositeMap.put(1, 13);
        appositeMap.put(2, 12);
        appositeMap.put(3, 11);
        appositeMap.put(4, 10);
        appositeMap.put(5, 9);
        appositeMap.put(6, 8);
        appositeMap.put(13, 1);
        appositeMap.put(12, 2);
        appositeMap.put(11, 3);
        appositeMap.put(10, 4);
        appositeMap.put(9, 5);
        appositeMap.put(8, 6);
    }

    @Override
    public Map<Integer, Integer> move(int pit, int playerId) {
        int turn = gameData.getTurn();
        if (playerId != turn) return null;

        int stones = stonesMap.get(pit);

        //Pit is already played, there is no stone on the pit
        if (stones == 0) return null;

        //Taking stones from the pit, pit will be empty
        stonesMap.put(pit, 0);

        while (stones > 0) {
            pit++;
            if (pit > 14) pit = 1;
            stonesMap.merge(pit, 1, Integer::sum);
            stones--;
        }

        //If last stone goes to player's own store, turn does not change
        turn = (pit == 7 && turn == 1) ? 1 : 2;
        turn = (pit == 14 && turn == 2) ? 2 : 1;

        //If player's last stone goes to empty pit(but player's own pit) and other player has any stone d
        if (pit == 14 && turn == turn) {
            turn = 1;
        }
        return stonesMap;
    }

    @Override
    public boolean status() {
        return gameData.isOver();
    }

    @Override
    public List<Player> getPlayers() {
        return gameData.getPlayers();
    }

    @Override
    public void onNext(GameData item) {
        System.out.println("Subscription request sended!");
        subscription.request(1);
    }
}

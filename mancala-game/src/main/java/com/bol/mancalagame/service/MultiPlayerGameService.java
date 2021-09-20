package com.bol.mancalagame.service;

import com.bol.mancalagame.model.GameData;
import com.bol.mancalagame.model.Player;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
public class MultiPlayerGameService extends GameService {

    @PostConstruct
    public void createStones() {

    }

    @Override
    public Map<Integer, Integer> move(int pit, int playerId) {
        return null;
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

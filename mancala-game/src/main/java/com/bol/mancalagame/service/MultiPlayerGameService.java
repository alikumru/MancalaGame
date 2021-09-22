package com.bol.mancalagame.service;

import com.bol.mancalagame.model.Game;
import com.bol.mancalagame.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MultiPlayerGameService extends GameService {

    @Override
    public Map<Integer, Integer> move(int pit, int playerId,Game game) {
        return null;
    }

    @Override
    public boolean status(Game game) {


        return getGame().isOver();
    }

    @Override
    public List<Player> getPlayers() {
        return getGame().getPlayers();
    }

    @Override
    int checkTurn(int player, int lastPosition, Map<Integer, Integer> stonesMap) {
        return 0;
    }

    @Override
    public void onNext(Game item) {
        System.out.println("Subscription request sended!");
        subscription.request(1);
    }
}

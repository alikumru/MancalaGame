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
    int checkTurn(int player, int lastPosition) {
        return 0;
    }

    @Override
    void calculateScore(Game game) {

    }

    @Override
    void clearBoard(Game game) {

    }

    @Override
    public void onNext(Game item) {
        subscription.request(1);
    }
}

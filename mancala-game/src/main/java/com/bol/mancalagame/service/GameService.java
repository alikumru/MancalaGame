package com.bol.mancalagame.service;

import com.bol.mancalagame.model.GameData;
import com.bol.mancalagame.model.Player;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow;

public abstract class GameService implements Flow.Subscriber<GameData> {

    Flow.Subscription subscription;
    GameData gameData;

    @PostConstruct
    abstract void createStones();

    abstract Map<Integer, Integer> move(int pit, int playerId);

    abstract public boolean status();

    abstract List<Player> getPlayers();

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(4);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error accured");
    }

    @Override
    public void onComplete() {
        System.out.println("Subscription successfull!");
    }
}

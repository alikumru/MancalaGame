package com.bol.mancalagame.service;

import com.bol.mancalagame.model.Game;
import com.bol.mancalagame.model.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow;

@Getter
@Setter
public abstract class GameService implements Flow.Subscriber<Game> {

    Flow.Subscription subscription;
    private Game game;

    abstract Map<Integer, Integer> move(int pit, int playerId, Game game);

    abstract int checkTurn(int player, int lastPosition);

    abstract void calculateScore(Game game);

    abstract void clearBoard(Game game);

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

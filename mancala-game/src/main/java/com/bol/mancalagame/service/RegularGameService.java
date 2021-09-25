package com.bol.mancalagame.service;

import com.bol.mancalagame.model.Game;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RegularGameService extends GameService {

    @Override
    public Map<Integer, Integer> move(int pit, int playerId, Game game) {
        Map<Integer, Integer> gameBoard = game.getGameBoard();
        int stones = gameBoard.get(pit);
        //Taking stones from the pit, pit will be empty
        gameBoard.put(pit, 0);

        while (stones > 0) {
            pit++;
            if (pit > 14) pit = 1;
            int stonesFromApposite = 0;

            // Dont put stone opponent's store
            if (playerId == 1 && pit == 14) {
                continue;
            }
            if (playerId == 2 && pit == 7) {
                continue;
            }
            stones--;

            if (stones == 0) {
                playLastStone(playerId, pit, gameBoard);
                break;
            }
            gameBoard.merge(pit, 1, Integer::sum);
        }
        calculateScore(game);
        //If the player's last stone lands in player's store, he gets another turn.
        game.setTurn(checkTurn(playerId, pit));

        return gameBoard;
    }

    private void playLastStone(int player, int pit, Map<Integer, Integer> gameBoard) {
        // If it is store and it is your just land the stone
        if (isStore(pit) && isYourSide(player, pit)) {
            gameBoard.merge(pit, 1, Integer::sum);
            return;
        }
        // If it is store, it means it is not your store. We checked it above,increase pit number
        if (isStore(pit)) {
            pit++;
            pit = pit > 14 ? 1 : pit;
        }

        // Now we are sure where to land the stone
        if (!isYourSide(player, pit)) {
            gameBoard.merge(pit, 1, Integer::sum);
            return;
        }

        //if last stone land player's and empty pit player gets stones from right apposite
        if (gameBoard.get(pit) == 0 && gameBoard.get(14 - pit) != 0) {
            gameBoard.merge(getStore(player), gameBoard.get(14 - pit) + 1, Integer::sum);
            gameBoard.put(pit, 0);
            gameBoard.put(14 - pit, 0);
            return;
        }
        gameBoard.merge(pit, 1, Integer::sum);
    }

    /*  Always when the last stone
           lands in an own empty pit, the player captures his own stone and all stones in the
           opposite pit (the other player’s pit) and puts them in his own (big or little?) pit.
    */
    private int checkAppositePit(int player, int pit, Map<Integer, Integer> gameBoard) {
        if (pit == 7 || pit == 14)
            return 0;
        if (player == 1 && pit > 7)
            return 0;
        if (player == 2 && pit < 7)
            return 0;

        int apposite = gameBoard.get(14 - pit);
        gameBoard.put(14 - pit, 0);
        return apposite;
    }

    private boolean isStore(int pit) {
        return (pit == 7 || pit == 14);
    }

    private int getStore(int player) {
        return player == 1 ? 7 : 14;
    }

    private boolean isYourSide(int player, int pit) {
        return (player == 1 && pit <= 7) || (player == 2 && pit > 7);
    }

    @Override
    int checkTurn(int player, int lastPosition) {

        if (lastPosition == 7 && player == 1) {
            return 1;
        } else if (lastPosition == 14 && player == 2) {
            return 2;
        }
        return (player == 1) ? 2 : 1;
    }

    @Override
    void calculateScore(Game game) {
        // Get Game Board
        Map<Integer, Integer> gameBoard = game.getGameBoard();

        // Sum player 1's pits
        int player1Score = gameBoard.entrySet().stream()
                .filter(s -> s.getKey() < 7)
                .map(s -> s.getValue())
                .mapToInt(Integer::valueOf).sum();

        // Sum player 2's pits
        int player2Score = gameBoard.entrySet().stream()
                .filter(s -> s.getKey() > 7 && s.getKey() < 14)
                .map(s -> s.getValue())
                .mapToInt(Integer::valueOf).sum();

        // if one of them's pit is empty game is over
        if (player1Score == 0 || player2Score == 0) {
            game.setOver(true);
            gameBoard.merge(7, player1Score, Integer::sum);
            gameBoard.merge(14, player2Score, Integer::sum);
            game.setWinner(gameBoard.get(1) > gameBoard.get(2) ? 1 : 2);
            clearBoard(game);
        }
    }

    @Override
    void clearBoard(Game game) {
        Map<Integer, Integer> gameBoard = game.getGameBoard();
        for (Map.Entry<Integer, Integer> entry : gameBoard.entrySet()) {
            if (entry.getKey() == 7)
                continue;
            if (entry.getKey() == 14)
                continue;
            entry.setValue(0);
        }
    }

    @Override
    public void onNext(Game item) {
        subscription.request(1);
    }
}

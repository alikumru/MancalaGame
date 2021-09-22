package com.bol.mancalagame.factory;

import java.util.HashMap;
import java.util.Map;

//Game board contains pits and stones
public class GameBoardFactory {

    public static Map<Integer, Integer> getNewGameBoard(String type) {
        Map<Integer, Integer> gameBoard = new HashMap<>();
        if (type.equals("regular")) {
            gameBoard.put(1, 6);
            gameBoard.put(2, 6);
            gameBoard.put(3, 6);
            gameBoard.put(4, 6);
            gameBoard.put(5, 6);
            gameBoard.put(6, 6);
            gameBoard.put(7, 0);
            gameBoard.put(8, 6);
            gameBoard.put(9, 6);
            gameBoard.put(10, 6);
            gameBoard.put(11, 6);
            gameBoard.put(12, 6);
            gameBoard.put(13, 6);
            gameBoard.put(14, 0);
            return gameBoard;
        } else {
            return null;
        }
    }
}

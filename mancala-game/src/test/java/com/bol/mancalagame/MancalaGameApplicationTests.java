package com.bol.mancalagame;

import com.bol.mancalagame.controller.GameController;
import com.bol.mancalagame.publisher.GamePublisher;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;


@RunWith(JUnit4.class)
public class MancalaGameApplicationTests {

    @InjectMocks
    private GameController gameController;

    private GamePublisher gamePublisher;

    private int gameId;

    @Before
    public void setup() throws Exception {
        this.gamePublisher = new GamePublisher();
        this.gameController = new GameController(gamePublisher);
        this.gameId = Integer.valueOf(gameController.startRegularGame("player1","player2"));
    }

    @Test
    public void fullGameTest() throws Exception{
        gameController.moveRegularGame(gameId,1,1);
        gameController.moveRegularGame(gameId,1,2);

        gameController.moveRegularGame(gameId,2,8);

        gameController.moveRegularGame(gameId,1,6);

        gameController.moveRegularGame(gameId,2,9);

        gameController.moveRegularGame(gameId,1,4);

        gameController.moveRegularGame(gameId,2,12);

        gameController.moveRegularGame(gameId,1,1);

        gameController.moveRegularGame(gameId,2,13);

        gameController.moveRegularGame(gameId,1,2);

        gameController.moveRegularGame(gameId,2,11);

        gameController.moveRegularGame(gameId,1,6);

        gameController.moveRegularGame(gameId,2,9);
        gameController.moveRegularGame(gameId,2,12);
        gameController.moveRegularGame(gameId,2,8);
        gameController.moveRegularGame(gameId,2,11);
        gameController.moveRegularGame(gameId,2,12);
        gameController.moveRegularGame(gameId,2,10);

        gameController.moveRegularGame(gameId,1,5);
        gameController.moveRegularGame(gameId,1,3);

        gameController.moveRegularGame(gameId,2,13);

        gameController.moveRegularGame(gameId,1,2);
        gameController.moveRegularGame(gameId,1,1);
        gameController.moveRegularGame(gameId,1,3);
        gameController.moveRegularGame(gameId,1,4);

        gameController.moveRegularGame(gameId,2,13);
        gameController.moveRegularGame(gameId,2,8);

        gameController.moveRegularGame(gameId,1,5);

        gameController.moveRegularGame(gameId,2,13);
        gameController.moveRegularGame(gameId,2,9);

        gameController.moveRegularGame(gameId,1,6);

        gameController.moveRegularGame(gameId,2,11);

        gameController.moveRegularGame(gameId,1,6);
        gameController.moveRegularGame(gameId,1,3);
        gameController.moveRegularGame(gameId,1,6);
        gameController.moveRegularGame(gameId,1,5);
        gameController.moveRegularGame(gameId,1,6);
        gameController.moveRegularGame(gameId,1,2);
        gameController.moveRegularGame(gameId,1,6);
        gameController.moveRegularGame(gameId,1,4);
        gameController.moveRegularGame(gameId,1,6);
        gameController.moveRegularGame(gameId,1,5);
        gameController.moveRegularGame(gameId,1,6);
        gameController.moveRegularGame(gameId,1,1);

        gameController.moveRegularGame(gameId,2,12);

        gameController.moveRegularGame(gameId,1,6);
        gameController.moveRegularGame(gameId,1,3);

        gameController.moveRegularGame(gameId,2,9);

        gameController.moveRegularGame(gameId,1,5);
        gameController.moveRegularGame(gameId,1,6);
        gameController.moveRegularGame(gameId,1,2);

        gameController.moveRegularGame(gameId,2,13);

        gameController.moveRegularGame(gameId,1,2);

    }


}

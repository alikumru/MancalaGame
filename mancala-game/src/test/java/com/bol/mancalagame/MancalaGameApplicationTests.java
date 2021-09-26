package com.bol.mancalagame;

import com.bol.mancalagame.controller.GameController;
import com.bol.mancalagame.model.Game;
import com.bol.mancalagame.publisher.GamePublisher;
import com.bol.mancalagame.response.ErrorHttpResponse;
import com.bol.mancalagame.service.MultiPlayerGameService;
import com.bol.mancalagame.validator.GameValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;


@RunWith(JUnit4.class)
public class MancalaGameApplicationTests {

    @InjectMocks
    private GameController gameController;

    private GamePublisher gamePublisher;
    private GameValidator gameValidator;

    private int gameId;

    @Before
    public void setup() throws Exception {
        this.gamePublisher = new GamePublisher();
        this.gameValidator = new GameValidator();
        this.gameController = new GameController(gamePublisher, gameValidator);
        this.gameId = Integer.valueOf(gameController.startRegularGame("player1", "player2"));
    }

    @Test
    public void testCreateNewRegularGame() throws Exception {
        assertNotNull(gameId);
        assertNotNull(gameController);
        assertNotNull(gamePublisher);
    }

    @Test
    public void testCreateNewMultiplayerGame() throws Exception {
        ResponseEntity<Object> entityObj = gameController.startMultiplayerGame();
        assertNotNull(entityObj);
        assertThat(entityObj.getBody(), instanceOf(MultiPlayerGameService.class));
    }

    @Test
    public void testFullGamePlayer1Wins() throws Exception {
        gameController.moveRegularGame(gameId, 1, 1);
        gameController.moveRegularGame(gameId, 1, 2);
        gameController.moveRegularGame(gameId, 2, 8);
        gameController.moveRegularGame(gameId, 1, 6);
        gameController.moveRegularGame(gameId, 2, 9);
        gameController.moveRegularGame(gameId, 1, 4);
        gameController.moveRegularGame(gameId, 2, 12);
        gameController.moveRegularGame(gameId, 1, 1);
        gameController.moveRegularGame(gameId, 2, 13);
        gameController.moveRegularGame(gameId, 1, 2);
        gameController.moveRegularGame(gameId, 2, 11);
        gameController.moveRegularGame(gameId, 1, 6);

        gameController.moveRegularGame(gameId, 2, 9);
        gameController.moveRegularGame(gameId, 2, 12);
        gameController.moveRegularGame(gameId, 2, 8);
        gameController.moveRegularGame(gameId, 2, 11);
        gameController.moveRegularGame(gameId, 2, 12);
        gameController.moveRegularGame(gameId, 2, 10);
        gameController.moveRegularGame(gameId, 1, 5);
        gameController.moveRegularGame(gameId, 1, 3);

        gameController.moveRegularGame(gameId, 2, 13);
        gameController.moveRegularGame(gameId, 1, 2);
        gameController.moveRegularGame(gameId, 1, 1);
        gameController.moveRegularGame(gameId, 1, 3);
        gameController.moveRegularGame(gameId, 1, 4);

        gameController.moveRegularGame(gameId, 2, 13);
        gameController.moveRegularGame(gameId, 2, 8);
        gameController.moveRegularGame(gameId, 1, 5);

        gameController.moveRegularGame(gameId, 2, 13);
        gameController.moveRegularGame(gameId, 2, 9);
        gameController.moveRegularGame(gameId, 1, 6);
        gameController.moveRegularGame(gameId, 2, 11);
        gameController.moveRegularGame(gameId, 1, 6);
        gameController.moveRegularGame(gameId, 1, 3);
        gameController.moveRegularGame(gameId, 1, 6);
        gameController.moveRegularGame(gameId, 1, 5);
        gameController.moveRegularGame(gameId, 1, 6);
        gameController.moveRegularGame(gameId, 1, 2);


        gameController.moveRegularGame(gameId, 1, 6);
        gameController.moveRegularGame(gameId, 1, 4);
        gameController.moveRegularGame(gameId, 1, 6);
        gameController.moveRegularGame(gameId, 1, 5);
        gameController.moveRegularGame(gameId, 1, 6);
        gameController.moveRegularGame(gameId, 1, 1);

        gameController.moveRegularGame(gameId, 2, 12);
        gameController.moveRegularGame(gameId, 1, 6);
        gameController.moveRegularGame(gameId, 1, 3);
        gameController.moveRegularGame(gameId, 2, 9);
        gameController.moveRegularGame(gameId, 1, 5);
        gameController.moveRegularGame(gameId, 1, 6);
        gameController.moveRegularGame(gameId, 1, 2);
        gameController.moveRegularGame(gameId, 1, 13);
        //24-42 current score
        //24-48 final score
        ResponseEntity<Object> response = gameController.moveRegularGame(gameId, 2, 13);
        Game game = (Game) response.getBody();
        int player1Score = game.getScore().get(1);
        int player2Score = game.getScore().get(2);
        assertEquals(player1Score, 48);
        assertEquals(player2Score, 24);
    }

    @Test
    public void testFullGamePlayer2Wins() throws Exception {
        gameController.moveRegularGame(gameId, 1, 3);
        gameController.moveRegularGame(gameId, 2, 11);
        gameController.moveRegularGame(gameId, 1, 2);
        gameController.moveRegularGame(gameId, 2, 10);
        gameController.moveRegularGame(gameId, 1, 6);
        gameController.moveRegularGame(gameId, 2, 13);

        gameController.moveRegularGame(gameId, 1, 5);
        gameController.moveRegularGame(gameId, 2, 12);
        gameController.moveRegularGame(gameId, 1, 4);
        gameController.moveRegularGame(gameId, 2, 8);
        gameController.moveRegularGame(gameId, 1, 1);
        gameController.moveRegularGame(gameId, 2, 9);

        gameController.moveRegularGame(gameId, 1, 4);
        gameController.moveRegularGame(gameId, 1, 5);
        gameController.moveRegularGame(gameId, 2, 11);
        gameController.moveRegularGame(gameId, 1, 3);
        gameController.moveRegularGame(gameId, 2, 10);
        gameController.moveRegularGame(gameId, 1, 4);
        gameController.moveRegularGame(gameId, 1, 2);

        gameController.moveRegularGame(gameId, 2, 11);
        gameController.moveRegularGame(gameId, 2, 13);
        gameController.moveRegularGame(gameId, 1, 2);
        gameController.moveRegularGame(gameId, 2, 9);
        gameController.moveRegularGame(gameId, 2, 8);
        gameController.moveRegularGame(gameId, 2, 10);

        gameController.moveRegularGame(gameId, 1, 3);
        gameController.moveRegularGame(gameId, 1, 4);
        gameController.moveRegularGame(gameId, 1, 5);
        gameController.moveRegularGame(gameId, 2, 11);

        gameController.moveRegularGame(gameId, 1, 1);
        gameController.moveRegularGame(gameId, 1, 6);
        gameController.moveRegularGame(gameId, 2, 12);
        gameController.moveRegularGame(gameId, 1, 6);
        gameController.moveRegularGame(gameId, 1, 4);
        gameController.moveRegularGame(gameId, 1, 6);
        gameController.moveRegularGame(gameId, 1, 3);

        gameController.moveRegularGame(gameId, 2, 13);
        gameController.moveRegularGame(gameId, 1, 6);
        gameController.moveRegularGame(gameId, 1, 5);
        gameController.moveRegularGame(gameId, 2, 11);
        gameController.moveRegularGame(gameId, 2, 13);
        gameController.moveRegularGame(gameId, 2, 10);

        gameController.moveRegularGame(gameId, 2, 13);
        gameController.moveRegularGame(gameId, 2, 12);
        gameController.moveRegularGame(gameId, 2, 13);
        gameController.moveRegularGame(gameId, 2, 9);
        gameController.moveRegularGame(gameId, 2, 13);
        gameController.moveRegularGame(gameId, 2, 12);

        gameController.moveRegularGame(gameId, 1, 6);
        gameController.moveRegularGame(gameId, 1, 3);
        gameController.moveRegularGame(gameId, 2, 8);
        gameController.moveRegularGame(gameId, 1, 4);
        gameController.moveRegularGame(gameId, 1, 6);
        gameController.moveRegularGame(gameId, 1, 5);

        gameController.moveRegularGame(gameId, 2, 10);
        gameController.moveRegularGame(gameId, 1, 6);
        ResponseEntity<Object> response = gameController.moveRegularGame(gameId, 1, 2);
        Game game = (Game) response.getBody();
        int player1Score = game.getScore().get(1);
        int player2Score = game.getScore().get(2);
        assertEquals(player1Score, 34);
        assertEquals(player2Score, 38);
    }

    @Test
    public void testNotYourTurnForPlayer1() throws Exception {
        gameController.moveRegularGame(gameId, 1, 1);
        gameController.moveRegularGame(gameId, 1, 3);
        gameController.moveRegularGame(gameId, 2, 11);
        gameController.moveRegularGame(gameId, 1, 4);
        gameController.moveRegularGame(gameId, 2, 8);
        gameController.moveRegularGame(gameId, 1, 1);
        gameController.moveRegularGame(gameId, 2, 10);
        gameController.moveRegularGame(gameId, 1, 3);
        gameController.moveRegularGame(gameId, 2, 11);
        ResponseEntity<Object> response = gameController.moveRegularGame(gameId, 1, 2);
        assertNotNull(response);
        assertThat(response.getBody(), instanceOf(ErrorHttpResponse.class));
        ErrorHttpResponse errorHttpResponse = (ErrorHttpResponse) response.getBody();
        assertEquals(errorHttpResponse.getErrorMessage(), "Not your turn wait!");
        assertEquals(errorHttpResponse.getErrorCode(), "1004");
    }

    @Test
    public void testNotYourTurnForPlayer2() throws Exception {
        gameController.moveRegularGame(gameId, 1, 3);
        gameController.moveRegularGame(gameId, 2, 12);
        gameController.moveRegularGame(gameId, 1, 2);
        gameController.moveRegularGame(gameId, 2, 13);
        gameController.moveRegularGame(gameId, 1, 2);
        gameController.moveRegularGame(gameId, 2, 10);
        gameController.moveRegularGame(gameId, 1, 3);
        ResponseEntity<Object> response = gameController.moveRegularGame(gameId, 2, 11);
        assertNotNull(response);
        assertThat(response.getBody(), instanceOf(ErrorHttpResponse.class));
        ErrorHttpResponse errorHttpResponse = (ErrorHttpResponse) response.getBody();
        assertEquals(errorHttpResponse.getErrorMessage(), "Not your turn wait!");
        assertEquals(errorHttpResponse.getErrorCode(), "1004");
    }

    @Test
    public void testEmptyPit() throws Exception {
        gameController.moveRegularGame(gameId, 1, 3);
        gameController.moveRegularGame(gameId, 2, 12);
        gameController.moveRegularGame(gameId, 1, 2);
        gameController.moveRegularGame(gameId, 2, 13);
        gameController.moveRegularGame(gameId, 1, 2);
        gameController.moveRegularGame(gameId, 2, 10);
        gameController.moveRegularGame(gameId, 1, 3);
        ResponseEntity<Object> response = gameController.moveRegularGame(gameId, 1, 3);
        assertNotNull(response);
        assertThat(response.getBody(), instanceOf(ErrorHttpResponse.class));
        ErrorHttpResponse errorHttpResponse = (ErrorHttpResponse) response.getBody();
        assertEquals(errorHttpResponse.getErrorMessage(), "Pit is Emtpy");
        assertEquals(errorHttpResponse.getErrorCode(), "1006");
    }

    @Test
    public void testInvalidPitNumberGreaterThan14() throws Exception {
        ResponseEntity<Object> response = gameController.moveRegularGame(gameId, 1, 15);
        assertNotNull(response);
        assertThat(response.getBody(), instanceOf(ErrorHttpResponse.class));
        ErrorHttpResponse errorHttpResponse = (ErrorHttpResponse) response.getBody();
        assertEquals(errorHttpResponse.getErrorMessage(), "Unsupported number, pit number should be between 1-14 except 7 and 14!");
        assertEquals(errorHttpResponse.getErrorCode(), "1002");
    }

    @Test
    public void testInvalidPitNumberLessThan1() throws Exception {
        ResponseEntity<Object> response = gameController.moveRegularGame(gameId, 1, 0);
        assertNotNull(response);
        assertThat(response.getBody(), instanceOf(ErrorHttpResponse.class));
        ErrorHttpResponse errorHttpResponse = (ErrorHttpResponse) response.getBody();
        assertEquals(errorHttpResponse.getErrorMessage(), "Unsupported number, pit number should be between 1-14 except 7 and 14!");
        assertEquals(errorHttpResponse.getErrorCode(), "1002");
    }

    @Test
    public void testInvalidPitNumberEquals7() throws Exception {
        ResponseEntity<Object> response = gameController.moveRegularGame(gameId, 1, 7);
        assertNotNull(response);
        assertThat(response.getBody(), instanceOf(ErrorHttpResponse.class));
        ErrorHttpResponse errorHttpResponse = (ErrorHttpResponse) response.getBody();
        assertEquals(errorHttpResponse.getErrorMessage(), "Stones can not be played");
        assertEquals(errorHttpResponse.getErrorCode(), "1003");
    }

    @Test
    public void testInvalidPitNumberEquals14() throws Exception {
        ResponseEntity<Object> response = gameController.moveRegularGame(gameId, 1, 14);
        assertNotNull(response);
        assertThat(response.getBody(), instanceOf(ErrorHttpResponse.class));
        ErrorHttpResponse errorHttpResponse = (ErrorHttpResponse) response.getBody();
        assertEquals(errorHttpResponse.getErrorMessage(), "Stones can not be played");
        assertEquals(errorHttpResponse.getErrorCode(), "1003");
    }

    @Test
    public void testNotYourPitWithPlayer1() throws Exception {
        ResponseEntity<Object> response = gameController.moveRegularGame(gameId, 1, 8);
        assertNotNull(response);
        assertThat(response.getBody(), instanceOf(ErrorHttpResponse.class));
        ErrorHttpResponse errorHttpResponse = (ErrorHttpResponse) response.getBody();
        assertEquals(errorHttpResponse.getErrorMessage(), "Play your pit:)");
        assertEquals(errorHttpResponse.getErrorCode(), "1005");

        response = gameController.moveRegularGame(gameId, 1, 13);
        assertNotNull(response);
        assertThat(response.getBody(), instanceOf(ErrorHttpResponse.class));
        errorHttpResponse = (ErrorHttpResponse) response.getBody();
        assertEquals(errorHttpResponse.getErrorMessage(), "Play your pit:)");
        assertEquals(errorHttpResponse.getErrorCode(), "1005");
    }

    @Test
    public void testNotYourPitWithPlayer2() throws Exception {
        gameController.moveRegularGame(gameId, 1, 2);
        ResponseEntity<Object> response = gameController.moveRegularGame(gameId, 2, 2);
        assertNotNull(response);
        assertThat(response.getBody(), instanceOf(ErrorHttpResponse.class));
        ErrorHttpResponse errorHttpResponse = (ErrorHttpResponse) response.getBody();
        assertEquals(errorHttpResponse.getErrorMessage(), "Play your pit:)");
        assertEquals(errorHttpResponse.getErrorCode(), "1005");

        response = gameController.moveRegularGame(gameId, 2, 4);
        assertNotNull(response);
        assertThat(response.getBody(), instanceOf(ErrorHttpResponse.class));
        errorHttpResponse = (ErrorHttpResponse) response.getBody();
        assertEquals(errorHttpResponse.getErrorMessage(), "Play your pit:)");
        assertEquals(errorHttpResponse.getErrorCode(), "1005");
    }

    @Test
    public void testCurrentScore() throws Exception {
        gameController.moveRegularGame(gameId, 1, 5);
        gameController.moveRegularGame(gameId, 2, 11);
        gameController.moveRegularGame(gameId, 1, 4);
        gameController.moveRegularGame(gameId, 2, 8);
        gameController.moveRegularGame(gameId, 1, 3);
        gameController.moveRegularGame(gameId, 2, 10);
        gameController.moveRegularGame(gameId, 1, 5);

        gameController.moveRegularGame(gameId, 2, 11);
        gameController.moveRegularGame(gameId, 2, 8);
        gameController.moveRegularGame(gameId, 1, 6);
        gameController.moveRegularGame(gameId, 2, 8);
        ResponseEntity<Object> response = gameController.moveRegularGame(gameId, 1, 3);
        Game game = (Game) response.getBody();
        int player1Score = game.getScore().get(1);
        int player2Score = game.getScore().get(2);
        assertEquals(player1Score, 21);
        assertEquals(player2Score, 7);
    }

    @Test
    public void testCurrentPitsStatus() throws Exception {
        gameController.moveRegularGame(gameId, 1, 1);
        gameController.moveRegularGame(gameId, 1, 2);
        gameController.moveRegularGame(gameId, 2, 10);
        gameController.moveRegularGame(gameId, 1, 3);
        gameController.moveRegularGame(gameId, 2, 9);
        gameController.moveRegularGame(gameId, 1, 4);
        ResponseEntity<Object> response = gameController.moveRegularGame(gameId, 2, 11);
        Game game = (Game) response.getBody();
        Map<Integer,Integer> gameBoard = game.getGameBoard();
        assertEquals((int)gameBoard.get(1),3);
        assertEquals((int)gameBoard.get(2),3);
        assertEquals((int)gameBoard.get(3),2);
        assertEquals((int)gameBoard.get(4),1);
        assertEquals((int)gameBoard.get(5),11);
        assertEquals((int)gameBoard.get(6),11);
        assertEquals((int)gameBoard.get(7),4);
        assertEquals((int)gameBoard.get(8),10);
        assertEquals((int)gameBoard.get(9),1);
        assertEquals((int)gameBoard.get(10),3);
        assertEquals((int)gameBoard.get(11),0);
        assertEquals((int)gameBoard.get(12),10);
        assertEquals((int)gameBoard.get(13),10);
        assertEquals((int)gameBoard.get(14),3);
    }

    @Test
    public void testGetAppositePitForPlayer1() throws Exception {
        gameController.moveRegularGame(gameId, 1, 1);
        gameController.moveRegularGame(gameId, 1, 2);
        gameController.moveRegularGame(gameId, 2, 8);
        ResponseEntity<Object> response = gameController.moveRegularGame(gameId, 1, 1);
        Game game = (Game) response.getBody();
        Map<Integer,Integer> gameBoard = game.getGameBoard();
        assertEquals((int)gameBoard.get(2),0);
        assertEquals((int)gameBoard.get(12),0);
        assertEquals((int)gameBoard.get(7),10);
        assertEquals((int)gameBoard.get(14),1);
    }

    @Test
    public void testGetAppositePitForPlayer2() throws Exception {
        gameController.moveRegularGame(gameId, 1, 1);
        gameController.moveRegularGame(gameId, 1, 2);
        gameController.moveRegularGame(gameId, 2, 8);
        gameController.moveRegularGame(gameId, 1, 1);
        gameController.moveRegularGame(gameId, 2, 9);
        gameController.moveRegularGame(gameId, 1, 3);
        gameController.moveRegularGame(gameId, 2, 10);
        gameController.moveRegularGame(gameId, 1, 3);
        ResponseEntity<Object> response = gameController.moveRegularGame(gameId, 2, 9);
        Game game = (Game) response.getBody();
        Map<Integer,Integer> gameBoard = game.getGameBoard();
        assertEquals((int)gameBoard.get(4),0);
        assertEquals((int)gameBoard.get(10),0);
        assertEquals((int)gameBoard.get(7),11);
        assertEquals((int)gameBoard.get(14),15);
    }

}

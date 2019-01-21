package Model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @Test
    void getCurrentPlayer() {

        // Tester getCurrentPlayer, setCurrentPlayer, getTurn


        GameState gameState = new GameState();

        Player[] players = new Player[3];



        for(int i = 0; i < players.length ; i++) {
            gameState.setCurrentPlayer(players[i]);
            Assert.assertEquals(gameState.getCurrentPlayer(),players[i]);
            Assert.assertEquals(gameState.getTurn(), gameState.getCurrentPlayer());
        }

    }
    @Test
    void getBoard() {
    }

    @Test
    void setBoard() {
    }
}
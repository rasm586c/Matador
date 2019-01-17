package Model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @Test
    void getCurrentPlayer() {

        // Tester getCurrentPlayer og setCurrentPlayer


        GameState gameState = new GameState();

        Player[] players = new Player[3];


        // tester at hvis man har en Array at spiller, benytte metoderne getCurrentPlayer og setCurrentplayer.
        for(int i = 0; i < players.length ; i++) {
            gameState.setCurrentPlayer(players[i]);
            Assert.assertEquals(gameState.getCurrentPlayer(),players[i]);
            Assert.assertEquals(gameState.getTurn(), gameState.getCurrentPlayer());
        }


                

    }

    @Test
    void setPlayers() {
    }

    @Test
    void getPlayers() {
    }

    @Test
    void setTurn() {
    }

    @Test
    void getTurn() {
    }

    @Test
    void getBoard() {
    }

    @Test
    void setBoard() {
    }
}
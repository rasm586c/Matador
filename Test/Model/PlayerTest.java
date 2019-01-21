package Model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getName() {

        Player player = new Player("hest", PlayerType.UFO, Color.BLACK);


        //tester getName funktionen.
        Assert.assertEquals("hest", player.getName());
        //tester getPlayerType funktionen.
        Assert.assertEquals(PlayerType.UFO, player.getPlayerType());

        // tester get og set Position.
        player.setPosition(3);
        Assert.assertEquals(3, player.getPosition());

        // Jailturns test
        player.setJailedTurns(2);
        Assert.assertEquals(2, player.getJailedTurns());

        // Bankrupt test
        player.setBankrupt(true);
        Assert.assertTrue(player.getBankrupt());

        // Player clone test.
        Player hans = player.clone();
        Assert.assertEquals(hans, player);


    }

    @Test
    void equals() {
    }

    @Test
    void clampPosition() {
    }
}
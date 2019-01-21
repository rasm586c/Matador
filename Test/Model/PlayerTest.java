package Model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getName() {

        Player player = new Player("hest", PlayerType.UFO, Color.BLACK);

        Assert.assertEquals("hest", player.getName());

    }
    @Test
    void testPlayertype() {
        Player player = new Player("hest", PlayerType.UFO, Color.BLACK);

        Assert.assertEquals(PlayerType.UFO, player.getPlayerType());

    }
   @Test
   void testPosition() {
       Player player = new Player("hest", PlayerType.UFO, Color.BLACK);

       player.setPosition(3);
       Assert.assertEquals(3, player.getPosition());

    }
   @Test
   void testJailTurns() {
       Player player = new Player("hest", PlayerType.UFO, Color.BLACK);

       player.setJailedTurns(2);
       Assert.assertEquals(2, player.getJailedTurns());

   }
   @Test
    void testBankRupt() {
       Player player = new Player("hest", PlayerType.UFO, Color.BLACK);

       player.setBankrupt(true);
       Assert.assertTrue(player.getBankrupt());

   }
   @Test
    void testClone() {
       Player player = new Player("hest", PlayerType.UFO, Color.BLACK);

       Player hans = player.clone();
       Assert.assertEquals(hans, player);

   }

}
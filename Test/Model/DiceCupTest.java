package Model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceCupTest {

    @Test
    void shake() {
        DiceCup cup = new DiceCup(new Die(6), new Die(6));

        int[] rolls = cup.shake();

        // tjekker at der kun bliver lavet 2 rolls.
        Assert.assertEquals(rolls.length,2 );

        // tester at alle rolls er større end 0 og mindre end 6, og tjekker at summen af rollene er mindre end eller = 12
        Assert.assertTrue(rolls[0] <= 6);
        Assert.assertTrue(rolls[1] <= 6);
        Assert.assertTrue(rolls[0] > 0);
        Assert.assertTrue(rolls[1] > 0);

        // tjekker at summen er imellem 2-12
        Assert.assertTrue(cup.getDiceSum() <= 12);
        Assert.assertTrue(cup.getDiceSum() > 2);

        //tester isEqual metoden.

        if(cup.isEqual()){
        Assert.assertTrue(rolls[1]-rolls[2] == 0);
        }

    }

}

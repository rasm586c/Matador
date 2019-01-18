package Model;

import Model.Die;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {
    /**
     * Unit-test of Roll method. creates a die and do 60000 rolls, it checks if the dice value is between 1-6 and makes
     * sure the die dosn't rolls the same value 50 times in a row.
     */

    @Test
    void roll() {

        Die dice = new Die(6);

        int maxSameRollsInARow = 0;

        int lastThrow = -1;

        int sameRollInRow = 0;

        int[] rollOff = new int[6];

        for (int i = 0; i < 60000; i++) {
            int diceValue;
            diceValue = dice.roll();

            Assert.assertTrue("Over 50 slag i streg!",sameRollInRow < 50);


            if (lastThrow == diceValue) {
                sameRollInRow++;
            } else {
                Assert.assertTrue(diceValue <= 6 && diceValue > 0);

                lastThrow = diceValue;

                if (sameRollInRow > maxSameRollsInARow) maxSameRollsInARow = sameRollInRow;

                sameRollInRow = 0;
            }

            rollOff[diceValue - 1]++;
        }
        for(int i = 0; i < rollOff.length; i++) {
            Assert.assertTrue("Biased Slag",rollOff[i] < 10000 + 400);
            Assert.assertTrue("Biased Slag",rollOff[i] > 10000 - 400);
        }
    }

}
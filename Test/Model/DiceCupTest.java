package Model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceCupTest {

    @Test
    void shake() {
        DiceCup cup = new DiceCup(new Die(6), new Die(6));
        int[] rolls = cup.shake();

        assert rolls[0] <= 6;
        assert rolls[1] <= 6;
        assert rolls[0] + rolls[1] <= 12;

        assert rolls[0] > 0;
        assert rolls[1] > 0;
    }

    @Test
    void getDiceSum() {

    }

    @Test
    void isEqual() {

    }
}
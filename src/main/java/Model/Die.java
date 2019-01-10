package Model;

import java.util.Random;

/**
 * This class is for creating a dice.
 */
public class Die {
    /**
     * We are defining Random as random.
     */
    Random random;
    /**
     * Defines the value.
     */
    int value = 1;

    /**
     * Defines the sides for the die with a final static so it can't be changed.
     */
    public final int DIE_SIDES;

    /**
     * This constructor is creating a new random.
     */
    public Die(int dieSides) {
        this.random = new Random();
        this.DIE_SIDES = dieSides;
    }

    /**
     * This method is used for rolling the die.
     * @return the value of the random roll.
     */
    public int roll() {
        random = new Random(random.nextInt());//tving ny random per kald
        value = random.nextInt(DIE_SIDES)+1;
        return value;
    }

    /**
     * This method is used for seeing the current value of the die.
     * @return Returning the value of the dice side.
     */
    public int getValue() { return value; }
}

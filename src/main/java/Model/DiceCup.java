package Model;


/**
 * This class is creating and controlling the dices.
 */
public class DiceCup {
    /**
     * Is a final list that holds the dices.
     */
    private final Die[] dices;

    /**
     * This method is creating the 2 dices the application uses.
     */
    public DiceCup() {
        dices = new Die[] { new Die(6), new Die(6) }; // There is always only 2 dices (final)
    }

    public DiceCup(Die... dices) { this.dices = dices; }

    /**
     * This method is rolling the dices, while storing them in a list.
     * @return Is returning the list which is holding the dices value.
     */
    public int[] shake() {

        int[] cast = new int[dices.length];

        // We are here rolling the dices.
        for (int i = 0; i < dices.length; i++) {
            Die dice = dices[i];
            cast[i] = dice.roll();
        }
        return new int[]{cast[0],cast[1]};
    }

    /**
     * This method is for seeing the total value of the 2 dices.
     * @return The value of both dices in a total sum.
     */
    public int getDiceSum() {
        int sum = 0;
        for (int i = 0; i < dices.length; i++) { sum += dices[i].getValue(); }
        return sum;
    }

    /**
     * This method is for checking if the dices has the same value.
     * @return Is returning true if the dices value are the same.
     */

    //TODO: IMPLEMENTO Dobbelt slag
    public boolean isEqual() {
        for (int i = 0; i < dices.length; i++) {
            for (int j = 0; j < dices.length; j++) {
                if (dices[i] != dices[j]) return false;
            }
        }
        return true;
    }
}
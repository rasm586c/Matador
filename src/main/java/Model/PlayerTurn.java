package Model;

public class PlayerTurn {

    public final boolean crossedStart;
    public final int diceRoll;

    public PlayerTurn(Boolean crossedStart, int diceRoll) {
        this.crossedStart = crossedStart;
        this.diceRoll = diceRoll;
    }

}

package Model;

public class PlayerTurn {
    public final Transaction jailTransaction;
    public final boolean crossedStart;
    public final int diceRoll;

    public PlayerTurn(Boolean crossedStart, int diceRoll) {
        this(crossedStart, diceRoll, null);
    }

    public PlayerTurn(Boolean crossedStart, int diceRoll, Transaction jailTransaction) {
        this.crossedStart = crossedStart;
        this.diceRoll = diceRoll;
        this.jailTransaction = jailTransaction;
    }

}

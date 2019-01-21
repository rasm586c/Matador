package Model;

public class PlayerTurn {
    public final Transaction jailTransaction;
    public final boolean crossedStart;
    public final int diceRoll;
    public final boolean getsAnotherTurn;

    public PlayerTurn(Boolean crossedStart, int diceRoll) {
        this(crossedStart, diceRoll, false, null);
    }

    public PlayerTurn(Boolean crossedStart, boolean getsAnotherTurn, int diceRoll) {
        this(crossedStart, diceRoll, getsAnotherTurn, null);
    }

    public PlayerTurn(Boolean crossedStart, int diceRoll, boolean getsAnotherTurn) {
        this(crossedStart, diceRoll, getsAnotherTurn, null);
    }

    public PlayerTurn(Boolean crossedStart, int diceRoll, Transaction jailTransaction) {
        this(crossedStart, diceRoll, false, jailTransaction);
    }

    public PlayerTurn(Boolean crossedStart, int diceRoll, boolean getsAnotherTurn, Transaction jailTransaction) {
        this.crossedStart = crossedStart;
        this.diceRoll = diceRoll;
        this.jailTransaction = jailTransaction;
        this.getsAnotherTurn = getsAnotherTurn;
    }
}

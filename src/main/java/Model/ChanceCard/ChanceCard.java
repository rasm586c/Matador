package Model.ChanceCard;

import Model.GameState;

public abstract class ChanceCard {
    private int moveAmount;
    private int moneyAmount;

    public void calculateCardFromState(GameState state) {}

    public String getDescription() { return "Du er landet på et chance felt!"; }

    public int getMoveAmount() { return moveAmount; }
    public int getMoneyAmount() { return moneyAmount; }

    protected void setMoneyAmount(int moneyAmount) { this.moneyAmount = moneyAmount; }
    protected void setMoveAmount(int moveAmount) { this.moveAmount = moveAmount; }
}

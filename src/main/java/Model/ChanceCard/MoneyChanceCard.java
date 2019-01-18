package Model.ChanceCard;

import Model.GameState;

public class MoneyChanceCard extends ChanceCard {
    public MoneyChanceCard(int money) {
        setMoneyAmount(money);
        setMoveAmount(0);
    }

    @java.lang.Override
    public java.lang.String getDescription() {
        if (getMoneyAmount() < 0)
            return "Du trak penge chance kortet og mistede " + getMoneyAmount() + " penge!";
        else
            return "Du trak penge chance kortet og fik " + getMoneyAmount() + " penge!";
    }
}

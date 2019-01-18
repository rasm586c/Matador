package Model.ChanceCard;

import Model.GameState;

public class MoneyChanceCard extends ChanceCard {
    public MoneyChanceCard(int money) {
        setMoneyAmount(money);
        setMoveAmount(0);
    }
}

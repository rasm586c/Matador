package Model.ChanceCard;

import Model.GameState;
import Model.LanguagePack;

public class MoneyChanceCard extends ChanceCard {
    public MoneyChanceCard(int money) {
        setMoneyAmount(money);
        setMoveAmount(0);
    }

    @java.lang.Override
    public java.lang.String getDescription(LanguagePack languagePack) {
        if (getMoneyAmount() < 0)
            return languagePack.getString("money_chance_card") + getMoneyAmount() + languagePack.getString("money");
        else
            return languagePack.getString("money_chance_card_2") + getMoneyAmount() + languagePack.getString("money");
    }
}

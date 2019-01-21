package Model.ChanceCard;

import Model.LanguagePack;

public class MoveChanceCard extends ChanceCard {
    public MoveChanceCard(int amount) {
        setMoneyAmount(0);
        setMoveAmount(amount);
    }

    @java.lang.Override
    public java.lang.String getDescription(LanguagePack languagePack) {
        return languagePack.getString("move_chance_card") + getMoveAmount() + languagePack.getString("field_forward");
    }
}

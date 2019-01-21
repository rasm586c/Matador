package Model.ChanceCard;

import Model.LanguagePack;

public class MoveChanceCard extends ChanceCard {
    private String specialDescription = null;

    public MoveChanceCard(int amount) {
        setMoneyAmount(0);
        setMoveAmount(amount);
    }

    public MoveChanceCard(int amount, String specialDescription) {
        this(amount);
        this.specialDescription = specialDescription;
    }

    @java.lang.Override
    public java.lang.String getDescription(LanguagePack languagePack) {
        if (specialDescription == null) {
            if (getMoveAmount() > 0) {
                return languagePack.getString("move_chance_card") + getMoveAmount() + languagePack.getString("field_forward");
            } else {
                return languagePack.getString("move_chance_card") + (getMoveAmount() * -1) + languagePack.getString("field_backward");
            }
        } else {
            return specialDescription;
        }
    }
}

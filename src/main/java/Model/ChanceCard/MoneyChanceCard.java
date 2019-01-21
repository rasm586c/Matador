package Model.ChanceCard;

public class MoneyChanceCard extends ChanceCard {
    private String specialDescription = null;

    public MoneyChanceCard(int money) {
        setMoneyAmount(money);
        setMoveAmount(0);
    }

    public MoneyChanceCard(int amount, String specialDescription) {
        this(amount);
        this.specialDescription = specialDescription;
    }

    @java.lang.Override
    public java.lang.String getDescription() {
        if (specialDescription == null) {
            if (getMoneyAmount() < 0) {
                return "Du trak penge chance kortet og mistede " + getMoneyAmount() + " penge!";
            } else {
                return "Du trak penge chance kortet og fik " + getMoneyAmount() * -1 + " penge!";
        }
    } else {
           return specialDescription;
        }

    }
}

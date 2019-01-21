package Model.ChanceCard;

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
    public java.lang.String getDescription() {
        if (specialDescription == null) {
            if (getMoveAmount() > 0) {
                return "Du trak chance kortet, som gør at du skal flytte dig " + getMoveAmount() + " felter frem!";
            } else {
                return "Du trak chance kortet, som gør at du skal flytte dig " + getMoveAmount() * -1 + " felter tilbage!";
            }
        } else {
            return specialDescription;
        }
    }
}

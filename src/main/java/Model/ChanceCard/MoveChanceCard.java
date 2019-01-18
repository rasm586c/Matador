package Model.ChanceCard;

public class MoveChanceCard extends ChanceCard {
    public MoveChanceCard(int amount) {
        setMoneyAmount(0);
        setMoveAmount(amount);
    }

    @java.lang.Override
    public java.lang.String getDescription() {
        return "Du trak chance kortet, som gør at du skal flytte dig " + getMoveAmount() + " felter frem!";
    }
}

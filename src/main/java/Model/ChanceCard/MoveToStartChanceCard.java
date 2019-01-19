package Model.ChanceCard;

import Model.Fields.BreweryField;
import Model.Fields.StartField;
import Model.GameBoard;
import Model.GameState;
import Model.Player;

public class MoveToStartChanceCard extends ChanceCard {
    private String specialDescription = null;
    @Override
    public void calculateCardFromState(GameState state) {
        super.calculateCardFromState(state);


        int currentPlayerPositionClamped = state.getCurrentPlayer().getPositionClamped();
        GameBoard board = state.getBoard();

        int delta = 0;
        while (true) {
            if (board.getFields()[Player.clampPosition(state.getCurrentPlayer().getPosition() + delta)] instanceof StartField) {
                break;
            }
            delta++;

            if (delta > 40)
                throw new IllegalArgumentException("");
        }

        setMoveAmount(delta);
    }
    @Override
    // TODO: Fiks kreativitet og gamestring
    public String getDescription() {
        return "Ryk til start";
    }
}

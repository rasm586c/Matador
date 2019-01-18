package Model.ChanceCard;

import Model.Fields.BreweryField;
import Model.GameBoard;
import Model.GameState;
import Model.Player;

public class MoveToBreweryChanceCard extends ChanceCard {
    @Override
    public void calculateCardFromState(GameState state) {
        super.calculateCardFromState(state);

        int currentPlayerPositionClamped = state.getCurrentPlayer().getPositionClamped();
        GameBoard board = state.getBoard();

        int delta = 0;
        while (true) {
            if (board.getFields()[Player.clampPosition(state.getCurrentPlayer().getPosition() + delta)] instanceof BreweryField) {
                break;
            }
            delta++;

            if (delta > 40)
                throw new IllegalArgumentException("");
        }

        setMoveAmount(delta);
    }
}

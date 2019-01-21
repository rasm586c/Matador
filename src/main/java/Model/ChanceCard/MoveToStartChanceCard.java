package Model.ChanceCard;

import Model.Fields.BreweryField;
import Model.Fields.StartField;
import Model.GameBoard;
import Model.GameState;
import Model.LanguagePack;
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
    @java.lang.Override
    public java.lang.String getDescription(LanguagePack languagePack) {
        return languagePack.getString("new_semester_start");
    }
}

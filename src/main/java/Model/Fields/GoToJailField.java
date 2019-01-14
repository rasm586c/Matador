package Model.Fields;

import Model.GameState;
import Model.PlayerTurn;

public class GoToJailField extends Field {

    public GoToJailField(String name, String fieldtxt) {
        super(name,0,fieldtxt,GUI_Type.Jail);
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);

        state.setTurn(new PlayerTurn(false, state.getTurn().diceRoll));
    }
}

package Controller;

import Model.*;
import Model.Fields.Field;
import View.View;

public class FieldController extends Controller {
    GameBoard board;

    public FieldController(View view, GameBoard board) {
        super(view);
        this.board = board;
    }

    public void update(GameState state) {
        // What field did we land on?
       Field landedOn = board.getFields()[state.getCurrentPlayer().getPositionClamped()];
       landedOn.onFieldLand(state);


    }

}

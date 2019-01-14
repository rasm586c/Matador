package Controller;

import Model.*;
import Model.Fields.*;
import View.*;

public class FieldController extends Controller {
    GameBoard board;
    View view;

    public FieldController(View view, GameBoard board) {
        super(view);
        this.board = board;
        this.view = view;
    }

    public Transaction update(GameState state) {
        Transaction transaction = null;

        // What field did we land on?
       Field landedOn = board.getFields()[state.getCurrentPlayer().getPositionClamped()];
       landedOn.onFieldLand(state);

       // Property Field
       if (landedOn instanceof OwnableField) {
            if (landedOn.getOwner() == null) {
                // TODO: Fix gamestrings
                String result = view.getUserSelect(((OwnableField)landedOn).getPurchaseText(),"yes","no") ;

                if (result.equals("yes")) {
                    transaction = new Transaction(state.getCurrentPlayer(), landedOn);
                }
            }
       }


        return transaction;
    }



}

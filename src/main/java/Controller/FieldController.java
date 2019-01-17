package Controller;

import Model.*;
import Model.Fields.*;
import View.*;

import java.util.ArrayList;

public class FieldController extends Controller {
    GameBoard board;
    View view;

    public FieldController(View view, GameBoard board) {
        super(view);
        this.board = board;
        this.view = view;
    }

    public Transaction fieldPurchase(GameState state) {
        Transaction transaction = null;

        // What field did we land on?
       Field landedOn = board.getFields()[state.getCurrentPlayer().getPositionClamped()];
       landedOn.onFieldLand(state);

       // Property Field
       if (landedOn instanceof OwnableField) {
           OwnableField field = (OwnableField)landedOn;

           if (landedOn.getOwner() == null) {
                // TODO: Fix gamestrings
                String result = view.getUserSelect(field.getPurchaseText(),"yes","no") ;

                if (result.equals("yes")) {
                    transaction = new Transaction(state.getCurrentPlayer(), landedOn, landedOn.value, Transaction.TransactionType.PurchaseProperty);
                }
            } else {
                // Withdraw rent!
                transaction = new Transaction(state.getCurrentPlayer(), landedOn, landedOn.rent, Transaction.TransactionType.ToPlayer);
                transaction.setTarget(landedOn.getOwner());
            }
       }


        return transaction;
    }

    public Transaction purchaseHouse(GameState state) {
        Transaction transaction  = null;

        // Find grunde som vi ejer
        ArrayList<Field> playerFields = new ArrayList<Field>();

        for (int i = 0; i < state.getBoard().getFields().length; i++) {
            if (state.getCurrentPlayer() == state.getBoard().getFields()[i].getOwner() &&
                state.getBoard().getFields()[i] instanceof PropertyField &&
                state.getBoard().getFields()[i].getHouseCounter() < 5 &&
                hasAllFields(state.getBoard().getFields()[i].fieldType, state.getBoard().getFields(), state.getCurrentPlayer())) {

                playerFields.add(state.getBoard().getFields()[i]);
            }
        }

        // Hvis antal grunde > 0
        if (playerFields.size() > 0) {
            // Spørg om brugeren vil købe et hus?
            String result = view.getUserSelect("Vil du købe et hus?", "yes", "no");

            if (result.equals("no")) {
                return null;
            }

            // Spørg hvilken grund brugeren vil købe et hus på!
            String[] properties = new String[playerFields.size()];
            for (int i = 0; i < properties.length; i++) {
                properties[i] = playerFields.get(i).name;
            }
            result = view.getUserSelect("Vælg grund at købe hus på", properties);

            // Find ud af hvilken grund som brugeren har valgt
            Field selectedField = null;
            for (int i = 0; i < properties.length; i++) {
                if (properties[i].equals(result)) {
                    selectedField = playerFields.get(i);
                    break;
                }
            }

            // Brugeren vil købe et hus! Lav transaction
            int housePrice = 50;
            transaction = new Transaction( state.getCurrentPlayer(), selectedField, housePrice, Transaction.TransactionType.PurchaseHouse);
        }

        return transaction;
    }

    private boolean hasAllFields(Field.GUI_Type fieldType, Field[] fields, Player player) {
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getOwner() != player && fields[i].fieldType == fieldType) {
                return false;
            }
        }
        return true;
    }
}

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

    public Transaction payRent(GameState state) {
        Transaction transaction = null;

        // What field did we land on?
        Field landedOn = board.getFields()[state.getCurrentPlayer().getPositionClamped()];
        landedOn.onFieldLand(state);

        if (landedOn instanceof OwnableField) {
            if (landedOn.getOwner() != null && landedOn.getOwner() != state.getCurrentPlayer()) {
                transaction = new Transaction(state.getCurrentPlayer(), landedOn, ((OwnableField) landedOn).calculateRent(state), Transaction.TransactionType.ToPlayer);
                transaction.setTarget(landedOn.getOwner());
            }
        }

        return transaction;
    }

    public Transaction purchaseField(GameState state) {
        Transaction transaction = null;

        // What field did we land on?
       Field landedOn = board.getFields()[state.getCurrentPlayer().getPositionClamped()];
       landedOn.onFieldLand(state);

       // Property Field
       if (landedOn instanceof OwnableField) {
           if (landedOn.getOwner() == null) {
                // TODO: Fix gamestrings
                transaction = new Transaction(state.getCurrentPlayer(), landedOn, landedOn.value, Transaction.TransactionType.PurchaseProperty);
            }
       }

        return transaction;
    }
    public Transaction purchaseHouse(GameState state) {
        Transaction transaction  = null;

        // Find grunde som vi ejer
        ArrayList<Field> playerFields = new ArrayList<Field>();

        for (int i = 0; i < state.getBoard().getFields().length; i++) {
            Field currentField = state.getBoard().getFields()[i];
            int lowestHouseValueOfField = lowestHouseValue(currentField.fieldType, state.getBoard().getFields(), state.getCurrentPlayer());

            if (state.getCurrentPlayer() == currentField.getOwner() &&
                    currentField instanceof PropertyField &&
                    currentField.getHouseCounter() < 5 &&
                    hasAllFields(currentField.fieldType, state.getBoard().getFields(), state.getCurrentPlayer()) &&
                    currentField.getHouseCounter() <= lowestHouseValueOfField) {

                playerFields.add(state.getBoard().getFields()[i]);
            }
        }

        // Hvis antal grunde > 0
        if (playerFields.size() > 0) {
            // Spørg hvilken grund brugeren vil købe et hus på!
            String[] properties = new String[playerFields.size()];
            for (int i = 0; i < properties.length; i++) {
                properties[i] = playerFields.get(i).name;
            }

            // TODO: Fix gamestrings
            String result = view.getUserSelect("Vælg grund at købe hus på", properties);

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
        } else {
            // TODO: Fix gamestrings
            view.print("Du kan ikke købe huse lige nu!");
        }

        return transaction;
    }

    public ControllerChoice[] getControllerChoices(GameState state) {
        ArrayList<ControllerChoice> choices = new ArrayList<ControllerChoice>();

        if (canPurchaseField(state)) { choices.add(ControllerChoice.BuyField); }
        if (canPurchaseHouse(state)) { choices.add(ControllerChoice.BuyHouse); }

        ControllerChoice[] choicesArray = new ControllerChoice[choices.size()];
        for (int i = 0; i < choices.size(); i++) {
            choicesArray[i] = choices.get(i);
        }

          return choicesArray;
    }

    private boolean canPurchaseField(GameState state) {
        Field landedOn = board.getFields()[state.getCurrentPlayer().getPositionClamped()];
        landedOn.onFieldLand(state);

        if (landedOn instanceof OwnableField && landedOn.getOwner() == null) {
            return true;
        }

        return false;
    }
    private boolean canPurchaseHouse(GameState state) {
        for (int i = 0; i < state.getBoard().getFields().length; i++) {
            Field currentField = state.getBoard().getFields()[i];
            int lowestHouseValueOfField = lowestHouseValue(currentField.fieldType, state.getBoard().getFields(), state.getCurrentPlayer());

            if (state.getCurrentPlayer() == currentField.getOwner() &&
                    currentField instanceof PropertyField &&
                    currentField.getHouseCounter() < 5 &&
                    hasAllFields(currentField.fieldType, state.getBoard().getFields(), state.getCurrentPlayer()) &&
                    currentField.getHouseCounter() <= lowestHouseValueOfField) {

                return true;
            }
        }
        return false;
    }

    private boolean hasAllFields(Field.GUI_Type fieldType, Field[] fields, Player player) {
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getOwner() != player && fields[i].fieldType == fieldType) {
                return false;
            }
        }
        return true;
    }

    private int lowestHouseValue(Field.GUI_Type fieldType, Field[] fields, Player player) {
        int lowestValue = Integer.MAX_VALUE;
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getOwner() == player && fields[i].fieldType == fieldType) {
                if (fields[i].getHouseCounter() < lowestValue) {
                    lowestValue = fields[i].getHouseCounter();
                }
            }
        }
        return (lowestValue == Integer.MAX_VALUE ? 0 : lowestValue);
    }

}

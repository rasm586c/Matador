package Controller;

import Model.*;
import Model.ChanceCard.ChanceCard;
import Model.Fields.*;
import View.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FieldController extends Controller {
    GameBoard board;
    View view;
    LanguagePack languagePack;

    public FieldController(View view, GameBoard board) {
        super(view);
        this.board = board;
        this.view = view;
    }

    public Transaction onFieldLand(GameState state) {
        Field field = state.getBoard().getFields()[state.getCurrentPlayer().getPositionClamped()];
        field.onFieldLand(state);

        Transaction playerTransaction = null;

        if (field instanceof ChanceField) {
            ChanceField chanceField = (ChanceField)field;
            ChanceCard card = chanceField.drawCard();
            card.calculateCardFromState(state);

            view.print(card.getDescription());

            playerTransaction = new Transaction(state.getCurrentPlayer(), null, card.getMoneyAmount(), Transaction.TransactionType.PayToBank);
            playerTransaction.setAmount(card.getMoneyAmount());

            int oldPosition = state.getCurrentPlayer().getPosition();
            state.getCurrentPlayer().setPosition(state.getCurrentPlayer().getPosition() + card.getMoveAmount());
            view.movePlayer(oldPosition, state.getCurrentPlayer().getPosition(), state.getCurrentPlayer());
        }

        return playerTransaction;
    }

    public Transaction payRent(GameState state) {
        Transaction transaction = null;

        // What field did we land on?
        Field landedOn = board.getFields()[state.getCurrentPlayer().getPositionClamped()];

        if (landedOn instanceof OwnableField) {
            if (landedOn.getOwner() != null && landedOn.getOwner() != state.getCurrentPlayer() && landedOn.getActive()) {
                transaction = new Transaction(state.getCurrentPlayer(), landedOn, ((OwnableField) landedOn).calculateRent(state), Transaction.TransactionType.ToPlayer);
                transaction.setTarget(landedOn.getOwner());
            }
        }

        if (landedOn instanceof TaxField) {
            transaction = new Transaction(state.getCurrentPlayer(),landedOn,Transaction.TransactionType.PayTax);
        }

        if (landedOn instanceof LoanField) {
            transaction = new Transaction(state.getCurrentPlayer(),landedOn,2000, Transaction.TransactionType.PayToBank);
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
            String result = view.getUserSelect(languagePack.getString("field_buy_house"), properties);

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
            view.print(languagePack.getString("can_not_buy_house"));
        }

        return transaction;
    }

    public Transaction tradeField(GameState state) {
        Transaction transaction = null;
        GameBoard board = state.getBoard();

        // 1. Select trade property
        Field[] ownedFields = Arrays.stream(board.getFields())
                .filter(field -> field.getOwner() == state.getCurrentPlayer())
                .toArray(Field[]::new);

        String[] ownedFieldSelection = Arrays.stream(ownedFields)
                .map(field -> field.name)
                .toArray(String[]::new);

        String tradeFieldName = view.getUserSelect(languagePack.getString("field_negotiate"), ownedFieldSelection);

        Field tradeField = Arrays.stream(ownedFields)
                .filter(field -> field.name.equals(tradeFieldName))
                .findFirst()
                .get();

        // 2. Select person to trade with
        Player[] otherPlayers = Arrays.stream(state.getPlayers())
                .filter(player -> player != state.getCurrentPlayer())
                .toArray(Player[]::new);

        String[] otherPlayerSelections = Arrays.stream(otherPlayers)
                .map(player -> player.getName())
                .toArray(String[]::new);

        String tradePlayerName = view.getUserSelect(languagePack.getString("trade_player_choice"), otherPlayerSelections);

        Player tradePlayer = Arrays.stream(state.getPlayers())
                .filter(player -> player.getName().equals(tradePlayerName))
                .findFirst()
                .get();

        // 3. Select amount you want for the property.
        int fieldPrice = 0;
        while (true) {
            try {
                fieldPrice = Integer.parseInt(view.getUserInput(languagePack.getString("how_much_field")));
                break;
            } catch (NumberFormatException nfe) {
                view.print(languagePack.getString("invalid_input"));
            }
        }

        // 4. Person to trade with agrees
        String result = view.getUserSelect(String.format(languagePack.getString("wish_buy_field"), tradePlayerName, tradeField.name, fieldPrice), languagePack.getString("yes"), languagePack.getString("no"));

        // 5. Create transaction
        if (result.equals(languagePack.getString("yes"))) {
            transaction = new Transaction(tradePlayer, tradeField, fieldPrice, Transaction.TransactionType.PurchaseProperty);
            transaction.setTarget(state.getCurrentPlayer());
        }

        return transaction;
    }
    public Transaction sellField(GameState state) {
        Transaction transaction = null;

        GameBoard board = state.getBoard();

        // 1. Select trade property
        Field[] ownedFields = Arrays.stream(board.getFields())
                .filter(field -> field.getOwner() == state.getCurrentPlayer())
                .toArray(Field[]::new);

        String[] ownedFieldSelection = Arrays.stream(ownedFields)
                .map(field -> field.name)
                .toArray(String[]::new);

        String tradeFieldName = view.getUserSelect(languagePack.getString("choose_field_negotiate"), ownedFieldSelection);

        Field tradeField = Arrays.stream(ownedFields)
                .filter(field -> field.name.equals(tradeFieldName))
                .findFirst()
                .get();

        // 2. Calculate field price
        int fieldPrice = tradeField.value / 2;

        // 3. Person to trade with agrees
        String result = view.getUserSelect(String.format(languagePack.getString("wish_field_mortgage"), tradeField.name, fieldPrice), languagePack.getString("yes"), languagePack.getString("no"));

        // 4. Create transaction
        if (result.equals(languagePack.getString("yes"))) {
            transaction = new Transaction(state.getCurrentPlayer(), tradeField, fieldPrice, Transaction.TransactionType.SellProperty);
            transaction.setTarget(state.getCurrentPlayer());
        }

        return transaction;
    }

    public Transaction buybackProperty(GameState state) {
        Transaction transaction = null;

        // 1. Find fields you can buy back
        Field[] buybackFields = Arrays.stream(state.getBoard().getFields())
                .filter(field -> field.getOwner() != null
                        && field.getOwner().equals(state.getCurrentPlayer())
                        && !field.getActive())
                .toArray(Field[]::new);


        // 2. Let user select buyback field
        String[] buybackFieldStrings = Arrays.stream(buybackFields)
                .map(field -> field.name)
                .toArray(String[]::new);

        String buybackFieldResult = view.getUserSelect(languagePack.getString("choose_field_buy_back"), buybackFieldStrings);

        Field buybackField = Arrays.stream(buybackFields)
                                .filter(field -> field.name.equals(buybackFieldResult))
                                .findFirst()
                                .get();

        // 3. Calculate price of field
        int fieldPrice = buybackField.value / 2 + (buybackField.value / 100) * 10;

        // 4. Ask user for agreement
        String result = view.getUserSelect(String.format(languagePack.getString("wish_buy_field_back"), buybackFieldResult, fieldPrice), languagePack.getString("yes"), languagePack.getString("no"));

        // 5. Create transaction
        if (result.equals(languagePack.getString("yes"))) {
            transaction = new Transaction(state.getCurrentPlayer(), buybackField, fieldPrice, Transaction.TransactionType.PurchaseProperty);
        }

        return transaction;
    }

    public ControllerChoice[] getControllerChoices(GameState state) {
        ArrayList<ControllerChoice> choices = new ArrayList<ControllerChoice>();

        if (canPurchaseField(state)) { choices.add(ControllerChoice.BuyField); }
        if (canPurchaseHouse(state)) { choices.add(ControllerChoice.BuyHouse); }
        if (canTradeProperty(state)) {
            choices.add(ControllerChoice.TradeProperty);
            choices.add(ControllerChoice.SellProperty);
        }
        if (canBuybackProperty(state)) {
            choices.add(ControllerChoice.BuyBackProperty);
        }

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
    private boolean canTradeProperty(GameState state) {
        return Arrays.stream(state.getBoard().getFields())
            .anyMatch(field -> field.getOwner() != null && field.getOwner().equals(state.getCurrentPlayer()) && field.getActive());
    }
    private boolean canBuybackProperty(GameState state) {
        return Arrays.stream(state.getBoard().getFields())
                .anyMatch(field -> field.getOwner() != null && field.getOwner().equals(state.getCurrentPlayer()) && !field.getActive());
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

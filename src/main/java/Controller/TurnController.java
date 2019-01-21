package Controller;

import Model.*;
import View.View;

import java.util.Arrays;

public class TurnController extends Controller {
    int playerTurn = 0;
    Player[] players;
    DiceCup diceCup;
    LanguagePack languagePack;

    public TurnController(View view, Player[] players, LanguagePack languagePack) {
        super(view);
        this.players = players;
        diceCup = new DiceCup();
        this.languagePack = languagePack;
    }

    public Player getNextPlayer() {
        playerTurn = getNextTurn(playerTurn);
        return players[getNextTurn(playerTurn)];
    }

    public Player getCurrentPlayer() {
        return players[playerTurn];
    }

    /*
    * Returns true if player crossed start
    * */
    public PlayerTurn takeTurn() {
        Boolean crossedStart = false;

        Player player = getCurrentPlayer();
        int[] rolledValues = diceCup.shake();
        view.print(languagePack.getString("die_roll", player.getName()));
        view.printDiceRoll(rolledValues[0], rolledValues[1]);

        boolean getsAnotherTurn = false;

        if (player.getJailedTurns() > 0) {
            // TODO: Fix game strings!
            String result = view.getUserSelect(languagePack.getString("prison"), languagePack.getString("pay_1000"), languagePack.getString("2_alike"));
            if (result.equals(languagePack.getString("pay_1000"))) {
                return new PlayerTurn(false, 0, new Transaction(player, null, 1000, Transaction.TransactionType.OutOfJail));
            }

            if (result.equals(languagePack.getString("2_alike"))) {
                int[] roll = diceCup.shake();
                view.printDiceRoll(roll[0], roll[1]);
                player.setJailedTurns(player.getJailedTurns() - 1);

                if (roll[0] == roll[1]) {
                    // Yay du er fri!
                    performMovement(player);
                    player.setJailedTurns(0);
                    return new PlayerTurn(false, diceCup.getDiceSum(), true);
                } else if (player.getJailedTurns() == 0) {
                    // TODO: Fix gamestrings
                    view.print(languagePack.getString("you_pay_1000"));
                    return new PlayerTurn(false, 0, new Transaction(player, null, 1000, Transaction.TransactionType.OutOfJailForced));
                }
            }
        }

        if (player.getJailedTurns() == 0) {
            if (rolledValues[0] == rolledValues[1]) {
                getsAnotherTurn = true;
            }

            crossedStart = performMovement(player);
        }

        return new PlayerTurn(crossedStart, diceCup.getDiceSum(), getsAnotherTurn);
    }

    public ControllerChoice makeTurnChoice(ControllerChoice[] choices) {
        ControllerChoice choice = ControllerChoice.StopTurn;

        //
        String[] playerChoices = new String[choices.length];
        for (int i = 0; i < choices.length; i++) {
            playerChoices[i] = choiceToString(choices[i]);
        }

        // TODO: Fix gamestrings
        String result = view.getUserSelect(languagePack.getString("want_in_turn"), playerChoices);

        for (int i = 0; i < choices.length; i++) {
            if (result.equals(choiceToString(choices[i]))) {
                choice = choices[i];
                break;
            }
        }

        return choice;
    }

    private String choiceToString(ControllerChoice choice) {
        switch (choice) {
            // TODO: GameStrings
            case StopTurn: return languagePack.getString("stop_tour");
            case BuyField: return languagePack.getString("buy_field");
            case SellProperty: return languagePack.getString("mortgage_field");
            case BuyBackProperty: return languagePack.getString("buy_field_back");
            case BuyHouse: return languagePack.getString("buy_house");
            case TradeProperty: return languagePack.getString("trade_field");
        }
        return languagePack.getString("unknown_case");
    }

    private boolean performMovement(Player player) {
        int oldPosition = player.getPositionClamped();
        player.setPosition(player.getPosition() + diceCup.getDiceSum());
        view.movePlayer(oldPosition, player.getPosition(), player);

        if (Player.clampPosition(oldPosition) > Player.clampPosition(player.getPosition())) {
            return true;
        }
        return false;
    }

    public void ensureJailPosition(GameState state) {
        Player player = state.getCurrentPlayer();
        if (player.getJailedTurns() > 0) {
            int oldPosition = player.getPositionClamped();
            player.setPosition(10);
            view.movePlayer(oldPosition, 10, player);
        }
    }

    public ControllerChoice[] getControllerChoices(GameState state) {
        return new ControllerChoice[] { ControllerChoice.StopTurn };
    }

    public boolean hasWinner() {
        return Arrays.stream(players)
                .filter(player -> !player.getBankrupt())
                .count() == 1;
    }

    public Player getWinner() {
        return Arrays.stream(players)
                .filter(player -> !player.getBankrupt())
                .findFirst()
                .get();
    }

    private int getNextTurn(int playerTurn) {
        int nextTurn = playerTurn;
        while (true) {
            nextTurn = nextTurn + 1 >= players.length ? 0 : nextTurn + 1;

            if (!players[nextTurn].getBankrupt()) {
                break;
            }
        }

        return nextTurn;
    }
}

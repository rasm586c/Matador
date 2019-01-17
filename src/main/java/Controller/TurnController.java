package Controller;

import Model.*;
import View.View;



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

        int oldPosition = player.getPosition();
        player.setPosition(player.getPosition() + diceCup.getDiceSum());
        view.movePlayer(oldPosition, player.getPosition(), player);

        if (Player.clampPosition(oldPosition) > Player.clampPosition(player.getPosition())) {
            crossedStart = true;
        }

        return new PlayerTurn(crossedStart, diceCup.getDiceSum());
    }

    public ControllerChoice makeTurnChoice(ControllerChoice[] choices) {
        ControllerChoice choice = ControllerChoice.StopTurn;

        //
        String[] playerChoices = new String[choices.length];
        for (int i = 0; i < choices.length; i++) {
            playerChoices[i] = choiceToString(choices[i]);
        }

        // TODO: Fix gamestrings
        String result = view.getUserSelect("Hvad vil du i denne tur?", playerChoices);

        if (result.equals(choiceToString(ControllerChoice.BuyField))) choice = ControllerChoice.BuyField;
        if (result.equals(choiceToString(ControllerChoice.BuyHouse))) choice = ControllerChoice.BuyHouse;
        if (result.equals(choiceToString(ControllerChoice.SellHouse))) choice = ControllerChoice.SellHouse;
        if (result.equals(choiceToString(ControllerChoice.StopTurn))) choice = ControllerChoice.StopTurn;

        return choice;
    }

    private String choiceToString(ControllerChoice choice) {
        switch (choice) {
            case StopTurn: return "Stop tur";
            case BuyField: return "Køb felt";
            case SellHouse: return "Sælg hus";
            case BuyHouse: return "Køb hus";
        }
        return "Unknown Case";
    }

    public ControllerChoice[] getControllerChoices(GameState state) {
        return new ControllerChoice[] { ControllerChoice.StopTurn };
    }

    private int getNextTurn(int playerTurn) {
        return playerTurn + 1 >= players.length ? 0 : playerTurn + 1;
    }
}

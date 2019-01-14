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

    private int getNextTurn(int playerTurn) {
        return playerTurn + 1 >= players.length ? 0 : playerTurn + 1;
    }
}

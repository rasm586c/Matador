package Controller;

import Model.*;
import View.View;

public class TurnController extends Controller {
    static final int BOARD_SIZE = 40;

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

    public void takeTurn() {
        Player player = getCurrentPlayer();
        int[] rolledValues = diceCup.shake();
        view.print(languagePack.getString("die_roll", player.getName()));
        view.printDiceRoll(rolledValues[0], rolledValues[1]);

        int oldPosition = player.getPosition();
        player.setPosition(clampPosition(player.getPosition() + diceCup.getDiceSum()));
        view.movePlayer(oldPosition, player.getPosition(), player);

        // TODO: Add more game logic here.
        sleep(100);
    }


    /**
     * Creates a timeout of n miliseconds. Calling this will make the program stop and wait for n miliseconds and then allow further execution.
     * This method has no lock so prevent calling it from multiple threads.
     * */
    public static void sleep(int n) {
        long t0 = System.currentTimeMillis();

        long t1;
        do {
            t1 = System.currentTimeMillis();
        } while(t1 - t0 < (long)n);

    }

    /**
     * This function recursively calls itself until it ensures the input value is between 0 and BOARD_SIZE (I.e. clamps a position to the board)
     * @param position
     * @return
     */
    private int clampPosition(int position) {
        if (position < 0) { return clampPosition(position + BOARD_SIZE); }
        if (position < BOARD_SIZE) return position;
        return clampPosition(position - BOARD_SIZE);
    }

    private int getNextTurn(int playerTurn) {
        return playerTurn + 1 >= players.length ? 0 : playerTurn + 1;
    }
}

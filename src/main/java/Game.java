import Controller.*;
import Model.*;
import View.*;

public class Game {
    public static void main(String[] args) {
        View view = new GUIView();
        TurnController turnController = new TurnController(view, getPlayers());

        // Game Loop
        while (true) {
            turnController.takeTurn();
            turnController.getNextPlayer();

        }
    }

    private static Player[] getPlayers() {
        return new Player[] {

        };
    }
}

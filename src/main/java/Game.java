import Controller.*;
import Model.*;
import View.*;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException {
        LanguagePackFactory languagePackFactory = new LanguagePackFactory(LanguagePackFactory.LanguageType.Danish);
        LanguagePack languagePack = languagePackFactory.getLanguagePack();

        // Create UI
        View view = new GUIView(languagePack);

        // Create GameBoard
        GameBoard board = new GameBoard(languagePack);

        // Retrieve players
        Player[] players = getPlayers(view, languagePack);

        // Update players on UI
        view.updatePlayers(players);

        // Create controllers
        TurnController turnController = new TurnController(view, players, languagePack);
        BankController bankController = new BankController(view, players);
        FieldController fieldController = new FieldController(view, board);

        // Game Loop
        while (true) {
            GameState currentState = new GameState();
            currentState.setCurrentPlayer(turnController.getCurrentPlayer());
            currentState.setTurn(turnController.takeTurn());

            if (currentState.getTurn().crossedStart) {
                bankController.addMoney(currentState.getCurrentPlayer(), 4000);
            }

            fieldController.update(currentState);

            turnController.getNextPlayer();
        }
    }

    private static Player[] getPlayers(View view, LanguagePack languagePack) {
        RetrievePlayerDialog dialog = new RetrievePlayerDialog(view, languagePack);
        return dialog.showPlayerDialog();
    }
}

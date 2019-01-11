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

        // Retrieve players
        Player[] players = getPlayers(view, languagePack);

        // Update players on UI
        view.updatePlayers(players);

        // Create controllers
        TurnController turnController = new TurnController(view, players, languagePack);
        BankController bankController = new BankController(view, players);

        // Game Loop
        while (true) {
            Player currentPlayer = turnController.getCurrentPlayer();

            if (turnController.takeTurn()) {
                bankController.addMoney(currentPlayer, 4000);
            }

            turnController.getNextPlayer();
        }
    }

    private static Player[] getPlayers(View view, LanguagePack languagePack) {
        RetrievePlayerDialog dialog = new RetrievePlayerDialog(view, languagePack);
        return dialog.showPlayerDialog();
    }
}

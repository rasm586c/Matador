import Controller.*;
import Model.*;
import View.*;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException {
        LanguagePackFactory languagePackFactory = new LanguagePackFactory(LanguagePackFactory.LanguageType.Danish);
        LanguagePack languagePack = languagePackFactory.getLanguagePack();

        // ...
        View view = new GUIView(languagePack);
        Player[] players = getPlayers(view, languagePack);
        TurnController turnController = new TurnController(view, players, languagePack);
        view.updatePlayers(players);

        // Game Loop
        while (true) {
            turnController.takeTurn();
            turnController.getNextPlayer();
        }
    }

    private static Player[] getPlayers(View view, LanguagePack languagePack) {
        RetrievePlayerDialog dialog = new RetrievePlayerDialog(view, languagePack);
        return dialog.showPlayerDialog();
    }
}

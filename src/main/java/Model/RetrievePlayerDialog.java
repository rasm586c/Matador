package Model;

import View.View;

import java.awt.*;

/**
 * Create a new dialog asking for player input, then converts that into an player array and returns it
 * */
public class RetrievePlayerDialog {
    private View view;
    private LanguagePack stringContainer;

    public RetrievePlayerDialog(View view, LanguagePack stringContainer)
    {
        this.view = view;
        this.stringContainer = stringContainer;
    }

    public Player[] showPlayerDialog() {
        return getPlayers();
    }

    private Player[] getPlayers() {
        Color[] playerColors = new Color[] {
            Color.GREEN,
            Color.RED,
            Color.BLACK,
            Color.BLUE,
            Color.WHITE,
            Color.MAGENTA
        };

        int n;

        while ((n = Integer.parseInt(view.getUserSelect(stringContainer.getString("amount_players"),"3", "4", "5", "6"))) <= 0) {
            view.print(stringContainer.getString("invalid_amount_players"));
        }
        Player[] players = new Player[n];
        for (int i = 0; i < n; i++) {

            String name = view.getUserInput(stringContainer.getString("give_player_name", (i + 1)));

            if (containsPlayerWithName(name, players)) {
                view.print("Sie mussen nicht habe die samme Nahme!");
                i--;
                continue;
            }

            String type = view.getUserSelect(stringContainer.getString("select_card_type"), "Car", "Racecar", "Tractor", "UFO");

            Player player = new Player(name, Enum.valueOf(PlayerType.class, type), playerColors[i]);
            players[i] = player;
        }

        return players;
    }

    private boolean containsPlayerWithName(String name, Player[] players) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) break;

            if (players[i].getName().equals(name)) return true;
        }
        return false;
    }
}

package Model;

import View.View;

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
        int n;

        while ((n = Integer.parseInt(view.getUserSelect(stringContainer.getString("amount_players"), "2", "3", "4"))) <= 0) {
            view.print(stringContainer.getString("invalid_amount_players"));
        }

        Player[] players = new Player[n];
        for (int i = 0; i < n; i++) {

            String name = view.getUserInput(stringContainer.getString("give_player_name", (i + 1)));
            String type = view.getUserSelect(stringContainer.getString("select_card_type"), "Car", "Racecar", "Tractor", "UFO");

            Player player = new Player(name, Enum.valueOf(PlayerType.class, type));
            players[i] = player;
        }

        return players;
    }
}

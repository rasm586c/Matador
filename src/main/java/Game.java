import Controller.*;
import Model.*;
import View.*;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException {
        LanguagePackFactory languagePackFactory = new LanguagePackFactory(LanguagePackFactory.LanguageType.Danish);
        LanguagePack languagePack = languagePackFactory.getLanguagePack();

        // Create UI
        View view = new GUIView(languagePack); // new ConsoleView();

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

        // Sæt ejetskab af nogle properties..
        //board.getFields()[1]
        //board.getFields()[3]
/*
        board.getFields()[1].setOwner(players[0]);
        view.updateOwner(players[0], 1);

        board.getFields()[3].setOwner(players[0]);
        view.updateOwner(players[0], 3);
*/


        // Game Loop
        while (true) {
            GameState currentState = new GameState();
            currentState.setCurrentPlayer(turnController.getCurrentPlayer());
            currentState.setTurn(turnController.takeTurn());
            currentState.setBoard(board);

            Transaction transaction = fieldController.fieldPurchase(currentState);

            if (currentState.getTurn().crossedStart) {
                bankController.addMoney(currentState.getCurrentPlayer(), 4000);
            }

            if (transaction != null) {
                bankController.processTransaction(transaction, currentState);
            }

            Transaction houseTransaction = null;
            while ((houseTransaction = fieldController.purchaseHouse(currentState)) != null) {
                bankController.processTransaction(houseTransaction, currentState);
                if (!houseTransaction.isApproved()) {
                    // Sælg hus?

                    // Du har ikke råd! Pantsæt ?

                }
            }

            turnController.getNextPlayer();
        }
    }

    private static Player[] getPlayers(View view, LanguagePack languagePack) {
        RetrievePlayerDialog dialog = new RetrievePlayerDialog(view, languagePack);
        return dialog.showPlayerDialog();
    }
}

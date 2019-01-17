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

            if (currentState.getTurn().crossedStart) {
                bankController.addMoney(currentState.getCurrentPlayer(), 4000);
            }

            Transaction rentPayment = fieldController.payRent(currentState);
            if (rentPayment != null) {
                bankController.processTransaction(rentPayment, currentState);
                if (!rentPayment.isApproved()) {
                    view.print("Hey fattig røv! Du har ikke råd til at betale husleje så du må sælge noget...!");

                }
            }

            ControllerChoice choice;
            while ((choice = turnController.makeTurnChoice(getAllControllerChoices(currentState, fieldController, bankController, turnController))) != ControllerChoice.StopTurn) {
                switch (choice) {
                    case BuyField:
                    Transaction transaction = fieldController.purchaseField(currentState);
                    if (transaction != null) {
                        bankController.processTransaction(transaction, currentState);

                        if (!transaction.isApproved()) {
                            view.print("Du har ikke råd til dette felt!");
                        }
                    }
                    break;

                    case BuyHouse:
                    Transaction houseTransaction = fieldController.purchaseHouse(currentState);
                    bankController.processTransaction(houseTransaction, currentState);
                    if (!houseTransaction.isApproved()) {
                        view.print("Du har ikke råd til dette hus!");
                    }

                    break;
                }
            }


            turnController.getNextPlayer();
        }
    }

    private static ControllerChoice[] getAllControllerChoices(GameState state, Controller... controllers) {
        int choiceSum = 0;
        for (int i = 0; i < controllers.length; i++) {
            choiceSum += controllers[i].getControllerChoices(state).length;
        }

        ControllerChoice[] choices = new ControllerChoice[choiceSum];
        int choiceIndex = 0;
        for (int i = 0; i < controllers.length; i++) {
            ControllerChoice[] controllerChoices = controllers[i].getControllerChoices(state);
            for (int j = 0; j < controllerChoices.length; j++) {
                choices[choiceIndex++] = controllerChoices[j];
            }
        }

        return choices;
    }
    private static Player[] getPlayers(View view, LanguagePack languagePack) {
        RetrievePlayerDialog dialog = new RetrievePlayerDialog(view, languagePack);
        return dialog.showPlayerDialog();
    }
}

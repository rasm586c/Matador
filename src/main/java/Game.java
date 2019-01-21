import Controller.*;
import Model.*;
import Model.Fields.OwnableField;
import Model.Fields.PropertyField;
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

        boolean førsteEjendom = true;
        for (int i = 0; i < 40; i++) {
            if (board.getFields()[i] instanceof OwnableField) {
                Player ownerPlayer = players[0];
                if (førsteEjendom) {
                    ownerPlayer = players[1];
                    førsteEjendom = false;
                }

                board.getFields()[i].setOwner(ownerPlayer);
                view.updateOwner(ownerPlayer, i, true);

                if (board.getFields()[i] instanceof PropertyField) {
                    board.getFields()[i].setHouseCounter(5);
                    view.updateHouse(i, 5);
                }
            }
        }
        bankController.withdrawMoney(players[1], 29500);


        // Game Loop
        while (true) {
            GameState currentState = new GameState();
            currentState.setCurrentPlayer(turnController.getCurrentPlayer());
            currentState.setPlayers(players);
            currentState.setTurn(turnController.takeTurn());
            currentState.setBoard(board);

            Transaction oweTransaction = null;

            while (true) {
                int position = currentState.getCurrentPlayer().getPosition();

                Transaction fieldTransaction = fieldController.onFieldLand(currentState);
                if (fieldTransaction != null) {
                    bankController.processTransaction(fieldTransaction, currentState);

                    if (!fieldTransaction.isApproved()) {
                        oweTransaction = fieldTransaction;
                        view.print("Du havde ikke råd til at betale " + oweTransaction.getAmount() + "kr for dette chance kort!");
                    }
                }

                if (currentState.getCurrentPlayer().getPosition() == position)
                    break;
            }

            turnController.ensureJailPosition(currentState);

            Transaction jailTransaction = currentState.getTurn().jailTransaction;
            if (jailTransaction != null) {
                bankController.processTransaction(jailTransaction, currentState);

                if (jailTransaction.getTransactionType() == Transaction.TransactionType.OutOfJailForced) {
                    if (!jailTransaction.isApproved()) {
                        // TODO: Fix game strings
                        view.print("Du har ikke råd til at betale dig ud af fængsel. Du skylder derfor 1000 kr.");
                        oweTransaction = jailTransaction;
                    }
                } else continue; // redo turn
            }

            if (currentState.getTurn().crossedStart) {
                bankController.addMoney(currentState.getCurrentPlayer(), 4000);
            }

            Transaction rentPayment = fieldController.payRent(currentState);
            if (rentPayment != null) {
                bankController.processTransaction(rentPayment, currentState);
                if (!rentPayment.isApproved()) {
                    view.print(String.format("Du havde ikke råd til at betale huslejen på %d. Sørg for at have over dette beløb når du slutter din tur, ellers har du tabt spillet.", rentPayment.getAmount()));
                    oweTransaction = rentPayment;
                }
            }

            ControllerChoice choice;
            boolean takingTurn = true;
            while (takingTurn) {
                choice = turnController.makeTurnChoice(getAllControllerChoices(currentState, fieldController, bankController, turnController));

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

                    case SellProperty:
                    Transaction sellPropertyTransaction = fieldController.sellField(currentState);
                    if (sellPropertyTransaction != null) {
                        bankController.processTransaction(sellPropertyTransaction, currentState);
                    }
                    break;

                    case BuyBackProperty:
                    Transaction buybackTransaction = fieldController.buybackProperty(currentState);
                    if (buybackTransaction != null) {
                        bankController.processTransaction(buybackTransaction, currentState);
                    }
                    break;

                    case TradeProperty:
                    Transaction tradeTransaction = fieldController.tradeField(currentState);
                    if (tradeTransaction != null) {
                        bankController.processTransaction(tradeTransaction, currentState);

                        if (!tradeTransaction.isApproved()) {
                            view.print("Handlen kunne ikke gennemføres! " + tradeTransaction.getPlayer() + " ikke nok penge.");
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

                    case StopTurn:
                    if (oweTransaction != null) {
                        bankController.processTransaction(oweTransaction, currentState);

                        if (!oweTransaction.isApproved()) {
                            String result = view.getUserSelect(String.format("Du skylder %d, men har ikke råd til at betale dette. Forsætter du, så vil du gå bankerot og du har tabt spillet.", oweTransaction.getAmount()), "Tab spil", "Sælg mere");
                            if (result.equals("Tab spil")) {
                                takingTurn = false;
                            }
                        } else {
                            takingTurn = false;
                        }
                    } else {
                        takingTurn = false;
                    }
                    break;
                }
            }

            if (oweTransaction != null && !oweTransaction.isApproved()) {
                view.print(String.format("%s gik bankerot. Alle spillerens ejendomme er blevet frigivet, og huse solgt til banken.", currentState.getCurrentPlayer().getName()));
                bankController.freeAssets(currentState.getCurrentPlayer(), currentState);
            }

            if (turnController.hasWinner()) {
                view.setGameWon(turnController.getCurrentPlayer().getName());
                while (true) { /**/ }
            }

            if (!currentState.getTurn().getsAnotherTurn) {
                turnController.getNextPlayer();
            }
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

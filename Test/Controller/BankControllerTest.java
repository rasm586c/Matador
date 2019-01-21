package Controller;

import Model.*;

import Model.Fields.Field;
import Model.Fields.PropertyField;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;

class BankControllerTest {


    @Test
    void addMoney() {
        Player[] players = new Player[] { new Player("Flodhest", PlayerType.UFO, Color.BLACK), new Player("Hest", PlayerType.UFO, Color.BLACK), new Player("Ko", PlayerType.UFO, Color.BLACK), new Player("Giraf", PlayerType.UFO, Color.BLACK), new Player("Høne", PlayerType.UFO, Color.BLACK), new Player("Peter", PlayerType.UFO, Color.BLACK)};
        BankController bankController = new BankController(new View.ConsoleView(),players, new LanguagePack());

        for(int i = 0; i < players.length; i++){
            bankController.addMoney(players[i], 4000);
            Assert.assertEquals(34000, bankController.getMoney(players[i]));
        }

    }

    @Test
    void withdrawMoney() {

        Player[] players = new Player[] { new Player("Flodhest", PlayerType.UFO, Color.BLACK), new Player("Hest", PlayerType.UFO, Color.BLACK), new Player("Ko", PlayerType.UFO, Color.BLACK), new Player("Giraf", PlayerType.UFO, Color.BLACK), new Player("Høne", PlayerType.UFO, Color.BLACK), new Player("Peter", PlayerType.UFO, Color.BLACK)};
        BankController bankController = new BankController(new View.ConsoleView(),players, new LanguagePack());

        for(int i = 0; i < players.length; i++) {
            bankController.withdrawMoney(players[i],15000);
            Assert.assertEquals(15000, bankController.getMoney(players[i]));

        }
    }

    @Test
    void processTransaction() {
        Player[] players = new Player[] { new Player("Flodhest", PlayerType.UFO, Color.BLACK), new Player("Hest", PlayerType.UFO, Color.BLACK), new Player("Ko", PlayerType.UFO, Color.BLACK), new Player("Giraf", PlayerType.UFO, Color.BLACK), new Player("Høne", PlayerType.UFO, Color.BLACK), new Player("Peter", PlayerType.UFO, Color.BLACK)};
        BankController bankController = new BankController(new View.ConsoleView(),players, new LanguagePack());

        GameState gameState = new GameState();
        LanguagePack languagePack = new LanguagePack();
        GameBoard gameBoard = new GameBoard(languagePack);
        gameState.setCurrentPlayer(players[0]);

        Transaction transaction = new Transaction(players[1],gameBoard.getFields()[3],gameBoard.getFields()[3].value, Transaction.TransactionType.PurchaseProperty);

        bankController.processTransaction(transaction, gameState);

        Assert.assertEquals(28800,bankController.getMoney(players[0]));



    }
}
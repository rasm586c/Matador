package Model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void setAmount() {
        Player player = new Player("hans", PlayerType.UFO, Color.BLACK);
        Account account = new Account(player, 30000);

        LanguagePack languagePack = new LanguagePack();
        GameBoard gameBoard = new GameBoard(languagePack);

        Transaction transaction = new Transaction(player, gameBoard.getFields()[3], 1200, Transaction.TransactionType.PayToBank);

        transaction.setAmount(1200);
        Assert.assertEquals(1200, transaction.getAmount());

        transaction.setTarget(player);
        Assert.assertEquals(player, transaction.getTarget());

        transaction.setApproved(true);
        Assert.assertTrue(transaction.isApproved());

        transaction.setPlayer(player);
        Assert.assertEquals(player, transaction.getPlayer());

        transaction.setField(gameBoard.getFields()[3]);
        Assert.assertEquals(gameBoard.getFields()[3], transaction.getField());

        transaction.setTransactionType(Transaction.TransactionType.PurchaseProperty);
        Assert.assertEquals(Transaction.TransactionType.PurchaseProperty, transaction.getTransactionType());

        transaction.setField(gameBoard.getFields()[3]);
    }
}
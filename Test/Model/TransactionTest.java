package Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    Player player;
    Account account;
    LanguagePack languagePack;
    GameBoard gameBoard;

    @BeforeEach
    void setup() {
        player = new Player("hans", PlayerType.UFO, Color.BLACK);
        account = new Account(player, 30000);

        languagePack = new LanguagePack();
        gameBoard = new GameBoard(languagePack);

    }

    @Test
    void testAmount() {
        Transaction transaction = new Transaction(player, gameBoard.getFields()[3], 1200, Transaction.TransactionType.PayToBank);

        transaction.setAmount(1200);
        Assert.assertEquals(1200, transaction.getAmount());
    }
    @Test
    void testTarget() {
        Transaction transaction = new Transaction(player, gameBoard.getFields()[3], 1200, Transaction.TransactionType.PayToBank);


        transaction.setTarget(player);
        Assert.assertEquals(player, transaction.getTarget());

    }
    @Test
    void testApproved(){
        Transaction transaction = new Transaction(player, gameBoard.getFields()[3], 1200, Transaction.TransactionType.PayToBank);

        transaction.setApproved(true);
        Assert.assertTrue(transaction.isApproved());

    }
    @Test
    void setplayerTest(){
        Transaction transaction = new Transaction(player, gameBoard.getFields()[3], 1200, Transaction.TransactionType.PayToBank);

        transaction.setPlayer(player);
        Assert.assertEquals(player, transaction.getPlayer());

    }

    @Test
    void testField() {
        Transaction transaction = new Transaction(player, gameBoard.getFields()[3], 1200, Transaction.TransactionType.PayToBank);

        transaction.setField(gameBoard.getFields()[3]);
        Assert.assertEquals(gameBoard.getFields()[3], transaction.getField());

    }
    @Test
    void TransactionType() {
        Transaction transaction = new Transaction(player, gameBoard.getFields()[3], 1200, Transaction.TransactionType.PayToBank);

        transaction.setTransactionType(Transaction.TransactionType.PurchaseProperty);
        Assert.assertEquals(Transaction.TransactionType.PurchaseProperty, transaction.getTransactionType());

    }

}
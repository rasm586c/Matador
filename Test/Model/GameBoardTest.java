package Model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    void getFields() {

        LanguagePack languagePack = new LanguagePack();

        GameBoard gameBoard = new GameBoard(languagePack);

        Assert.assertEquals(gameBoard.getFields().length,40);


    }
}
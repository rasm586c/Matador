package Controller;

import Model.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;

class BankControllerTest {


    @Test
    void addMoney() {
        ;

        Player[] players = new Player[5];

        Account[] account = new Account[players.length];
        BankController bankController = new BankController(null,players);

        for(int i = 0; i < players.length; i++){
            bankController.addMoney(players[i], 4000);

        }
    }

    @Test
    void withdrawMoney() {
    }

    @Test
    void getMoney() {
    }

    @Test
    void processTransaction() {
    }
}
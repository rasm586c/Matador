package Model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void getBalance() {


        // tester getBalance når man opretter en spiller og tjekker at den starter på 4000.
        Player player = new Player("hest", PlayerType.UFO);

        Account account = new Account(player, 30000;

        Assert.assertEquals(account.getBalance(), 30000);




    }

    @Test
    void setBalance() {


        // test setBalan ce, således at hvis man sætter et accounts balance til noget, bliver det også den værdi.
        Player player = new Player("hest", PlayerType.UFO);

        Account account = new Account(player, 30000);

        account.setBalance(50000);

        Assert.assertEquals(account.balance, 50000);

    }
}
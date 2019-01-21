package Model.Fields;

import Model.ChanceCard.ChanceCard;
import Model.ChanceCard.MoneyChanceCard;
import Model.ChanceCard.MoveChanceCard;
import Model.ChanceCard.MoveToBreweryChanceCard;

import java.util.Random;

public class ChanceField extends Field {
    ChanceCard[] chanceCards;

    public ChanceField(String fieldText) {
        super("?",0,0,fieldText, GUI_Type.Chance);
        chanceCards = createChanceCards();
    }

    public ChanceCard drawCard() {
        Random rnd = new Random();
        return chanceCards[rnd.nextInt(chanceCards.length)];
    }

    private ChanceCard[] createChanceCards() {
        return new ChanceCard[] { new MoveToBreweryChanceCard()};

        /*
        return new ChanceCard[] {
            new MoneyChanceCard(500),
            new MoneyChanceCard(-500),
            new MoveChanceCard(5),
            new MoveChanceCard(-2),

        };
        */
    }
}

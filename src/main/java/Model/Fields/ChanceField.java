package Model.Fields;

import Model.ChanceCard.*;
import Model.LanguagePack;

import java.util.Random;

public class ChanceField extends Field {
    ChanceCard[] chanceCards;

    public ChanceField(String fieldText, LanguagePack languagePack) {
        super("?",0,0,fieldText, GUI_Type.Chance);
        chanceCards = createChanceCards(languagePack);
    }

    public ChanceCard drawCard() {
        Random rnd = new Random();
        return chanceCards[rnd.nextInt(chanceCards.length)];
    }

    private ChanceCard[] createChanceCards(LanguagePack languagePack) {
        return new ChanceCard[] {
                new MoveChanceCard(-5,languagePack.getString("move_chance_1")),
                new MoveChanceCard(-1, languagePack.getString("move_chance_2")),
                new MoveChanceCard(10,languagePack.getString("move_chance_3")),
                new MoveChanceCard(12,languagePack.getString("move_chance_4")),
                new MoveChanceCard(3,languagePack.getString("move_chance_5")),
                new MoneyChanceCard(1000,languagePack.getString("money_chance_1")),
                new MoneyChanceCard(-1000,languagePack.getString("money_chance_2")),
                new MoneyChanceCard(-5,languagePack.getString("money_chance_3")),
                new MoneyChanceCard(45,languagePack.getString("money_chance_4")),
                new MoneyChanceCard(-350,languagePack.getString("money_chance_5")),
                new MoneyChanceCard(-500,languagePack.getString("money_chance_6")),
                new MoveToBreweryChanceCard(),
                new MoveToStartChanceCard(),
        };
    }
}

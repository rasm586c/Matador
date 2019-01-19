package Model.Fields;

import Model.ChanceCard.*;

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
        return new ChanceCard[] {
                new MoveChanceCard(-5,"Du sidder fast i studievejledning, ryk 5 felter grundet kedsomhed"),
                new MoveChanceCard(-1, "Du deltog i trappen og kastede op, tag et skridt tilbage."),
                new MoveChanceCard(20,"Du deltager i DTUs seriøse motionsløb, tag en tur halvvejs rundt om brættet"),
                new MoveChanceCard(24,"Deltag i trappen. Bevæg dig 24 felter frem."),
                new MoveChanceCard(3,"Du fik noget studiehjælp til et fag du havde svært ved, du flyver 3 felter frem."),
                new MoneyChanceCard(-1000,"Du skylder 1.000 kroner til Ian, det er tid til at process sale"),
                new MoneyChanceCard(1000,"Dit idiotprojekt blev accepteret af dtu, modtag et stipendium på 1.000 kr"),
                new MoneyChanceCard(5,"Du fandt 5 kroner på jorden, det næsten nok til kaffe! Fedt!"),
                new MoneyChanceCard(-45,"Du købte en kebabboks til 45 kr til frokost, lækkert men dyrt"),
                new MoneyChanceCard(350,"Du fik solgt nogle bøger fra beståede fag til 350 kr"),
                new MoneyChanceCard(500,"Du tager et lille SU lån. Ingen kvaler, det skal først betales om lang tid"),
                new MoveToBreweryChanceCard(),
                new MoveToStartChanceCard(),
        };

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

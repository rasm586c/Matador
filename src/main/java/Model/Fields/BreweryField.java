package Model.Fields;

import Model.GameState;
import Model.LanguagePack;
import Model.PlayerTurn;

public class BreweryField extends Field implements OwnableField {
    LanguagePack languagePack;

    public BreweryField(String name, int value, int mortgage, String fieldText) {
        super(name, value, mortgage, fieldText, GUI_Type.Brewery);
    }

    // TODO: Fix gamestring tekst: bar_sale
    public String getPurchaseText() {
        return "Bryggeri til salg! Vil du købe? Den koster " + value;
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }

    public int calculateRent(GameState state) {
        if (getOwner() != null && getOwner() != state.getCurrentPlayer()) {
            PlayerTurn turn = state.getTurn();
            int rentPrice = turn.diceRoll * 100;

            // Find ud af om renten SKAL fordobles
            // loop over alle fields
            boolean doubleRent = true;
            for (int i = 0; i < state.getBoard().getFields().length; i++) {
                // Hvis field == fieldType så ..
                if (fieldType.equals(state.getBoard().getFields()[i])) {
                    // Hvis owneren IKKE ejer dette felt og det er af samme type så er der IKKE dobbelt leje!
                    if (!getOwner().equals(state.getBoard().getFields()[i].getOwner())) {
                        doubleRent = false;
                        break;
                    }
                }
            }

            // Double rent ?
            if (doubleRent) {
                rentPrice *= 2;
            }

            return rentPrice;
        }

        return value;
    }

    private int[] rentPrices;
    public void setRentPrices(int[] rentPrices) { this.rentPrices = rentPrices; }
    public int[] getRentPrices() { return rentPrices; }
}

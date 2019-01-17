package Model.Fields;

import Model.GameState;

public class ShippingField extends Field implements OwnableField {

    public ShippingField (String name, int value, int rent, int mortgage, String fieldText) {
        super(name, value, rent, mortgage, fieldText, GUI_Type.Shipping);
    }

    // TODO: Fix gamestring tekst
    public String getPurchaseText() {
        return "Rederi til salg! Vil du købe? Den koster " + value;
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }

    public int calculateRent(GameState state) {
        if (getOwner() != null && getOwner() != state.getCurrentPlayer()) {
            int antal = 1;
            // loop over alle fields
            for (int i = 0; i < state.getBoard().getFields().length; i++) {
                // Hvis field == fieldType så ..
                if (fieldType.equals(state.getBoard().getFields()[i])) {
                    // Hvis owneren ejer dette felt og det er af samme type så bliver antallet 1 højere!
                    if (getOwner().equals(state.getBoard().getFields()[i].getOwner())) {
                        antal++;
                    }
                }
            }
            //returnere rent værdien efter hvor mange owneren ejer!
            if(antal == 1)
            {
                return value;
            }
            else if(antal == 2)
            {
                return value * 2;
            }
            else if (antal == 3)
            {
                return value * 4;
            }
            else
            {
                return value * 8;
            }
        }

        return value;
    }

    private int[] rentPrices;
    public void setRentPrices(int[] rentPrices) { this.rentPrices = rentPrices; }
    public int[] getRentPrices() { return rentPrices; }
}

package Model.Fields;

import Model.GameState;

public class PropertyField extends Field implements OwnableField {
    public PropertyField(String name, int value, int mortgage, String fieldText, GUI_Type type) {
        super(name, value, mortgage, fieldText, type);
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }

    // TODO: Gamestring skal fikses
    public String getPurchaseText() {
        return "Bolig til salg! Vil du købe? Den koster " + value;
    }

    public int calculateRent(GameState state) {
        if (getOwner() != null && getOwner() != state.getCurrentPlayer()) {
            // Find ud af om renten SKAL fordobles
            // loop over alle fields
            boolean doubleRent = true;
            for (int i = 0; i < state.getBoard().getFields().length; i++) {
                // Hvis field == fieldType så ..
                if (fieldType.equals(state.getBoard().getFields()[i].fieldType)) {
                    // Hvis owneren IKKE ejer dette felt og det er af samme type så er der IKKE dobbelt leje!
                    if (!getOwner().equals(state.getBoard().getFields()[i].getOwner())) {
                        doubleRent = false;
                        break;
                    }
                }
            }

            // Find ud af hus prisen
            int rentPrice = getRentPrices()[getHouseCounter()];

            // Double rent ?
            if (doubleRent) {
                rentPrice *= 2;
            }

            return rentPrice;
        }

        return value;
    }

    private int[] rentPrices;
    public int[] getRentPrices() {
        return rentPrices;
    }

    public void setRentPrices(int[] rentPrices) {
        this.rentPrices = rentPrices;
    }
}

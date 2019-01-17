package Model.Fields;

import Model.GameState;

public class PropertyField extends Field implements OwnableField {
    public PropertyField(String name, int value, int rent, String fieldText, GUI_Type type) {
        super(name, value,rent, fieldText, type);
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

            if (doubleRent) {
                return value * 2;
            }
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

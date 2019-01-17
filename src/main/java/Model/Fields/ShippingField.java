package Model.Fields;

import Model.GameState;

public class ShippingField extends Field implements OwnableField {

    public ShippingField (String name, int value, int rent, String fieldText) {
        super(name, value, rent, fieldText, GUI_Type.Shipping);
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
            int amount = 0;

            for (int i = 0; i < state.getBoard().getFields().length; i++) {
                if (state.getBoard().getFields()[i].fieldType.equals( fieldType )) {
                    if (state.getBoard().getFields()[i].getOwner() != null && state.getBoard().getFields()[i].getOwner().equals(getOwner())) {
                        amount++;
                    }
                }
            }

            return getRentPrices()[amount-1];
        }

        return value;
    }

    private int[] rentPrices;
    public void setRentPrices(int[] rentPrices) { this.rentPrices = rentPrices; }
    public int[] getRentPrices() { return rentPrices; }
}

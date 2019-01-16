package Model.Fields;

import Model.GameState;

public class ShippingField extends Field implements OwnableField {

    public ShippingField (String name, int value, int rent, String fieldText) {
        super(name, value, rent, fieldText, GUI_Type.Shipping);
    }

    // TODO: Mangler i string container!
    public String getPurchaseText() { return "Shipping ting til salg! Vil du købe? Den koster " + value; }

    public int calculateRent(GameState state) {
        return 0;
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }
}

package Model.Fields;

import Model.GameState;

public class ShippingField extends Field {

    public ShippingField (String name, int value, String fieldText) {
        super(name, value, fieldText, GUI_Type.Shipping);
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }
}

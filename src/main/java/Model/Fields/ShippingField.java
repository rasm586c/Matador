package Model.Fields;

import Model.GameState;

public class ShippingField extends Field {

    public ShippingField (String name, int value, int rent, int mortgage, String fieldText) {
        super(name, value, rent, mortgage, fieldText, GUI_Type.Shipping);
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }
}
